



var exec = require('cordova/exec'),
    _PLUGIN_NAME = "WebPageManagerPlugin";


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

            _PLUGIN_NAME,
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
            _PLUGIN_NAME,
            "getCookie",
            [
                this.url,
                cookieName
            ]);

    }


};

if (module.exports) {
  module.exports = WebPageManagerPlugin;
}