package com.fact.finder.repository;

import com.fact.finder.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FactRepository extends JpaRepository<Fact, UUID> {
    Fact findByTitle(String title);
    List<Fact> findAllByTitle(String title);
}
