package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.sql.Timestamp;

import org.junit.Test;

import com.hotelmanage.CommonMethod;

public class TestCommonMethod {

	@Test
	public void testStringToBool() {
		// fail("Not yet implemented");
	}

	@Test
	public void testStringToDate() {
		Timestamp sTimestamp = CommonMethod.StringToDate("2016Äê04ÔÂ12ÈÕ");
		System.out.println(sTimestamp);
		fail("Not yet implemented");
	}

	@Test
	public void testGuestTypeChange() {
		fail("Not yet implemented");
	}

}
