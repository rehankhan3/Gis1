package com.example.rehan.gis;
import com.example.rehan.gis.BuildConfig;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static boolean connectcheck;
    //defining views
    private Button buttonSignIn;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;
    DatabaseReference intcheck= FirebaseDatabase.getInstance().getReference(".info/connected");
    DatabaseReference vercheck= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intcheck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                connectcheck = dataSnapshot.getValue(Boolean.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();


        //if the objects getcurrentuser method is not null
        //means user is already logged in

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){

            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }


        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        //textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

       // textViewSignup.setText(versionName);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(this);
        //textViewSignup.setOnClickListener(this);

        //System.out.println("connected");
        //Toast.makeText(LoginActivity.this, "CONNECTED after login", Toast.LENGTH_SHORT).show();
    }

    /*
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;


        @Override
        protected String doInBackground(String... params) {
            resp="";
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                Query que3= vercheck.child("todaytimestamp");
                ValueEventListener date=que3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        resp=dataSnapshot.child("version").getValue().toString();
                        //if(versionName==(dataSnapshot.child("version").getValue().toString())){
                          //  finish();
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        //}else{
                          //  Toast.makeText(LoginActivity.this, "Please update the version", Toast.LENGTH_SHORT).show();


                       // }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);

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
            progressDialog.dismiss();
            int versionCode = BuildConfig.VERSION_CODE;
            String versionName = BuildConfig.VERSION_NAME;
            textViewSignup.setText(result);

            if(versionName==result){
              finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }else{
              Toast.makeText(LoginActivity.this, "Please update the version", Toast.LENGTH_SHORT).show();


             }
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "ProgressDialog",
                    "Wait for 5 seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //txt1.setText(text[0]);

        }
    }

    */



    //method for user login
    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Connecting Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){


                            //AsyncTaskRunner runner1 = new AsyncTaskRunner();
                            //String sleepTime = "5";
                            //runner1.execute(sleepTime);
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            /*
                            //start the profile activity
                            intcheck.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    boolean connected = dataSnapshot.getValue(Boolean.class);
                                    if (connected) {
                                        Query que3= vercheck.child("todaytimestamp");
                                        que3.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                int versionCode = BuildConfig.VERSION_CODE;
                                                String versionName = BuildConfig.VERSION_NAME;
                                                if(versionName==(dataSnapshot.child("version").getValue().toString())){
                                                    finish();
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                                }else{
                                                    Toast.makeText(LoginActivity.this, "Please update the version", Toast.LENGTH_SHORT).show();


                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                    } else {
                                        //System.out.println("not connected");
                                        //Toast.makeText(LoginActivity.this, "NOT CONNECTED after login", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            */

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Wrong id, password OR no connectivity", Toast.LENGTH_SHORT).show();


                        }



                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignIn){
            userLogin();
        }

       // if(v == textViewSignup){
         //   finish();
           // startActivity(new Intent(this, LoginFirebase.class));
        //}

    }

}
