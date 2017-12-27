package com.hitechpestcontrol.bills;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.filter;


public class CustAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{


    private ActionMode mActionMode;
    private LayoutInflater inflator;
    private static String Tag = "Hahaha";
    public ArrayList<Model> mod = new ArrayList<Model>();
    private ArrayList<Model> mFilteredList = new ArrayList<Model>();
    private CustomFilter filter;
    private Context con;
    private Frag2 frag2;
    private int pos;
    private String Year, Months[] = {"January", "February", "March", "April", "May", "June",
                                     "July", "August", "September", "October", "November", "December"};
    private int MonthPosition = 1;


    public CustAdapter(Context con, ArrayList<Model> mod)
    {
        inflator=LayoutInflater.from(con);
        this.mod = mod;
        this.mFilteredList = mod;
        this.con = con;
        //System.out.println(mod.get(0).getDate());
        //Log.d("Size of Mod: " + mod.size(),"!");
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


                /*holder1.tvName.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();


                        Bundle args = new Bundle();
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
                        ListDetailsDialog newFragment = new ListDetailsDialog();
                        newFragment.setArguments(args);
                        newFragment.show(((Activity) con).getFragmentManager(), "TAG");

                        Toast.makeText(con, "Click on item no: "+ (position+1), Toast.LENGTH_SHORT).show();
                    }
                });

                //holder1.tvName.setOnLongClickListener(frag2);

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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                });*/

                break;

            case 1:
                MonthRowHolder holder2 = (MonthRowHolder) holder;
                holder2.tvRow.setText(Months[MonthPosition-1]+", "+Year);
                holder2.tvName.setText(mod.get(position).getName());
                holder2.tvTreat.setText(mod.get(position).getTreat());
                holder2.tvAmount.setText(Integer.toString(mod.get(position).getAmount()));
                holder2.tvDate.setText(mod.get(position).getDate());

                /*holder2.tvName.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

                        Bundle args = new Bundle();
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                        args.putInt("_BILL", mod.get(position).getBill());
                        args.putString("_DATE", mod.get(position).getDate());
                        args.putString("_NAME", mod.get(position).getName());
                        args.putString("_TREATMENT", mod.get(position).getTreat());
                        args.putString("_CONTACT", mod.get(position).getContact());
                        args.putInt("_AMOUNT", mod.get(position).getAmount());
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
                });*/

                break;
        }
        //Log.d(Tag, "OnBindViewHolder: "+ position);
    }

    @Override
    public int getItemCount() {
        return mod.size();
    }

    public int getPosition() {
        return pos;
    }

    public void setPosition(int position) {
        this.pos = pos;
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
        String date1 = mod.get(position).getDate();
        StringBuilder d1, d2;

        d1 = new StringBuilder();
        d2 = new StringBuilder();
        d1.append(date1.charAt(5));
        d1.append(date1.charAt(6));
        date1 = d1.toString();
        MonthPosition = Integer.parseInt(date1);

        d1.setLength(0);
        d1.append(mod.get(position).getDate().charAt(0));
        d1.append(mod.get(position).getDate().charAt(1));
        d1.append(mod.get(position).getDate().charAt(2));
        d1.append(mod.get(position).getDate().charAt(3));
        Year = d1.toString();

        if (position>0) {
            String date2 = mod.get(position - 1).getDate();

            d2.append(date2.charAt(5));
            d2.append(date2.charAt(6));
            date2 = d2.toString();

            int dd1, dd2;
            dd1 = Integer.parseInt(date1);
            dd2 = Integer.parseInt(date2);
            MonthPosition = dd1;
            if ((dd1 - dd2) == 0)
                return 0;
            else
                return 1;
        }
        else{
            return 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener{

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
            tvName.setOnLongClickListener(frag2);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

            Bundle args = new Bundle();
            args.putInt("_BILL", mod.get(getAdapterPosition()).getBill());
            args.putString("_DATE", mod.get(getAdapterPosition()).getDate());
            args.putString("_NAME", mod.get(getAdapterPosition()).getName());
            args.putString("_TREATMENT", mod.get(getAdapterPosition()).getTreat());
            args.putString("_CONTACT", mod.get(getAdapterPosition()).getContact());
            args.putInt("_AMOUNT", mod.get(getAdapterPosition()).getAmount());
            ListDetailsDialog newFragment = new ListDetailsDialog();
            newFragment.setArguments(args);
            newFragment.show(((Activity) con).getFragmentManager(), "TAG");

            Toast.makeText(con, "Click on item no: "+ (getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {

            Toast.makeText(con, "Click on long click item no: "+ (getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    class MonthRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
    View.OnLongClickListener{

        private TextView tvName;
        private TextView tvTreat;
        private TextView tvAmount;
        private TextView tvDate;
        private TextView tvRow;

        public MonthRowHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.RowNameX);
            tvTreat = (TextView) itemView.findViewById(R.id.RowTreatX);
            tvAmount = (TextView) itemView.findViewById(R.id.RowAmtX);
            tvDate = (TextView) itemView.findViewById(R.id.RowDateX);
            tvRow = (TextView) itemView.findViewById(R.id.MonthRow);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View v) {
            android.app.FragmentManager manager = ((Activity) con).getFragmentManager();

            Bundle args = new Bundle();
            args.putInt("_BILL", mod.get(getAdapterPosition()).getBill());
            args.putString("_DATE", mod.get(getAdapterPosition()).getDate());
            args.putString("_NAME", mod.get(getAdapterPosition()).getName());
            args.putString("_TREATMENT", mod.get(getAdapterPosition()).getTreat());
            args.putString("_CONTACT", mod.get(getAdapterPosition()).getContact());
            args.putInt("_AMOUNT", mod.get(getAdapterPosition()).getAmount());
            ListDetailsDialog newFragment = new ListDetailsDialog();
            newFragment.setArguments(args);
            newFragment.show(((Activity) con).getFragmentManager(), "TAG");

            Toast.makeText(con, "Click on item no: "+ (getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {

            Toast.makeText(con, "Click on long click item no: "+ (getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}