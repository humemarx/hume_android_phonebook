package com.example.hume_android_phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tcp on 2014/12/17.
 */
public class PhoneNewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_new);//载入画面

        EditText myName = (EditText)findViewById(R.id.edit_name);
        EditText myNum = (EditText)findViewById(R.id.edit_phone);
        EditText myCompus = (EditText)findViewById(R.id.edit_compus);
        EditText myEmail = (EditText)findViewById(R.id.edit_email);
        EditText myWork = (EditText)findViewById(R.id.edit_work);

        final String namestr = myName.getText().toString();
        final String numstr = myNum.getText().toString();
        final String compusstr = myCompus.getText().toString();
        final String emailstr = myEmail.getText().toString();
        final String workstr = myWork.getText().toString();

        Button false_btn = (Button)findViewById(R.id.false_btn);
        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneNewActivity.this,PhoneActivity.class);
                PhoneNewActivity.this.setResult(RESULT_FIRST_USER,intent);
                PhoneNewActivity.this.finish();
            }
        });

        Button ok_btn = (Button)findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneNewActivity.this,PhoneActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name_in",namestr);
                bundle.putString("num_in",numstr);
//                bundle.putString("compus_in",compusstr);
//                bundle.putString("email_in",emailstr);
//                bundle.putString("work_in",workstr);
                intent.putExtras(bundle);
                PhoneNewActivity.this.setResult(RESULT_OK,intent);
                PhoneNewActivity.this.finish();
            }
        });
    }
}
