package com.ankesh.instasplit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ankesh.instasplit.Models.User;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstName, lastName, email, mobileNumber, password;
    private Button register;
    private FirebaseAuth firebaseAuth;
    private Firebase userReference,userIdReference;
    private DatabaseReference rootReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        rootReference = FirebaseDatabase.getInstance().getReference();
        userReference = new Firebase("https://instasplit-77aa0.firebaseio.com/Users");
        userIdReference = new Firebase("https://instasplit-77aa0.firebaseio.com/UserID");
        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_register);

        firstName = (EditText) findViewById(R.id.reg_name);
        lastName = (EditText) findViewById(R.id.reg_last_name);
        email = (EditText) findViewById(R.id.reg_email);
        mobileNumber = (EditText) findViewById(R.id.reg_mobile);
        password = (EditText) findViewById(R.id.reg_password);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }


    private void writeNewUser() {
        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String mno = mobileNumber.getText().toString();
        String uid = firebaseAuth.getCurrentUser().getUid();
        String email = firebaseAuth.getCurrentUser().getEmail();
        DatabaseReference usersRef = rootReference.child("Users");
        DatabaseReference usersRefValue = usersRef.child(uid);
        User user = new User(fname, lname, mno);
        usersRefValue.setValue(user);


        DatabaseReference userIDMobileRef = rootReference.child("UsersIdMobile");
        DatabaseReference userIDMobileRefValue = userIDMobileRef.child(uid);
        userIDMobileRefValue.setValue(mno);

        DatabaseReference userIDEmailRef = rootReference.child("UsersIdEmail");
        DatabaseReference userIDEmailRefValue = userIDEmailRef.child(uid);
        userIDEmailRefValue.setValue(email);

       // Firebase childRef = userReference.child(uid);
        //Firebase userIdChildRef = userIdReference.child(mno);
        //userIdChildRef.setValue(uid);

       // childRef.setValue(user);

        //Firebase myFriendsChild = childRef.child("my_Friends");

    }

    public void registerUser() {
        String emailValue = email.getText().toString().trim();
        String passValue = password.getText().toString().trim();
        if (TextUtils.isEmpty(emailValue) ||
                TextUtils.isEmpty(passValue) ||
                TextUtils.isEmpty(firstName.toString()) ||
                TextUtils.isEmpty(lastName.toString()) ||
                TextUtils.isEmpty(mobileNumber.toString())) {
            Toast.makeText(RegisterActivity.this, "Enter all the values", Toast.LENGTH_LONG).show();
        }
        else {
            progressDialog.setMessage("Registering user");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(emailValue, passValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        writeNewUser();

                        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();

                    } else {
                        String result = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }

    }
}



