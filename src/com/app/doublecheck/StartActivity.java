package com.app.doublecheck;

import com.app.doublecheck.map.MapOverlayActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity {
	private Context context = this;
	private EditText usernameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.start_activity);

		usernameEditText = (EditText)findViewById(R.id.sa_username_edittext);
		
		Button button = (Button)findViewById(R.id.sa_button_login);
		button.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String
				Global.userID = 3;
				Global.userName = "Lory";
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
