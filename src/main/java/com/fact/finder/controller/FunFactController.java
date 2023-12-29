package com.fact.finder.controller;

import com.fact.finder.model.FunFact;
import com.fact.finder.repository.FunFactRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/facts")
public class FunFactController {
    @Autowired
    FunFactRespository factRespository;

    @GetMapping("/all")
    public ResponseEntity<List<FunFact>> getAllFacts(){
        var facts = factRespository.findAll();

        return ResponseEntity.status(200).body(facts);
    }

    @GetMapping("/random-fact")
    public ResponseEntity getRandomFact(){
        List<FunFact> facts = getAllFacts().getBody();
        Random random = new Random();

        if (facts != null) {
            int randomIndex = random.nextInt(facts.size());

            return ResponseEntity.status(200).body(facts.get(randomIndex));
        }

        return ResponseEntity.status(400).body("Erro ao buscar fato");
    }
}
