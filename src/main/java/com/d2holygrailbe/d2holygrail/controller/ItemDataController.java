package com.d2holygrailbe.d2holygrail.controller;

import com.d2holygrailbe.d2holygrail.service.ItemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ItemDataController {

    private ItemDataService itemDataService;

    @Autowired
    public ItemDataController(ItemDataService itemDataService) {
        this.itemDataService = itemDataService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/public/api/getItemData",
            produces = {"application/json"}
    )
    public ResponseEntity<String> publicApiGetItemData() {
        try {
            String itemData = this.itemDataService.getData();
            return ResponseEntity.ok(itemData);
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading item data.");

        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/public/api/saveItemData"
    )
    public ResponseEntity<String> publicApiSaveItemData(@RequestBody String jsonData) {
        try {
            this.itemDataService.saveData(jsonData);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }
}
