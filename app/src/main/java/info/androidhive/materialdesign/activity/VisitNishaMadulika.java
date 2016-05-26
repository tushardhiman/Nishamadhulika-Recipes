package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.CustomAdapter;

/**
 * Created by Dell on 5/19/2016.
 */
public class VisitNishaMadulika extends Fragment {



    public VisitNishaMadulika() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visit_nishamadulika, container, false);
         WebView learn2crack = (WebView)rootView.findViewById(R.id.webview);
        WebSettings webSettings=learn2crack.getSettings();
        webSettings.setJavaScriptEnabled(true);

        learn2crack.loadUrl("http://www.nishamadulika.com");



//        WebView myWebView = (WebView)rootView.findViewById(R.id.webview);
//        myWebView.loadUrl("nishamadhulika.com");


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
