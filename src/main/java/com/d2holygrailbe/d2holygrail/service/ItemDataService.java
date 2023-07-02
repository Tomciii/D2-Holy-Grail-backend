package com.d2holygrailbe.d2holygrail.service;

import jdk.jshell.spi.ExecutionControl;
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

    private final String fileName = "static/item-data.json";
    private final String fullPath;
    private final String exportPath;

    private String currentJson;

    public ItemDataService(@Value("${my.filepath}") String filePath, @Value("${my.exportpath}") String exportPath){
        this.fullPath = filePath + fileName;
        this.exportPath = exportPath;
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

    public void resetData(){
        if (this.currentJson != null){
            this.currentJson = this.currentJson.replace("true", "false");
        }
    }

    public void exportFile(){
        if (this.currentJson.isBlank() || this.currentJson.isEmpty() || this.currentJson.equals("[]")){
            return;
        }

        try (FileWriter fileWriter = new FileWriter(this.exportPath)) {
            fileWriter.write(this.currentJson);
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

    public void importFile() {

    }
}
