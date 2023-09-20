package com.julioluis.accounts.service.client;

import com.julioluis.accounts.model.Cards;
import com.julioluis.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsClient {

    @RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
    List<Cards> getCardDetails(@RequestBody Customer customer);
}
