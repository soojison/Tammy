package io.github.soojison.tammy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends AppCompatActivity {

    @BindView(R.id.btnEnter)
    Button btnEnter;

    @BindView(R.id.etCode)
    EditText etCode;

    @BindView(R.id.imgMain)
    ImageView imgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Generated code, necessary for the activity to work
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        // Using external library to make getting elements from the view easier
        ButterKnife.bind(this);
    }

    public boolean isValidCode(String code){
        boolean ret = false;
        String[] possibilities = {"Tammy","Truck","Musical Watch"};

        for (String possibility:possibilities) {
            ret |= code.equalsIgnoreCase(possibility);
        }

        return ret;
    }

    @OnClick(R.id.btnEnter)
    public void openActivity() {
        if(TextUtils.isEmpty(etCode.getText().toString())) {
            etCode.setError("yall can't leave this blank to get a clue");
        } else if (isValidCode(etCode.getText().toString())){
            // Intents are how two activities (screens) communicate with each other.
            // So, this activity is creating an intent to start a new activity (clue activity)
            //      and we are putting extra information in the intent (the integer code)
            // Then we start the new activity!
            // Go to the ClueActivity.java class to see what happens there
            Intent intent = new Intent(CodeActivity.this, ClueActivity.class);
            intent.putExtra("code", etCode.getText().toString());
            startActivity(intent);
        } else {
            etCode.setError("Not a valid clue");
        }
    }
}
