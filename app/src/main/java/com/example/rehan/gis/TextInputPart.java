package com.example.rehan.gis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextInputPart extends AppCompatActivity {
TextView txx;
    Button b1;
    EditText ed;
    String st1,st2,st3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_input_layout);
        Bundle bundle = getIntent().getExtras();
        txx=(TextView) findViewById(R.id.textView3);
        b1=(Button) findViewById(R.id.button3);
        ed=(EditText) findViewById(R.id.editText);

//Extract the data…
        st1 = bundle.getString("stuff");
        //txx.setText(st);
        //WebView browser = (WebView) findViewById(R.id.webview);
        //browser.loadUrl(st);

        //Create a bundle object




        //txx.setText(gettingtext);




        //Inserts a String value into the mapping of this Bundle
        //String getrec="http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=kapbulk&pass=kapbulk@user!123&senderid=KAPMSG&message=YOUR WARD IS ABSENT TODAY %20Test%20Message&dest_mobileno="+final_mob+"&response=Y";






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextInputPart.this.getApplicationContext(), SecondActivity.class);
                Bundle bundle1 = new Bundle();
                st2= ed.getText().toString();
                bundle1.putString("stuff2",st1);
                bundle1.putString("stuff3",st2);
                intent.putExtras(bundle1);
                startActivity(intent);
                txx.setText(st2);

            }
        });

    }
}
