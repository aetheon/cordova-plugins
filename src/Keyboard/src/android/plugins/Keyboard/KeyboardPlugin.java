package plugins.Keyboard;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

/**
 * Keyboard Manager plugin to Phonegap
 */
public class KeyboardPlugin extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        InputMethodManager in = (InputMethodManager) cordova.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        boolean isActive = in.isActive();

        if(action.equals("show")){

            if(!in.isAcceptingText() || !isActive)
                in.showSoftInput(webView, 0);

            return true;

        }

        if(action.equals("hide")){

            if(in.isAcceptingText() || isActive)
                in.hideSoftInputFromWindow(webView.getWindowToken(), 0);

            return true;

        }

        return true;

    }

}
