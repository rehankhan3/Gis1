package com.example.rehan.gis;
import com.example.rehan.gis.BuildConfig;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


//import android.widget.Button;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;
import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by rehan on 26-Dec-16.
 */

public class HomeworkFragment extends Fragment implements OnItemSelectedListener{
    View myview;
    TextView txt,txt1,txt2;
    public String class_selected= "";
    public String[] list2;
    public String str5="";
    public String selected_class="";
    public boolean  att_taken= false;
    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;



Boolean sendmsg=false;
    Button b1,b2,b3;
    String sst="",mob_list="",name_list="",final_mob="";
    Context context;
    String[] foody;
    String[] class_list={"9b","9c"};
    String ittems="";
    //String[] abst_lists;
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> abst_ids = new ArrayList<String>();
    ArrayList<String> abst_ids_name = new ArrayList<String>();
    ArrayList<String> abst_lists = new ArrayList<String>();
    //mylist.add(mystring);
    ArrayList<String> fri = new ArrayList<String>();
    //ArrayAdapter arrayAdapter;

    //private Firebase fbs;
    //String ssp="9c2";
    //TextView txt,txt1;
    ListView lv1;
    DatabaseReference dbr= FirebaseDatabase.getInstance().getReference();
    DatabaseReference intcheck= FirebaseDatabase.getInstance().getReference(".info/connected");
   // dbr.setPersistenceEnabled(true);
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    //DatabaseReference mdf= dbr.child("student_detail");
    //DatabaseReference ccf= mdf.child("second");
    //.child("9thA").child("student_detail").child("9c1").child("mob_1");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.homework_layout,container,false);

       progressDialog = new ProgressDialog(getActivity());


        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // spinner.OnItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();



/*

        FloatingActionButton fab = (FloatingActionButton) myview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Toast.makeText(MainActivity.this,"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
                //fragmentManager.beginTransaction().replace(R.id.content_frame, new ShowingWebview()).commit();
                //Toast.makeText(getActivity(),"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), TextInputPart.class);
                //Create a bundle object
                Bundle b = new Bundle();


                //Inserts a String value into the mapping of this Bundle
                //String getrec="http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=kapbulk&pass=kapbulk@user!123&senderid=KAPMSG&message=YOUR WARD IS ABSENT TODAY %20Test%20Message&dest_mobileno="+final_mob+"&response=Y";
                String getrec=final_mob;
                b.putString("stuff",getrec);
                intent.putExtras(b);

                if(sendmsg) {
                    Toast.makeText(getActivity(), final_mob, Toast.LENGTH_LONG).show();

                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(),"First select and press SAVE button",Toast.LENGTH_LONG).show();
                //String getrec="https://control.msg91.com/api/sendhttp.php?authkey=135869AXcyquj7of586a8765&mobiles=919893151021&message=your ward is absent today&sender=GISIND&route=4&country=91";

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

        */







        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Please slect anyone");
        categories.add("9c");
        categories.add("9b");



        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);













        //context = this;
        //  Firebase.setAndroidContext(this);
        //txt= (TextView) myview.findViewById(R.id.textView2);
        txt1= (TextView) myview.findViewById(R.id.textView4);
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
        // fbs= new Firebase("https://myfirsttry-28128.firebaseio.com/");
         b1=(Button) myview.findViewById(R.id.button2);
        b2=(Button) myview.findViewById(R.id.button);
        b3=(Button) myview.findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(sendmsg)
                                       {
                                         intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                             @Override
                                             public void onDataChange(DataSnapshot dataSnapshot) {
                                                 boolean connected = dataSnapshot.getValue(Boolean.class);
                                                 if (connected) {
                                                     //System.out.println("connected");
                                                     Toast.makeText(getActivity(), "CONNECTED", Toast.LENGTH_LONG).show();


                                                     // alert dialog starts from here
                                             /*
                                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                      alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
                                    //  alertDialogBuilder.setPositiveButton("yes",
                                      //        new DialogInterface.OnClickListener() {
                                        //          @Override
                                          //        public void onClick(DialogInterface arg0, int arg1) {
                                            //          Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                                              //    }
                                              //});

                                      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              //getActivity().finish();  // this code will close the app
                                              dialog.cancel();
                                          }
                                      });

                                      AlertDialog alertDialog = alertDialogBuilder.create();
                                      alertDialog.show();
                                                 */
                                                     // alert dialog ends here

                                                     dbr.child(MainActivity.session).child("Attendance").child("rough").setValue(ServerValue.TIMESTAMP);
                                                     dbr.child("users").child("rehan").child(MainActivity.currentdate).setValue("done");
                                                     // String sspp= (ServerValue.TIMESTAMP).toString();
                                                     //long t =  1485800802;
                                                     //long t= Long.parseLong(sspp.substring(0,sspp.length()-3));
                                                     // Date myDate = new Date(t*1000);
                                                     // SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                                     // sfd.format(myDate);
                                                     //sfd.format(new Date(dbr.ServerValue.TIMESTAMP));
                                                     // txt1.setText(myDate.toString());
                                                     //txt1.setText(sspp);
                                                     //dbr.child("users").child("date").setValue(ServerValue.TIMESTAMP);
                                                     Query qu = dbr.child("todaytimestamp");
                                                     ValueEventListener date = qu.addValueEventListener(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                                             // sspp=(dataSnapshot.child(item+i+"/Name").getValue().toString());
                                                             // below for current date fetching
                                                             String str2;
                                                             str2 = dataSnapshot.child("timestamp").getValue().toString();
                                                             long t = Long.parseLong(str2.substring(0, str2.length() - 3));
                                                             Date myDate = new Date(t * 1000);
                                                             //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                                             //SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
                                                             String str3 = (myDate.toString()).substring(4, myDate.toString().length() - 24);// for date only
                                                             // String str3 = (myDate.toString()).substring(4,myDate.toString().length()-14); // for date and time
                                                             String str4 = (myDate.toString()).substring(myDate.toString().length() - 5); // for year
                                                             str5 = str3.concat(str4);
                                                             int versionCode = BuildConfig.VERSION_CODE;
                                                             String versionName = BuildConfig.VERSION_NAME;

                                                             // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                                                             //Date date = formatter.parse(str3);

                                                             //sfd.format(myDate);
                                                             //txt1.setText((myDate.toString()));
                                                             txt1.setText(versionName);
                                                             //txt1.setText(class_list[1]);
                                                             // txt1.setText(MainActivity.session);
                                                             //txt1.setText(formatter.format(myDate));
                                                         }

                                                         @Override
                                                         public void onCancelled(DatabaseError databaseError) {

                                                         }
                                                     });

                                                     Query que3 = dbr.child(MainActivity.session).child("Attendance");

                                                     que3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                                             progressDialog.setMessage("Fetching information ...");
                                                             progressDialog.setCancelable(false);
                                                             progressDialog.show();
                                                             if (dataSnapshot.hasChild(MainActivity.currentdate)) {

                                                                 if (dataSnapshot.child(MainActivity.currentdate).hasChild(selected_class)) {
                                                                     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                                                     alertDialogBuilder.setMessage("Attendance for date " + MainActivity.currentdate + " for class " + selected_class + " is already been taken");
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


                                                                 } else {
                                                                     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                                                     alertDialogBuilder.setMessage("Are you sure you want to save the attendance of date " + MainActivity.currentdate + " for class " + selected_class);
                                                                     alertDialogBuilder.setPositiveButton("YES",
                                                                             new DialogInterface.OnClickListener() {
                                                                                 @Override
                                                                                 public void onClick(DialogInterface arg0, int arg1) {
                                                                                     FirebaseUser user = firebaseAuth.getCurrentUser();
                                                                                     // dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                                                     dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("timedate").setValue(ServerValue.TIMESTAMP);
                                                                                     dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("att_taken").setValue("yes");
                                                                                     dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("TakenByTeacher").setValue(user.getUid());
                                                                                     int i = 1;
                                                                                     for (String itee : abst_ids) {                    // TO INSERT ABST IDS IN FIREBASE
                                                                                         String ctt = "abs" + i;

                                                                                         dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").child(ctt).setValue(itee);


                                                                                         i++;

                                                                                     }
                                                                                     Toast.makeText(getActivity(), "ATTENDANCE SAVED ", Toast.LENGTH_LONG).show();
                                                                                     //Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
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


                                                                 }


                                                             } else {
                                                                 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                                                 alertDialogBuilder.setMessage("Are you sure you want to save the attendance of date " + MainActivity.currentdate + " for class " + selected_class);
                                                                 alertDialogBuilder.setPositiveButton("YES",
                                                                         new DialogInterface.OnClickListener() {
                                                                             @Override
                                                                             public void onClick(DialogInterface arg0, int arg1) {
                                                                                 FirebaseUser user = firebaseAuth.getCurrentUser();
                                                                                 // dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                                                 dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("timedate").setValue(ServerValue.TIMESTAMP);
                                                                                 dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("att_taken").setValue("yes");
                                                                                 dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("TakenByTeacher").setValue(user.getUid());
                                                                                 int i = 1;
                                                                                 for (String itee : abst_ids) {                    // TO INSERT ABST IDS IN FIREBASE
                                                                                     String ctt = "abs" + i;

                                                                                     dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").child(ctt).setValue(itee);


                                                                                     i++;

                                                                                 }
                                                                                 Toast.makeText(getActivity(), "ATTENDANCE SAVED ", Toast.LENGTH_LONG).show();
                                                                                 //Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
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


                                                             }

                                                         }

                                                         @Override
                                                         public void onCancelled(DatabaseError databaseError) {

                                                         }
                                                     });
                                       /*
                                      //Query que2= dbr.child("2016-17").child("Attendance").orderByChild("att_taken");
                                      Query que2= dbr.child(MainActivity.session).child("Attendance");
                                      que2.addValueEventListener(new ValueEventListener() {
                                          @Override
                                          public void onDataChange(DataSnapshot dataSnapshot) {



                                              int i=0;

                                              for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                                              {
                                                 // String str6;
                                                  //str6 = postSnapshot.getValue().toString();
                                                  //long t1 = Long.parseLong(str6.substring(0, str6.length() - 3));
                                                  //Date myDate1 = new Date(t1 * 1000);
                                                  //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                                  //SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
                                                  //String str3 = (myDate.toString()).substring(4,myDate.toString().length()-24);// for date only
                                                  //String str7 = (myDate1.toString()).substring(4,myDate1.toString().length()-14); // for date and time
                                                  //String str8= (myDate1.toString()).substring(myDate1.toString().length()-5); // for year
                                                  //String str9= str7.concat(str8);
                                                  //if(str5==str9) {
                                                      list2[i] = postSnapshot.getValue().toString();
                                                      i++;
                                                  //}

                                                  // TODO get the data here

                                              }

                                          }

                                          @Override
                                          public void onCancelled(DatabaseError databaseError) {

                                          }
                                      });
                                           */

                                                     // dbr.child(MainActivity.session).child("Attendance").child(ServerValue.TIMESTAMP).child(selected_class).child("Att_taken").setValue("yes");
                                                     //dbr.child("users").child("abs_ids").setValue("true");
                                                     // dbr.child("users").child("abs_ids").removeValue();    // TO DELETE A NODE -->
                                                     //dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").removeValue();
                                                     // txt1.setText(list2[0]);

                                                     //String tempuid= dbr.child(MainActivity.session).child("Attendance").push().getKey();
                                                     //dbr.child(MainActivity.session).child("Attendance").child(tempuid).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                     //dbr.child(MainActivity.session).child("Attendance").child(tempuid).child(selected_class).child("timedate").setValue(ServerValue.TIMESTAMP);
                                                     //dbr.child(MainActivity.session).child("Attendance").child(tempuid).child(selected_class).child("att_taken").setValue("yes");
                                                     //dbr.child(MainActivity.session).child("Attendance").child(tempuid).child(selected_class).child("TakenByTeacher").setValue(user.getUid());
                                                     // FirebaseUser user = firebaseAuth.getCurrentUser();
                                                     // dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                     //dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("timedate").setValue(ServerValue.TIMESTAMP);
                                                     //dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("att_taken").setValue("yes");
                                                     //dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("TakenByTeacher").setValue(user.getUid());
                                                     //       int i=1;
                                                     //for(String itee: abst_ids ){                    // TO INSERT ABST IDS IN FIREBASE
                                                     //       String ctt= "abs"+i;

                                                     //    dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child(selected_class).child("Abs_ids").child(ctt).setValue(itee);


                                                     //  i++;

                                                     //}

                                                     //for(int x=1;x<=6;x++)
                                                     //{
                                                     //   String ctt= "abss_"+x;
                                                     // String ct= "9cc"+x;
                                                     //dbr.child("users").child("abs_ids").child(ctt).setValue(ct);
                                                     //dbr.setValue(c.getTime().toString());

                                                     //dbr.setValue(dbr.ServerValue.TIMESTAMP);


                                                     //}






                                                 } else {
                                                     //System.out.println("not connected");
                                                     Toast.makeText(getActivity(), "NOT CONNECTED ", Toast.LENGTH_LONG).show();
                                                 }

                                             }

                                             @Override
                                             public void onCancelled(DatabaseError databaseError) {

                                             }
                                         });


                                  }else{
                                          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                          alertDialogBuilder.setMessage("First select and then press SAVE button and then Press this SUBMIT button ");
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
                                          //progressDialog.dismiss();
                                          alertDialog.show();

                                      }

                                  }

                              });





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

        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                abst_lists.clear();
                abst_ids_name.clear();
                sst="";
                Query quablist=dbr.child(MainActivity.session).child("Attendance");
                ValueEventListener date= quablist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        abst_lists.clear();
                        // txt1.setText("");
                        sst="";

                        if(dataSnapshot.hasChild(MainActivity.currentdate))
                        {
                            // int i=0;
                            //if(dataSnapshot.child(MainActivity.currentdate).hasChild(class_list[i]))
                            for(DataSnapshot cchild: dataSnapshot.child(MainActivity.currentdate).getChildren())
                            {
                                if(cchild.hasChild("Abs_ids"))
                                {

                                    for(int j=1;j<=(cchild.child("Abs_ids").getChildrenCount());j++)
                                    {

                                        abst_lists.add(cchild.child("Abs_ids").child("abs"+j).getValue().toString()) ;

                                        //abst_lists.add("hi"+j);

                                    }
                                }

                            }
                        }
                        /*

                        Query abstname= dbr.child("classes").child("9b").child("student_detail");
                        ValueEventListener ddtt= abstname.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                abst_ids_name.clear();
                                sst="";
                                //txt1.setText("");
                                for(DataSnapshot ccname: dataSnapshot.getChildren()){

                                    for(String ggs: abst_lists)
                                    {
                                          if(ccname.toString()==ggs){
                                            String abname=ccname.child("Name").getValue().toString();

                                              abst_ids_name.add(abname);

                                            }

                                     }


                                }
                                String ssoo="";
                                for(String ggsi: abst_ids_name)
                                {
                                    //  String temp =(abst_lists[x]);
                                    ssoo += ggsi+",";

                                }
                                txt1.setText(ssoo);


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        */


                        //progressDialog.dismiss();
                        //for(String ggs: abst_lists)
                        //{




                        // String temp =(abst_lists[x]);
                        //  sst += ggs+",";

                        //}
                        //txt1.setText(sst);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





                Query abstname= dbr.child("classes").child("9b").child("student_detail");
                ValueEventListener ddtt= abstname.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        abst_ids_name.clear();
                        sst="";
                        //txt1.setText("");
                        //int i=1;
                        //while(dataSnapshot.hasChild("9b"+i)) {
                            for(String ggs: abst_lists)
                            {
                            //if(dataSnapshot.child("9b"+i).getValue().equals(ggs)){
                                String abname=dataSnapshot.child(ggs).child("Name").getValue().toString();

                                abst_ids_name.add(abname);

                            //}

                            }



                          //  i++;
                        //}

                        /*

                        for(DataSnapshot ccname: dataSnapshot.getChildren()){

                            //for(String ggs: abst_lists)
                            //{
                                if(ccname.toString()=="9b8"){
                                    String abname=ccname.child("Name").getValue().toString();

                                    abst_ids_name.add(abname);

                                }

                            //}


                        }

                        */

                        //String ssoo="";
                        for(String ggsi: abst_ids_name)
                        {
                            //  String temp =(abst_lists[x]);
                            sst += ggsi+",";

                        }
                        txt1.setText(sst);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






                //abst_lists.clear();

               // AsyncTaskRunner runner = new AsyncTaskRunner();
                //String sleepTime = "7";
                //runner.execute(sleepTime);

                //progressDialog.setMessage("Fetching information...");
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //progressDialog.setCancelable(false);
                //progressDialog.show();

               // txt1.setText("experiment done");


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // txt1.setText(MainActivity.appver);
                sendmsg=true;
                mob_list="";
                name_list="";
                abst_ids.clear();
                for(String ite: fri ){
                    //String nms = ite.substring(ite.length() - 10);
                    String nms = ite.substring(ite.length()-10); // mobile
                    String pqr= ite.substring(0,ite.length()-17);  //  name
                    String abidds= ite.substring(ite.length()-14,ite.length()-11); // roll no
                    abst_ids.add(abidds); // for storing abst ids to be stored in firebase
                    mob_list +=nms+",";
                    name_list +=pqr+"\n";
                }
                if(mob_list.length()==0)
                    final_mob="";
                else
                    final_mob=mob_list.substring(0,mob_list.length()-1);
                if(name_list=="") {
                    //sendmsg = false;  // to maintain flow that first select items then press final submission
                }

                Toast.makeText(getActivity(),name_list,Toast.LENGTH_LONG).show();

            }
        });
        return myview;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;


        @Override
        protected String doInBackground(String... params) {
                 sst="";
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                Query quablist=dbr.child(MainActivity.session).child("Attendance");
                ValueEventListener date= quablist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        abst_lists.clear();
                        // txt1.setText("");
                        sst="";

                        if(dataSnapshot.hasChild(MainActivity.currentdate))
                        {
                            // int i=0;
                            //if(dataSnapshot.child(MainActivity.currentdate).hasChild(class_list[i]))
                            for(DataSnapshot cchild: dataSnapshot.child(MainActivity.currentdate).getChildren())
                            {
                                if(cchild.hasChild("Abs_ids"))
                                {

                                    for(int j=1;j<=(cchild.child("Abs_ids").getChildrenCount());j++)
                                    {

                                        abst_lists.add(cchild.child("Abs_ids").child("abs"+j).getValue().toString()) ;

                                        //abst_lists.add("hi"+j);

                                    }
                                }

                            }
                        }
                        /*

                        Query abstname= dbr.child("classes").child("9b").child("student_detail");
                        ValueEventListener ddtt= abstname.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                abst_ids_name.clear();
                                sst="";
                                //txt1.setText("");
                                for(DataSnapshot ccname: dataSnapshot.getChildren()){

                                    for(String ggs: abst_lists)
                                    {
                                          if(ccname.toString()==ggs){
                                            String abname=ccname.child("Name").getValue().toString();

                                              abst_ids_name.add(abname);

                                            }

                                     }


                                }
                                String ssoo="";
                                for(String ggsi: abst_ids_name)
                                {
                                    //  String temp =(abst_lists[x]);
                                    ssoo += ggsi+",";

                                }
                                txt1.setText(ssoo);


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        */


                        //progressDialog.dismiss();
                        //for(String ggs: abst_lists)
                        //{




                            // String temp =(abst_lists[x]);
                          //  sst += ggs+",";

                        //}
                        //txt1.setText(sst);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });











                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
               // for(String ggs: abst_lists)
                //{
                    //  String temp =(abst_lists[x]);
                  //  sst += ggs+"\n";

                //}

                //resp= sst;
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
            Query abstname= dbr.child("classes").child("9b").child("student_detail");
            ValueEventListener ddtt= abstname.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    abst_ids_name.clear();
                    sst="";
                    //txt1.setText("");
                    for(DataSnapshot ccname: dataSnapshot.getChildren()){

                        for(String ggs: abst_lists)
                        {
                            if(ccname.toString()==ggs){
                                String abname=ccname.child("Name").getValue().toString();

                                abst_ids_name.add(abname);

                            }

                        }


                    }
                    String ssoo="";
                    for(String ggsi: abst_ids_name)
                    {
                        //  String temp =(abst_lists[x]);
                        ssoo += ggsi+",";

                    }
                    txt1.setText(ssoo);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

           // txt1.setText("done");
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    "ProgressDialog",
                    "Wait for 5 seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
           // txt1.setText(text[0]);

        }
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

          selected_class=item;
        // Showing selected spinner item
        if(item=="Please select anyone") {
           // Toast.makeText(parent.getContext(), "NO ITEM IS SELECTED", Toast.LENGTH_LONG).show();
            // progressDialog.setMessage("Registering Please Wait...");
             //progressDialog.show();
            //progressDialog.dismiss();
            lv1.setVisibility(View.INVISIBLE);
            ittems="";
            mob_list="";
            name_list="";
            sendmsg=false;

            friends.clear();
            fri.clear();
            abst_ids.clear();
            lv1.setAdapter(null);
            //progressDialog.dismiss();

        }
        else {
           // progressDialog.setMessage("Registering Please Wait...");
            //progressDialog.show();

            lv1.setVisibility(View.VISIBLE);
            mob_list="";
            name_list="";
            sendmsg=false;
            abst_ids.clear();


            //Toast.makeText(parent.getContext(), "SELECTED : " + item, Toast.LENGTH_SHORT).show();
           // Toast.makeText(getActivity(),"YOUR rehan zzzz MESSAGE",Toast.LENGTH_LONG).show();
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
                   // long sst4=(dataSnapshot.child("9b1").getChildrenCount());
                    //String bsf= Long.toString(sst4);
                    //txt1.setText(bsf);

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
                            //progressDialog.dismiss();
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
            //progressDialog.dismiss();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onStart() {

intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        boolean connected = dataSnapshot.getValue(Boolean.class);
        if (connected) {
           // System.out.println("connected");
            //Toast.makeText(getActivity(), "CONNECTED in HW", Toast.LENGTH_SHORT).show();


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



        } else {
            //System.out.println("not connected");
            //Toast.makeText(getActivity(), "NOT CONNECTED in HW", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

         //txt.setText(myDate.toString());
         //startActivity(new Intent(getApplicationContext(), TextInputPart.class));
         //dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
         //Date myDate = new Date();
         //txt1.setText(myDate.toString());
     super.onStart();



     }
}
