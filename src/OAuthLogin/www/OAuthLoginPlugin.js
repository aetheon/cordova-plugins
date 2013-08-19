



var exec = require('cordova/exec'),
    _PLUGIN_NAME = "OAuthLoginPlugin";


/*
 * OAuth cordova plugin
 * 
 * @param{url}  the oauth url
 * @param{closeUrl} close dialog url
 */
var OAuthLoginRequestPlugin = function(url, closeUrls){
    this.url = url;
    this.closeUrls = closeUrls;
};


OAuthLoginRequestPlugin.prototype = {

    /*
     * show oauth dialog
     * 
     * 
     */
    dialog: function (success, failure) {

        exec(
            success,
            failure,

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
  module.exports = OAuthLoginRequestPlugin;
}