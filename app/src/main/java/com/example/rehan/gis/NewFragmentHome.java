package com.example.rehan.gis;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import android.widget.Button;

//import com.firebase.client.Firebase;
//import com.firebase.client.core.view.View;

/**
 * Created by rehan on 26-Dec-16.
 */

public class NewFragmentHome extends Fragment  {
    View myview;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.new_fragment_home, container, false);
        //TextView txt8= (TextView) myview.findViewById(R.id.textView8);
       // WebView wb = (WebView) myview.findViewById(R.id.wv);
        //wb.loadUrl("http://www.gisindore.in/");
        //txt8.setText("HI NAYA NAYA MAIN BANA HE ");

        //return super.onCreateView(inflater, container, savedInstanceState);
        return  myview;
    }
}

