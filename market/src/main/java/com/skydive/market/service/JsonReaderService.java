package com.skydive.market.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skydive.market.model.Registration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReaderService {
    public List<Registration> getTestData(String filePath){
        Gson gson = new Gson();
        Type personListType = new TypeToken<List<Registration>>(){}.getType();
        List<Registration> departments;
        try {
            Reader reader = new FileReader(filePath);
            departments = gson.fromJson(reader, personListType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }


}
