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

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.SyncStateContract.Constants;

public class MappaFragment extends Fragment implements LocationListener{
	
	private static final int MAX_PLACES = 20;
	private GoogleMap mMap;
	private Marker userMarker;
	private Marker[] placeMarkers;
	private MarkerOptions[] places;
	boolean isGPSEnabled = false;
	private LocationManager locMan;	
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2; // 2 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	
	public MappaFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        	
        initLoc(locMan);

        if(mMap==null){
        	
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            LatLng parma = new LatLng(44.801717, 10.324552);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parma,13));
            
			if(mMap!=null){
				
		        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		        mMap.setMyLocationEnabled(true);
				placeMarkers = new Marker[MAX_PLACES];
				//zoom();
				updatePlaces();	
			}
			
        }
        return rootView;
    }
	
	/*
	 * Questo pezzo di codice serve per leggere un file dalla cartella service
	 * penso sia utile per un sviluppo futuro
	public String leggiFiles() throws IOException {
		 Log.d("prova", "dentroleggi");
		AssetManager am = getActivity().getAssets();
		InputStream in = am.open("prova.txt");
		
		    InputStreamReader inputStreamReader = new InputStreamReader(in);
		    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        sb.append(line);
		        Log.d("prova", "while");
		    }
		    Log.d("prova", "ok");
		    return sb.toString();
	}*/

	@Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            Fragment fragment = (Fragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
            if (fragment != null)
                getFragmentManager().beginTransaction().remove(fragment).commit();

        } catch (IllegalStateException e) {}
    }
	@Override
	public void onLocationChanged(Location arg0) {
		updatePlaces();
	}
	@Override
	public void onProviderDisabled(String arg0) {}
	@Override
	public void onProviderEnabled(String arg0) {}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
	
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

		if(!isGPSEnabled) {
			Toast.makeText(getActivity(), "GPS non attivo. \nVai nelle opzioni per attivarlo.",Toast.LENGTH_SHORT).show();
		}
		if(!isOnline()) {
			Toast.makeText(getActivity(), "Internet non attivo. \nVai nelle opzioni per attivarlo.",Toast.LENGTH_SHORT).show();
		}

	}
	public Location getLocation(long MIN_DISTANCE, long MIN_TIME ) {
		Location location = null; 
	try {
	    	LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
	        // getting GPS status e network
	        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	
	        if (!isGPSEnabled && !isNetworkEnabled) {
	            // no network provider is enabled
	        } else {
	            if (isNetworkEnabled) {
	                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,MIN_DISTANCE, this);
	                if (locationManager != null) {
	                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                }
	            }
	            // if GPS Enabled get lat/long using GPS Services
	            if (isGPSEnabled) {
	                if (location == null) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.GPS_PROVIDER,
	                            MIN_TIME,
	                            MIN_DISTANCE, this);
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
	
	/*private void zoom(){
		
		locMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location lastLoc = getLocation(0,0);
		
		if(lastLoc != null){

			double myLat = lastLoc.getLatitude();
			double myLon = lastLoc.getLongitude();
	        LatLng myL = new LatLng(myLat, myLon);
	        
	       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myL,15));
		}
	}*/

	private void updatePlaces() {

		locMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location lastLoc = getLocation(MIN_DISTANCE_CHANGE_FOR_UPDATES,MIN_TIME_BW_UPDATES);
		
		if(lastLoc != null){

			double myLat = lastLoc.getLatitude();
			double myLon = lastLoc.getLongitude();
	        LatLng myL = new LatLng(myLat, myLon);
	        
	        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myL,15));
	        
	        if(userMarker!=null) 
	        	userMarker.remove();

			String placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+myLat+","+myLon+"&radius=10000&sensor=true&keyword=autobus&key=INSERT KEY";
	        /*String placesSearchStr = null;
			
				Log.d("prova", "p");
				try {
					placesSearchStr = leggiFiles();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.d("prova", "sono nel catch");
					e.printStackTrace();
				}*/
			
			//execute query
			new GetPlaces().execute(placesSearchStr); //onPostExecute(placesSearchStr);
			
		}
		if (isGPSEnabled) {
			
			locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		}
		
	}
	
	private class GetPlaces extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... placesURL) {

			StringBuilder placesBuilder = new StringBuilder();
			
			for (String placeSearchURL : placesURL) {
				HttpClient placesClient = new DefaultHttpClient();
				try {
			
					HttpGet placesGet = new HttpGet(placeSearchURL);
					//execute GET with Client - return response
					HttpResponse placesResponse = placesClient.execute(placesGet);
					//check response status
					StatusLine placeSearchStatus = placesResponse.getStatusLine();
					//only carry on if response is OK
					if (placeSearchStatus.getStatusCode() == 200) {
						//get response entity
						HttpEntity placesEntity = placesResponse.getEntity();
						//get input stream setup
						InputStream placesContent = placesEntity.getContent();
						//create reader
						InputStreamReader placesInput = new InputStreamReader(placesContent);
						//use buffered reader to process
						BufferedReader placesReader = new BufferedReader(placesInput);
						//read a line at a time, append to string builder
						String lineIn;
						while ((lineIn = placesReader.readLine()) != null) {
							placesBuilder.append(lineIn);
						}
					}
				}
				catch(Exception e){ 
					e.printStackTrace(); 
				}
			}
			return placesBuilder.toString();
			//return null;
		}
		//process data retrieved from doInBackground
		protected void onPostExecute(String result) {
			//parse place data returned from Google Places
			//remove existing markers
			if(placeMarkers!=null){
				for(int pm=0; pm<placeMarkers.length; pm++){
					if(placeMarkers[pm]!=null)
						placeMarkers[pm].remove();
				}
			}
			try {
				//parse JSON
				
				//create JSONObject, pass string returned from doInBackground
				JSONObject resultObject = new JSONObject(result);
				//get "results" array
				JSONArray placesArray = resultObject.getJSONArray("results");
				//marker options for each place returned
				places = new MarkerOptions[placesArray.length()];
				//loop through places
				for (int p=0; p<placesArray.length(); p++) {
					//parse each place
					//if any values are missing we won't show the marker
					boolean missingValue=false;
					LatLng placeLL=null;
					String placeName="";
					String vicinity="";
					try{
						//attempt to retrieve place data values
						missingValue=false;
						//get place at this index
						JSONObject placeObject = placesArray.getJSONObject(p);
						//get location section
						JSONObject loc = placeObject.getJSONObject("geometry").getJSONObject("location");
						//read lat lng
						placeLL = new LatLng(Double.valueOf(loc.getString("lat")),Double.valueOf(loc.getString("lng")));	
						//vicinity
						vicinity = placeObject.getString("vicinity");
						//name
						placeName = placeObject.getString("name");
					}
					catch(JSONException jse){
						Log.v("PLACES", "missing value");
						missingValue=true;
						jse.printStackTrace();
					}
					//if values missing we don't display
					if(missingValue)	
						places[p]=null;
					else
						places[p]=new MarkerOptions().position(placeLL).title(placeName).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).snippet(vicinity);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			if(places!=null && placeMarkers!=null){
				for(int p=0; p<places.length && p<placeMarkers.length; p++){
					//will be null if a value was missing
					if(places[p]!=null)
						placeMarkers[p]=mMap.addMarker(places[p]);
				}
			}
			
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		if(mMap!=null){

			locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 100, this);
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(mMap!=null){
			locMan.removeUpdates(this);
		}
	}

}
