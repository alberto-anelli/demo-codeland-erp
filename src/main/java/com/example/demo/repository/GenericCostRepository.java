package com.example.demo.repository;

import com.example.demo.model.GenericCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericCostRepository extends JpaRepository<GenericCost, Long> {
}
