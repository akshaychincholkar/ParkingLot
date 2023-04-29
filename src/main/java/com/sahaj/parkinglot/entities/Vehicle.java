package com.sahaj.parkinglot.entities;

import com.sahaj.parkinglot.models.FeeModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface Vehicle {
    int[] getFees(String feeModel);
    int[] getTimeFrames(String feeModel);
    String getType();
    String getModel();
    void setModel(String model);

    default Properties setConfig(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/ParkingFeeModelConfig.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
}
