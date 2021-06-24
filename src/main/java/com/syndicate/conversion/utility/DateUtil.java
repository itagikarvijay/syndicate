package com.syndicate.conversion.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtil {

	public static String convertDateToString(LocalDate date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		synchronized (sdf) {
			return sdf.format(date);
		}
	}

}
