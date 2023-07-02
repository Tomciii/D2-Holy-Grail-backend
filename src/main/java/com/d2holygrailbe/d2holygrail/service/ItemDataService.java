package com.d2holygrailbe.d2holygrail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Service
@Slf4j
public class ItemDataService {

    final private String fileName = "static/item-data.json";

    private final String fullPath;

    private String currentJson;

    public ItemDataService(@Value("${my.filepath}") String filePath){
        this.fullPath = filePath + fileName;
    }

    public void saveData(String jsonData) {
        if (jsonData.isBlank() || jsonData.isBlank() || jsonData.equals("[]")){
            return;
        }

        try (FileWriter fileWriter = new FileWriter(this.fullPath)) {
            fileWriter.write(jsonData);
            this.currentJson = jsonData;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() throws IOException {

        if (currentJson == null){
            ClassPathResource resource = new ClassPathResource(this.fileName);
            InputStream inputStream = resource.getInputStream();
            byte[] jsonData = FileCopyUtils.copyToByteArray(inputStream);
            inputStream.close();
            this.currentJson = new String(jsonData, StandardCharsets.UTF_8);
            return this.currentJson;
        }

        return currentJson;
    }
}
