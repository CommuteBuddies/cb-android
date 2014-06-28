package com.commutebuddies.trips;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.commutebuddies.Index;
import com.commutebuddies.R;
import com.commutebuddies.extras.DatePickerFragment;
import com.commutebuddies.extras.TimePickerFragment;

public class CreateTrip extends FragmentActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_trip);
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	
	public void map(View arg0){
		Intent i=new Intent(CreateTrip.this,Maps.class);
		startActivity(i);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, Index.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	@Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // TODO Auto-generated method stub
        //Log.i("TimePicker", "Time picker set!");
		System.out.println(hourOfDay);
		EditText time=(EditText) findViewById(R.id.time);
		time.setText(""+hourOfDay+":"+minute+"");
    }

	@Override
	public void onDateSet(DatePicker arg0, int year, int month, int day) {
		// TODO Auto-generated method stub
		EditText time=(EditText) findViewById(R.id.date);
		time.setText(""+day+"/"+month+"/"+year+"");
	}
}
