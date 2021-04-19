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
	public Boolean validateVersion(int currentMax, int version) throws Exception {
		
		if(currentMax==0 || currentMax<=version)
			DeutscheServiceImpl.currentMax = version;
		else{
			throw new Exception();
		}
		
		return true;
	}

}
