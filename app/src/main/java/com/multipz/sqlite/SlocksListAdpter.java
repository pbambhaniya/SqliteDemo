package com.multipz.sqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Anil on 8/14/2016.
 */
public class SlocksListAdpter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    private List<BadCategory> slocksList;

    public SlocksListAdpter(Context context, List<BadCategory> slocksList) {
        this.context = context;
        this.slocksList = slocksList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return slocksList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.activity_status_list_rowlayout, parent, false);

            viewHolder.txtitem = (TextView) convertView.findViewById(R.id.txtitem);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            viewHolder.txtitem.setText(slocksList.get(position).getContain());


            viewHolder.ItemName = slocksList.size();

            viewHolder.intent = new Intent("custom-message");
            //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
            viewHolder.intent.putExtra("item",viewHolder.ItemName);
            LocalBroadcastManager.getInstance(context).sendBroadcast(viewHolder.intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        TextView txtitem;
        int ItemName;
        Intent intent;


    }
}




