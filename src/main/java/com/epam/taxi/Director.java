package com.epam.taxi;

import com.epam.taxi.creator.PassengersCreator;
import com.epam.taxi.creator.TaxiCreator;
import com.epam.taxi.entity.Car;
import com.epam.taxi.entity.Person;
import com.epam.taxi.entity.Uber;
import com.epam.taxi.reader.DataReader;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Director {
    private static final String PATH_TO_TXT_PEOPLE = "src/main/resources/people.txt";
    private static final String PATH_TO_TXT_TAXIS = "src/main/resources/taxi.txt";

    public static void main(String[] args) throws InterruptedException {
        DataReader reader = new DataReader();
        PassengersCreator creatorPassengers = new PassengersCreator(reader);
        List<Person> passengers = creatorPassengers.create(PATH_TO_TXT_PEOPLE);
        TaxiCreator creatorTaxi = new TaxiCreator(reader);
        List<Car> taxi = creatorTaxi.create(PATH_TO_TXT_TAXIS);
        AtomicReference<Uber> uber = Uber.getInstance();
        uber.get().fillQueueTaxi(taxi);

        int numberPassengers = passengers.size();
        ExecutorService executorService = Executors.newFixedThreadPool(numberPassengers);
        for (int i = 0; i < numberPassengers; i++) {
            executorService.submit(passengers.get(i));
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("All brought!");
    }
}
