package plugins.WebPageManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;


/*
 * WebPageManagerPlugin  
 * . success callback adds url as a parameter
 * 
 */
public class WebPageManagerPlugin extends CordovaPlugin {

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if(args.length() == 0)
			return false;

		final Activity context = this.cordova.getActivity();


        // Action: get all cookies values from a given url
        // @param{url}
        // @param{cookieName}
        // return{String} The cookie value or empty

        if(action.equals("getCookie")){

            String url = args.getString(0);
            String cookieName = args.getString(1);

            URL uri = null;
            try {

                uri = new URL(url);
                String hostname = uri.getHost();

                String cookiesStr = CookieManager.getInstance().getCookie(hostname);
                HashMap<String, String> cookies = Cookie.parseCookies(cookiesStr);

                String cookieValue = cookies.get(cookieName);

                if(cookieValue == null) cookieValue = "";

                callbackContext.success(cookieValue.trim());
                return true;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            finally {
                callbackContext.error(0);
            }

        }


        // Action: show dialog
        // @param{url} url

        if(action.equals("showDialog")){

            final String url = args.getString(0);
            final ArrayList<URL> closeUrls = new ArrayList<URL>();

            // load the close url's from the arguments
            for(int i=1; i<args.length(); i++){
                try {
                    closeUrls.add(new URL(args.getString(i)));
                } catch (MalformedURLException e) {
                    // do not run the plugin!
                    callbackContext.error(e.getMessage());
                    return false;
                }
            }

            final IBrowserDialogControlListener listener = new BrowserDialogControlListener(context, callbackContext);
            final CallbackContext callback = callbackContext;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Dialog dialog = null;
                    try {

                        dialog = new WebPageManagerDialog(context, new URL(url), closeUrls, listener);
                        dialog.show();

                    } catch (Exception e) {
                        callback.success("");
                    }

                }
            });

            return true;

        }


		// fallback return
		return false;
	}
	
	
	private class BrowserDialogControlListener implements IBrowserDialogControlListener {

		CallbackContext callbackContext = null;
		Context context = null;
		
		public BrowserDialogControlListener(Context context, CallbackContext callbackContext){
			this.callbackContext = callbackContext;
			this.context = context;
		}
		
		@Override
		public void onDone(URL url) {
			this.callbackContext.success(url.toString());
		}
		
	}
	
}
