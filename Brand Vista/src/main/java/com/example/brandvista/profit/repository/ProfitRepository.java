package com.example.brandvista.profit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.brandvista.profit.model.Profit;

public interface ProfitRepository extends JpaRepository<Profit, Integer> {
    public List<Profit> findByMediaTypeIn(List<String> mediaTypes);
}
