package com.cjbooms.sisu.test.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;

@Named
public class PrototypeTimeStamper {

	public String generateTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return dateFormat.format(now);
	}
}
