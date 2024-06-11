package com.example.api_project.mapper;

import com.example.api_project.dto.CityDTO;
import com.example.api_project.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    default CityDTO csvRecordToDTO(String[] record) {
        if (record == null || record.length < 26) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();

        cityDTO.setRegionCode(Long.parseLong(record[0]));
        cityDTO.setTerritorialCode(Long.parseLong(record[1]));
        cityDTO.setProvinceCode(Long.parseLong(record[2]));
        cityDTO.setMunicipalityNum(Long.parseLong(record[3]));
        cityDTO.setCommonCode(Long.parseLong(record[4]));
        cityDTO.setItalianForeignName(record[5]);
        cityDTO.setItalianName(record[6]);
        cityDTO.setForeignName(record[7]);
        cityDTO.setGeoDistCode(Long.parseLong(record[8]));
        cityDTO.setGeoDist(record[9]);
        cityDTO.setRegionName(record[10]);
        cityDTO.setTerritorialName(record[11]);
        cityDTO.setTerritorialType(Long.parseLong(record[12]));
        cityDTO.setFlagMunicipality(Long.parseLong(record[13]));
        cityDTO.setAutomotiveAcronym(record[14]);
        cityDTO.setCommonCodeNumeric(Long.parseLong(record[15]));
        cityDTO.setMunicipalityCodeNumerical110(Long.parseLong(record[16]));
        cityDTO.setMunicipalityCodeNumerical107(Long.parseLong(record[17]));
        cityDTO.setMunicipalityCodeNumerical103(Long.parseLong(record[18]));
        cityDTO.setCadastralCode(record[19]);
        cityDTO.setNuts1Code2021(record[20]);
        cityDTO.setNuts2Code2021(record[21]);
        cityDTO.setNuts3Code2021(record[22]);
        cityDTO.setNuts1Code2024(record[23]);
        cityDTO.setNuts2Code2024(record[24]);
        cityDTO.setNuts3Code2024(record[25]);

        return cityDTO;
    }

    default void updateCityEntityFields(City existingCityEntity, CityDTO cityDTO) {
        existingCityEntity.setRegionCode(cityDTO.getRegionCode());
        existingCityEntity.setTerritorialCode(cityDTO.getTerritorialCode());
        existingCityEntity.setProvinceCode(cityDTO.getProvinceCode());
        existingCityEntity.setMunicipalityNum(cityDTO.getMunicipalityNum());
        existingCityEntity.setCommonCode(cityDTO.getCommonCode());
        existingCityEntity.setItalianForeignName(cityDTO.getItalianForeignName());
        existingCityEntity.setItalianName(cityDTO.getItalianName());
        existingCityEntity.setForeignName(cityDTO.getForeignName());
        existingCityEntity.setGeoDistCode(cityDTO.getGeoDistCode());
        existingCityEntity.setGeoDist(cityDTO.getGeoDist());
        existingCityEntity.setRegionName(cityDTO.getRegionName());
        existingCityEntity.setTerritorialName(cityDTO.getTerritorialName());
        existingCityEntity.setTerritorialType(cityDTO.getTerritorialType());
        existingCityEntity.setFlagMunicipality(cityDTO.getFlagMunicipality());
        existingCityEntity.setAutomotiveAcronym(cityDTO.getAutomotiveAcronym());
        existingCityEntity.setCommonCodeNumeric(cityDTO.getCommonCodeNumeric());
        existingCityEntity.setMunicipalityCodeNumerical110(cityDTO.getMunicipalityCodeNumerical110());
        existingCityEntity.setMunicipalityCodeNumerical107(cityDTO.getMunicipalityCodeNumerical107());
        existingCityEntity.setMunicipalityCodeNumerical103(cityDTO.getMunicipalityCodeNumerical103());
        existingCityEntity.setCadastralCode(cityDTO.getCadastralCode());
        existingCityEntity.setNuts1Code2021(cityDTO.getNuts1Code2021());
        existingCityEntity.setNuts2Code2021(cityDTO.getNuts2Code2021());
        existingCityEntity.setNuts3Code2021(cityDTO.getNuts3Code2021());
        existingCityEntity.setNuts1Code2024(cityDTO.getNuts1Code2024());
        existingCityEntity.setNuts2Code2024(cityDTO.getNuts2Code2024());
        existingCityEntity.setNuts3Code2024(cityDTO.getNuts3Code2024());
    }

    CityDTO cityToDTO(City city);

    City dtoToCity(CityDTO cityDTO);
}
