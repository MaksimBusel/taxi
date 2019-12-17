package com.epam.taxi.creator;

import com.epam.taxi.entity.Car;
import com.epam.taxi.entity.DistrictEnum;
import com.epam.taxi.reader.DataReader;

import java.util.ArrayList;
import java.util.List;

public class TaxiCreator {
    private DataReader reader;

    public TaxiCreator(DataReader reader) {
        this.reader = reader;
    }

    public List<Car> create(String path){
        List<String> dataCar = reader.readLine(path);
        List<Car> cars = new ArrayList<>();
        int size = dataCar.size();
        for (int i = 0; i <size ; i++) {
            String car = dataCar.get(i);
            String[] parametersCar = car.trim().split(" ");
            String color = parametersCar[0];
            String brand = parametersCar[1];
            String areaLocation = parametersCar[2];
            DistrictEnum locationCar = DistrictEnum.valueOf(areaLocation.trim().toUpperCase());
            Car taxi = new Car(color, brand, locationCar);
            cars.add(taxi);
        }
        return cars;
    }
}
