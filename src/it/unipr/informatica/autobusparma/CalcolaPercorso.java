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
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class CalcolaPercorso extends Fragment{
	
	public CalcolaPercorso(){}
	public WebView myWebView;
	public ProgressBar progressBar;
	private String curURL="http://lab.okkam.it/tepparma/doveSiamo.html";
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	private LocationManager locMan;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2; // 2 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
    	
        View view = inflater.inflate(R.layout.fragment_calcolapercorso, container, false); 
        initLoc(locMan);
        updatePlaces();		
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
    public Location getLocation(long MIN_DISTANCE, long MIN_TIME ) {
		Location location = null; 
	try {

	    	LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
	
	        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	
	        if (!isGPSEnabled && !isNetworkEnabled) {
	  
	        } else {
	            if (isNetworkEnabled) {
	                if (locationManager != null) {
	                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                }
	            }
	            if (isGPSEnabled) {
	                if (location == null) {
	                    if (locationManager != null) {
	                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                     
	                    }
	                }
	            }
	        }
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	    return location;
	}

    public void updateUrl(String url) {
        curURL = url;
        myWebView = (WebView) getView().findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }
    
    public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public void initLoc(LocationManager locMan){

		locMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		isGPSEnabled = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(!isGPSEnabled)
		{
			Toast.makeText(getActivity(), "GPS non attivo. \nVai nelle opzioni per attivarlo.",Toast.LENGTH_SHORT).show();
		}
		if(!isOnline()){
			Toast.makeText(getActivity(), "Internet non attivo. \nVai nelle opzioni per attivarlo.",Toast.LENGTH_SHORT).show();
		}

    }
	
	private void updatePlaces() {
		
		locMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location lastLoc = getLocation(MIN_DISTANCE_CHANGE_FOR_UPDATES,MIN_TIME_BW_UPDATES);
		
		if(lastLoc != null){

			double myLat = lastLoc.getLatitude();
			double myLon = lastLoc.getLongitude();
			curURL="http://lab.okkam.it/tepparma/doveSiamo.html?la="+myLat+"&lo="+myLon+"";
		}
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    try {
	        Fragment fragment = (Fragment) getActivity().getFragmentManager().findFragmentById(R.id.webview);
	        if (fragment != null)
	            getFragmentManager().beginTransaction().remove(fragment).commit();
	
	    } catch (IllegalStateException e) {
	    }
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
	@Override
	public void onResume() {
		super.onResume();
		updatePlaces();
	}
	

}
