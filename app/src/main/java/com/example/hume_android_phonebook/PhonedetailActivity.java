package com.example.hume_android_phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tcp on 2014/12/17.
 */
public class PhonedetailActivity extends Activity {
    private String myName;
    private String myNum;
    private int myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_detail);//载入画面
        Bundle bundle = this.getIntent().getExtras();
        myName = bundle.getString("name");//获取数据
        myNum = bundle.getString("num");
        myImage = bundle.getInt("image");

        TextView nametext = (TextView)findViewById(R.id.text_name_show);
        TextView numtext = (TextView)findViewById(R.id.text_num_show);
        ImageView imagetext = (ImageView)findViewById(R.id.text_image_show);

        nametext.setText(myName);
        numtext.setText(myNum);
        imagetext.setImageResource(myImage);

        ImageView image_back = (ImageView)findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PhonedetailActivity.this,PhoneActivity.class);
                PhonedetailActivity.this.setResult(RESULT_CANCELED,intent);
                PhonedetailActivity.this.finish();//关闭此界面
            }
        });
    }
}
