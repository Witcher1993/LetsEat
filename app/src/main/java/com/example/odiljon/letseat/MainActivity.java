package com.example.odiljon.letseat;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Create sign up and sign in buttons
    Button signUpButton;
    Button signInButton;
    //Create slogan TextView
    TextView sloganTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize sign up and sign in buttons
        signUpButton = findViewById(R.id.sign_up_button);
        signInButton = findViewById(R.id.sign_in_button);

        //Initialize sloganTextView
        sloganTextView = findViewById(R.id.text_view_slogan);
        //add some font style
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/DEARJOE.TTF");
        sloganTextView.setTypeface(face);

        //
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });


        //
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);

            }
        });




    }
}
