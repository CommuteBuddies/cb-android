package com.commutebuddies.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkManager {
	
	// Context - access to system resources
	public int checkNetworkStatus(Context ctxt)
	{
		int status;
	    ConnectivityManager connMgr = (ConnectivityManager) ctxt.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	            status = 0;
	            System.out.println("Connection is OK");
	    } else {
	            status = -1;
	            System.out.println("Connection status check failed");
	    }
		return status;
	}

	public String retrieveUrlData(String _url,String post)
	{
		
		String res = new String();
		InputStream in_stream;
		URL url=null;
		try {
			url = new URL(_url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy); 
		
		try{
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(50000);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type","application/json");
	        conn.setRequestProperty("Accept","application/json");
	        conn.setRequestMethod("POST");
	        conn.connect();
	        OutputStream os=conn.getOutputStream();
	        os.write(post.getBytes("UTF-8"));
	        os.close();
	        
	        System.out.println("Created Connection");
	        
	        in_stream = conn.getInputStream();
	       
	        System.out.println("Created Input Stream");
	        
	        res = readIt(in_stream);
	        System.out.println(res);

	        
	        System.out.println("Closing");

	        in_stream.close();
	        conn.disconnect();
		}catch(Exception e){
			System.out.println("Exception " + e);
		}
 
		return res;
		
	}
	
	public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
	    String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(stream));

		// Read response until the end
		try {
			while ((line = rd.readLine()) != null) { 
				total.append(line); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total.toString(); 
	    
	}
	
	public String getData(Context context, String url, String post){
		String res = new String();
		
		if(checkNetworkStatus(context) == -1){
			res = "No Internet Connection Available";
		}else{
			res = retrieveUrlData(url,post);
		}
		return res;
	}
	
	
	
	

}
