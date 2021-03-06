package com.deutsche.cbs.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deutsche.cbs.helper.Trade;

@Component
public class DeutscheRepo {
	
	Map<String,Trade> tradeMap = new HashMap<>();
	int currentMax = 0;
	
	public String addTrade(Trade trade) throws Exception {
		
		tradeMap.put(trade.getTradeId()+"-"+trade.getVersion(), trade);
		
		return "Successfully Added : "+trade.toString();
		
	}

	public List<Trade> getAllTrades() {
		List<Trade> list = new ArrayList<>();
		for(String key : tradeMap.keySet()) {
			list.add(tradeMap.get(key));
		}
		return list;
	}
	
	

}
