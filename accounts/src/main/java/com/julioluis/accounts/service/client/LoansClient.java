package com.julioluis.accounts.service.client;

import com.julioluis.accounts.model.Customer;
import com.julioluis.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("loans")
public interface LoansClient {

    @PostMapping(path = "myLoans", consumes = "application/json")
    List<Loans> getLoanDetails(@RequestBody Customer customer);
}
