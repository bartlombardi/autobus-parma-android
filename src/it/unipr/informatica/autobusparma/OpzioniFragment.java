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

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class OpzioniFragment extends Fragment{
	
    private ToggleButton switchGPS;
    private ToggleButton switchInternet;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_opzioni, container, false);
        
       
        switchGPS = (ToggleButton) rootView.findViewById(R.id.switchGPS);
        switchInternet = (ToggleButton) rootView.findViewById(R.id.switchInternet);
        
		controllaStati();
		
		switchGPS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
            	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				if(!isGPSEnabled()){
	            	 Toast.makeText(getActivity(), "Attivando il GPS...", Toast.LENGTH_SHORT).show();
	            	 startActivity(intent);
				}else{
					 Toast.makeText(getActivity(), "Disattivando il GPS...", Toast.LENGTH_SHORT).show();
					 startActivity(intent);
				}
				
			}
		});

           switchInternet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                if(!isOnline()){
					  Toast.makeText(getActivity(), "Attivando Internet...", Toast.LENGTH_SHORT).show();
					  getActivity().startActivity(intent);
                }else{
					  Toast.makeText(getActivity(), "Disattivando Internet...", Toast.LENGTH_SHORT).show();
					  getActivity().startActivity(intent);
                }
			}
		});
        
        
        return rootView;
    }
	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public boolean isGPSEnabled(){
		
		boolean isGPSEnabled = false;
		LocationManager locMan;
		
		locMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		isGPSEnabled = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(isGPSEnabled){
			return true;
		}
		return false;
	}
	
	private void controllaStati(){
					
		switchGPS.setChecked(isGPSEnabled());
		switchInternet.setChecked(isOnline());
    
	}
	@Override
	public void onResume() {
		controllaStati();
		super.onResume();
	}



	





	
}
