package com.hitechpestcontrol.bills;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spongebob on 12/21/2017.
 */

public class CustAdapterAccounts extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<AccountsModel> mod = new ArrayList<AccountsModel>();
    private CustomFilter filter;
    private Context con;
    private LayoutInflater inflator;

    public CustAdapterAccounts(Context con, ArrayList<AccountsModel> mod)
    {
        inflator= LayoutInflater.from(con);
        this.mod = mod;
        this.con = con;
        Log.d("Size of Mod: " + mod.size(),"!");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = inflator.inflate(R.layout.accounts_row, parent, false);
        View view2 = inflator.inflate(R.layout.profit_row, parent, false);
        switch (viewType) {
            case 0: return new RowHolder(view1);
            case 1: return new LastRowHolder(view2);
        }
        return new RowHolder(view1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                CustAdapterAccounts.RowHolder holder1 = (CustAdapterAccounts.RowHolder) holder;
                //holder1.tvBill.setText(mod.get(position).getBill());
                holder1.tvName.setText(mod.get(position).getCustName());
                //holder1.tvTrav.setText(mod.get(position).getTravelExpense());
                //holder1.tvChem.setText(mod.get(position).getChemicalExpense());
                //holder1.tvTotal.setText(mod.get(position).getTotal());
                //holder1.tvProfit.setText(mod.get(position).getProfit());

                break;

            case 1:
                CustAdapterAccounts.LastRowHolder holder2 = (CustAdapterAccounts.LastRowHolder) holder;
                String temp = "Total Bookings: "+Integer.toString(mod.size());
                holder2.tvLastBooking.setText(temp);
                temp = "Travel: "+"2500";
                holder2.tvLastTrav.setText(temp);
                holder2.tvLastChem.setText("Chemical");
                holder2.tvLastTotal.setText("Total");
                holder2.tvLastPercent.setText("Percent");
                holder2.tvLastProfit.setText("Profit");
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==(mod.size()-1))
            return 1;
        else
            return 0;
    }


    @Override
    public int getItemCount() {
        return mod.size();
    }


    class RowHolder extends RecyclerView.ViewHolder {

        private TextView tvBill;
        private TextView tvName;
        private TextView tvTrav;
        private TextView tvChem;
        private TextView tvTotal;
        private TextView tvProfit;

        public RowHolder(View itemView) {
            super(itemView);
            tvBill = (TextView) itemView.findViewById(R.id.ColBill);
            tvName = (TextView) itemView.findViewById(R.id.ColName);
            tvTrav = (TextView) itemView.findViewById(R.id.ColTrav);
            tvChem = (TextView) itemView.findViewById(R.id.ColChem);
            tvTotal = (TextView) itemView.findViewById(R.id.ColTotal);
            tvProfit = (TextView) itemView.findViewById(R.id.ColProfit);

            //tvName.setOnLongClickListener(frag2);
        }
    }

    class LastRowHolder extends RecyclerView.ViewHolder {

        private TextView tvLastBooking;
        private TextView tvLastTrav;
        private TextView tvLastChem;
        private TextView tvLastTotal;
        private TextView tvLastPercent;
        private TextView tvLastProfit;

        public LastRowHolder(View itemView) {
            super(itemView);
            tvLastBooking = (TextView) itemView.findViewById(R.id.LastBooking);
            tvLastTrav = (TextView) itemView.findViewById(R.id.LastTrav);
            tvLastChem = (TextView) itemView.findViewById(R.id.LastChem);
            tvLastTotal = (TextView) itemView.findViewById(R.id.LastTotal);
            tvLastPercent = (TextView) itemView.findViewById(R.id.LastPercent);
            tvLastProfit = (TextView) itemView.findViewById(R.id.LastProfit);

        }
    }

}
