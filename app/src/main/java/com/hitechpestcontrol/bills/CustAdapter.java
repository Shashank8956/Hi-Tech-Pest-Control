package com.hitechpestcontrol.bills;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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


public class CustAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{


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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = inflator.inflate(R.layout.row, parent, false);
        View view2 = inflator.inflate(R.layout.month_row, parent, false);
        switch (viewType) {
            case 0: return new MyViewHolder(view1);
            case 1: return new MonthRowHolder(view2);
        }
        /*View view = inflator.inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //Log.d(Tag, "OnCreateViewHolder: ");*/
        return new MyViewHolder(view1);
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                MyViewHolder holder1 = (MyViewHolder) holder;
                holder1.tvName.setText(mod.get(position).getName());
                holder1.tvTreat.setText(mod.get(position).getTreat());
                holder1.tvAmount.setText(Integer.toString(mod.get(position).getAmount()));
                holder1.tvDate.setText(mod.get(position).getDate());

                holder1.tvName.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder1.tvName.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder1.tvTreat.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder1.tvTreat.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder1.tvAmount.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder1.tvAmount.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder1.tvDate.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder1.tvDate.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                break;

            case 1:
                MonthRowHolder holder2 = (MonthRowHolder) holder;
                holder2.tvName.setText(mod.get(position).getName());
                holder2.tvTreat.setText(mod.get(position).getTreat());
                holder2.tvAmount.setText(Integer.toString(mod.get(position).getAmount()));
                holder2.tvDate.setText(mod.get(position).getDate());

                holder2.tvName.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder2.tvName.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder2.tvTreat.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder2.tvTreat.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder2.tvAmount.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder2.tvAmount.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                holder2.tvDate.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("key", "Shashank");
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                holder2.tvDate.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(con, "Long Clicked on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                        return true;              //Return true in this case because if we return false, OnClickListener will be called after OnLongClickListner
                    }
                });

                break;
        }



       /*holder.tvName.setText(mod.get(position).getName());
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
        });*/
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

    @Override
    public int getItemViewType(int position) {
        if (position>0) {

            String date1 = mod.get(position).getDate();
            String date2 = mod.get(position - 1).getDate();
            StringBuilder d1, d2;
            d1 = new StringBuilder();
            d2 = new StringBuilder();

            d1.append(date1.charAt(5));
            d1.append(date1.charAt(6));
            d2.append(date2.charAt(5));
            d2.append(date2.charAt(6));
            date1 = d1.toString();
            date2 = d2.toString();

            int dd1, dd2;
            dd1 = Integer.parseInt(date1);
            dd2 = Integer.parseInt(date2);
            if ((dd1 - dd2) == 0)
                return 0;
            else
                return 1;
        }
        else{
            return 0;
        }
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

    class MonthRowHolder extends RecyclerView.ViewHolder {

        private TextView tvRow;
        private TextView tvName;
        private TextView tvTreat;
        private TextView tvAmount;
        private TextView tvDate;

        public MonthRowHolder(View itemView) {
            super(itemView);
            tvRow = (TextView) itemView.findViewById(R.id.MonthRow);
            tvName = (TextView) itemView.findViewById(R.id.RowNameX);
            tvTreat = (TextView) itemView.findViewById(R.id.RowTreatX);
            tvAmount = (TextView) itemView.findViewById(R.id.RowAmtX);
            tvDate = (TextView) itemView.findViewById(R.id.RowDateX);
        }
    }
}