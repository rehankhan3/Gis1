package com.example.rehan.gis;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String cur_id;
    public static String  session= "2016-17";
    public static String currentdate="";
    public static String appver="";
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private boolean admncheck=false;
   // private boolean connected;
    Context context;
    ArrayList<String> admn_list = new ArrayList<String>();
    //ArrayList<String> fri = new ArrayList<String>();
    FragmentManager fragmentManager= getFragmentManager();
    TextView txt;
    DatabaseReference dbr= FirebaseDatabase.getInstance().getReference();
    DatabaseReference intcheck= FirebaseDatabase.getInstance().getReference(".info/connected");
    NavigationView navg=null;
    Toolbar tlb=null;
    //ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //String[] foody = {"pizza", "burger", "chocolate", "ice-cream", "banana", "apple"};
        // set adapter for listview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mysecond,R.id.txn_1, foody);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt= (TextView)findViewById(R.id.textView4);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        txt.setText("THISI IS THE REQUIRED TEST WHICH IS NEEDED\n AND THE WAY REQUIRED IS \n AND ANOTHER");

        Query queauth= dbr.child(MainActivity.session).child("Admin");
        queauth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                admn_list.clear();
                for(DataSnapshot xchild: dataSnapshot.getChildren()){

                    admn_list.add(xchild.child("id").getValue().toString());


                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //WebView browser = (WebView) findViewById(R.id.webview);
       // browser.loadUrl("http://www.gisindore.in/");
        /*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               // Toast.makeText(MainActivity.this,"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
               //fragmentManager.beginTransaction().replace(R.id.content_frame, new ShowingWebview()).commit();
                Toast.makeText(MainActivity.this,"YOUR MESSAGE rehan",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                //Create a bundle object
                Bundle b = new Bundle();

                //Inserts a String value into the mapping of this Bundle

                String getrec=" http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=kapbulk&pass=kapbulk@user!123&senderid=KAPMSG&message=Hi sadiya%20Test%20Message&dest_mobileno=7879151021&response=Y";

                b.putString("stuff",getrec);

                //Add the bundle to the intent.
                intent.putExtras(b);

                //start the DisplayActivity
                startActivity(intent);
                //startActivity(new Intent(getApplicationContext(),SecondActivity.class) );

               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });

        */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       fragmentManager.beginTransaction().replace(R.id.content_frame, new NewFragmentHome()).commit();
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;


        @Override
        protected String doInBackground(String... params) {
            //sst="";
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       boolean connected = dataSnapshot.getValue(Boolean.class);

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


        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Connecting to the server",
                    "Wait...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            // txt1.setText(text[0]);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            //finish();

            //and open profile activity
            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_attendance) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AttendanceDuplicate()).commit();
            // Handle the camera action
        }  else if (id == R.id.nav_see_attendance) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SeeingAttendance()).commit();
            //fragmentManager.beginTransaction().replace(R.id.content_frame, new NewFragmentHome()).commit();

        }
        /*
        else if (id == R.id.nav_homework) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeworkThird()).commit();
        }
        else if (id == R.id.nav_see_messages) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SeeMessages()).commit();
        }else if (id == R.id.nav_send_sms) {
            for(String ggss: admn_list){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user.getUid().toString().equals(ggss)){
                    admncheck=true;
                    //fragmentManager.beginTransaction().replace(R.id.content_frame, new SendingSms()).commit();


                }

            }
            if(admncheck){
                fragmentManager.beginTransaction().replace(R.id.content_frame, new SendingSms()).commit();


            }else{

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("YOU ARE NOT AUTHORIZED TO ACCESS IT");
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
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
         //AsyncTaskRunner runner = new AsyncTaskRunner();
        //String sleepTime = "3";
        //runner.execute(sleepTime);

       intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               LoginActivity.connectcheck = dataSnapshot.getValue(Boolean.class);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

       // intcheck.addListenerForSingleValueEvent(new ValueEventListener() {
         //   @Override
           // public void onDataChange(DataSnapshot dataSnapshot) {
             //   boolean connected = dataSnapshot.getValue(Boolean.class);
                //if (LoginActivity.connectcheck) {
                    if (isInternetOn()) {
                    //System.out.println("connected");

                   // Toast.makeText(MainActivity.this,"CONNECTED in main",Toast.LENGTH_SHORT).show();

                    //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    //      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressDialog.setMessage("Connecting to the server...");
                    //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    dbr.child("todaytimestamp").child("timestamp").setValue(ServerValue.TIMESTAMP);
                    //Date myDate = new Date();
                    Query qu = dbr.child("todaytimestamp");
                    ValueEventListener date = qu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // sspp=(dataSnapshot.child(item+i+"/Name").getValue().toString());
                            String str2;
                            str2 = dataSnapshot.child("timestamp").getValue().toString();
                            appver=dataSnapshot.child("version").getValue().toString();
                            long t = Long.parseLong(str2.substring(0, str2.length() - 3));
                            Date myDate = new Date(t * 1000);
                            //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            //SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
                            String str3 = (myDate.toString()).substring(4,myDate.toString().length()-24);// for date only
                            //String str3 = (myDate.toString()).substring(4,myDate.toString().length()-14); // for date and time
                            String str4= (myDate.toString()).substring(myDate.toString().length()-5); // for year
                            currentdate= str3.concat(str4);

                            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                            //Date date = formatter.parse(str3);

                            //sfd.format(myDate);
                            //txt1.setText((myDate.toString()));
                            // txt1.setText(str5);
                            // txt1.setText(MainActivity.session);
                            //txt1.setText(formatter.format(myDate));
                            dbr.child(MainActivity.session).child("Attendance").child("rough").setValue(ServerValue.TIMESTAMP);
                            progressDialog.dismiss();
                            //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //txt.setText(myDate.toString());
                    //startActivity(new Intent(getApplicationContext(), TextInputPart.class));




                } else {



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Please check your internet connection. And try again ");
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
                            firebaseAuth.signOut();
                            //closing activity
                            finish();
                            //starting login activity
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    progressDialog.dismiss();
                    alertDialog.show();

                }/*
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        super.onStart();



    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

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
