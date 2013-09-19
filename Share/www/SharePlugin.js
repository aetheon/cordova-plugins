

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
    // name used on config.xml
    PLUGIN_NAME = "SharePlugin";


/*
 * Loading plugin Instance
 * 
 * @constructor
 *
 */
var Plugin = {


    /*
     * show the native share window
     * 
     * @param {Object} options - The options for this operation
     *                  { message: , error: }
     */
    share: function(options){

        options = options || {};

        var msg = {
            title: options.title,
            text: options.text
        };
            
        var error = options.error || function(){};

        exec(
            function(){},
            error,

            PLUGIN_NAME,
            "share",

            [ msg ]);

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
        module.exports = Plugin;
    }
}catch(e){

    // module is not undefined - means that we are being load 
    // outside a define statement
    if(define)
        define(PLUGIN_NAME, [], function(){ return Plugin; });

}

