package com.example.routes.demo.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routes {

    private Map<String, List<String>> routes = new HashMap<>();


    public Map<String, List<String>> getRoutes() {
        return routes;
    }
    public void setRoutes(Map<String, List<String>> routes) {
        this.routes = routes;
    }

    public boolean isConnected(String origin, String destination){
        List<String> adjacent = routes.get(origin);
        if (adjacent == null)
            return false;

        return adjacent.contains(destination);
    }

    public ArrayList<String> adjacentNodes(String str){
        List<String> adjacent = routes.get(str);
        if (adjacent == null)
            return new ArrayList<>();

        return new ArrayList<String>(adjacent);
    }
}
