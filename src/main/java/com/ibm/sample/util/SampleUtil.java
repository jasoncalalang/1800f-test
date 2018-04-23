package com.ibm.sample.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sample.model.User;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class SampleUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static List<User> fetch() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        URL url = new URL("http://jsonplaceholder.typicode.com/posts");

        List<User> users = mapper.readValue(url, new TypeReference<List<User>>(){});

        return users;
    }

}
