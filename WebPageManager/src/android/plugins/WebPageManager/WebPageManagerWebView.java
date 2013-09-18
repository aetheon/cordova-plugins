package plugins.WebPageManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * WebPageManagerWebView
 *
 */
public class WebPageManagerWebView extends WebView {

    public final int PAGE_FINISH_TIMEOUT = 10000;


    /*
     * Private vars
     *
     */
    private URL url = null;
    private URL currentUrl = null;
    private ArrayList<URL> closeUrls = null;

    // flags if the page has finish loading
    private boolean pageFinished = false;

    // run tasks async
    private Handler async = new Handler();

    // keeps checking if the page has finished loading
    private Runnable isTimeoutTickChecker = new Runnable() {
        @Override
        public void run() {
            isOnTimeoutCheck();
        }
    };

    // onClose Event
    private Runnable onClose = null;

    // onReady event
    private Runnable onReady = null;

    // onTimeout event
    private Runnable onTimeout = null;



    /*
     * .ctor
     *
     */
    public WebPageManagerWebView(Context context, URL url, ArrayList<URL> closeUrls, Runnable onReady, Runnable onClose, Runnable onTimeout){

        super(context);

        this.url = url;
        this.closeUrls = closeUrls;

        this.onReady = onReady;
        this.onClose = onClose;
        this.onTimeout = onTimeout;

        this.setupWebView();

    }

    public URL getCurrentUrl(){
        return this.currentUrl;
    }

    @Override
    public void destroy() {

        this.stopLoading();
        this.clearTimeoutCheck();

        this.onClose = this.onReady = null;

        super.destroy();

    }


    /*
     * Setup WebView
     *
     */
    private void setupWebView(){

        WebSettings webSettings = this.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setSupportMultipleWindows(false);

        final WebPageManagerWebView me = this;
        this.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // doesn't care about ssl errors
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // for redirects to work is needed to explicity
                // call for the given url

                me.currentUrl = null;
                try {
                    me.currentUrl = new URL(url);

                    // test if its time to close dialog!
                    if (canCloseDialog()) {
                        me.stopLoading();   // stop the browser from loading
                        me.onClose();
                        return true;
                    }

                    return false;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                return true;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pageFinished = false;

                // check if can close the dialog
                try {

                    me.currentUrl = new URL(url);

                    if(canCloseDialog()){
                        clearTimeoutCheck();
                        onClose();
                        return;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                me.setupTimeoutCheck();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pageFinished = true;
            }



            // IMPORTANT: this is not called for HTTP status codes just
            // unrecoverable erros ( file not found, network problems, ... )
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                super.onReceivedError(view, errorCode, description, failingUrl);

                pageFinished = true;

                me.clearTimeoutCheck();
                me.onTimeout();

            }

        });


        String url = this.url.toString();
        this.loadUrl(url);

    }


    /*
     * Tests if closeUrl is equal to the current URL.
     *  . In this case we only care about host, port and path, ignoring query strings
     *
     * @param{current} The current URL
     * @return{Boolean}
     */
    private Boolean canCloseDialog(){

        for(URL closeUrl : this.closeUrls){

            String currentHost = this.currentUrl.getHost();
            int currentPort = this.currentUrl.getPort();
            String currentPath = this.currentUrl.getPath();

            String closeHost = closeUrl.getHost();
            int closePort = closeUrl.getPort();
            String closePath = closeUrl.getPath();

            if(currentHost.equals(closeHost) && currentPort == closePort && currentPath.equals(closePath) ){
                return true;
            }

        }

        return false;

    }


    /*
     * trigger onClose handlers
     *
     */
    private void onClose(){

        if(this.onClose != null){
            this.onClose.run();
        }

    }


    /*
     * trigger onClose handlers
     *
     */
    private void onReady(){

        if(this.onReady != null){
            this.onReady.run();
        }

    }


    /*
     * trigger onTimeout handlers
     *
     */
    private void onTimeout(){

        if(this.onTimeout != null){
            this.onTimeout.run();
        }

    }


    /*
     * Checks if the page has finished loading
     *
     */
    private void isOnTimeoutCheck(){

        if(this.pageFinished){
           this.onReady();
        }else{
            this.onTimeout();
        }

    }


    /*
     * setup timer timeout check
     *
     */
    private void setupTimeoutCheck() {
        this.clearTimeoutCheck();
        async.postDelayed(isTimeoutTickChecker, PAGE_FINISH_TIMEOUT);
    }


    /*
     * clear timer timeout check
     *
     */
    private void clearTimeoutCheck() {
        async.removeCallbacks(isTimeoutTickChecker);
    }

}
