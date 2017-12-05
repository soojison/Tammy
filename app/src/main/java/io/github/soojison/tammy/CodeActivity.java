package io.github.soojison.tammy;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
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



    public static Bitmap[] arr;

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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bitmap it = decodeSampledBitmapFromResource(getResources(), R.drawable.it, 400, 400);
        Bitmap could = decodeSampledBitmapFromResource(getResources(), R.drawable.could, 400, 400);
        Bitmap have = decodeSampledBitmapFromResource(getResources(), R.drawable.have, 400, 400);
        Bitmap been = decodeSampledBitmapFromResource(getResources(), R.drawable.been, 400, 400);

        arr = new Bitmap[] {it, could, have, been};
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

            if(code.equalsIgnoreCase("any of us")) {
                finish();
            }
        } else {
            etCode.setError("This is not a valid clue.");
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
