define(["require"], function(require){
    
    var exec = function(
        successCallback,
        failureCallback,
        pluginName,
        action,
        args
    ){

        if(onCall){
            onCall(successCallback, failureCallback, pluginName, action, args);
        }

    };

    return exec;

});