package com.example.hume_android_phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by tcp on 2014/12/17.
 */
public class PhoneNewActivity extends Activity {
    private Button false_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_new);//载入画面
        false_btn = (Button)findViewById(R.id.false_btn);
        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneNewActivity.this,PhoneActivity.class);
                PhoneNewActivity.this.startActivity(intent);
                PhoneNewActivity.this.finish();
            }
        });
    }
}
