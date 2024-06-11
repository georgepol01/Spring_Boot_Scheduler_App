package com.example.api_project.service;

import com.example.api_project.dto.CityDTO;
import com.example.api_project.entity.City;
import com.example.api_project.mapper.CityMapper;
import com.example.api_project.repository.CityRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::cityToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CityDTO> getCityById(Long id) {
        return cityRepository.findById(id).map(cityMapper::cityToDTO);
    }

    public List<CityDTO> parseCSV(InputStream inputStream) {
        CsvParserSettings setting = new CsvParserSettings();
        setting.getFormat().setDelimiter(';');
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);

        List<String[]> records = parser.parseAll(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));

        return records.stream()
                .map(cityMapper::csvRecordToDTO)
                .collect(Collectors.toList());
    }

    public void updateCityEntityFields(City existingCityEntity, CityDTO cityDTO) {
        cityMapper.updateCityEntityFields(existingCityEntity, cityDTO);
    }

}