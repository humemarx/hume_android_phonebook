package com.example.hume_android_phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tcp on 2014/12/19.
 * 自定义适配器类
 */
public class ListViewAdpter extends BaseAdapter {
    public ArrayList<Map<String,Object>>data;
    private Context context;
    private Button btn_del;
    private View view;

    public ListViewAdpter(ArrayList<Map<String,Object>> data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int arg0){
        return data.get(arg0);
    }
    @Override
    public long getItemId(int arg0){
        return arg0;
    }

    @Override
    public View getView(final int position,View convertView,ViewGroup parent){
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.phone_list,null);
            holder = new ViewHolder();
            holder.button = (Button) convertView.findViewById(R.id.mybutton);
            holder.friend_image = (ImageView) convertView.findViewById(R.id.myimage);
            holder.friend_name = (TextView) convertView.findViewById(R.id.mytitle);
            holder.friend_num = (TextView) convertView.findViewById(R.id.mytext);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setOnTouchListener(new View.OnTouchListener() {
            float downX,upX,downY,upY;
//            int position1,position2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final ViewHolder holder = (ViewHolder) v.getTag();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        if (btn_del!=null){
                            btn_del.setVisibility(View.GONE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        upX = event.getX();
                        upY = event.getY();
//                        position1 = ((ListView) view).pointToPosition((int)downX,(int)downY);
//                        position2 = ((ListView) view).pointToPosition((int)upX,(int)upY);
                        break;
                }

                if(holder.button!=null){
                    if((downX-upX)>50){
                        holder.button.setVisibility(View.VISIBLE);
                        btn_del = holder.button;
                        view = v;
                        return true;
                    }
                    else if((upX-downX)>50){
                        holder.button.setVisibility(View.GONE);
                        btn_del = holder.button;
                        view = v;
                        return true;
                    }
                    return false;
                }
                return false;
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_del!=null){
                    btn_del.setVisibility(View.GONE);
                    data.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        holder.friend_image.setImageResource((Integer)data.get(position).get("image"));
        holder.friend_name.setText((String)data.get(position).get("name"));
        holder.friend_num.setText((String)data.get(position).get("num"));//适配数据
        return convertView;
    }

    static class ViewHolder {
        ImageView friend_image;
        TextView friend_name;
        TextView friend_num;
        Button button;  //删除按钮
    }
}
