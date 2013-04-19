package com.mehuljoisar.skypecalldemo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Context mContext;
	private EditText etRecepient;
	private Button btnSkypeCall;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}

	private void initialize() {
		mContext=this;
		etRecepient = (EditText)findViewById(R.id.etRecepient);
		btnSkypeCall = (Button)findViewById(R.id.btnSkypeCall);
		btnSkypeCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				skypeCall(etRecepient.getText().toString(), mContext);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private static void goToMarket(Context ctx) {
		// TODO Auto-generated method stub
		  Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=com.skype.raider");
		  Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
		  myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  ctx.startActivity(myIntent);
		  
		  return;
	}

	private static boolean isSkypeClientInstalled(Context ctx) {
		PackageManager myPackageMgr = ctx.getPackageManager();
		  try {
		    myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
		  }
		  catch (PackageManager.NameNotFoundException e) {
		    return (false);
		  }
		  return (true);
	}
	

	
	private static void skypeCall(String name, Context ctx) {
        try {
    		if (!isSkypeClientInstalled(ctx)) {
        	    goToMarket(ctx);
        	    return;
        	  }
    		
/*    		Intent skype_intent = new Intent("android.intent.action.CALL_PRIVILEGED");
            skype_intent.setClassName("com.skype.raider","com.skype.raider.Main");
            skype_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            skype_intent.setData(uri); 
*/            
            Intent skype_intent = new Intent("android.intent.action.VIEW");
    		Uri uri=Uri.parse("skype:"+name);
            skype_intent.setData(uri);
            ctx.startActivity(skype_intent);
        } catch (ActivityNotFoundException e) {
            Log.e("SKYPE CALL", "Skype failed", e);
        }

    }

}
