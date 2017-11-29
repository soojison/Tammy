package io.github.soojison.tammy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ClueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue);

        // we always have to make sure that the intent we get has an extra that we put in
        if (getIntent().hasExtra("code")) {
            String code = getIntent().getStringExtra("code");

            //test to see if we have unpacked the correct one
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        }
    }

    // here we write a function that decides what happens based on the code

    // todo: back button on certain codes so people can go back and enter more clues
    // todo: black screen for a certain code so we can say that the app crashed!!!
}
