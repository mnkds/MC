package com.example.routes.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class DemoService{


    @Autowired
    @Qualifier("routes")
    private Map<String, String> routes;



    public Boolean processConnectedRoute(String origin, String destination){

        List<String> keyList = routes.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue().equalsIgnoreCase(origin))
                                    .map(Map.Entry::getKey)
                                    .collect(Collectors.toList());

        List<String> valueList = routes.entrySet()
                                        .stream()
                                        .filter(entry -> entry.getKey().equalsIgnoreCase(origin))
                                        .map(Map.Entry::getValue)
                                        .collect(Collectors.toList());

        if (keyList.contains(destination) || valueList.contains(destination))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }
}
