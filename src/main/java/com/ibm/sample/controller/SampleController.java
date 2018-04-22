package com.ibm.sample.controller;

import com.ibm.sample.model.User;
import com.ibm.sample.util.SampleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
@SpringBootApplication
public class SampleController {

    @RequestMapping(value = "/count/", method = RequestMethod.GET, consumes = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User[] count(@RequestBody final User[] userArray) {

        try {
            User[] response = Arrays.stream(userArray)
                    .filter(SampleUtil.distinctByKey(user -> user.getUserId()))
                    .toArray(size -> new User[size]);

            return response;


        } catch (Exception e) {

        }


        return null;
    }


    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

}
