package com.deutsche.cbs.service;

import java.util.List;

import com.deutsche.cbs.helper.Trade;

public interface DeutscheService {

	public String addTrade(Trade trade);

	public List<Trade> getAllTrades();

}
