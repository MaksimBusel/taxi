package com.epam.taxi.creator;

import com.epam.taxi.entity.DistrictEnum;
import com.epam.taxi.entity.Person;
import com.epam.taxi.reader.DataReader;

import java.util.ArrayList;
import java.util.List;

public class PassengersCreator {
    private DataReader reader;

    public PassengersCreator(DataReader reader) {
        this.reader = reader;
    }

    public List<Person> create(String path){
        List<String> dataPerson = reader.readLine(path);
        List<Person> persons = new ArrayList<>();
        int size = dataPerson.size();
        for (int i = 0; i <size ; i++) {
            String person = dataPerson.get(i);
            String[] parametersPerson = person.trim().split(" ");
            String name = parametersPerson[0];
            DistrictEnum location = DistrictEnum.valueOf(parametersPerson[1].toUpperCase());
            DistrictEnum pointDestination = DistrictEnum.valueOf(parametersPerson[2].toUpperCase());
            Person passenger = new Person(name, location, pointDestination);
            persons.add(passenger);
        }
        return persons;
    }
}
