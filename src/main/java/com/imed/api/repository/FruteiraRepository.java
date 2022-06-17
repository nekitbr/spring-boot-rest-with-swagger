package com.imed.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imed.api.model.Matricula;

@Repository
public interface FruteiraRepository extends JpaRepository<Matricula, Long>{}
