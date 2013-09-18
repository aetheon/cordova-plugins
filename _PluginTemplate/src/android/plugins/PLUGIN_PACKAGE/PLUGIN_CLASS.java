package plugins.LoadingPlugin;

import android.app.ProgressDialog;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

/*
 * Loading cordova plugin
 *
 *
 */
public class PLUGIN_CLASS extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        action = action.toLowerCase();


        if(action.equals("run")){
            return true;
        };


        return true;

    }
    

}
