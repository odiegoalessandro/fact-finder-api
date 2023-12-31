package com.fact.finder.services;

import com.fact.finder.dto.FactDto;
import com.fact.finder.model.Fact;
import com.fact.finder.repository.FactRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FactService {

    @Autowired
    private FactRespository factRespository;

    public List<Fact> getAllFacts() {

        return factRespository.findAll();
    }

    public Fact getRandomFact() {

        Random random = new Random();
        List<Fact> facts = getAllFacts();

        int randomFactIndex = random.nextInt(facts.size());

        return facts.get(randomFactIndex);
    }

    public Fact getByTitle(String title) {

        Fact fact = factRespository.findByTitle(title);

        return fact;
    }

    public Fact save(FactDto fact) {

        if (factRespository.findByTitle(fact.getTitle()) != null) {
            throw new IllegalArgumentException("Fato já cadastrado");
        }

        // Por segurança criamos uma nova instacia de Fact
        Fact factToSave = new Fact(fact);

        return factRespository.save(factToSave);
    }
}
