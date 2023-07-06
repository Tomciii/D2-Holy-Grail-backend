package com.d2holygrailbe.d2holygrail.controller;

import com.d2holygrailbe.d2holygrail.service.ItemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class ItemDataController implements PublicApi {

    private ItemDataService itemDataService;

    @Autowired
    public ItemDataController(ItemDataService itemDataService) {
        this.itemDataService = itemDataService;
    }

    public ResponseEntity<String> publicApiGetItemData() {
        try {
            String itemData = this.itemDataService.getData();
            return ResponseEntity.ok(itemData);
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading item data.");

        }
    }

    public ResponseEntity<String> publicApiResetItemData() {
        try {
            this.itemDataService.resetData();
            return ResponseEntity.ok("Data reset successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading item data.");

        }
    }

    public ResponseEntity<String> publicApiexportItemData() {
        try {
            this.itemDataService.exportFile();
            return ResponseEntity.ok("Data exported successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading item data.");
        }
    }

    public ResponseEntity<String> publicApiimportItemData() {
        try {
            this.itemDataService.importFile();
            return ResponseEntity.ok("Data exported successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading item data.");
        }
    }

    public ResponseEntity<String> publicApiSaveItemData(@RequestBody String jsonData) {
        try {
            this.itemDataService.saveData(jsonData);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }
}
