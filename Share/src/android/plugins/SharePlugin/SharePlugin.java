package plugins.SharePlugin;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Loading cordova plugin
 *
 *
 */
public class SharePlugin extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        action = action.toLowerCase();

        if(action.equals("share")){

            String text = "",
                   title = "";

            if(args.length() == 1) try {
                JSONObject msg = args.getJSONObject(0);
                text = msg.getString("text").trim();
                title = msg.getString("title").trim();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(text.length() > 0){
                 return Share(this.cordova.getActivity(), title, text);
            }

            return false;
        };


        return true;

    }


    /**
     * Run share dialog
     *
     * @param context - The activity context
     * @param title - The sharing message title
     * @param message - The sharing message content
     * @return boolean - True if the dialog was shown; otherwise False
     */
    private static boolean Share(Context context, String title, String message){

        if(message.length()<=0) return false;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.setType("text/plain");

        // For a file in shared storage.  For data in private storage, use a ContentProvider.

        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(Intent.createChooser(intent, "Share"));

        return true;

    }
    

}
