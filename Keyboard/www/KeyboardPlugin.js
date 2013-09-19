

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


var exec = require('cordova/exec');

/*
 * Keyboard management native plugin
 * 
 */
var Keyboard = function () { };

var PLUGIN_NAME = "KeyboardPlugin";


/*
 * Ensures that the keyboard is show on the device
 */
Keyboard.show = function (url, eventfn) {

    exec(
        eventfn,
        eventfn, // the same on purpouse
        PLUGIN_NAME,
        "show",
        [
            url
        ]);

};


/*
 * Ensures that the keyboard is hidden on the device
 */
Keyboard.hide = function (url, eventfn) {

    exec(
        eventfn,
        eventfn, // the same on purpouse
        PLUGIN_NAME,
        "hide",
        [
            url
        ]);

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
        module.exports = Keyboard;
    }
}catch(e){

    // module is not undefined - means that we are being load 
    // outside a define statement
    if(define)
        define(PLUGIN_NAME, [], function(){ return Keyboard; });

}
