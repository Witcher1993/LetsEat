package com.example.odiljon.letseat;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.odiljon.letseat.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    //
    EditText phoneEdit;
    EditText passwordEdit;

    //
    Button signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //
        phoneEdit = (MaterialEditText)findViewById(R.id.edit_phone);
        passwordEdit = (MaterialEditText)findViewById(R.id.edit_password);
        signInButton = (Button)findViewById(R.id.sign_in_button_sign_in_activity);

        //initialize firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PROGRESS DIALOG
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        //Check if user exists in the database
                        if(dataSnapshot.child(phoneEdit.getText().toString()).exists()) {
                            //Get user information
                            User user = dataSnapshot.child(phoneEdit.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(passwordEdit.getText().toString())) {
                                Toast.makeText(SignIn.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User does not exist in DATABASE", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
