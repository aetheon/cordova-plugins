

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
    PLUGIN_NAME = "WebPageManagerPlugin";


/*
 * WebPageManager Plugin
 * 
 * @param{url}  the url
 * @param{closeUrl} close dialog url
 */
var WebPageManagerPlugin = function(url, closeUrls){
    this.url = url;
    this.closeUrls = closeUrls;
};


WebPageManagerPlugin.prototype = {

    /*
     * show as a dialog
     * 
     * @param {callback} function callled with the last url of the window
     */
    dialog: function (callback) {

        exec(
            callback,
            callback,

            PLUGIN_NAME,
            "showDialog",
            
            // add the close url's after the url argument
            [this.url].concat(this.closeUrls)

            );

    },

    /*
     * get the cookie value for the given cookie name
     * 
     * 
     */
    getCookie: function (success, failure) {

        exec(
            success,
            failure,
            PLUGIN_NAME,
            "getCookie",
            [
                this.url,
                cookieName
            ]);

    }


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
        module.exports = WebPageManagerPlugin;
    }
}catch(e){

    // module is not undefined - means that we are being load 
    // outside a define statement
    if(define)
        define(PLUGIN_NAME, [], function(){ return WebPageManagerPlugin; });

}

