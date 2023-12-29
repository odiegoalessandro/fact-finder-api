package com.fact.finder.controller;

import com.fact.finder.repository.FunFactRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunFactController {
    @Autowired
    FunFactRespository factRespository;


}
