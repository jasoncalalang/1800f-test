package com.ibm.sample.controller;

import com.ibm.sample.model.ResponseCount;
import com.ibm.sample.model.User;
import com.ibm.sample.util.SampleUtil;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "sample")
public class SampleController {

    @RequestMapping(value = "/count", method = RequestMethod.GET, consumes = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseCount count() throws Exception{

        List<User> users = SampleUtil.fetch();
        List<User> distinctUsers = users.stream()
                .filter(SampleUtil.distinctByKey(user -> user.getUserId()))
                .collect(Collectors.toList());

        return new ResponseCount(distinctUsers.size());
    }

    @RequestMapping(value = "/update/{index}", method = RequestMethod.PUT, consumes = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User[] update(@PathVariable("index") int index, @RequestBody final User inputUser) throws Exception{

        List<User> userList = SampleUtil.fetch();

        User user = userList.get(index);
        user.setBody(inputUser.getBody());
        user.setTitle(inputUser.getTitle());
        userList.set(index, user);

        return userList.toArray(new User[userList.size()]);

    }
}
