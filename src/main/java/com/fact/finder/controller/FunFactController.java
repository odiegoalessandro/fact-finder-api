package com.fact.finder.controller;

import com.fact.finder.model.FunFact;
import com.fact.finder.repository.FunFactRespository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/facts")
public class FunFactController {
    @Autowired
    FunFactRespository factRespository;

    @GetMapping("/all")
    public ResponseEntity<List<FunFact>> getRandomFacts(){
        var facts = factRespository.findAll();

        return ResponseEntity.status(200).body(facts);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createFact(@RequestBody FunFact funFact){
        FunFact fact = new FunFact();
        fact.setBody(funFact.getBody());
        fact.setTitle(funFact.getTitle());
        fact.setSource(funFact.getSource());

        return ResponseEntity.status(200).body(factRespository.save(fact).toString());
    }

}
