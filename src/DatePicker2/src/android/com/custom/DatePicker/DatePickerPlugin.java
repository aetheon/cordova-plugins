/**
 * 
 */
package com.custom.DatePicker;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;


public class DatePickerPlugin extends CordovaPlugin {

	private static final String ACTION_DATE = "date";
	private static final String ACTION_TIME = "time";
	private final String pluginName = "DatePickerPlugin";

    @Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		Log.d(pluginName, "DatePicker called with options: " + args);

		return this.show(args, callbackContext);
	}

	public synchronized boolean show(final JSONArray data, final CallbackContext callbackContext) {
		final Calendar c = Calendar.getInstance();
		final Runnable runnable;
		final Context currentCtx = cordova.getActivity();
		final DatePickerPlugin datePickerPlugin = this;

		String action = "date";

		/*
		 * Parse information from data parameter and where possible, override
		 * above date fields
		 */
		int month = -1, day = -1, year = -1, hour = -1, min = -1;
		try {
			JSONObject obj = data.getJSONObject(0);
			action = obj.getString("mode");

			String optionDate = obj.getString("date");

			String[] datePart = optionDate.split("/");
			month = Integer.parseInt(datePart[0]);
			day = Integer.parseInt(datePart[1]);
			year = Integer.parseInt(datePart[2]);
			hour = Integer.parseInt(datePart[3]);
			min = Integer.parseInt(datePart[4]);

			/* currently not handled in Android */
			// boolean optionAllowOldDates = obj.getBoolean("allowOldDates");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// By default initalize these fields to 'now'
		final int mYear = year == -1 ? c.get(Calendar.YEAR) : year;
		final int mMonth = month == -1 ? c.get(Calendar.MONTH) : month - 1;
		final int mDay = day == -1 ? c.get(Calendar.DAY_OF_MONTH) : day;
		final int mHour = hour == -1 ? c.get(Calendar.HOUR_OF_DAY) : hour;
		final int mMinutes = min == -1 ? c.get(Calendar.MINUTE) : min;


		if (ACTION_TIME.equalsIgnoreCase(action)) {

			/*
			 * Time Dialog
			 */
			runnable = new Runnable() {
				public void run() {
					final TimeSetListener timeSetListener = new TimeSetListener(datePickerPlugin, callbackContext);
					final TimePickerDialog timeDialog = new TimePickerDialog(currentCtx, timeSetListener, mHour,
							mMinutes, true);
					timeDialog.show();
				}
			};

		} else if (ACTION_DATE.equalsIgnoreCase(action)) {
			
			/*
			 * Date Dialog
			 */
			runnable = new Runnable() {
				public void run() {
					final DateSetListener dateSetListener = new DateSetListener(datePickerPlugin, callbackContext);
					final DatePickerDialog dateDialog = new DatePickerDialog(currentCtx, dateSetListener, mYear,
							mMonth, mDay);
					dateDialog.show();
				}
			};

		} else {
			Log.d(pluginName, "Unknown action. Only 'date' or 'time' are valid actions");
			return false;
		}

		cordova.getActivity().runOnUiThread(runnable);
		return true;
	}


	

}