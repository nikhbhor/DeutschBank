package com.deutsche.cbs.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutsche.cbs.helper.Trade;
import com.deutsche.cbs.helper.ValidateTrade;
import com.deutsche.cbs.repo.DeutscheRepo;
import com.deutsche.cbs.resource.DeutscheController;
import com.deutsche.cbs.service.DeutscheService;
import com.deutsche.cbs.service.DeutscheServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeutscheServiceImplTest {
	
	@InjectMocks
	DeutscheServiceImpl sut;

	@Mock
	ValidateTrade validateTrade;
	
	@Mock
	DeutscheRepo deutscheRepo;
	
	@Test
	public void addTradeTestPositive() {
		String todaysDate = "20/05/2020";
		String matDate = "20/06/2020";
		
		try {
			Mockito.when(validateTrade.validateDate(new Date(matDate), new Date(todaysDate))).thenReturn(true);
		
			Mockito.when(validateTrade.validateVersion(0, 4)).thenReturn(true);
			
			Trade trade = new Trade("T1", 1, "C1", "B1", matDate, todaysDate, "N");
			
			Mockito.when(deutscheRepo.addTrade(trade)).thenReturn("Successfully Added : "+trade.toString());
			String actual = sut.addTrade(trade);
			
			assertEquals("Successfully Added : "+trade.toString(), actual);
		} catch (Exception e) {
			//logger.error("lower version Trade. Trade Rejected.");
		}
	}
	
	@Test
	public void addTradeTestNegative1() {
		String todaysDate = "20/05/2020";
		String matDate = "20/04/2020";
		
		try {
			Mockito.when(validateTrade.validateDate(new Date(matDate), new Date(todaysDate))).thenReturn(false);
		
			Trade trade = new Trade("T1", 1, "C1", "B1", matDate, todaysDate, "N");
			
			String actual = sut.addTrade(trade);
			
			assertEquals("Maturity Date is less than todays Date", actual);
		} catch (Exception e) {
			//logger.error("lower version Trade. Trade Rejected.");
		}
	}
	
	@Test
	public void addTradeTestNegative2() {
		String todaysDate = "20/05/2020";
		String matDate = "20/06/2020";
		
		try {
			Mockito.when(validateTrade.validateDate(new Date(matDate), new Date(todaysDate))).thenReturn(true);
			
			Mockito.when(validateTrade.validateVersion(3, 2)).thenThrow(new Exception());
		
			Trade trade = new Trade("T1", 1, "C1", "B1", matDate, todaysDate, "N");
			
			sut.currentMax = 3;
			
			String actual = sut.addTrade(trade);
			
			assertEquals("lower version Trade. Trade Rejected.", actual);
		} catch (Exception e) {
			//logger.error("lower version Trade. Trade Rejected.");
		}
	}
}
