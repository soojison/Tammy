package io.github.soojison.tammy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

public class CodeActivity extends AppCompatActivity {

    @BindView(R.id.btnEnter)
    Button btnEnter;

    @BindView(R.id.etCode)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
    }

    @OnClick(R.id.btnEnter)
    public void openActivity() {
        Toast.makeText(this, "Yoo", Toast.LENGTH_SHORT).show();
    }
}
