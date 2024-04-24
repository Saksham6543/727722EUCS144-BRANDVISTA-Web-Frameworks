package com.example.brandvista.profit.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.brandvista.profit.model.Profit;
import com.example.brandvista.profit.service.ProfitService;

@RestController
@RequestMapping("/profits")
public class ProfitController {
    private ProfitService profitService;

    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String postMethodName(@RequestBody Profit profit) {
        profitService.saveProfit(profit);
        return "Posted";
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Profit> getProfits() {
        return profitService.getAllProfit();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Profit getMethodName(@PathVariable int id) {
        return profitService.getProfitById(id);
    }

    @GetMapping("/get/mediatype")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Double> getMethodName() {
        List<Profit> p = profitService.getProfitsByMediaTypes(Arrays.asList("Instagram", "Facebook"));
        List<Double> ans = new ArrayList<>();
        for (Profit pr : p)
            ans.add(pr.getProfitAmount());
        return ans;
    }

    @GetMapping("/get/pageNation/{pn}/{sz}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Profit> getInteractions(@PathVariable("pn") int pn, @PathVariable("sz") int sz) {
        return profitService.getPageNation(pn, sz);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable int id, @RequestBody Profit profit) {
        Profit exist = profitService.getProfitById(id);
        if (exist != null) {
            exist.setProfitAmount(profit.getProfitAmount());
            exist.setMediaType(profit.getMediaType());
            exist.setDateOfCreation(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            profitService.saveProfit(exist);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfit(@PathVariable int id) {
        Profit exist = profitService.getProfitById(id);
        if (exist != null) {
            profitService.deleteProfit(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
        }
    }
}
