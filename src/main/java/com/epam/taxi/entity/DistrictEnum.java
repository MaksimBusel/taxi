package com.epam.taxi.entity;

public enum DistrictEnum {
    CENTRALNY("centralny"),
    SOVIETSKY("sovietsky"),
    MOSCOWSKY("moscowsky"),
    FRUNZENSKY("frunzensky"),
    OKTYABRSKY("oktyabrsky"),
    LENINSKY("leninsky"),
    PERVOMAISKY("pervomaisky"),
    PARTISANSKY("partisansky"),
    ZAVODSKOY("zavodskoy");

    private String name;

    DistrictEnum(String name) {
        this.name = name;
    }
}
