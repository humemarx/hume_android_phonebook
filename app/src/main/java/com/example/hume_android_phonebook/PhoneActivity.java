package com.example.hume_android_phonebook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcp on 2014/12/15.
 */
public class PhoneActivity extends ListActivity{
    private String[] myListTitle = {"张三","李四","王五","赵钱","孙吴","陈一",
            "张三","李四","王五","赵钱","孙吴","陈一"};//姓名
    private String[] myListText = {"15565","45256","546446","4556","155656","45656",
            "15565","45256","546446","4556","155656","45656"};//电话
    private int[] myListImage = {R.drawable.ic_launcher};//图片
    private ArrayList<Map<String,Object>> myData = new ArrayList<>();
    private ListView myListview = null;
    private int length = myListTitle.length;
    private ImageView myNewfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState){
//        myListview = getListView();
//        myListview = (ListView)findViewById(R.id.mylistView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_test);
        myListview = (ListView)findViewById(android.R.id.list);
        myNewfriend = (ImageView)findViewById(R.id.myNewfriend);
        SimpleAdapter simpeadapter = new SimpleAdapter(this,getListdata(),R.layout.phone_list,
                new String[]{"image","title","text"},new int[]{R.id.myimage,R.id.mytitle,R.id.mytext});
        setListAdapter(simpeadapter);
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneActivity.this,PhoneNewActivity.class);
                PhoneActivity.this.startActivity(intent);
//                Toast.makeText(PhoneActivity.this, "您选择了标题:" + myListTitle[position]+"内容:"+myListText[position], Toast.LENGTH_LONG).show();
            }
        });

        myNewfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneActivity.this,PhoneNewActivity.class);
                PhoneActivity.this.startActivity(intent);
                PhoneActivity.this.finish();
            }
        });
    }

    private ArrayList<Map<String,Object>> getListdata()//获取数据的方法
    {
        for(int i=0; i<length; ++i)
        {
            Map<String,Object> item = new HashMap<>();
            item.put("title",myListTitle[i]);
            item.put("text",myListText[i]);
            item.put("image",myListImage[0]);
            myData.add(item);
        }
        return myData;
    }

}
