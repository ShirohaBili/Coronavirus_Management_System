package com.Shiroha.coronavirus.controller;

import com.Shiroha.coronavirus.entity.Inspect;
import com.Shiroha.coronavirus.service.InspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class InspectController {
    @Autowired
    InspectService service;

    @RequestMapping(value = "/inspect/get/{id}",method = RequestMethod.GET)
    public List<Inspect> get(@PathVariable("id")int id){

        return service.find(id);
    }

    @RequestMapping(value="/inspect/add",method=RequestMethod.POST)
    public void add(@RequestBody Inspect Inspect)
    {
         service.add(Inspect);
    }

}
