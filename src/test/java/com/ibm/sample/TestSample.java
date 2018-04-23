package com.ibm.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sample.model.User;
import com.ibm.sample.util.SampleUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSample {

    @Test
    public void contextLoads(){
        SampleApplication.main(new String[] {});

    }

    @Test
    public void canaryTest() throws Exception {
        Assert.assertEquals(true, true);
    }

    @Test
    public void testFetchShouldReturnListOfUsers() throws Exception {
        Assert.assertNotNull(SampleUtil.fetch());
        Assert.assertEquals(SampleUtil.fetch().size(), 100);
    }

    @Test
    public void testParseAndFilterDistinct() throws Exception {
        List<User> collect = SampleUtil.fetch().stream()
                .filter(SampleUtil.distinctByKey(user->user.getUserId()))
                .collect(Collectors.toList());

        Assert.assertEquals(10, collect.size());
    }
}
