package com.epam.taxi.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public List<String> readLine(String path){
        List<String> data= new ArrayList<>();
        FileReader fileReader;
        BufferedReader reader=null;
        try{
            fileReader = new FileReader(path);
            reader = new BufferedReader(fileReader);
            String time;
            while ((time=reader.readLine())!=null){
                data.add(time);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
           // e.printStackTrace();
        } finally {
            try {
                if(reader!=null){
                reader.close();}
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return data;
    }
}
