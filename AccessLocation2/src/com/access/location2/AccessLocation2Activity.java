package com.access.location2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.access.location2.MyLocation.LocationResult;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccessLocation2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView lv=(TextView)findViewById(R.id.locationview);
        final TextView ad=(TextView)findViewById(R.id.address);
        final TextView lv2=(TextView)findViewById(R.id.locationview1);
        final TextView ad2=(TextView)findViewById(R.id.address2);
        final Button refresh=(Button)findViewById(R.id.refresh);
        LocationResult locationResult = new LocationResult()
        {
            @Override
            public void gotLocation(Location location)
            {
                //Got the location!
            	lv.setText("Latitude :"+location.getLatitude()+"\n"+"Longitude :"+location.getLongitude());
            	Toast.makeText(AccessLocation2Activity.this,"Latitude :"+location.getLatitude()+"\n"+"Longitude :"+location.getLongitude(),Toast.LENGTH_LONG).show();
            	String cityName=null;  
                Geocoder gcd = new Geocoder(getBaseContext(),   
             Locale.getDefault());  
                try {
                	  List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);               	 
                	  if(addresses != null) {
                	   Address returnedAddress = addresses.get(0);
                	   StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                	   for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                	    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                	   }
                	   ad.setText(strReturnedAddress.toString());
                	  }
                }
                catch (IOException e)
                {              
                  e.printStackTrace();    
                }  
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
        refresh.setOnClickListener(new OnClickListener() 
        {	
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				LocationResult locationresult2= new LocationResult() 
				{
					@Override
					public void gotLocation(Location location) {
						// TODO Auto-generated method stub
						lv2.setText("Latitude :"+location.getLatitude()+"\n"+"Longitude :"+location.getLongitude());
		                Geocoder gcd2 = new Geocoder(getBaseContext(),   
		             Locale.getDefault());  
		                try {
		                	  List<Address> addresses2 = gcd2.getFromLocation(location.getLatitude(), location.getLongitude(), 1);               	 
		                	  if(addresses2 != null) {
		                	   Address returnedAddress2 = addresses2.get(0);
		                	   StringBuilder strReturnedAddress2 = new StringBuilder("Address:\n");
		                	   for(int i=0; i<returnedAddress2.getMaxAddressLineIndex(); i++) {
		                	    strReturnedAddress2.append(returnedAddress2.getAddressLine(i)).append("\n");
		                	   }
		                	   ad2.setText(strReturnedAddress2.toString());
		                	  }
		                }
		                catch (IOException e)
		                {              
		                  e.printStackTrace();    
		                }  
					}
				};
				MyLocation myLocation2 = new MyLocation();
		        myLocation2.getLocation(getBaseContext(), locationresult2);
			}
		});
    }
}