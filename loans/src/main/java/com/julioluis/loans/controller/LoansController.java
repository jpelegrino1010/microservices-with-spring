/**
 * 
 */
package com.julioluis.loans.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.julioluis.loans.config.LoansServerConfig;
import com.julioluis.loans.model.Customer;
import com.julioluis.loans.model.Loans;
import com.julioluis.loans.model.Properties;
import com.julioluis.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@Autowired
	private LoansServerConfig loansServerConfig;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}

	@GetMapping("/loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loansServerConfig.getMsg(), loansServerConfig.getBuildVersion(),
				loansServerConfig.getMailDetails(), loansServerConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
