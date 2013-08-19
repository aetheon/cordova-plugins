package com.custom.plugin;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

/**
 * Keyboard Manager plugin to Phonegap
 */
public class Plugin extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        return true;

    }
    

}
