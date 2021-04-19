package com.deutsche.cbs.helper;

import java.util.Date;

public interface ValidateTrade {

	Boolean validateDate(Date matDate, Date todaysDate);

	Boolean validateVersion(int currentMax, int version) throws Exception;

}
