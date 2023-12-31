package com.fact.finder.controller;

import com.fact.finder.model.Fact;
import com.fact.finder.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/facts")
public class FunFactController {
    @Autowired
    FactService factService;

    @GetMapping("/all")
    public ResponseEntity<List<Fact>> getAllFacts() {
        return ResponseEntity.status(200).body(factService.getAllFacts());
    }

    @GetMapping("/random-fact")
    public ResponseEntity<Object> getRandomFact() {
        try {
            List<Fact> facts = getAllFacts().getBody();
            Random random = new Random();

            int randomIndex = random.nextInt(facts.size());

            return ResponseEntity.status(200).body(facts.get(randomIndex));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
