package com.fact.finder.repository;

import com.fact.finder.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FactRespository extends JpaRepository<Fact, UUID> {
    Fact findByTitle(String title);
}
