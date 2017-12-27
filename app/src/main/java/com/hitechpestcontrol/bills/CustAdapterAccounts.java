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

import java.util.ArrayList;

/**
 * Created by Spongebob on 12/21/2017.
 */

public class CustAdapterAccounts extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<AccountsModel> mod = new ArrayList<AccountsModel>();
    private CustomFilter filter;
    private Context con;
    private String[] sum;
    private LayoutInflater inflator;

    public CustAdapterAccounts(Context con, ArrayList<AccountsModel> mod, String[] sum)
    {
        inflator= LayoutInflater.from(con);
        this.mod = mod;
        this.con = con;
        this.sum = sum;
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
                String Rupees = con.getString(R.string.Rs);
                holder1.tvBill.setText(Integer.toString(mod.get(position).getBill()));
                holder1.tvName.setText(mod.get(position).getCustName());
                holder1.tvTrav.setText(Rupees +" "+ Integer.toString(mod.get(position).getTravelExpense()));
                holder1.tvChem.setText(Rupees +" "+ Integer.toString(mod.get(position).getChemicalExpense()));
                holder1.tvTotal.setText(Rupees +" "+ Integer.toString(mod.get(position).getTotal()));
                holder1.tvProfit.setText(Rupees +" "+ Integer.toString(mod.get(position).getProfit()));
                break;

            case 1:
                CustAdapterAccounts.LastRowHolder holder2 = (CustAdapterAccounts.LastRowHolder) holder;
                Rupees = con.getString(R.string.Rs);
                String temp = "Total Bookings: "+Integer.toString(mod.size());
                holder2.tvLastBooking.setText(temp);
                temp = Rupees+" "+"2500";
                double percent =0.0;
                System.out.println("Profit amount: "+ Integer.parseInt(sum[3]));
                System.out.println("Total amount: "+ Integer.parseInt(sum[0]));
                percent = (((float)Integer.parseInt(sum[3])/Integer.parseInt(sum[0])))*100;
                String str = String.format("%2.02f", percent);
                Log.d("Percent", " "+str);
                holder2.tvLastTrav.setText("Trav: "+sum[2]);
                holder2.tvLastChem.setText("Chem: "+ sum[1]);
                holder2.tvLastTotal.setText("Total: "+ sum[0]);
                holder2.tvLastPercent.setText(str+"%");
                holder2.tvLastProfit.setText("Profit: "+sum[3]);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==(mod.size()))
            return 1;
        else
            return 0;
    }


    @Override
    public int getItemCount() {
        int i = mod.size() +1;
        return i;
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
