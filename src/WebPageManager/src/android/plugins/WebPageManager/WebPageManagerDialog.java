package plugins.WebPageManager;

import java.net.URL;
import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;

/*
 * WebPageManagerDialog
 *
 * . keeps tracking changes in requests host - when changes fires the listener onDone
 * . does not grant that the request was successful just tracks the redirects
 *
 */
public class WebPageManagerDialog extends Dialog {


	private IBrowserDialogControlListener listener;
	private WebView browser;

    ProgressDialog loading = null;

	/*
	 * Creates an instance o the dialog
	 *
	 * @param{url} the url to show
	 */
	public WebPageManagerDialog(Context context, URL url, ArrayList<URL> closeUrls, IBrowserDialogControlListener listener) throws Exception {
		super(context);

		final WebPageManagerDialog me = this;

        this.loading = ProgressDialog.show(getContext(), "", "Loading");

        this.listener = listener;
		this.browser = new WebPageManagerWebView(
                getContext(),
                url,
                closeUrls,
                new Runnable() {
                    @Override
                    public void run() {
                        // on Ready to show
                        me.showDialog();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        // on Close to show
                        URL url = ((WebPageManagerWebView) me.browser).getCurrentUrl();
                        me.sendEvent(url);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        // on Timeout to show
                        URL url = ((WebPageManagerWebView) me.browser).getCurrentUrl();
                        me.sendEvent(url);
                    }
                });




	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// sets the layout of the dialog
		FrameLayout layout = new FrameLayout(getContext());
		addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
		
		layout.addView(this.browser, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

	}


	
	@Override
    public void show() {
        this.showLoading();
	}


    @Override
    public void onStop(){
        super.onStop();

        if(this.loading != null)
            this.hideLoading();

        this.sendEvent(null);
    }



	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}


    /*
     * Really show the dialog
     *
     */
    private void showDialog(){
        this.hideLoading();
        super.show();
    }


    /*
     * Show loading
     *
     */
    private void showLoading(){
        loading.show();
    }

    /*
     * Hide Loading
     *
     */
    private void hideLoading(){
        if(loading != null)
            loading.dismiss();
    }

    /*
     * Set's the web view state
     *
     */
    private void sendEvent(URL url){

        if(this.browser == null)
            return;

        this.hideLoading();
        super.hide();

        this.listener.onDone(url);

        this.dispose();

    }

    private void dispose(){

        this.hideLoading();
        this.browser.destroy();

        this.browser = null;
        this.loading = null;
    }


}
