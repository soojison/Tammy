package io.github.soojison.tammy;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClueActivity extends AppCompatActivity {

    @BindView(R.id.imgClue)
    ImageView imgClue;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    boolean crashed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue);
        ButterKnife.bind(this);

        crashed = false;

        // we always have to make sure that the intent we get has an extra that we put in
        if (getIntent().hasExtra("code")) {
            String code = getIntent().getStringExtra("code");
            //test to see if we have unpacked the correct one
            int imageIndex = returnImgIndex(code);
            //Toast.makeText(this, imageIndex + "", Toast.LENGTH_SHORT).show();
            if(imageIndex == 4) {
                crashed = true;
                View view = this.getWindow().getDecorView();
                view.setBackgroundColor(000000);
                imgClue.setVisibility(View.INVISIBLE);
                btnContinue.setVisibility(View.INVISIBLE);
            } else {
                Bitmap bitmap = CodeActivity.arr[imageIndex];
                Glide.with(this).load(bitmap).into(imgClue);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if(crashed) {
            new AlertDialog.Builder(this)
                    .setTitle("What do you trust?")
                    .setMessage("We're all vulnerable")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            ClueActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
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

    @OnClick(R.id.btnContinue)
    public void closeActivity() {
        finish();
    }
}
