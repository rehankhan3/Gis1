package com.example.rehan.gis;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import android.widget.Button;


/**
 * Created by rehan on 26-Dec-16.
 */

public class HomeworkThird extends Fragment {
    View myview;
    TextView txt,txt1,txt2;
    public String class_selected= "";
    public String[] list2;
    public String str5="";
    public String selected_class="", selected_subject="";
    public boolean  att_taken= false;
    private FirebaseAuth firebaseAuth;
    private CheckBox chk1;
    private EditText ed1;
    private ProgressDialog pDialog;


    private ProgressDialog progressDialog;



Boolean sendmsg=false;
    Button b1,b2,b3;
    String sst="",mob_list="",name_list="",final_mob="";
    Context context;
    String[] foody;
    String[] class_list={"9b","9c"};
    String[] sub_list={"Hindi","English","Maths"};
    String ittems="";
    //String[] abst_lists;
    final ArrayList<String> friends = new ArrayList<String>();
    ArrayList<String> abst_ids = new ArrayList<String>();
    ArrayList<String> abst_lists = new ArrayList<String>();
    ArrayList<String> itemsel = new ArrayList<String>();
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
        myview=inflater.inflate(R.layout.homework_third,container,false);

        pDialog=new ProgressDialog(getActivity());
       progressDialog = new ProgressDialog(getActivity());


        //return super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) myview.findViewById(R.id.spinner);
        chk1=(CheckBox) myview.findViewById(R.id.checkBox);
        ed1=(EditText) myview.findViewById(R.id.editText4);
        ed1.setText("");


         CheckBox chkAll =  ( CheckBox ) myview.findViewById(R.id.checkBox);

        /** Setting a click listener for the checkbox **/
        chkAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                int itemCount = lv1.getCount();
                for(int i=0 ; i < itemCount ; i++){
                    lv1.setItemChecked(i, chk.isChecked());

                }



                getCheckedItem();

            }
        });


        //String sspp= ed1.getText().toString();
        //b2.setText(sspp);



        // Spinner click listener
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CheckBox chkAll =  ( CheckBox ) myview.findViewById(R.id.checkBox);
                chkAll.setChecked(false);
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
                if(item.equals("Class")) {
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
                    intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            boolean connected = dataSnapshot.getValue(Boolean.class);
                            if (connected) {
                                pDialog.setMessage("Please Wait");
                                pDialog.setCancelable(false);
                                pDialog.show();




                    // progressDialog.setMessage("Registering Please Wait...");
                    //progressDialog.show();

                    lv1.setVisibility(View.VISIBLE);
                    mob_list="";
                    name_list="";
                    sendmsg=false;
                    abst_ids.clear();


                    //Toast.makeText(parent.getContext(), "SELECTED : " + item, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getActivity(),"YOUR rehan zzzz MESSAGE",Toast.LENGTH_LONG).show();
                    Query query = dbr.child(MainActivity.session).child("class/"+item+"/student_detail").orderByChild("Name");
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ittems="";
                            friends.clear();
                            fri.clear();
                            lv1.setAdapter(null);
                            pDialog.dismiss();



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
                            for(DataSnapshot bchild: dataSnapshot.getChildren()){

                                sspp=bchild.child("Name").getValue().toString();
                                // ssqp=bchild.child("mob_1").getValue().toString();
                                ssqp= bchild.getKey().toString();
                                friends.add(i+") "+sspp+" : "+ssqp);
                                //lv1.setAdapter(adapter);
                                lv1.setAdapter(arrayAdapter);
                                i++;

                            }

                            /*

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
                    //progressDialog.dismiss();


                } else {

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
                sendmsg = false;

            }
        });
        Spinner spinner2 = (Spinner) myview.findViewById(R.id.spinner4);

        // Spinner click listener
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item2 = parent.getItemAtPosition(position).toString();


                selected_subject=item2;

                if(item2=="Subject"){


                }else{



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        // spinner.OnItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();













        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Class");
        categories.add("KG1A");
        categories.add("KG1B");
        //categories.add("KG1A");
        //categories.add("KG1B");
        categories.add("KG2A");
        categories.add("KG2B");
        categories.add("1A");
        categories.add("1B");
        categories.add("1C");
        categories.add("2A");
        categories.add("2B");
        categories.add("3A");
        categories.add("3B");
        categories.add("4A");
        categories.add("4B");
        categories.add("5A");
        categories.add("5B");

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Subject");
        categories2.add("Hindi");
        categories2.add("English");
        categories2.add("Maths");



        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);














        //context = this;
        //  Firebase.setAndroidContext(this);
        //txt= (TextView) myview.findViewById(R.id.textView2);
        //txt1= (TextView) myview.findViewById(R.id.textView4);
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
         b2=(Button) myview.findViewById(R.id.button5);
       // b3=(Button) myview.findViewById(R.id.button3);


        /*
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hjk= lv1.getItemAtPosition(1).toString();

                sendmsg=true;
                mob_list="";
                name_list="";
                abst_ids.clear();
                for(String ite: fri ){
                    //String nms = ite.substring(ite.length() - 10);
                    //String nms = ite.substring(ite.length()-10); // mobile
                    String pqr= ite.substring(3,ite.length()-7);  //  name
                    String abidds= ite.substring(ite.length()-4,ite.length()); // roll no
                    abst_ids.add(abidds); // for storing abst ids to be stored in firebase
                    //mob_list += abidds+"\n";
                    name_list +=pqr+"\n";
                }
                if(mob_list.length()==0)
                    final_mob="";
                else
                    final_mob=mob_list.substring(0,mob_list.length()-1);
                if(name_list=="") {
                    sendmsg = false;  // to maintain flow that first select items then press final submission
                }

                Toast.makeText(getActivity(),hjk,Toast.LENGTH_LONG).show();

            }
        });

        */
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmsg=true;
                mob_list="";
                name_list="";
                abst_ids.clear();
                for(String ite: fri ){
                    //String nms = ite.substring(ite.length() - 10);
                    //String nms = ite.substring(ite.length()-10); // mobile
                    String pqr= ite.substring(3,ite.length()-7);  //  name
                    String abidds= ite.substring(ite.length()-4,ite.length()); // roll no
                    abst_ids.add(abidds); // for storing abst ids to be stored in firebase
                    //mob_list += abidds+"\n";
                    name_list +=pqr+"\n";
                }
                if(name_list.equals("")) {
                    sendmsg = false;  // to maintain flow that first select items then press final submission
                }

                if(sendmsg&&!((ed1.getText().toString()).equalsIgnoreCase(""))&&(selected_subject!="Subject"))
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure you want to continue");
                      alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                              @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                              //Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                                  pDialog.setMessage("Please Wait");
                                  pDialog.setCancelable(false);
                                  pDialog.show();
                                  intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(DataSnapshot dataSnapshot) {
                                          boolean connected = dataSnapshot.getValue(Boolean.class);
                                          if (connected) {
                                              Query quehw= dbr.child(MainActivity.session);
                                              quehw.addListenerForSingleValueEvent(new ValueEventListener() {
                                                  @Override
                                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                                      pDialog.dismiss();

                                                      FirebaseUser user = firebaseAuth.getCurrentUser();
                                                      //dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child("msg1").child("Text").setValue("hi how");
                                                      dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).push();
                                                      // dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                      String tempuid=dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).push().getKey();
                                                      dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Text").setValue(selected_subject+", "+ed1.getText().toString());
                                                      dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Teacherid").setValue(user.getUid());
                                                      dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Teacheremail").setValue(user.getEmail());
                                                      dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Timestamp").setValue(ServerValue.TIMESTAMP);
                                                      // dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child("TakenByTeacher").setValue(user.getUid());
                                                      int i = 1;
                                                      for (String itee : abst_ids) {                    // TO INSERT ABST IDS IN FIREBASE
                                                          String ctt = "abs" + i;

                                                          dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("To_ids").child(ctt).setValue(itee);


                                                          i++;

                                                      }
                                                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                                      alertDialogBuilder.setMessage("Your Homework is saved");
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

                                                  @Override
                                                  public void onCancelled(DatabaseError databaseError) {

                                                  }
                                              });














                                          } else {
                                              pDialog.dismiss();
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
                                              //System.out.println("not connected");
                                              //Toast.makeText(getActivity(), "NOT CONNECTED after button ", Toast.LENGTH_LONG).show();
                                          }

                                      }

                                      @Override
                                      public void onCancelled(DatabaseError databaseError) {

                                      }
                                  });


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
                    //progressDialog.dismiss();
                    alertDialog.show();


                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("First select \n1) Class\n2) Subject\n3) Students\nAnd then Press SAVE");
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


        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                          @Override
                                          public void onDataChange(DataSnapshot dataSnapshot) {
                                              boolean connected = dataSnapshot.getValue(Boolean.class);
                                              if (connected) {
                                      sendmsg=true;
                                      mob_list="";
                                      name_list="";
                                      abst_ids.clear();
                                      for(String ite: fri ){
                                          //String nms = ite.substring(ite.length() - 10);
                                          //String nms = ite.substring(ite.length()-10); // mobile
                                          String pqr= ite.substring(3,ite.length()-7);  //  name
                                          String abidds= ite.substring(ite.length()-4,ite.length()); // roll no
                                          abst_ids.add(abidds); // for storing abst ids to be stored in firebase
                                          //mob_list += abidds+"\n";
                                          name_list +=pqr+"\n";
                                      }
                                      if(name_list.equals("")) {
                                          sendmsg = false;  // to maintain flow that first select items then press final submission
                                      }

                                      if(sendmsg&&!((ed1.getText().toString()).equalsIgnoreCase(""))&&(selected_subject!="Subject"))
                                       {
                                           //pDialog.setMessage("Please Wait");
                                           //pDialog.setCancelable(false);
                                           //pDialog.show();

                                                     Query quehw= dbr.child(MainActivity.session);
                                                     quehw.addListenerForSingleValueEvent(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                                             //pDialog.dismiss();

                                                             AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                                             alertDialogBuilder.setMessage("Are you sure you want to save the homework for:\n1) Date: " + MainActivity.currentdate + "\n2) Class: " + selected_class+"\n3) Subject: "+selected_subject);
                                                             alertDialogBuilder.setPositiveButton("YES",
                                                                     new DialogInterface.OnClickListener() {

                                                                         @Override
                                                                         public void onClick(DialogInterface arg0, int arg1) {
                                                                             pDialog.setMessage("Please Wait");
                                                                             pDialog.setCancelable(false);
                                                                             pDialog.show();
                                                                             //pDialog.dismiss();

                                                                             intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                 @Override
                                                                                 public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                     boolean connected = dataSnapshot.getValue(Boolean.class);
                                                                                     if (connected) {
                                                                                        // pDialog.setMessage("Please Wait");
                                                                                         //pDialog.setCancelable(false);
                                                                                         //pDialog.show();
                                                                                         //System.out.println("connected");
                                                                                        // Toast.makeText(getActivity(), "CONNECTED", Toast.LENGTH_SHORT).show();








                                                                                         FirebaseUser user = firebaseAuth.getCurrentUser();
                                                                             //dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child("msg1").child("Text").setValue("hi how");
                                                                             dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).push();
                                                                             // dbr.child(MainActivity.session).child("Attendance").child(MainActivity.currentdate).child("MainTime").setValue(ServerValue.TIMESTAMP);
                                                                             String tempuid=dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).push().getKey();
                                                                             dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Text").setValue(selected_subject+", "+ed1.getText().toString());
                                                                             dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Teacherid").setValue(user.getUid());
                                                                                         dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Teacheremail").setValue(user.getEmail());
                                                                             dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("Timestamp").setValue(ServerValue.TIMESTAMP);
                                                                            // dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child("TakenByTeacher").setValue(user.getUid());
                                                                             int i = 1;
                                                                             for (String itee : abst_ids) {                    // TO INSERT ABST IDS IN FIREBASE
                                                                                 String ctt = "abs" + i;

                                                                                dbr.child(MainActivity.session).child("Homework").child(MainActivity.currentdate).child(selected_class).child(selected_subject).child(tempuid).child("To_ids").child(ctt).setValue(itee);


                                                                                 i++;

                                                                             }

                                                                                         pDialog.dismiss();
                                                                            // Toast.makeText(getActivity(), "HOMEWORK SAVED ", Toast.LENGTH_SHORT).show();
                                                                             //Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();







                                                                         } else {
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
                                                                             //System.out.println("not connected");
                                                                             //Toast.makeText(getActivity(), "NOT CONNECTED after button ", Toast.LENGTH_LONG).show();
                                                                         }

                                                                     }

                                                             @Override
                                                             public void onCancelled(DatabaseError databaseError) {

                                                             }
                                                         });
                                                                         }
                                                                     });

                                                             alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialog, int which) {
                                                                     pDialog.dismiss();
                                                                     //getActivity().finish();  // this code will close the app
                                                                     dialog.cancel();
                                                                 }
                                                             });

                                                             AlertDialog alertDialog = alertDialogBuilder.create();
                                                             progressDialog.dismiss();
                                                             pDialog.dismiss();
                                                             alertDialog.show();


                                                         }

                                                         @Override
                                                         public void onCancelled(DatabaseError databaseError) {

                                                         }
                                                     });












                                  }else{
                                          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                          alertDialogBuilder.setMessage("First select \n1) Class\n2) Subject\n3) Students\nAnd then Press SAVE");
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

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });



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
                CheckBox chk = (CheckBox) myview.findViewById(R.id.checkBox);
                int checkedItemCount = getCheckedItemCount();

                if(lv1.getCount()==checkedItemCount)
                    chk.setChecked(true);
                else
                    chk.setChecked(false);


                 getCheckedItem();




                /*
                String selecteditems=((TextView)view).getText().toString();
                if(fri.contains(selecteditems)){
                    fri.remove(selecteditems);
                }
                else{
                    fri.add(selecteditems);
                }
                */
            }
        });



        return myview;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    private void getCheckedItem(){
        fri.clear();
        int cnt = 0;
        SparseBooleanArray positions = lv1.getCheckedItemPositions();

        int itemCount = lv1.getCount();

        for(int i=0;i<itemCount;i++){
            if(positions.get(i))
                fri.add(lv1.getItemAtPosition(i).toString());
            cnt++;
        }
        //return cnt;
        String tempss="";
        for(String ddr: fri){
            tempss +=ddr+"\n";

        }
        //Toast.makeText(getActivity(),tempss, Toast.LENGTH_SHORT).show();
    }
    private int getCheckedItemCount(){
        int cnt = 0;
        SparseBooleanArray positions = lv1.getCheckedItemPositions();
        int itemCount = lv1.getCount();

        for(int i=0;i<itemCount;i++){
            if(positions.get(i))
                cnt++;
        }
        return cnt;
    }






    @Override
    public void onStart() {

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
              */


        } else {
            //System.out.println("not connected");
            //Toast.makeText(getActivity(), "NOT CONNECTED in HW", Toast.LENGTH_SHORT).show();
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
     super.onStart();



     }
}
