/* oscar */
define(
    ["jquery", "cordova"], 
    function($, cordova){
        "use strict";

        var exec = cordova.require('cordova/exec');

        /*
         * Keyboard management native plugin
         * 
         */
        var Keyboard = function () { };

        Keyboard._PLUGIN_NAME = "Keyboard";
       

        /*
         * Ensures that the keyboard is show on the device
         */
        Keyboard.show = function (url, eventfn) {

            exec(
                eventfn,
                eventfn, // the same on purpouse
                Keyboard._PLUGIN_NAME,
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
                Keyboard._PLUGIN_NAME,
                "hide",
                [
                    url
                ]);

        };


        /*
         * Maintain API consistency with iOS
         */
        Keyboard.prototype.install = function () {};


    });
