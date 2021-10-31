package com.example.rehan.gis;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import android.widget.Button;

//import com.firebase.client.Firebase;
//import com.firebase.client.core.view.View;

/**
 * Created by rehan on 26-Dec-16.
 */

public class SendingSms extends Fragment {
    View myview;



     TextView txt5,txt6,txt7,txt9;
    long total_strength;
    long total_abst;
    long total_pre;
   Boolean sendmsg= false;
   Button b1,b2,b3,b4;
    String mob_list="",name_list="",final_mob="",sst="",sstm="";
    String tempmsg,tempmob,mobit;
    //public String selected_class="";
    String all_mult="";
    Context context;
    String[] foody;
    String ittems="";
    String mobtemp="";
    public String selected_class="", all_mob_no;
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> fri = new ArrayList<String>();
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    ArrayList<String> abst_ids = new ArrayList<String>();
    ArrayList<String> abst_lists = new ArrayList<String>();
    ArrayList<String> abst_ids_name = new ArrayList<String>();
    ArrayList<String> all_rollno = new ArrayList<String>();
    ArrayList<String> mult_msg = new ArrayList<String>();
    ArrayList<String> all_hw_ids = new ArrayList<String>();
    ArrayList<String> ids_hw = new ArrayList<String>();
    ArrayList<String> ids_hw_mob = new ArrayList<String>();
    private ProgressDialog pDialog;
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
        myview=inflater.inflate(R.layout.sending_sms,container,false);
        pDialog=new ProgressDialog(getActivity());

        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner5);

        List<String> categories = new ArrayList<String>();
        categories.add("Please select");
        categories.add("Send message for the absentees");
        categories.add("Send message for the homework");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        txt5= (TextView) myview.findViewById(R.id.textView5);
        txt6= (TextView) myview.findViewById(R.id.textView6);
        txt7= (TextView) myview.findViewById(R.id.textView7);
        txt9= (TextView) myview.findViewById(R.id.textView9);
       // Spinner spinner = (Spinner) myview.findViewById(R.id.spinner5);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                selected_class= item;
                if(item.equals("Please select")) {
                    sendmsg=false;

                }else if (item.equals("Send message for the absentees")){
                    sendmsg=true;

                    getmob();


                }else if(item.equals("Send message for the homework")){
                    sendmsg=true;

                    loadsms();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         //AsyncTaskRunner runner = new AsyncTaskRunner();
        //String sleepTime = "3";
        //runner.execute(sleepTime);

        //loadsms();
        //getmob();


        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);
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
        //List<String> categories = new ArrayList<String>();
        //categories.add("Select class");
        //categories.add("9c");
        //categories.add("9b");



        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        //spinner.setAdapter(dataAdapter);













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
       // b1=(Button) myview.findViewById(R.id.button6);
        //b2=(Button) myview.findViewById(R.id.button7);
        //b3=(Button) myview.findViewById(R.id.button8);
        b4=(Button) myview.findViewById(R.id.button4);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendmsg){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure you want to continue.");
                      alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                              @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                              //Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                                  if(isInternetOn()){
                                      //categories.add("Send message for the absentees");
                                      //categories.add("Send message for the homework");
                                      if(selected_class.equals("Send message for the absentees")){

                                          new GetMethodDemo().execute(all_mob_no, "Dear parent, Your ward is absent today");



                                      }else if(selected_class.equals("Send message for the homework")){
                                          int i=0;
                                          for (String mobtest: ids_hw_mob) {

                                              new GetMethodDemo().execute(mobtest, "Dear parent, Homework for today is: "+ids_hw.get(i));
                                              i++;

                                          }


                                      }

                                  }else{
                                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                      alertDialogBuilder.setMessage("No internet connectivity");
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
                    });

                    alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //getActivity().finish();  // this code will close the app
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    progressDialog.dismiss();
                    alertDialog.show();




                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("First select and then press button");
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
        });


        /*

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query newsch= dbr.child("class");
                newsch.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String tsch="2111";
                        String hh= dataSnapshot.child("KG1A").child("student_detail").child(tsch).child("mob_1").getValue().toString();
                        txt5.setText(hh);
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
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query hwmsg=dbr.child(MainActivity.session);
                hwmsg.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        all_hw_ids.clear();
                        ids_hw.clear();
                        mult_msg.clear();
                        all_rollno.clear();
                        ids_hw_mob.clear();
                        //toids_hw.clear();
                        all_mult="";
                        mobit="";










                        for(DataSnapshot tchild: dataSnapshot.child("class").getChildren()){

                            for(DataSnapshot uchild: tchild.child("student_detail").getChildren()){

                             all_rollno.add(uchild.getKey().toString());

                            }
                        }
                        String ttrn="";
                        for(String grnn: all_rollno){

                            ttrn+= grnn+", ";
                        }
                       // txt5.setText(ttrn);



                        for(String groll: all_rollno){
                            boolean inside=false;
                           tempmsg="";
                           for(DataSnapshot pchild: dataSnapshot.child("Homework").child(MainActivity.currentdate).getChildren()){
                               for(DataSnapshot qchild: pchild.getChildren()){
                                   for(DataSnapshot rchild: qchild.getChildren()){

                                       for (int j = 1; j <= (rchild.child("To_ids").getChildrenCount()); j++) {

                                           if((rchild.child("To_ids").child("abs" + j).getValue().toString()).equalsIgnoreCase(groll)){
                                               //toids_hw.add(rchild.child("To_ids").child("abs" + j).getValue().toString());
                                               //for(DataSnapshot uchild: dataSnapshot.child("classes").getChildren()){
                                                 //  mobit= uchild.child("student_detail").child(groll).child("mob_1").getValue().toString();
                                               //}
                                               //mobtemp= groll;
                                               inside=true;
                                                String temphw = rchild.child("Text").getValue().toString();
                                                 tempmsg += temphw+": ";

                                           }
                                           //abst_lists.add("hi"+j);

                                       }
                                   }

                               }


                           }


                            //for(DataSnapshot schild : dataSnapshot.child("classes").getChildren()){
                                //for(DataSnapshot tchild: schild.child("student_detail").child(groll))
                              //   tempmob = schild.child("student_detail").child(groll).child("mob_1").getValue().toString();



                            //}

                          //mult_msg.add(mobtemp+": "+tempmsg);
                            if(inside){
                               // mult_msg.add(groll+": "+tempmsg);
                                all_hw_ids.add(groll);
                                ids_hw.add(tempmsg);

                            }
                            //mult_msg.add(mobtemp);
                           // mult_msg.add(groll);

                        }

                        for(DataSnapshot uchild: dataSnapshot.child("class").getChildren()){
                            for(String ppr: all_hw_ids){
                                if(uchild.child("student_detail").hasChild(ppr)){

                                    ids_hw_mob.add(uchild.child("student_detail").child(ppr).child("mob_1").getValue().toString());

                                }

                            }


                        }
                        int i=0;
                        for(String son: ids_hw){
                          mult_msg.add(ids_hw_mob.get(i)+","+son);
                           i++;

                        }
                        String sstthh="";
                       // int i=0;
                        for(String rmult: mult_msg){
                            sstthh += rmult+"\n";
                            //i++;

                        }
                        // Toast.makeText(getActivity(),all_mult, Toast.LENGTH_LONG);

                        txt6.setText(sstthh);







                        //String ven=dataSnapshot.child("classes").child("9c").child("student_detail").child("9c8").child("mob_1").getValue().toString();



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
                        //txt9.setText(databaseError.getMessage());

                    }
                });

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean connected = dataSnapshot.getValue(Boolean.class);
                        if (connected) {
                            abst_lists.clear();
                            sst="";
                            sstm="";
                            abst_lists.clear();





                Query sendabs=dbr.child(MainActivity.session);
                sendabs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        abst_lists.clear();
                        sst="";
                        abst_ids_name.clear();
                        sstm="";

                        if(dataSnapshot.child("Attendance").hasChild(MainActivity.currentdate))
                        {
                            for (DataSnapshot xchild: dataSnapshot.child("Attendance").child(MainActivity.currentdate).getChildren()){

                                if(xchild.hasChild("Abs_ids")){
                                    for (int j = 1; j <= (xchild.child("Abs_ids").getChildrenCount()); j++) {

                                        abst_lists.add(xchild.child("Abs_ids").child("abs" + j).getValue().toString());
                                    }

                                }


                            }


                        }else{
                            txt5.setText("NO CLASS HAS TAKEN ATTENDANCE");
                            // Toast.makeText(getActivity(),"NO class has taken ATTENDANCE FOR TODAY",Toast.LENGTH_LONG).show();

                        }
                        //progressDialog.dismiss();

                        for(String ggs: abst_lists)
                        {
                           // for(DataSnapshot ychild: dataSnapshot.child("classes").getChildren()){

                             //   String mobab= ychild.child("student_detail").child(ggs).child("mob_1").getValue().toString();
                                //abst_ids_name.add(mobab);
                            sst += ggs+"\n";
                            //}
                            //  String temp =(abst_lists[x]);
                            //sst += ggs+"\n";

                        }
                        txt7.setText(sst);
                        //total_abst=abst_lists.size();
                       // txt5.setText(sst);
                       // txt6.setText(Long.toString(total_abst));
                       // txt9.setText(Long.toString(total_strength-total_abst));
                       // txt6.setText(sst);

                             for(DataSnapshot ychild: dataSnapshot.child("class").getChildren()){
                                 for(String ggs: abst_lists)
                                 {
                                     if(ychild.child("student_detail").hasChild(ggs)){
                                         String mobab= ychild.child("student_detail").child(ggs).child("mob_1").getValue().toString();
                                         abst_ids_name.add(mobab);


                                     }



                           }
                            //  String temp =(abst_lists[x]);
                            //sst += ggs+"\n";

                        }








                        //String ssoo="";
                        for(String ggsi: abst_ids_name)
                        {

                            //  String temp =(abst_lists[x]);
                            sstm += ggsi+",";

                        }
                        //String nms = ite.substring(ite.length()-10); // mobile
                        //String pqr= ite.substring(0,ite.length()-17);  //  name
                        //String abidds= ite.substring(ite.length()-14,ite.length()-11); // roll no

                        String subsstm=sstm.substring(0,sstm.length()-1);
                        txt6.setText(subsstm);
                        //txt9.setText(Long.toString(total_strength-total_abst));




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




                           // AsyncTaskRunner runner = new AsyncTaskRunner();
                            //String sleepTime = "3";
                            //runner.execute(sleepTime);



                        }else{
                            Toast.makeText(getActivity(),"NOT CONNECTED IN SEENG ATTENDANCE", Toast.LENGTH_LONG);


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
        });



        */



        //b1.setOnClickListener(new View.OnClickListener() {
          //  @Override
//            public void onClick(View v) {




       //     }
     //   });




        return myview;
    }

    /*
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;


        @Override
        protected String doInBackground(String... params) {
            sst="";
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                loadsms();
                getmob();

                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";

                 return resp;

            }catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return  resp;
        }


        @Override
        protected void onPostExecute(String result) {

            // execution of result of Long time consuming operation
            //sst="";
            progressDialog.dismiss();

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    "ProgressDialog",
                    "Wait...");

        }


        @Override
        protected void onProgressUpdate(String... text) {
            //txt1.setText(text[0]);

        }
    }
    */

    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        private String server_response;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {
            //String server_result= sendSms();


            //return server_result;




            URL url;
            HttpURLConnection urlConnection = null;

            try {
                String user = "username=" + URLEncoder.encode("rehankhan3@gmail.com", "UTF-8");
                String hash = "&hash=" + URLEncoder.encode("2db988a89473dd79ca65cad1443e1190d5c22dffbb57a34db15df1f8b5df4b2e", "UTF-8");
                String message = "&message=" + URLEncoder.encode(strings[1], "UTF-8");
                String sender = "&sender=" + URLEncoder.encode("GOLDEN", "UTF-8");
                String numbers = "&numbers=" + URLEncoder.encode(strings[0], "UTF-8");

                // Send data
                String data = "http://api.textlocal.in/send/?" + user + hash + sender + numbers + message;
                url = new URL(data);
                // url = new URL("http://api.textlocal.in/send/?username=rehankhan3@gmail.com&hash=2db988a89473dd79ca65cad1443e1190d5c22dffbb57a34db15df1f8b5df4b2e&sender=GOLDEN&numbers=7879151021&message=Dear parents, first test of API");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);

                BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                String sResult = "";
                while ((line = rd.readLine()) != null) {
                    // Process line...
                    sResult = sResult + line + " ";
                }
                rd.close();
                //server_response=sResult;
                int time = 5*1000;

                Thread.sleep(time);


                return sResult;
                //return server_response;

                //int responseCode = urlConnection.getResponseCode();

//                if(responseCode == HttpURLConnection.HTTP_OK){
                //                  server_response = readStream(urlConnection.getInputStream());
                //                Log.v("CatalogClient", server_response);
                //          }

            } catch (Exception e) {
                //e.printStackTrace();
                //server_response = e.getMessage();

                System.out.println("Error SMS " + e);
                return "Error " + e;
            }


            /*

            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            */

            //return server_response;

        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            progressDialog.dismiss();

            //Log.e("Response", ""+ s.substring(s.length()-10,s.length()-2));
            //txt1.setText(server_response);
            Log.e("Response", "" + s);
            //txt1.setText("yes");
            //txt1.setText("hi: "+s.substring(s.length()-10,s.length()-3));


        }
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    "ProgressDialog",
                    "Wait for 5 seconds");
        }
    }

    public void loadsms(){
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();

        Query hwmsg=dbr.child(MainActivity.session);
        hwmsg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // pDialog.dismiss();
                all_hw_ids.clear();
                ids_hw.clear();
                mult_msg.clear();
                all_rollno.clear();
                ids_hw_mob.clear();
                //toids_hw.clear();
                all_mult="";
                mobit="";










                for(DataSnapshot tchild: dataSnapshot.child("class").getChildren()){

                    for(DataSnapshot uchild: tchild.child("student_detail").getChildren()){

                        all_rollno.add(uchild.getKey().toString());

                    }
                }
                String ttrn="";
                for(String grnn: all_rollno){

                    ttrn+= grnn+", ";
                }
                // txt5.setText(ttrn);



                for(String groll: all_rollno){
                    boolean inside=false;
                    tempmsg="";
                    for(DataSnapshot pchild: dataSnapshot.child("Homework").child(MainActivity.currentdate).getChildren()){
                        for(DataSnapshot qchild: pchild.getChildren()){
                            for(DataSnapshot rchild: qchild.getChildren()){

                                for (int j = 1; j <= (rchild.child("To_ids").getChildrenCount()); j++) {

                                    if((rchild.child("To_ids").child("abs" + j).getValue().toString()).equalsIgnoreCase(groll)){
                                        //toids_hw.add(rchild.child("To_ids").child("abs" + j).getValue().toString());
                                        //for(DataSnapshot uchild: dataSnapshot.child("classes").getChildren()){
                                        //  mobit= uchild.child("student_detail").child(groll).child("mob_1").getValue().toString();
                                        //}
                                        //mobtemp= groll;
                                        inside=true;
                                        String temphw = rchild.child("Text").getValue().toString();
                                        tempmsg += temphw+": ";

                                    }
                                    //abst_lists.add("hi"+j);

                                }
                            }

                        }


                    }


                    //for(DataSnapshot schild : dataSnapshot.child("classes").getChildren()){
                    //for(DataSnapshot tchild: schild.child("student_detail").child(groll))
                    //   tempmob = schild.child("student_detail").child(groll).child("mob_1").getValue().toString();



                    //}

                    //mult_msg.add(mobtemp+": "+tempmsg);
                    if(inside){
                        // mult_msg.add(groll+": "+tempmsg);
                        all_hw_ids.add(groll);
                        ids_hw.add(tempmsg);

                    }
                    //mult_msg.add(mobtemp);
                    // mult_msg.add(groll);

                }

                for(DataSnapshot uchild: dataSnapshot.child("class").getChildren()){
                    for(String ppr: all_hw_ids){
                        if(uchild.child("student_detail").hasChild(ppr)){

                            ids_hw_mob.add(uchild.child("student_detail").child(ppr).child("mob_1").getValue().toString());

                        }

                    }


                }
                int i=0;

                for(String son: ids_hw){
                    mult_msg.add(ids_hw_mob.get(i)+","+son);

                    i++;


                }
                pDialog.dismiss();
                txt7.setText(Integer.toString(i));
                String sstthh="";
                // int i=0;
                for(String rmult: mult_msg){
                    sstthh += rmult+"\n";
                    //i++;

                }
                // Toast.makeText(getActivity(),all_mult, Toast.LENGTH_LONG);

                txt6.setText(sstthh);







                //String ven=dataSnapshot.child("classes").child("9c").child("student_detail").child("9c8").child("mob_1").getValue().toString();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });



    }


    public void getmob(){
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();

        abst_lists.clear();
        sst="";
        sstm="";
        abst_lists.clear();





        Query sendabs=dbr.child(MainActivity.session);
        sendabs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // pDialog.dismiss();
                abst_lists.clear();
                sst="";
                abst_ids_name.clear();
                sstm="";

                if(dataSnapshot.child("Attendance").hasChild(MainActivity.currentdate))
                {
                    for (DataSnapshot xchild: dataSnapshot.child("Attendance").child(MainActivity.currentdate).getChildren()){

                        if(xchild.hasChild("Abs_ids")){
                            for (int j = 1; j <= (xchild.child("Abs_ids").getChildrenCount()); j++) {

                                abst_lists.add(xchild.child("Abs_ids").child("abs" + j).getValue().toString());
                            }

                        }


                    }


                }else{
                    //txt5.setText("NO CLASS HAS TAKEN ATTENDANCE");
                    // Toast.makeText(getActivity(),"NO class has taken ATTENDANCE FOR TODAY",Toast.LENGTH_LONG).show();

                }
                //progressDialog.dismiss();

                for(String ggs: abst_lists)
                {
                    // for(DataSnapshot ychild: dataSnapshot.child("classes").getChildren()){

                    //   String mobab= ychild.child("student_detail").child(ggs).child("mob_1").getValue().toString();
                    //abst_ids_name.add(mobab);
                    sst += ggs+"\n";
                    //}
                    //  String temp =(abst_lists[x]);
                    //sst += ggs+"\n";

                }
                //txt7.setText(sst);
                //total_abst=abst_lists.size();
                // txt5.setText(sst);
                // txt6.setText(Long.toString(total_abst));
                // txt9.setText(Long.toString(total_strength-total_abst));
                // txt6.setText(sst);

                for(DataSnapshot ychild: dataSnapshot.child("class").getChildren()){
                    for(String ggs: abst_lists)
                    {
                        if(ychild.child("student_detail").hasChild(ggs)){
                            String mobab= ychild.child("student_detail").child(ggs).child("mob_1").getValue().toString();
                            abst_ids_name.add(mobab);


                        }



                    }
                    //  String temp =(abst_lists[x]);
                    //sst += ggs+"\n";

                }








                //String ssoo="";
                int x=0;
                for(String ggsi: abst_ids_name)
                {

                    //  String temp =(abst_lists[x]);
                    sstm += ggsi+",";

                    x++;


                }
                //String nms = ite.substring(ite.length()-10); // mobile
                //String pqr= ite.substring(0,ite.length()-17);  //  name
                //String abidds= ite.substring(ite.length()-14,ite.length()-11); // roll no
                pDialog.dismiss();
                all_mob_no=sstm.substring(0,sstm.length()-1);
                txt6.setText(all_mob_no);
                txt7.setText(Integer.toString(x));
                //txt9.setText(Long.toString(total_strength-total_abst));




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


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getActivity().getSystemService(getActivity().getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            // Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

}
