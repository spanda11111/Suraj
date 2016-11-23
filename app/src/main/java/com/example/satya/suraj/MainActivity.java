package com.example.satya.suraj;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    EditText et1;
    Button b1;
    WebView wv;
    String str1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        b1 = (Button) findViewById(R.id.b1);
        wv = (WebView) findViewById(R.id.wv);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str1 = et1.getText().toString();
                ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo= manager.getActiveNetworkInfo();
                if (networkInfo== null || networkInfo.isConnected() == false)
                {
                    wv.loadData("<h1>No Internet... Check Connection!!</h1>","text/html",null);
                    return;
                }
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setWebViewClient(new WebViewClient());
                wv.loadUrl("https://www.youtube.com/results?search_query="+str1);
                wv.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if(url.equals("https://www.youtube.com/results?search_query="+str1))
                        {
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            Toast.makeText(MainActivity.this, url,Toast.LENGTH_LONG).show();
                            int index =url.indexOf("=");
                            index++;
                            String myurl = url.substring(index);
                            intent.putExtra("key1",myurl);
                            startActivity(intent);
                        }

                    }
                });
            }
        });
    }
}
