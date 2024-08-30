package com.food.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdGenerator {

	 private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String DIGITS_AND_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final Random random = new Random();
	    
	    public static String generateOrderId() {
	        StringBuilder orderId = new StringBuilder();
	        
	        // Add the first character (a letter)
	        orderId.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
	        
	        // Add the current date and time in yyMMddHHmmss format
	        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
	        String dateTime = sdf.format(new Date());
	        orderId.append(dateTime);
	        
	        // Add the last character (a digit or a letter)
	        orderId.append(DIGITS_AND_LETTERS.charAt(random.nextInt(DIGITS_AND_LETTERS.length())));
	        
	        return orderId.toString();
	    }
}