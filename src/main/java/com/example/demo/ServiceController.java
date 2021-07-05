package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceController {

    @RequestMapping(value = "/countries")
    public ResponseEntity<Object> getAllCountries() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.eu/rest/v2/all";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "/countries/{name}")
    public ResponseEntity<Object> getCountries(@PathVariable("name") String name) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.eu/rest/v2/name/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + name, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}