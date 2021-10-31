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

public class SeeMessages extends Fragment implements OnItemSelectedListener{
    View myview;



     TextView txt5,txt6;
   Boolean sendmsg= false;
   Button b1,b2,b3;
    String mob_list="",name_list="",final_mob="",sst="";
    Context context;
    String[] foody;
    String ittems="";
    public String selected_class="";
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> fri = new ArrayList<String>();
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    ArrayList<String> abst_ids = new ArrayList<String>();
    ArrayList<String> abst_lists = new ArrayList<String>();
    //ArrayAdapter arrayAdapter;

    // private Firebase fbs;
    //String ssp="9c2";
    //TextView txt,txt1;
    ListView lv1;
    DatabaseReference dbr= FirebaseDatabase.getInstance().getReference();
    Calendar c = Calendar.getInstance();
    DatabaseReference intcheck= FirebaseDatabase.getInstance().getReference(".info/connected");
    //DatabaseReference mdf= dbr.child("student_detail");
    //DatabaseReference ccf= mdf.child("second");
    //.child("9thA").child("student_detail").child("9c1").child("mob_1");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.see_messages,container,false);

        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner);
       // txt5= (TextView) myview.findViewById(R.id.textView5);
        //txt6= (TextView) myview.findViewById(R.id.textView6);


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
        categories.add("Please select");
        categories.add("Show all messages");




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
        //b3=(Button) myview.findViewById(R.id.button4);
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

        /*
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendmsg=true;
                mob_list="";
                name_list="";
                abst_ids.clear();
                for(String ite: fri ){
                    //String nms = ite.substring(ite.length() - 10);
                    String nms = ite;
                    //String pqr= ite.substring(0,ite.length()-17);  //  name
                    //String abidds= ite.substring(ite.length()-14,ite.length()-11); // roll no
                    abst_ids.add(nms); // for storing abst ids to be stored in firebase
                    mob_list +=nms+"\n";
                    //name_list +=pqr+"\n";
                }
                //if(mob_list.length()==0)
                //     final_mob="";
                // else
                //   final_mob=mob_list.substring(0,mob_list.length()-1);
                // if(name_list=="") {
                //sendmsg = false;  // to maintain flow that first select items then press final submission
                //}

                Toast.makeText(getActivity(),"SELECTED messages ARE:\n"+mob_list,Toast.LENGTH_LONG).show();
                //b3.setText(MainActivity.appver);
                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //getActivity().startActivity(intent);





            }
        });
        */
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean connected = dataSnapshot.getValue(Boolean.class);
                        if (connected) {
                            Query deltxt= dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate);
                            deltxt.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot cchild: dataSnapshot.getChildren()){
                                        for(DataSnapshot dchild: cchild.getChildren()){
                                            for(DataSnapshot echild: dchild.getChildren()){

                                                for(String iisst: fri){
                                                    if(echild.child("Text").getValue().toString()==iisst){
                                                        echild.getRef().removeValue();
                                                    }


                                                }



                                            }
                                        }
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });




                        }else{
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setMessage("NOT CONNECTED IN ATT BUTT");
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


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




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

        selected_class= item;
        // Showing selected spinner item
        if(item.equals("Please select")) {
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
            intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean connected = dataSnapshot.getValue(Boolean.class);
                    if (connected) {
                        lv1.setVisibility(View.VISIBLE);
                        mob_list="";
                        name_list="";
                        sendmsg=false;
                        //Toast.makeText(parent.getContext(), "SELECTED : " + item, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(),"YOUR rehan zzzz MESSAGE",Toast.LENGTH_LONG).show();
                        Query query = dbr.child(MainActivity.session).child("Homework");
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String tempch;
                                ittems="";
                                friends.clear();
                                fri.clear();
                                lv1.setAdapter(null);
                                for(DataSnapshot cchild: dataSnapshot.child(MainActivity.currentdate).getChildren()){
                                    for(DataSnapshot dchild: cchild.getChildren()){
                                        //tempch=dchild.getKey().toString();
                                        for(DataSnapshot echild: dchild.getChildren()){
                                            String txtval= echild.child("Text").getValue().toString();
                                            //String emailval= echild.child("Teacheremail").getValue().toString();
                                            //String classval= cchild.getKey().toString();
                                            friends.add(txtval);
                                            //lv1.setAdapter(adapter);
                                            lv1.setAdapter(arrayAdapter);


                                        }





                                    }

                                }



                                //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                                //      friends);
                                //dbr.setValue("right");
                                //dbr.setValue(c.getTime().toString());
                                //dbr.setValue(dbr.ServerValue.TIMESTAMP);
                                //var createdDate = new Firebase('https://somedomain.firebaseIO.com/post/createDate');
                                //createdDate.set(Firebase.ServerValue.TIMESTAMP);
                                // friends.add(player.child("9c3/Name").getValue().toString());
                                // Boolean bn=true;




                                 /*

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

                                */





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

                    } else {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        alertDialogBuilder.setMessage("NOT CONNECTED IN att selection ");
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
                        //System.out.println("not connected");
                    /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Connectivity is lost pleas login again");
                    //  alertDialogBuilder.setPositiveButton("yes",
                    //        new DialogInterface.OnClickListener() {
                    //          @Override
                    //        public void onClick(DialogInterface arg0, int arg1) {
                    //          Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                    //    }
                    //});

                    alertDialogBuilder.setNegativeButton("LOGIN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //getActivity().finish();  // this code will close the app
                            firebaseAuth.signOut();
                            //closing activity
                            finish();
                            //starting login activity
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));

                            //dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    progressDialog.dismiss();
                    alertDialog.show(); 8*/


                        //Toast.makeText(MainActivity.this,"NOT CONNECTED in main",Toast.LENGTH_SHORT).show();
                    }
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
        int versionCode = BuildConfig.VERSION_CODE;
        String vcode= Integer.toString(versionCode);
        String versionName = BuildConfig.VERSION_NAME;
        int verfire= Integer.parseInt(MainActivity.appver);

        if(versionCode==verfire)
        {
           //Toast.makeText(getActivity(),"right version",Toast.LENGTH_LONG).show();


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
                       //Toast.makeText(getActivity(), "NOT CONNECTED in attendance button", Toast.LENGTH_SHORT).show();
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
