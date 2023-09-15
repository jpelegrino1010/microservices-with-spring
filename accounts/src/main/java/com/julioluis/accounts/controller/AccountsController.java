/**
 * 
 */
package com.julioluis.accounts.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.julioluis.accounts.config.AccountServerConfig;
import com.julioluis.accounts.model.Accounts;
import com.julioluis.accounts.model.Customer;
import com.julioluis.accounts.model.Properties;
import com.julioluis.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountServerConfig accountServerConfig;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountServerConfig.getMsg(), accountServerConfig.getBuildVersion(),
				accountServerConfig.getMailDetails(), accountServerConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
