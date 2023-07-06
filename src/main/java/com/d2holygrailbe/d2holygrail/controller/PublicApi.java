package com.d2holygrailbe.d2holygrail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:4200")
public interface PublicApi {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/public/api/getItemData",
            produces = {"application/json"}
    )
    ResponseEntity<String> publicApiGetItemData();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/public/api/resetItemData"
    )
    ResponseEntity<String> publicApiResetItemData();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/public/api/exportItemData"
    )
    ResponseEntity<String> publicApiexportItemData();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/public/api/importItemData"
    )
    ResponseEntity<String> publicApiimportItemData();

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/public/api/saveItemData"
    )
    ResponseEntity<String> publicApiSaveItemData(@RequestBody String jsonData);
}
