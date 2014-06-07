package com.matsol.android.apps.dialerapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matsol.android.apps.dialerapp.R;

public class MainActivity extends Activity implements View.OnClickListener {
	private Button buttons[] = new Button[13];

	private final static int BUTTON_IDS[] = { R.id.button0, R.id.button1,
			R.id.button2, R.id.button3, R.id.button4, R.id.button5,
			R.id.button6, R.id.button7, R.id.button8, R.id.button9,
			R.id.button0, R.id.button_dot, R.id.button_hash };
	// btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
	private Button btnCall, btnDelete;
	private EditText mainLCD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		init();
	}

	private void init() {
		
		btnDelete = (Button) findViewById(R.id.button_del);
		btnDelete.setOnClickListener(this);
		btnCall = (Button) findViewById(R.id.button_call);
		btnCall.setOnClickListener(this);
		mainLCD = (EditText) findViewById(R.id.txt_main_number);

		int i = 0;
		for (int id : BUTTON_IDS) {
			final int t = i;
			buttons[i] = (Button) findViewById(id);
			buttons[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					mainLCD.append(buttons[t].getText().toString());
				}
			});
			i++;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_del:
			if (mainLCD.getText().toString().length() > 0) {
				String str = mainLCD.getText().toString();
				str = str.substring(0, str.length() - 1);
				mainLCD.setText(str);
				mainLCD.setSelection(mainLCD.getText().length());
			}
			break;

		case R.id.button_call:
			if (mainLCD.getText().length() > 0) {
				performDial(mainLCD.getText().toString());
			} else {
				Toast.makeText(MainActivity.this, "Please enter a number",
						Toast.LENGTH_LONG).show();
			}
			break;
		}

	}

	private void performDial(String numberString) {
		if (!numberString.equals("")) {
			Uri number = Uri.parse("tel:" + numberString);
			Intent dial = new Intent(Intent.ACTION_CALL, number);
			startActivity(dial);
		}
	}
}
