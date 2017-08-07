package com.votingsytem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;



public class Contact extends Activity {

	// ///Header

	LinearLayout back;
	TextView head;

	// ///

	Spinner spinner_city;
	TextView txt_city, txt_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		// ///Header

		head = (TextView) findViewById(R.id.head);
		head.setText("Contact Us");
		back = (LinearLayout) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// ///
		txt_city = (TextView) findViewById(R.id.city_name);
		txt_add = (TextView) findViewById(R.id.city_add);
		spinner_city = (Spinner) findViewById(R.id.spinner_city);
		spinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {

					case 0:
						txt_city.setText("Select");
						break;
					case 1:
						txt_city.setText("Sacramento");
						txt_add.setText("Sacramento-95820");
						break;
					case 2:
						txt_city.setText("Los Angeles");
						txt_add.setText("Los Angeles-90007");
						break;
					case 3:
						txt_city.setText("San Diego");
						txt_add.setText("San Diego-92103");
						break;
					case 4:
						txt_city.setText("Fullerton");
						txt_add.setText("Fullerton-92831");
						break;
					case 5:
						txt_city.setText("Culver city");
						txt_add.setText("Culver city-90066");
						break;

					default:
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

}
