package com.deutsche.cbs.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.cbs.helper.Trade;
import com.deutsche.cbs.helper.ValidateTrade;
import com.deutsche.cbs.repo.DeutscheRepo;

import org.slf4j.Logger;

@Service
public class DeutscheServiceImpl implements DeutscheService {

	@Autowired
	ValidateTrade validateTrade;
	
	@Autowired
	DeutscheRepo deutscheRepo;
	
	Calendar cal= Calendar.getInstance();
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String todaysDate = formatter.format(cal.getTime());
	
	public static Map<String, Integer> tradeMax = new HashMap<>();
	
	private static final Logger logger = LoggerFactory.getLogger(DeutscheServiceImpl.class);
	
	@Override
	public String addTrade(Trade trade) {
		try{
			Date matDate = new Date(trade.getMaturityDate());
			
			if(!validateTrade.validateDate(matDate, formatter.parse(todaysDate))) {
				return "Maturity Date is less than todays Date";
			}
			
			validateTrade.validateVersion(trade.getTradeId(), trade.getVersion());
			
			return deutscheRepo.addTrade(trade);
		}catch(Exception e){
			logger.error("lower version Trade. Trade Rejected.");
			return "lower version Trade. Trade Rejected.";
		}

	}

	@Override
	public List<Trade> getAllTrades() {
		List<Trade> list = deutscheRepo.getAllTrades();
		return list;
	}

}
