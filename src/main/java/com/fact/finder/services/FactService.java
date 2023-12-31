package com.fact.finder.services;

import com.fact.finder.dto.FactDto;
import com.fact.finder.model.Fact;
import com.fact.finder.repository.FactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FactService {

    @Autowired
    private FactRepository factRepository;

    public List<Fact> getAllFacts() {
        List<Fact> facts = factRepository.findAll();

        if(facts != null){
            return facts;
        }

        return new ArrayList<>();
    }

    public Fact getRandomFact() {
        Random random = new Random();
        List<Fact> facts = getAllFacts();

        if(facts != null){
            int randomFactIndex = random.nextInt(facts.size());
            return facts.get(randomFactIndex);
        }

        throw new IllegalArgumentException("Erro ao obter um fato");
    }

    public Fact save(FactDto fact) {

        if (factRepository.findByTitle(fact.getTitle()) != null) {
            throw new IllegalArgumentException("Fato já cadastrado");
        }

        Fact factToSave = new Fact(fact);

        return factRepository.save(factToSave);
    }
}
