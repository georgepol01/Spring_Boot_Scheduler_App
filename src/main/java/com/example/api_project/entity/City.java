package com.example.api_project.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCode;
    private Long territorialCode;
    private Long provinceCode;
    private Long municipalityNum;

    @Column(unique = true)
    private Long commonCode;

    private String italianForeignName;
    private String italianName;
    private String foreignName;
    private Long geoDistCode;
    private String geoDist;
    private String regionName;
    private String territorialName;
    private Long territorialType;
    private Long flagMunicipality;
    private String automotiveAcronym;
    private Long commonCodeNumeric;
    private Long municipalityCodeNumerical110;
    private Long municipalityCodeNumerical107;
    private Long municipalityCodeNumerical103;
    private String cadastralCode;
    private String nuts1Code2021;
    private String nuts2Code2021;
    private String nuts3Code2021;
    private String nuts1Code2024;
    private String nuts2Code2024;
    private String nuts3Code2024;

}