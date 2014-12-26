/*
 * © Copyright 2014 Bartolomeo Lombardi
	This file is part of AutobusParma.

    AutobusParma is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 1 of the License, or
	any later version.

    AutobusParma is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AutobusParma.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.unipr.informatica.autobusparma;

import it.unipr.informatica.autobusparma.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")
public class TepFragment extends Fragment {
	
	private WebView myWebView;
	private ProgressBar progressBar;
	private String curURL="http://www.tep.pr.it/news/";
	
	public TepFragment(){}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tep, container, false);

        if (curURL != null) {
            myWebView = (WebView) view.findViewById(R.id.webview);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setLoadWithOverviewMode(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setBuiltInZoomControls(true);
            myWebView.setWebViewClient(new myWebClient());
            myWebView.loadUrl(curURL);
        }
        return view;
    }
    public void updateUrl(String url) {
        curURL = url;
        myWebView = (WebView) getView().findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }
    
    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
 
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

}
