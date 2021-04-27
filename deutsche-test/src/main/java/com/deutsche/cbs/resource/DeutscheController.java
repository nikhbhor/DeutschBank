package com.deutsche.cbs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.cbs.helper.Trade;
import com.deutsche.cbs.service.DeutscheService;

@RestController
public class DeutscheController {

	@Autowired
	DeutscheService service;
	
	@RequestMapping(value = "/addTrade", method = RequestMethod.POST)
	public String addTrade(@RequestBody Trade trade) {
		return service.addTrade(trade);
	}
	
	@RequestMapping(value = "/getAllTrades", method = RequestMethod.GET)
	public List<Trade> getAllTrades() {
		return service.getAllTrades();
	}
}
