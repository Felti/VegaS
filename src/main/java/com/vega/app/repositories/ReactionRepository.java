package com.vega.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.app.entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long>{

}
