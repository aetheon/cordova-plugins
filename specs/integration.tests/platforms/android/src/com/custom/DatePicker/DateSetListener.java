package com.custom.DatePicker;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;

import org.apache.cordova.CallbackContext;


/*
 * Listener for android DateSet Dialog 
 * 
 */
public final class DateSetListener implements OnDateSetListener {
	
	private final DatePickerPlugin datePickerPlugin;
	private final CallbackContext callbackContext;

	public DateSetListener(DatePickerPlugin datePickerPlugin, CallbackContext callbackContext) {
		this.datePickerPlugin = datePickerPlugin;
		this.callbackContext = callbackContext;
	}

	/**
	 * Return a string containing the date in the format YYYY/MM/DD
	 */
	public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
		
		Calendar calendar = Calendar.getInstance();  
		calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0); // Note that months are 0-based
		Date date = calendar.getTime();
		
		//return Millis since Unix epoch
		callbackContext.success(
				String.format("%s", date.getTime())
		);	
		
	}
}