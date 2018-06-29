package com.example.routes.demo.controller;


import com.example.routes.demo.exception.EmptyReqParameterException;
import com.example.routes.demo.exception.ProcessingException;
import com.example.routes.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes")
public class DemoController {

    @Autowired
    private DemoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> processRoute(@RequestParam("city1") String origin, @RequestParam("city2") String destination){

        if(StringUtils.isEmpty(origin)|| StringUtils.isEmpty(destination))
            throw new EmptyReqParameterException("Request Parameters are Empty");

        Boolean result;
        try {
            result = service.processConnectedRoute(origin, destination);
        }catch (ProcessingException ex){
            throw new ProcessingException("Something Went Wring !!!!");
        }
        if (result)
            return ResponseEntity.ok("YES");
        else
            return ResponseEntity.status(HttpStatus.OK).body("NO");

    }
}
