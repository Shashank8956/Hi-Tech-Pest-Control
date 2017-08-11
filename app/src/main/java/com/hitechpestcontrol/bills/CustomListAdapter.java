package com.hitechpestcontrol.bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spongebob on 8/2/2017.
 */

public class CustomListAdapter extends BaseAdapter {

    private String[] name;
    private String[] treat;
    private String[] amt;
    private String[] date;
    private Context con;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context con, String[] name, String[] treat, String[] date, String[] amt)
    {
        layoutInflater = LayoutInflater.from(con);
        this.con = con;
        this.name = name;
        this.date = date;
        this.treat = treat;
        this.amt = amt;
    }

    @Override
    public int getCount() {
        //Return the no of items in the list
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row, null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.RowName);
        TextView tv_treat = (TextView) convertView.findViewById(R.id.RowTreat);
        TextView tv_date = (TextView) convertView.findViewById(R.id.RowDate);
        TextView tv_amt = (TextView) convertView.findViewById(R.id.RowAmt);

        tv_name.setText(name[position]);
        tv_treat.setText(treat[position]);
        tv_date.setText(date[position]);
        tv_amt.setText(amt[position]);
        return convertView;
    }
}