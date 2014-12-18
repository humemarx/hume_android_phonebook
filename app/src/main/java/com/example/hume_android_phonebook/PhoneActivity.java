package com.example.hume_android_phonebook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
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
    private SimpleAdapter simpeadapter;
    private Button btnDelete,curbtn;   // 删除按钮

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_test);
        myListview = (ListView)findViewById(android.R.id.list);
        myNewfriend = (ImageView)findViewById(R.id.myNewfriend);
        btnDelete = (Button)findViewById(R.id.btn_del);

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
                myitem.put("image", R.drawable.ic_launcher);
                myitem.put("name",bundle_in.getString("name_in"));
                myitem.put("num",bundle_in.getString("num_in"));
                myitem.put("compus",bundle_in.getString("compus_in"));
                myitem.put("email",bundle_in.getString("email_in"));
                myitem.put("work",bundle_in.getString("work_in"));
                myData.add(myitem);
                break;
            case RESULT_CANCELED:
                break;
            default:
                break;
        }
        simpeadapter = new SimpleAdapter(this,myData,R.layout.phone_list,
                new String[]{"image","name","num"},new int[]{R.id.myimage,R.id.mytitle,R.id.mytext});
        setListAdapter(simpeadapter);
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() //点击事件
        {
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

        myListview.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()//长按监听
        {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("长按菜单");
                menu.add(0,0,0,"删除");
                menu.add(0,1,0,"取消删除");
            }
        });

        myListview.setOnTouchListener(new View.OnTouchListener() {
            float x,y,upx,upy;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    x = event.getX();
                    y = event.getY();
                    if(curbtn != null){
                        if(curbtn.getVisibility()==View.VISIBLE){
                            curbtn.setVisibility(View.GONE);
                            return true;
                        }
                    }
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    upx = event.getX();
                    upy = event.getY();
                    int position1 = ((ListView) view).pointToPosition((int)x,(int)y);
                    int position2 = ((ListView) view).pointToPosition((int)upx,(int)upy);
                    if(position1==position2 && Math.abs(x-upx)>20){
                        btnDelete.setVisibility(View.VISIBLE);
                        curbtn = btnDelete;
                        btnDelete.setTag(position1);
                        return true;
                    }
                }
                return false;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curbtn!=null)curbtn.setVisibility(View.GONE);
                int curposition = (int)v.getTag();
                myData.remove(curposition);
                simpeadapter.notifyDataSetChanged();
                myListview.invalidate();
            }
        });
    }
    //长按菜单处理函数
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        setTitle("长按操作");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case 0:
                int selectedPosition = info.position;
                myData.remove(selectedPosition);
                simpeadapter.notifyDataSetChanged();
                myListview.invalidate();
                break;
            case 1:
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    //按钮显示的动画
    private void btnShow(View v){
        v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_show));
    }

    //按钮删除的动画
    private void btnHide(View v){
        v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_hide));
    }
}
