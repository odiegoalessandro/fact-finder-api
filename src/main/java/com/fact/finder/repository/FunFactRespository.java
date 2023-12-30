package com.fact.finder.repository;

import com.fact.finder.model.FunFact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FunFactRespository extends JpaRepository<FunFact, UUID> {
    FunFact findByTitle(String title);
}
