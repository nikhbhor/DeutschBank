package com.deutsche.cbs.helper;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.deutsche.cbs.service.DeutscheServiceImpl;

@Component
public class ValidateTradeImpl implements ValidateTrade {

	@Override
	public Boolean validateDate(Date matDate, Date todaysDate) {
		if(matDate.compareTo(todaysDate)<0) {
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean validateVersion(String tradeId, int version) throws Exception {
		
		if(!DeutscheServiceImpl.tradeMax.containsKey(tradeId) || DeutscheServiceImpl.tradeMax.get(tradeId)<=version)
			DeutscheServiceImpl.tradeMax.put(tradeId, version);
		else{
			throw new Exception();
		}
		
		return true;
	}

}
