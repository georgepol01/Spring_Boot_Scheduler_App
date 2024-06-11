package com.example.api_project.batch;

import com.example.api_project.dto.CityDTO;
import com.example.api_project.entity.City;
import com.example.api_project.mapper.CityMapper;
import com.example.api_project.repository.CityRepository;
import com.example.api_project.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class CityDataBatchJob {

    private static final Logger logger = LoggerFactory.getLogger(CityDataBatchJob.class);
    private final CityService cityService;
    private final CityRepository cityRepository;

    public CityDataBatchJob(CityService cityService, CityRepository cityRepository) {
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void updateDatabaseFromCsv() {
        logger.info("Scheduled job 'updateDatabaseFromCsv' triggered.");
        try {
            RestTemplate restTemplate = new RestTemplate();
            String ZIP_FILE_URL = "https://www.istat.it/storage/codici-unita-amministrative/Elenco-codici-statistici-e-denominazioni-delle-unita-territoriali.zip";
            byte[] zipFileBytes = restTemplate.getForObject(ZIP_FILE_URL, byte[].class);
            if (zipFileBytes != null) {
                processZipFile(new ByteArrayInputStream(zipFileBytes));
            }
        } catch (Exception e) {
            logger.error("Error occurred during 'updateDatabaseFromCsv' job:", e);
        }
    }

    public void processZipFile(InputStream zipFileStream) {
        logger.info("Processing zip file...");
        try (ZipInputStream zipInputStream = new ZipInputStream(zipFileStream, StandardCharsets.ISO_8859_1)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".csv")) {
                    processCsvData(zipInputStream);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Error occurred during zip file processing:", e);
        }
    }

    private void processCsvData(InputStream csvFileStream) throws IOException {
        List<CityDTO> cityDTOs = cityService.parseCSV(csvFileStream);
        for (CityDTO cityDTO : cityDTOs) {
            Long commonCode = cityDTO.getCommonCode();
            Optional<City> existingCity = cityRepository.findByCommonCode(commonCode);
            City cityEntity = existingCity.orElseGet(() -> CityMapper.INSTANCE.dtoToCity(cityDTO));
            cityService.updateCityEntityFields(cityEntity, cityDTO);
            cityRepository.save(cityEntity);
            logger.info("{} record with commonCode={} {}.", existingCity.isPresent() ? "Existing" : "New", commonCode, existingCity.isPresent() ? "updated" : "inserted");
        }
        logger.info("Data from CSV file processed.");
    }
}