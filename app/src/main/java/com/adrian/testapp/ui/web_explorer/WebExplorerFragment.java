package com.adrian.testapp.ui.web_explorer;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.adrian.testapp.databinding.FragmentWebExplorerBinding;
import com.adrian.testapp.ui.base.BaseFragment;

import java.net.URL;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class WebExplorerFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = "WebExplorer_FRAGMENT_TAG";
    private static final String TARGET_URL_BUNDLE_KEY = "TARGET_URL_BUNDLE_KEY";


    public static Bundle generateArgs(String targetURL) {
        Bundle args = new Bundle();
        args.putString(TARGET_URL_BUNDLE_KEY, targetURL);
        return args;
    }

    private String mTargetURL;
    private FragmentWebExplorerBinding mMainBinding;

    @Override
    protected String getTitle() {
        try {
            URL uri = new URL(mTargetURL);
            return uri.getHost();
        } catch (Exception err) {
            //
        }
        return super.getTitle();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(TARGET_URL_BUNDLE_KEY)) {
            mTargetURL = getArguments().getString(TARGET_URL_BUNDLE_KEY);
        }
    }

    @Override
    public View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMainBinding = FragmentWebExplorerBinding.inflate(inflater, container, false);
        initView(savedInstanceState);
        return mMainBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMainBinding.fragmentWebExplorerWebView.saveState(outState);
    }

    //** Private methods
    private void initView(Bundle state) {
        WebSettings settings = mMainBinding.fragmentWebExplorerWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        mMainBinding.fragmentWebExplorerWebView.setWebChromeClient(new WebChromeClient());
        mMainBinding.fragmentWebExplorerWebView.setWebViewClient(new MyWebViewClient());
        mMainBinding.fragmentWebExplorerWebView.requestFocus();

        if(state != null)
            mMainBinding.fragmentWebExplorerWebView.restoreState(state);
        else mMainBinding.fragmentWebExplorerWebView.loadUrl(mTargetURL);

    }

    private class MyWebViewClient extends WebViewClient {


        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler,
                                       SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            String message = "SSL Certificate error.";
            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    message = "The certificate authority is not trusted.";
                    break;
                case SslError.SSL_EXPIRED:
                    message = "The certificate has expired.";
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "The certificate Hostname mismatch.";
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "The certificate is not yet valid.";
                    break;
            }
            message += " Do you want to continue anyway?";

            builder.setTitle("SSL Certificate Error");
            builder.setMessage(message);
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
