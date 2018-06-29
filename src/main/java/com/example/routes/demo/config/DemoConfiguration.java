package com.example.routes.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
public class DemoConfiguration {

    @Value("${application.static.fileName}")
    private Resource file;


    @Bean(name = "routes")
    public Map<String, String> getRoutes() throws IOException {

        Map<String, String> routes = new HashMap<>();
        Scanner scan = null;
        try{
            scan = new Scanner(file.getInputStream());
            while(scan.hasNextLine()){
                parseLine(scan.nextLine(), routes);
            }
        } catch (IOException e) {
            throw e;
        }finally {
            scan.close();
        }

        return routes;
    }

    private void parseLine(String token, Map<String, String> routes){
       Scanner sc = new Scanner(token);
       sc.useDelimiter(", ");

       while(sc.hasNext()){
           routes.put(sc.next(), sc.next());
       }
        sc.close();
    }
}
