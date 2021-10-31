package com.example.rehan.gis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
TextView tx1,tx2,tx3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //tx1=(TextView)findViewById(R.id.textView4) ;
        //tx2=(TextView)findViewById(R.id.textView5) ;
        //tx3=(TextView)findViewById(R.id.textView6) ;
        Bundle bundle = getIntent().getExtras();
        String st2 = bundle.getString("stuff3");
        String st1 = bundle.getString("stuff2");
        //stuff2 is mobile
        // stuff3 is text
//Extract the data…

        //tx1.setText(st2);
        //tx2.setText(st1);
        //tx3.setText(st2);
        //String st="http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=kapbulk&pass=kapbulk@user!123&senderid=KAPMSG&message="+st2+"%20Test%20Message&dest_mobileno="+st1+"&response=Y";
        String st="https://control.msg91.com/api/sendhttp.php?authkey=135869AXcyquj7of586a8765&mobiles="+st1+"&message="+st2+"&sender=GISIND&route=4&country=91";


        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl(st);

    }
}
