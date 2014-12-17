package com.example.hume_android_phonebook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcp on 2014/12/15.
 */
public class PhoneActivity extends ListActivity{
    private ArrayList<Map<String,Object>> myData = new ArrayList<>();
    private ListView myListview = null;
    private ImageView myNewfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_test);
        myListview = (ListView)findViewById(android.R.id.list);
        myNewfriend = (ImageView)findViewById(R.id.myNewfriend);

        myNewfriend.setOnClickListener(new View.OnClickListener() //新建联系人
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneActivity.this,PhoneNewActivity.class);
                PhoneActivity.this.startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        Map<String,Object> myitem = new HashMap<>();
        switch (resultCode){
            case RESULT_OK:
                Bundle bundle_in = data.getExtras();
                String namestr = bundle_in.getString("name_in");
                String numstr = bundle_in.getString("num_in");
                myitem.put("image",R.drawable.ic_launcher);
                myitem.put("name",namestr);
                myitem.put("num",numstr);
//                myitem.put("compus",bundle_in.getString("compus_in"));
//                myitem.put("email",bundle_in.getString("email_in"));
//                myitem.put("work",bundle_in.getString("work_in"));
                myData.add(myitem);
                break;
            case RESULT_CANCELED:
                break;
            default:
                break;
        }
        SimpleAdapter simpeadapter = new SimpleAdapter(this,myData,R.layout.phone_list,
                new String[]{"image","name","num"},new int[]{R.id.myimage,R.id.mytitle,R.id.mytext});
        setListAdapter(simpeadapter);
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();//用于主界面跳转
                intent.setClass(PhoneActivity.this,PhonedetailActivity.class);
                Bundle bundle = new Bundle();//建立bundle对象用于数据传递
                bundle.putString("name",myData.get(position).get("name").toString());
                bundle.putString("num",myData.get(position).get("num").toString());
                bundle.putInt("image",Integer.parseInt(myData.get(position).get("image").toString()));

                intent.putExtras(bundle);//将bundle对象传递给intent
                PhoneActivity.this.startActivityForResult(intent,0);
            }
        });
    }
}
