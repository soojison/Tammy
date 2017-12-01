package io.github.soojison.tammy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
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

    @Override
    protected void onResume() {
        super.onResume();
        etCode.setText("");
    }

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
        String[] possibilities = {"It","Could","Have", "Been", "Any of Us"};

        for (String possibility:possibilities) {
            ret |= code.equalsIgnoreCase(possibility);
        }

        return ret;
    }


    public int returnImgIndex(String code){
        String[] possibilities = {"It","Could","Have", "Been", "Any of Us"};

        for (int i = 0; i < possibilities.length; i++) {
            if(possibilities[i].equalsIgnoreCase(code)) {
                return i;
            }
        }
        return 0;
    }

    @OnClick(R.id.btnEnter)
    public void openActivity() {
        String code = etCode.getText().toString().trim();
        if(TextUtils.isEmpty(etCode.getText().toString())) {
            etCode.setError("You must seek for the clue.");
        } else if (isValidCode(code)){
            // Intents are how two activities (screens) communicate with each other.
            // So, this activity is creating an intent to start a new activity (clue activity)
            //      and we are putting extra information in the intent (the integer code)
            // Then we start the new activity!
            // Go to the ClueActivity.java class to see what happens there
            Intent intent = new Intent(CodeActivity.this, ClueActivity.class);
            intent.putExtra("code", code);
            startActivity(intent);
        } else {
            etCode.setError("This is not a valid clue.");
        }
    }
}
