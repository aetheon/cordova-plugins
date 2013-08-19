package com.custom.DatePicker;

import java.util.Date;

import org.apache.cordova.CallbackContext;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.TimePicker;



/*
 * Listener for android TimeSet Dialog 
 * 
 */
public final class TimeSetListener implements OnTimeSetListener {

	private final DatePickerPlugin datePickerPlugin;
	private final CallbackContext callbackContext;

	public TimeSetListener(DatePickerPlugin datePickerPlugin, CallbackContext callbackContext) {
		this.datePickerPlugin = datePickerPlugin;
		this.callbackContext = callbackContext;
	}

	/**
	 * Return the current date with the time modified as it was set in the
	 * time picker.
	 */
	public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
		Date date = new Date();
		date.setHours(hourOfDay);
		date.setMinutes(minute);

		callbackContext.success(date.toLocaleString());
	}
}