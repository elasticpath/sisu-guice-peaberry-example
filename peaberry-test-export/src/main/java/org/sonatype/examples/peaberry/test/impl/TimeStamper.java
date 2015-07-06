package org.sonatype.examples.peaberry.test.impl;

/**
 * Created by conor on 03/07/15.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;

@Named
public class TimeStamper {

	public String generateTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return "TimeStamper[" + dateFormat.format(now) + "]";
	}
}
