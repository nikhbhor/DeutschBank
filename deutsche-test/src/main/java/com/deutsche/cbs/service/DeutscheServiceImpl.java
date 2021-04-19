package com.deutsche.cbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	Date todaysDate = new Date("20/05/2020");
	
	public static int currentMax = 0;
	
	private static final Logger logger = LoggerFactory.getLogger(DeutscheServiceImpl.class);
	
	@Override
	public String addTrade(Trade trade) {
		try{
			Date matDate = new Date(trade.getMaturityDate());
			
			if(!validateTrade.validateDate(matDate, todaysDate)) {
				return "Maturity Date is less than todays Date";
			}
			
			validateTrade.validateVersion(currentMax, trade.getVersion());
			
			return deutscheRepo.addTrade(trade);
		}catch(Exception e){
			logger.error("lower version Trade. Trade Rejected.");
			return "lower version Trade. Trade Rejected.";
		}

	}

}
