package com.example.odiljon.letseat;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.odiljon.letseat.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    //
    MaterialEditText phoneEdit;
    MaterialEditText nameEdit;
    MaterialEditText passwordEdit;

    //
    Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialize widgets
        phoneEdit = (MaterialEditText) findViewById(R.id.edit_phone_sign_up_activity);
        nameEdit = (MaterialEditText) findViewById(R.id.edit_Name_sign_up_activity);
        passwordEdit = (MaterialEditText) findViewById(R.id.edit_password_sign_up_activity);

        signUpButton = (Button) findViewById(R.id.sign_up_button_sign_up_activity);

        //initialize firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PROGRESS DIALOG
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //CHECK IF USER EXISTS
                        if(dataSnapshot.child(phoneEdit.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number already exists", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User user = new User(nameEdit.getText().toString(), passwordEdit.getText().toString());
                            table_user.child(phoneEdit.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                            finish();
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
