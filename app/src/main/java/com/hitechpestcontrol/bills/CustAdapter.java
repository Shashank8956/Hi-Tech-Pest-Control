package com.hitechpestcontrol.bills;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.filter;


public class CustAdapter extends RecyclerView.Adapter<CustAdapter.MyViewHolder> implements Filterable{


    private LayoutInflater inflator;
    private static String Tag = "Hahaha";
    public ArrayList<Model> mod = new ArrayList<Model>();
    private ArrayList<Model> mFilteredList = new ArrayList<Model>();
    private CustomFilter filter;

    public CustAdapter(Context con, ArrayList<Model> mod)
    {
        inflator=LayoutInflater.from(con);
        this.mod = mod;
        this.mFilteredList = mod;
        //System.out.println(mod.get(0).getDate());
        Log.d("Size of Mod: " + mod.size(),"!");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //Log.d(Tag, "OnCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(mod.get(position).getName());
        holder.tvTreat.setText(mod.get(position).getTreat());
        holder.tvAmount.setText(Integer.toString(mod.get(position).getAmount()));
        holder.tvDate.setText(mod.get(position).getDate());
        //Log.d(Tag, "OnBindViewHolder: "+ position);
    }

    @Override
    public int getItemCount() {
        return mod.size();
    }


    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(mFilteredList,this);
        }
        return filter;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTreat;
        private TextView tvAmount;
        private TextView tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.RowName);
            tvTreat = (TextView) itemView.findViewById(R.id.RowTreat);
            tvAmount = (TextView) itemView.findViewById(R.id.RowAmt);
            tvDate = (TextView) itemView.findViewById(R.id.RowDate);
        }
    }
}