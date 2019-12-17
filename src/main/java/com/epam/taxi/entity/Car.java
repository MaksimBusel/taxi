package com.epam.taxi.entity;

import java.util.concurrent.TimeUnit;

public class Car {
    private  String color;
    private String brand;
    private DistrictEnum areaLocation;

    public Car(String color, String brand, DistrictEnum areaLocation) {
        this.color = color;
        this.brand = brand;
        this.areaLocation = areaLocation;
    }

    public void transportPassenger(Person person) throws InterruptedException {
        System.out.println(color+" "+brand+" got "+person.getName());
        DistrictEnum pointDestination = person.getPointDestination();
        TimeUnit.SECONDS.sleep(1);
        person.setLocation(pointDestination);
    }
}
