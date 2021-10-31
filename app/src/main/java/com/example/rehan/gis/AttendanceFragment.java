package com.example.rehan.gis;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;



import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import com.firebase.client.Firebase;
//import com.firebase.client.core.view.View;

/**
 * Created by rehan on 26-Dec-16.
 */

public class AttendanceFragment extends Fragment implements OnItemSelectedListener{
    View myview;




   Boolean sendmsg= false;
   Button b1,b2,b3;
    String mob_list="",name_list="",final_mob="";
    Context context;
    String[] foody;
    String ittems="";
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> fri = new ArrayList<String>();
    //ArrayAdapter arrayAdapter;

    // private Firebase fbs;
    //String ssp="9c2";
    //TextView txt,txt1;
    ListView lv1;
    DatabaseReference dbr= FirebaseDatabase.getInstance().getReference();
    Calendar c = Calendar.getInstance();
    //DatabaseReference mdf= dbr.child("student_detail");
    //DatabaseReference ccf= mdf.child("second");
    //.child("9thA").child("student_detail").child("9c1").child("mob_1");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.attendance_layout,container,false);

        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
       // spinner.OnItemSelectedListener(this);



        FloatingActionButton fab = (FloatingActionButton) myview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Toast.makeText(MainActivity.this,"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
                //fragmentManager.beginTransaction().replace(R.id.content_frame, new ShowingWebview()).commit();
                //Toast.makeText(getActivity(),"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), WebviewAttendance.class);
                //Create a bundle object
                Bundle b = new Bundle();

                //Inserts a String value into the mapping of this Bundle

                 if(sendmsg) {
                     Toast.makeText(getActivity(), final_mob, Toast.LENGTH_LONG).show();
                     //String getrec="http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=kapbulk&pass=kapbulk@user!123&senderid=KAPMSG&message=YOUR WARD IS ABSENT TODAY %20Test%20Message&dest_mobileno="+final_mob+"&response=Y";
                     String getrec="https://control.msg91.com/api/sendhttp.php?authkey=135869AXcyquj7of586a8765&mobiles="+final_mob+"&message=your ward is absent today&sender=GISIND&route=4&country=91";
                     b.putString("stuff",getrec);

                     //Add the bundle to the intent.
                     intent.putExtras(b);
                     startActivity(intent);
                 }
                else
                     Toast.makeText(getActivity(),"First select and press SAVE button",Toast.LENGTH_LONG).show();
               // String getrec="https://control.msg91.com/api/sendhttp.php?authkey=135869AXcyquj7of586a8765&mobiles=919893151021&message=your ward is absent today&sender=GISIND&route=4&country=91";

                //b.putString("stuff",getrec);

                //Add the bundle to the intent.
                //intent.putExtras(b);

                //start the DisplayActivity
               // startActivity(intent);
                //startActivity(new Intent(getApplicationContext(),SecondActivity.class) );

                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Please slect anyone");
        categories.add("9c");
        categories.add("9b");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);













        //context = this;
        //  Firebase.setAndroidContext(this);
        //txt= (TextView) myview.findViewById(R.id.textView2);
        //txt1= (TextView) myview.findViewById(R.id.textView);
        lv1 = (ListView) myview.findViewById(R.id.listview1);

        //String[] foody = {"pizza", "burger", "chocolate", "ice-cream", "banana", "apple"};
        // set adapter for listview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mysecond,R.id.txn_1, foody);
        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.mysecond,R.id.txn_1,friends);
        lv1.setAdapter(arrayAdapter);
        lv1.setItemsCanFocus(false);
        // we want multiple clicks
        lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.mysecond,R.id.txn_1,friends);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.mysecond, friends);
        //lv1.setAdapter(arrayAdapter);
        //lv1.setItemsCanFocus(false);
        // we want multiple clicks
        //listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.mysecond,R.id.txn_1,
        //      friends);
        //  fbs= new Firebase("https://myfirsttry-28128.firebaseio.com/");
       // b1=(Button) myview.findViewById(R.id.button2);
        b2=(Button) myview.findViewById(R.id.button);
        //b1.setOnClickListener(new View.OnClickListener() {
          //  @Override
//            public void onClick(View v) {

                /*



                //        Firebase nchild= fbs.child("Address");
                //     nchild.setValue("indore");
                //Log.i("dd","");
                //sst = dbr.child("classes").child("9thA").child("student_detail").orderByChild("Name");
                Toast.makeText(getActivity(),"YOUR rehan zzzz MESSAGE",Toast.LENGTH_LONG).show();
                Query query = dbr.child("classes/9cA/student_detail");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ittems="";
                        friends.clear();
                        fri.clear();
                        lv1.setAdapter(null);



                        //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                        //      friends);
                        //dbr.setValue("right");
                        //dbr.setValue(c.getTime().toString());
                        //dbr.setValue(dbr.ServerValue.TIMESTAMP);
                        //var createdDate = new Firebase('https://somedomain.firebaseIO.com/post/createDate');
                        //createdDate.set(Firebase.ServerValue.TIMESTAMP);
                        // friends.add(player.child("9c3/Name").getValue().toString());
                        // Boolean bn=true;



                        String sspp,ssqp;
                        String sx= "rakes  \n:m9p8";
                        int lenght = sx.length();
                        String numbers = sx.substring(sx.length() - 3);
                        //txt.setText(numbers);
                        //bn= sspp.equalsIgnoreCase("");
                        //txt.setText(bn.toString());

                        int i=1;
                        while(dataSnapshot.hasChild("9c"+i)) {
                            sspp=(dataSnapshot.child("9c"+i+"/Name").getValue().toString());
                            ssqp= (dataSnapshot.child("9c"+i+"/mob_1").getValue().toString());
                            if(sspp.equalsIgnoreCase("")||(ssqp.equalsIgnoreCase("")))
                            {
                                i++;
                                continue;
                            }
                            else{
                                friends.add(sspp+" : "+" 9c"+i+"\n"+ssqp);
                                //lv1.setAdapter(adapter);
                                lv1.setAdapter(arrayAdapter);

                                //friends.add(dataSnapshot.child("9c" + i + "/Name").getValue().toString());
                                // lv1.setAdapter(arrayAdapter);

                            }
                            i++;
                        }





                        // arrayAdapter.notifyDataSetChanged();
                        // for (DataSnapshot player : dataSnapshot.getChildren()) {
                        //Log.i("player", player.getKey());
                        //friends.add(player.getKey());
                        // Log.i("player", player.getValue().toString());
                        //friends.add(player.getValue().toString());
                        //}
                        //lv1.setAdapter(arrayAdapter);

                        //String sst= dataSnapshot.getValue(String.class);
                        //txt.setText(sst);
                        //Log.i("dd","");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                */


       //     }
     //   });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendmsg=false;
                String selecteditems=((TextView)view).getText().toString();
                if(fri.contains(selecteditems)){
                    fri.remove(selecteditems);
                }
                else{
                    fri.add(selecteditems);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmsg=true;
                mob_list="";
                name_list="";
                for(String ite: fri ){
                    //String nms = ite.substring(ite.length() - 10);
                    String nms = ite.substring(ite.length()-10);
                    String pqr= ite.substring(0,ite.length()-17);
                    mob_list +=nms+",";
                    name_list +=pqr+"\n";
                }
                if(mob_list.length()==0)
                    final_mob="";
                else
                    final_mob=mob_list.substring(0,mob_list.length()-1);
                if(name_list=="")
                    sendmsg=false;

                Toast.makeText(getActivity(),name_list,Toast.LENGTH_LONG).show();


            }
        });

        return myview;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String item = parent.getItemAtPosition(position).toString();
        //        Firebase nchild= fbs.child("Address");
        //     nchild.setValue("indore");
        //Log.i("dd","");
        //sst = dbr.child("classes").child("9thA").child("student_detail").orderByChild("Name");
        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.mysecond,R.id.txn_1,friends);
        lv1.setAdapter(arrayAdapter);
        lv1.setItemsCanFocus(false);
        // we want multiple clicks
        lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        // Showing selected spinner item
        if(item=="Please slect anyone") {
            //Toast.makeText(parent.getContext(), "NO ITEM IS SELECTED", Toast.LENGTH_LONG).show();
            lv1.setVisibility(View.INVISIBLE);
            ittems="";
            mob_list="";
            name_list="";
            sendmsg=false;
            friends.clear();
            fri.clear();
            lv1.setAdapter(null);


        }
        else {
            lv1.setVisibility(View.VISIBLE);
            mob_list="";
            name_list="";
            sendmsg=false;
            //Toast.makeText(parent.getContext(), "SELECTED : " + item, Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(),"YOUR rehan zzzz MESSAGE",Toast.LENGTH_LONG).show();
            Query query = dbr.child("classes/"+item+"/student_detail");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ittems="";
                    friends.clear();
                    fri.clear();
                    lv1.setAdapter(null);



                    //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                    //      friends);
                    //dbr.setValue("right");
                    //dbr.setValue(c.getTime().toString());
                    //dbr.setValue(dbr.ServerValue.TIMESTAMP);
                    //var createdDate = new Firebase('https://somedomain.firebaseIO.com/post/createDate');
                    //createdDate.set(Firebase.ServerValue.TIMESTAMP);
                    // friends.add(player.child("9c3/Name").getValue().toString());
                    // Boolean bn=true;



                    String sspp,ssqp;
                    String sx= "rakes  \n:m9p8";
                    int lenght = sx.length();
                    String numbers = sx.substring(sx.length() - 3);
                    //txt.setText(numbers);
                    //bn= sspp.equalsIgnoreCase("");
                    //txt.setText(bn.toString());

                    int i=1;
                    while(dataSnapshot.hasChild(item+i)) {

                        sspp=(dataSnapshot.child(item+i+"/Name").getValue().toString());
                        ssqp= (dataSnapshot.child(item+i+"/mob_1").getValue().toString());
                        if(sspp.equalsIgnoreCase("")||(ssqp.equalsIgnoreCase("")))
                        {
                            i++;
                            continue;
                        }
                        else{
                            friends.add(sspp+" : "+" "+item+i+"\n"+ssqp);
                            //lv1.setAdapter(adapter);
                            lv1.setAdapter(arrayAdapter);

                            //friends.add(dataSnapshot.child("9c" + i + "/Name").getValue().toString());
                            // lv1.setAdapter(arrayAdapter);

                        }
                        i++;
                    }





                    // arrayAdapter.notifyDataSetChanged();
                    // for (DataSnapshot player : dataSnapshot.getChildren()) {
                    //Log.i("player", player.getKey());
                    //friends.add(player.getKey());
                    // Log.i("player", player.getValue().toString());
                    //friends.add(player.getValue().toString());
                    //}
                    //lv1.setAdapter(arrayAdapter);

                    //String sst= dataSnapshot.getValue(String.class);
                    //txt.setText(sst);
                    //Log.i("dd","");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public  void onStart() {
        dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
     super.onStart();



     }

}
