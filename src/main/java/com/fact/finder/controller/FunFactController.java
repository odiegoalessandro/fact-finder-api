package com.fact.finder.controller;

import com.fact.finder.model.Fact;
import com.fact.finder.repository.FactRespository;
import com.fact.finder.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/facts")
public class FunFactController {
    @Autowired
    FactRespository factRespository;

    @Autowired
    FactService factService;

    @GetMapping("/all")
    public ResponseEntity<List<Fact>> getAllFacts(){
        var facts = factService.getAllFacts();

        return ResponseEntity.status(200).body(facts);
    }

    @GetMapping("/random-fact")
    public ResponseEntity getRandomFact(){
        List<Fact> facts = getAllFacts().getBody();
        Random random = new Random();

        if (facts != null) {
            int randomIndex = random.nextInt(facts.size());

            return ResponseEntity.status(200).body(facts.get(randomIndex));
        }

        return ResponseEntity.status(400).body("Erro ao buscar fato");
    }
}
