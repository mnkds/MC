package com.example.routes.demo;


import com.example.routes.demo.model.Routes;
import com.example.routes.demo.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoServiceTest {

    @InjectMocks
    private Routes routes;

    @Autowired
    private DemoService service;

    @Before
    public void setup(){

        Map<String, List<String>> routeList = new HashMap<>();
        List<String> citiesNY = Arrays.asList(new String[]{"Boston"});
        List<String> citiesMD = Arrays.asList(new String[]{"Boston", "Philadelphia"});
        List<String> citiesPA = Arrays.asList(new String[]{"Maryland", "Newark"});
        List<String> citiesBS = Arrays.asList(new String[]{"New York", "Newark", "Maryland"});
        routeList.put("New York", citiesNY );
        routeList.put("Maryland", citiesMD );
        routeList.put("Philadelphia", citiesPA );
        routeList.put("Boston", citiesBS );
        routes.setRoutes(routeList);

    }


    @Test
    public void processConnectedRouteTest(){
        assertTrue(service.processConnectedRoute("New York", "Maryland"));
        assertFalse(service.processConnectedRoute("New York", "Albany"));
    }
}
