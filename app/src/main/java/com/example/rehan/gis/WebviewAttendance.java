package com.example.rehan.gis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

public class WebviewAttendance extends AppCompatActivity {
//TextView tx1,tx2,tx3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_attendance);
       // tx1=(TextView)findViewById(R.id.textView4) ;
        //tx2=(TextView)findViewById(R.id.textView5) ;
        //tx3=(TextView)findViewById(R.id.textView6) ;
        Bundle bundle = getIntent().getExtras();
        //String st2 = bundle.getString("stuff3");
        //String st1 = bundle.getString("stuff2");
//Extract the data…
        String st = bundle.getString("stuff");
        //tx1.setText(st2);
        //tx2.setText(st1);
        //tx3.setText(st2);
        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl(st);

    }
}
