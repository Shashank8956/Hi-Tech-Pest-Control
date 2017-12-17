package com.hitechpestcontrol.bills;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.filter;


public class CustAdapter extends RecyclerView.Adapter<CustAdapter.MyViewHolder> implements Filterable{


    private LayoutInflater inflator;
    private static String Tag = "Hahaha";
    public ArrayList<Model> mod = new ArrayList<Model>();
    private ArrayList<Model> mFilteredList = new ArrayList<Model>();
    private CustomFilter filter;
    private Context con;

    public CustAdapter(Context con, ArrayList<Model> mod)
    {
        inflator=LayoutInflater.from(con);
        this.mod = mod;
        this.mFilteredList = mod;
        this.con = con;
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvName.setText(mod.get(position).getName());
        holder.tvTreat.setText(mod.get(position).getTreat());
        holder.tvAmount.setText(Integer.toString(mod.get(position).getAmount()));
        holder.tvDate.setText(mod.get(position).getDate());

        holder.tvDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                android.app.FragmentManager manager = ((Activity) con).getFragmentManager();
                ListDetailsDialog dia = new ListDetailsDialog();
                dia.show(manager, "ListDialog");
                Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvDate.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
            }
        });
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