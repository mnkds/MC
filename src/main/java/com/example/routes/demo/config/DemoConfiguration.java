package com.example.routes.demo.config;

import com.example.routes.demo.model.Route;
import com.example.routes.demo.model.Routes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class DemoConfiguration {

    @Value("${application.static.fileName}")
    private Resource file;


    @Bean(name = "routes")
    public Routes getRoutes() throws IOException {
        List<Route> routeList = new ArrayList<>();
        Routes routes = new Routes();

        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream()));

            Stream<String> stream = reader.lines();

            stream.map(lines ->{
                String [] str = lines.split(", ");
                Route routeFwd = new Route(str[0], str[1]);
                Route routeBwd = new Route(str[1], str[0]);

                routeList.add(routeFwd);
                routeList.add(routeBwd);
                return routeFwd;
            }).collect(Collectors.toList());
        }catch(FileNotFoundException e){
            throw e;
        }catch (IOException ex){
            throw ex;
        }

        Map<String, List<String>> routesMap = routeList.stream()
                                                        .collect(Collectors.groupingBy(
                                                                Route::getOrigin,
                                                                Collectors.mapping(Route::getDestination, Collectors.toList()))
                                                        );
        routes.setRoutes(routesMap);
        return routes;
    }
}
