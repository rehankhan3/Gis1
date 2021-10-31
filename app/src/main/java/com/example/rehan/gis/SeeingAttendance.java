package com.example.rehan.gis;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

public class SeeingAttendance extends Fragment implements OnItemSelectedListener{
    View myview;



     TextView total_txt, absent_txt, present_txt,class_txt,display_txt;
    long total_strength;
    long total_abst;
    long total_pre;
   Boolean sendmsg= false;
   Button b1,b2,b3;
    String mob_list="",name_list="",final_mob="",sst="",sstm="";
    Context context;
    String[] foody;
    String ittems="";
    public String selected_class="";
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> fri = new ArrayList<String>();
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog pDialog;
    ArrayList<String> abst_ids = new ArrayList<String>();
    ArrayList<String> abst_lists = new ArrayList<String>();
    ArrayList<String> abst_ids_name = new ArrayList<String>();
    ListView lv1;
    //ArrayAdapter arrayAdapter;

    // private Firebase fbs;
    //String ssp="9c2";
    //TextView txt,txt1;
    //ListView lv1;
    DatabaseReference dbr= FirebaseDatabase.getInstance().getReference();
    Calendar c = Calendar.getInstance();
    DatabaseReference intcheck= FirebaseDatabase.getInstance().getReference(".info/connected");
    //DatabaseReference mdf= dbr.child("student_detail");
    //DatabaseReference ccf= mdf.child("second");
    //.child("9thA").child("student_detail").child("9c1").child("mob_1");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.seeing_attendance,container,false);
        pDialog=new ProgressDialog(getActivity());

        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner);
        class_txt= (TextView) myview.findViewById(R.id.txt_class); //txt5
        total_txt= (TextView) myview.findViewById(R.id.txt_total_strength); //txt6
        absent_txt= (TextView) myview.findViewById(R.id.txt_absent);  //txt7
        present_txt= (TextView) myview.findViewById(R.id.txt_present);  //txt8
        display_txt= (TextView) myview.findViewById(R.id.textView19);  //txt8
        lv1 = (ListView) myview.findViewById(R.id.lv1);


        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,friends);
        lv1.setAdapter(arrayAdapter);
        lv1.setItemsCanFocus(false);
        // we want multiple clicks
       // lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
       // spinner.OnItemSelectedListener(this);
        progressDialog = new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        int versionCode = BuildConfig.VERSION_CODE;
        String vcode= Integer.toString(versionCode);
        String versionName = BuildConfig.VERSION_NAME;
        int verfire= Integer.parseInt(MainActivity.appver);
       // txt5.setText(vcode);
        //txt6.setText(MainActivity.appver);





        /*   floating button disabled from here


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
        }); floating button is disabled up to here */


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select class");
        categories.add("NUR");
        categories.add("KG1A");
        categories.add("KG1B");
        categories.add("KG2A");
        categories.add("KG2B");
        categories.add("1A");
        categories.add("1B");
        categories.add("1C");
        categories.add("2A");
        categories.add("2B");
        categories.add("2C");
        categories.add("3A");
        categories.add("3B");
        categories.add("3C");
        categories.add("4A");
        categories.add("4B");
        categories.add("4C");
        categories.add("5A");
        categories.add("5B");
        categories.add("5C");
        categories.add("6A");
        categories.add("6B");
        categories.add("7A");
        categories.add("7B");
        categories.add("8A");
        categories.add("8B");
        categories.add("8C");
        categories.add("9A");
        categories.add("9B");
        categories.add("9C");
        categories.add("10A");
        categories.add("10B");
        categories.add("10C");
        categories.add("11A");
        categories.add("11B");
        categories.add("11C");
        categories.add("12A");
        categories.add("12B");
        categories.add("12C");;



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);













        //context = this;
        //  Firebase.setAndroiRContext(this);
        //txt= (TextView) myview.findViewById(R.id.textView2);
        //txt1= (TextView) myview.findViewById(R.id.textView);
        //lv1 = (ListView) myview.findViewById(R.id.listview1);

        //String[] foody = {"pizza", "burger", "chocolate", "ice-cream", "banana", "apple"};
        // set adapter for listview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mysecond,R.id.txn_1, foody);
       // final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.mysecond,R.id.txn_1,friends);
        //lv1.setAdapter(arrayAdapter);
        //lv1.setItemsCanFocus(false);
        // we want multiple clicks
       // lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


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
        //b1=(Button) myview.findViewById(R.id.button6);
       // b2=(Button) myview.findViewById(R.id.button);
       // b3=(Button) myview.findViewById(R.id.button4);

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




        return myview;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String item = parent.getItemAtPosition(position).toString();
        //        Firebase nchild= fbs.child("Address");
        //     nchild.setValue("indore");
        //Log.i("dd","");
        //sst = dbr.child("classes").child("9thA").child("student_detail").orderByChild("Name");
        //final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.mysecond,R.id.txn_1,friends);

        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,friends);
        lv1.setAdapter(arrayAdapter);
        lv1.setItemsCanFocus(false);
        // we want multiple clicks
       // lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        selected_class= item;
        // Showing selected spinner item
        if(item.equals("Select class")) {
            lv1.setVisibility(View.INVISIBLE);
            class_txt.setText("");
            total_txt.setText("");
            absent_txt.setText("");
            present_txt.setText("");
            //Toast.makeText(parent.getContext(), "NO ITEM IS SELECTED", Toast.LENGTH_LONG).show();



        }
        else {
            intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean connected = dataSnapshot.getValue(Boolean.class);
                    if (connected) {
                        pDialog.setMessage("Please Wait");
                        pDialog.setCancelable(false);
                        pDialog.show();
                        class_txt.setText(item);
                        lv1.setVisibility(View.VISIBLE);
                        abst_lists.clear();
                        sst="";
                        sstm="";
                        //abst_lists.clear();

                        Query questrength= dbr.child(MainActivity.session).child("class").child(selected_class).child("student_detail");
                        questrength.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                friends.clear();
                                //fri.clear();
                                lv1.setAdapter(null);
                                total_strength=dataSnapshot.getChildrenCount();
                                //class_txt.setVisibility(View.INVISIBLE);

                               // total_txt.setVisibility(View.VISIBLE);
                                //absent_txt.setVisibility(View.INVISIBLE);
                                //present_txt.setVisibility(View.INVISIBLE);
                                total_txt.setText(Long.toString(total_strength));

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



                        Query quablist=dbr.child(MainActivity.session);
                        quablist.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                pDialog.dismiss();
                                friends.clear();
                               // fri.clear();
                                lv1.setAdapter(null);
                                abst_lists.clear();
                                sst="";
                                abst_ids_name.clear();
                                sstm="";

                                if(dataSnapshot.child("Attendance").hasChild(MainActivity.currentdate))
                                {
                                    class_txt.setVisibility(View.VISIBLE);

                                    total_txt.setVisibility(View.VISIBLE);
                                    absent_txt.setVisibility(View.VISIBLE);
                                    present_txt.setVisibility(View.VISIBLE);

                                    // int i=0;
                                    //if(dataSnapshot.child(MainActivity.currentdate).hasChild(class_list[i]))
                                    //for(DataSnapshot cchild: dataSnapshot.child(MainActivity.currentdate).getChildren()) {
                                    if (dataSnapshot.child("Attendance").child(MainActivity.currentdate).hasChild(selected_class)) {
                                        class_txt.setVisibility(View.VISIBLE);

                                        total_txt.setVisibility(View.VISIBLE);
                                        absent_txt.setVisibility(View.VISIBLE);
                                        present_txt.setVisibility(View.VISIBLE);
                                        if (dataSnapshot.child("Attendance").child(MainActivity.currentdate).child(selected_class).hasChild("Abs_ids")) {
                                            display_txt.setVisibility(View.VISIBLE);
                                            display_txt.setText("List of absentees are :");


                                            for (int j = 1; j <= (dataSnapshot.child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").getChildrenCount()); j++) {

                                                abst_lists.add(dataSnapshot.child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").child("abs" + j).getValue().toString());
                                                //abst_lists.add("hi"+j);

                                            }
                                        }else{
                                            lv1.setVisibility(View.INVISIBLE);
                                            display_txt.setVisibility(View.INVISIBLE);
                                            //txt6.setText("ALL PRESENT IN CLASS");
                                            //Toast.makeText(getActivity(),"ALL PRESENT IN CLASS: "+selected_class,Toast.LENGTH_LONG).show();
                                        }

                                    }else{
                                        lv1.setVisibility(View.INVISIBLE);
                                         class_txt.setVisibility(View.INVISIBLE);

                                        total_txt.setVisibility(View.INVISIBLE);
                                        absent_txt.setVisibility(View.INVISIBLE);
                                        present_txt.setVisibility(View.INVISIBLE);
                                        display_txt.setVisibility(View.VISIBLE);
                                        display_txt.setText("ATTENDANCE NOT TAKEN FOR THIS CLASS");
                                        display_txt.setHighlightColor(Color.RED);

                                        //txt6.setText("ATTENDANCE NOT TAKEN BY CALSS ");
                                        //Toast.makeText(getActivity(),"ATTENDANCE NOT TAKEN BY CLASS: "+selected_class,Toast.LENGTH_LONG).show();

                                    }
                                    // }
                                }else{
                                    lv1.setVisibility(View.INVISIBLE);
                                    class_txt.setVisibility(View.INVISIBLE);

                                    total_txt.setVisibility(View.INVISIBLE);
                                    absent_txt.setVisibility(View.INVISIBLE);
                                    present_txt.setVisibility(View.INVISIBLE);
                                    display_txt.setText("NO CLASS HAS TAKEN THE ATTENDANCE TODAY");
                                    display_txt.setHighlightColor(Color.RED);

                                    //txt6.setText("NO CLASS HAS TAKEN ATTENDANCE");
                                    // Toast.makeText(getActivity(),"NO class has taken ATTENDANCE FOR TODAY",Toast.LENGTH_LONG).show();

                                }
                                //progressDialog.dismiss();

                                for(String ggs: abst_lists)
                                {
                                    //  String temp =(abst_lists[x]);
                                    sst += ggs+"\n";

                                }
                                total_abst=abst_lists.size();
                                // txt5.setText(sst);
                                //class_txt.setVisibility(View.INVISIBLE);

                                //total_txt.setVisibility(View.INVISIBLE);
                                //absent_txt.setVisibility(View.VISIBLE);
                               // present_txt.setVisibility(View.INVISIBLE);
                                absent_txt.setText(Long.toString(total_abst));
                                // txt9.setText(Long.toString(total_strength-total_abst));



                                //txt1.setText("");
                                //int i=1;
                                //while(dataSnapshot.hasChild("9b"+i)) {
                                int h=1;
                                for(String ggs: abst_lists)
                                {
                                    //if(dataSnapshot.child("9b"+i).getValue().equals(ggs)){
                                    String abname=dataSnapshot.child("class").child(selected_class).child("student_detail").child(ggs).child("Name").getValue().toString();

                                    abst_ids_name.add(abname+":"+ggs);
                                    friends.add(h+") "+abname+":"+ggs);
                                    //lv1.setAdapter(adapter);
                                    lv1.setAdapter(arrayAdapter);
                                    h++;

                                    //}

                                }





                                //String ssoo="";
                                for(String ggsi: abst_ids_name)
                                {
                                    //  String temp =(abst_lists[x]);
                                    sstm += ggsi+"\n";

                                }
                                //txt5.setText(sstm);
                                present_txt.setText(Long.toString(total_strength-total_abst));


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                            /*
                Query abstname= dbr.child("classes").child(selected_class).child("student_detail");
                ValueEventListener ddtt= abstname.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        abst_ids_name.clear();
                        sstm="";
                        //txt1.setText("");
                        //int i=1;
                        //while(dataSnapshot.hasChild("9b"+i)) {
                        for(String ggs: abst_lists)
                        {
                            //if(dataSnapshot.child("9b"+i).getValue().equals(ggs)){
                            String abname=dataSnapshot.child(ggs).child("Name").getValue().toString();

                            abst_ids_name.add(abname+":"+ggs);

                            //}

                        }





                        //String ssoo="";
                        for(String ggsi: abst_ids_name)
                        {
                            //  String temp =(abst_lists[x]);
                            sstm += ggsi+"\n";

                        }
                       // txt9.setText(sstm);
                        //total_abst=abst_lists.size();
                        //total_pre=total_strength-total_abst;
                        //txt6.setText(Long.toString(total_abst));
                        //txt5.setText(Long.toString(total_pre));


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                dbr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        txt5.setText(sstm);
                        total_abst=abst_lists.size();
                        total_pre=total_strength-total_abst;
                        //txt6.setText(Long.toString(total_abst));
                        txt9.setText(Long.toString(total_pre));


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                            */



                        // AsyncTaskRunner runner = new AsyncTaskRunner();
                        //String sleepTime = "3";
                        //runner.execute(sleepTime);



                    }else{
                        lv1.setVisibility(View.INVISIBLE);
                        class_txt.setVisibility(View.INVISIBLE);

                        total_txt.setVisibility(View.INVISIBLE);
                        absent_txt.setVisibility(View.INVISIBLE);
                        present_txt.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        alertDialogBuilder.setMessage("No internet connectivity ");
                        //  alertDialogBuilder.setPositiveButton("yes",
                        //        new DialogInterface.OnClickListener() {
                        //          @Override
                        //        public void onClick(DialogInterface arg0, int arg1) {
                        //          Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                        //    }
                        //});

                        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //getActivity().finish();  // this code will close the app
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        progressDialog.dismiss();
                        alertDialog.show();

                       // Toast.makeText(getActivity(),"NOT CONNECTED IN SEENG ATTENDANCE", Toast.LENGTH_LONG);


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("I am inside firebase error ");
                    //  alertDialogBuilder.setPositiveButton("yes",
                    //        new DialogInterface.OnClickListener() {
                    //          @Override
                    //        public void onClick(DialogInterface arg0, int arg1) {
                    //          Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                    //    }
                    //});

                    alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //getActivity().finish();  // this code will close the app
                            dialog.cancel();
                            //firebaseAuth.signOut();
                            //closing activity
                            //finish();
                            //starting login activity
                            //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    progressDialog.dismiss();
                    alertDialog.show();

                }
            });



        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public  void onStart() {
        int versionCode = BuildConfig.VERSION_CODE;
        String vcode= Integer.toString(versionCode);
        String versionName = BuildConfig.VERSION_NAME;
        int verfire= Integer.parseInt(MainActivity.appver);

        if(versionCode==verfire)
        {
           // Toast.makeText(getActivity(),"right version",Toast.LENGTH_LONG).show();


            intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean connected = dataSnapshot.getValue(Boolean.class);
                    if (connected) {

                    /*

                    // System.out.println("connected");
                    //Toast.makeText(getActivity(), "CONNECTED in attendance", Toast.LENGTH_SHORT).show();


                    progressDialog.setMessage("Fetching current date...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    abst_lists.clear();
                    sst="";
                    dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
                    //Date myDate = new Date();
                    Query qu = dbr.child("todaytimestamp");
                    ValueEventListener date = qu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // sspp=(dataSnapshot.child(item+i+"/Name").getValue().toString());
                            String str2;
                            str2 = dataSnapshot.child("timestamp").getValue().toString();
                            long t = Long.parseLong(str2.substring(0, str2.length() - 3));
                            Date myDate = new Date(t * 1000);
                            //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            //SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
                            String str3 = (myDate.toString()).substring(4,myDate.toString().length()-24);// for date only
                            //String str3 = (myDate.toString()).substring(4,myDate.toString().length()-14); // for date and time
                            String str4= (myDate.toString()).substring(myDate.toString().length()-5); // for year
                            MainActivity.currentdate= str3.concat(str4);

                            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                            //Date date = formatter.parse(str3);

                            //sfd.format(myDate);
                            //txt1.setText((myDate.toString()));
                            // txt1.setText(str5);
                            // txt1.setText(MainActivity.session);
                            //txt1.setText(formatter.format(myDate));
                            dbr.child(MainActivity.session).child("Attendance").child("rough").setValue(ServerValue.TIMESTAMP);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    */



                    } else {
                        //System.out.println("not connected");
                        //Toast.makeText(getActivity(), "NOT CONNECTED in attendance main", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else
        {
            Toast.makeText(getActivity(),"YOU ARE USING OLD VERSION\nPlease update your version",Toast.LENGTH_LONG).show();
            getActivity().finish();

            //and open profile activity
            startActivity(new Intent(getActivity().getApplicationContext(), ProfileActivity.class));
        }




        //txt.setText(myDate.toString());
        //startActivity(new Intent(getApplicationContext(), TextInputPart.class));
        //dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
        //Date myDate = new Date();
        //txt1.setText(myDate.toString());
        //dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
     super.onStart();



     }

}
