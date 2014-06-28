package com.commutebuddies;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.commutebuddies.network.NetworkManager;

public class SignUp extends Activity {

	EditText name;
	EditText mobile;
	EditText email;
	EditText pass;
	EditText confirm_pass;
	String gender="male";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
	}

	public void signUp_submit(View arg0){
		name=(EditText)findViewById(R.id.name);
		mobile=(EditText)findViewById(R.id.mobile);
		email=(EditText)findViewById(R.id.email);
		pass=(EditText)findViewById(R.id.password);
		confirm_pass=(EditText)findViewById(R.id.confirm_password);
		String names[]=name.getText().toString().split(" ");
		
		if(pass.getText().toString().equalsIgnoreCase(confirm_pass.getText().toString())){
			JSONObject object = new JSONObject();
			JSONObject users=new JSONObject();
			try {
				users.put("users", object);
				object.put("first_name", names[0]);
				object.put("last_name", names[1]);
				object.put("gender", gender);
				object.put("mobile", mobile.getText().toString());
				object.put("email", email.getText().toString());
				object.put("password", pass.getText().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String json=users.toString();
			System.out.println(json);
			String url=getResources().getString(R.string.signup_url);
			new SignUpThread().execute(url,json);
		}
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
		case R.id.radio_male:
			if (checked)
				gender="male";
			break;
		case R.id.radio_female:
			if (checked)
				gender="female";
			break;
		}
	}

	private class SignUpThread extends AsyncTask<String,Void,String>{

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
				String id=object.getString("registration");
				if(id.equalsIgnoreCase("successful")){
					Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
					//Intent i=new Intent(getApplicationContext(),Home.class);
					//startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

	}

}
