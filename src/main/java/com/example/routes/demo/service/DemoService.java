package com.example.routes.demo.service;

import com.example.routes.demo.model.Routes;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DemoService{


    @Autowired
    @Qualifier("routes")
    private Routes routes;


    public Boolean processConnectedRoute(String origin, String destination){

        LinkedList<String> visited = new LinkedList<>();
        visited.add(origin);
        ArrayList<String> path = new ArrayList<>();
        depthFirstSearch(destination, routes, visited, path);

        if (path.isEmpty())
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    private void depthFirstSearch(String destination, Routes routes, LinkedList<String> visited, ArrayList<String> path){
        ArrayList<String> nodes = routes.adjacentNodes(visited.getLast());

        for (String node : nodes){
            if (visited.contains(node))
                continue;

            if (node.equalsIgnoreCase(destination)){
                visited.add(node);
                storePsth(visited, path);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes){
            if (visited.contains(node) || node.equalsIgnoreCase(destination))
                continue;

            visited.addLast(node);
            depthFirstSearch(destination, routes, visited, path);
            visited.removeLast();
        }
    }

    private void storePsth(LinkedList<String> visited, ArrayList<String> path){
        String pathVal = "";
        for (String node : visited){
            pathVal = pathVal + node + " ";
        }
        path.add(pathVal);
    }
}
