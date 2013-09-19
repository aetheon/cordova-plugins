
/*
 * Cordova / Phonegap Javascript Plugin API
 * 
 * When running with plugman this script will live as a cordova AMD module as:
 *
 *      cordova.define("PLUGIN_NAME.plugin", function(require, exports, module) { }
 *
 *      The definitions for the define name are included in plugin.xml:
 *      <plugin id="PLUGIN_NAME" ...>
 *            <js-module src="FILE" name="plugin"></js-module>
 *
 * Oscar Brito - @aetheon
 *
 */


var exec = require('cordova/exec'),
    PLUGIN_NAME = "DatePickerPlugin";  // name used on config.xml


function failureCallback(err) {
    console.log("datePickerPlugin.js failed: " + err);
}

/**
 * Constructor
 */
function DatePicker() {
    this._callback = null;
}

/**
 * show - true to show the ad, false to hide the ad
 */
DatePicker.prototype.show = function (options, cb) {

    if (options.date) {
        options.date = (options.date.getMonth() + 1) + "/" + (options.date.getDate()) + "/" + (options.date.getFullYear()) + "/" + (options.date.getHours()) + "/" + (options.date.getMinutes());
    }
    var defaults = {
        mode: '',
        date: '',
        allowOldDates: true
    };

    for (var key in defaults) {
        if (typeof options[key] !== "undefined")
            defaults[key] = options[key];
    }

    this._callback = cb;

    return exec(
            cb,                       //Callback which will be called when directory listing is successful
            failureCallback,          //Callback which will be called when directory listing encounters an error
            PLUGIN_NAME,         //Telling Cordova that we want to run "DirectoryListing" Plugin
            'show',                   //Telling the plugin, which action we want to perform
            new Array(defaults));     //Passing a list of arguments to the plugin
    

};


DatePicker.prototype._dateSelected = function (date) {

    var d = new Date(parseFloat(date) * 1000);
    if (this._callback)
        this._callback(d);

};


// AMD loading
//
// Lets try to load the plugin on different context. In other words
// without and with require.js
//

try{
    // if module exists means that we are inside a define 
    // statement
    if (module && module.exports) {
        module.exports = DatePicker;
    }
}catch(e){

    // module is not undefined - means that we are being load 
    // outside a define statement
    if(define)
        define(PLUGIN_NAME, [], function(){ return DatePicker; });

}

