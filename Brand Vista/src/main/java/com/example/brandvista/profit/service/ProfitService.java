package com.example.brandvista.profit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.brandvista.profit.model.Profit;
import com.example.brandvista.profit.repository.ProfitRepository;

@Service
public class ProfitService {
    @Autowired
    private ProfitRepository profitRepository;

    public Profit saveProfit(Profit profit) {
        return profitRepository.save(profit);
    }

    public Profit getProfitById(int id) {
        return profitRepository.findById(id).orElse(null);
    }

    public List<Profit> getAllProfit() {
        return (List<Profit>) profitRepository.findAll();
    }

    public List<Profit> getProfitsByMediaTypes(List<String> types) {
        return profitRepository.findByMediaTypeIn(types);
    }

    public List<Profit> getPageNation(int pn, int sz) {
        return profitRepository.findAll(PageRequest.of(pn, sz, Sort.by(Sort.Direction.DESC, "profitAmount")))
                .getContent();
    }

    public void deleteProfit(int id) {
        profitRepository.deleteById(id);
    }
}
