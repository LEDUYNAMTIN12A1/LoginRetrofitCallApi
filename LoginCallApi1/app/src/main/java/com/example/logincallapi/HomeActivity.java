package com.example.logincallapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tv = (TextView) findViewById(R.id.tv_result_infor);
        Bundle bundlerevice = getIntent().getExtras();
        if(bundlerevice != null){
            Result result = (Result) bundlerevice.get("object result");
            if(result != null){
                tv.setText(result.toString());
            }
            else{
                Toast.makeText(HomeActivity.this,"result không có giá trị",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(HomeActivity.this, "bundle không có giá trị", Toast.LENGTH_SHORT).show();
        }
    }
}