package com.epam.taxi.entity;

import com.epam.taxi.exception.CallInactiveThreadException;

import java.util.concurrent.atomic.AtomicReference;

public class Person implements Runnable {
    private String name;
    private DistrictEnum location;
    private DistrictEnum pointDestination;

    public Person(String name, DistrictEnum location, DistrictEnum pointDestination) {
        this.name = name;
        this.location = location;
        this.pointDestination = pointDestination;
    }

    public void setLocation(DistrictEnum location) {
        this.location = location;
    }

    public DistrictEnum getPointDestination() {
        return pointDestination;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(name + " call taxi. I'm in "+ location.name());
        AtomicReference<Uber> uber = Uber.getInstance();
        Car taxi = uber.get().getTaxi();
        try {
            taxi.transportPassenger(this);
            System.out.println(name + " I'm in "+pointDestination+ " Thanks!");
        } catch (InterruptedException e) {
           throw  new CallInactiveThreadException("Thread operation error, cause ",e);
        } finally {
            uber.get().returnTaxi(taxi);
        }
    }
}
