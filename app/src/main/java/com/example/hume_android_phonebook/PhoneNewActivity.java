package com.example.hume_android_phonebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        final EditText myName = (EditText)findViewById(R.id.edit_name);
        final EditText myNum = (EditText)findViewById(R.id.edit_phone);
        final EditText myCompus = (EditText)findViewById(R.id.edit_compus);
        final EditText myEmail = (EditText)findViewById(R.id.edit_email);
        final EditText myWork = (EditText)findViewById(R.id.edit_work);

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

                String namestr = myName.getText().toString();
                String numstr = myNum.getText().toString();
                String compusstr = myCompus.getText().toString();
                String emailstr = myEmail.getText().toString();
                String workstr = myWork.getText().toString();

                //将键入的数据写入数据库
                ContentValues myvalue = new ContentValues();
                myvalue.put("name",namestr);
                myvalue.put("num",numstr);
                myvalue.put("compus",compusstr);
                myvalue.put("email",emailstr);
                myvalue.put("work",workstr);
                MySQLiteDB mydb = new MySQLiteDB(getApplicationContext());
                mydb.insert(myvalue);

                bundle.putString("name_in",namestr);
                bundle.putString("num_in",numstr);
                bundle.putString("compus_in",compusstr);
                bundle.putString("email_in",emailstr);
                bundle.putString("work_in",workstr);

                intent.putExtras(bundle);
                PhoneNewActivity.this.setResult(RESULT_OK,intent);
                PhoneNewActivity.this.finish();
            }
        });
    }
}
