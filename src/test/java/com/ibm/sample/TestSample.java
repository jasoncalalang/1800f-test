package com.ibm.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sample.model.User;
import com.ibm.sample.util.SampleUtil;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TestSample {

    @Test
    public void canaryTest() throws Exception {
        Assert.assertEquals(true, true);
    }

    @Test
    public void testParseAndFilterDistinct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        URL url = new URL("http://jsonplaceholder.typicode.com/posts");

        List<User> users = mapper.readValue(url, new TypeReference<List<User>>(){});

        int count = 0;
        List<User> collect = users.stream()
                .filter(SampleUtil.distinctByKey(user->user.getUserId()))
                .collect(Collectors.toList());

        Assert.assertEquals(10, collect.size());
    }
}
