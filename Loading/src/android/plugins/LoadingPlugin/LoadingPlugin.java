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
public class LoadingPlugin extends CordovaPlugin {

    // this might be dangerous
    public ProgressDialog progressDialog = null;


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        action = action.toLowerCase();

        if(progressDialog == null)
            createProgressDialog();


        if(action.equals("show")){
            progressDialog.show();
            return true;
        };


        progressDialog.hide();
        return true;

    }


    private void createProgressDialog(){
        progressDialog = new ProgressDialog(this.cordova.getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
    }
    

}
