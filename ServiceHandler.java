package com.nishamadhulika.enemsml2.nishamadhulika;


import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EnemSML2 on 20-05-2016.
 */
public class ServiceHandler {

   String url;
    String[] params;
    public ServiceHandler() {
    }
    public ServiceHandler(String url,String params[]) {
        this.url = url;
        this.params = params;
    }

    public static String makeGetRequest(String url) {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        // replace with your url

        HttpResponse response;
        try {
            response = client.execute(request);

            Log.d("Response of GET request", response.toString());
            String responseString = new BasicResponseHandler().handleResponse(response);
            return responseString;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    private void makePostRequest(String url) {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(url);


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
//        nameValuePair.add(new BasicNameValuePair("username", "test_user"));
//        nameValuePair.add(new BasicNameValuePair("password", "123456789"));


        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

    }
}
