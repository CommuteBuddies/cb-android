package com.commutebuddies;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.commutebuddies.network.NetworkManager;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void login(View arg) throws MalformedURLException{
		EditText mobile=(EditText) findViewById(R.id.mobile);
		EditText pass=(EditText) findViewById(R.id.password);
		
		JSONObject object = new JSONObject();
		  try {
		    object.put("username", mobile.getText().toString());
		    object.put("password", pass.getText().toString());
		    object.put("login", "Login");
		  } catch (JSONException e) {
		    e.printStackTrace();
		  }
		String json=object.toString();
		System.out.println(object);
		String url=getResources().getString(R.string.login_url);
		new LoginThread().execute(url,json);
	}
	
	public void sign_up(View arg){
		Intent i=new Intent(this,SignUp.class);
		startActivity(i);
	}
	
	
	private class LoginThread extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetworkManager nw=new NetworkManager();
			String data=nw.getData(getApplicationContext(), arg0[0] ,arg0[1]);
			System.out.println(data);
			return data;
		}
		
		@Override
		protected void onPostExecute(String data){
			try {
				JSONObject object=new JSONObject(data);
				String id=object.getString("id");
				if(!id.equalsIgnoreCase("-1")){
					Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
					Intent i=new Intent(getApplicationContext(),Index.class);
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
}
