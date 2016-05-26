package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.os.Bundle;

public class FullScreen extends Activity {
	
		HTML5WebView m2WebView;
		String mime = "text/html";
		String encoding = "utf-8";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 m2WebView = new HTML5WebView(this);
		 m2WebView.setInitialScale(200);
		 if (savedInstanceState != null) {
             m2WebView.restoreState(savedInstanceState);
     } else {
    	 	Bundle b1 = new Bundle();
    	 	b1 = getIntent().getExtras();
            String html = b1.getString("html");                     
            setContentView(m2WebView.getLayout());
       		m2WebView.loadDataWithBaseURL(null, html, mime, encoding, null);
       		
     }     
   
 
	}
	 @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        m2WebView.saveState(outState);
	    }
	    
	    @Override
	    public void onStop() {
	        super.onStop();
	        m2WebView.stopLoading();
	    }

	    @Override
	    public void onBackPressed() {
	    	// TODO Auto-generated method stub
	    	finish();
	    }
	
	
}
