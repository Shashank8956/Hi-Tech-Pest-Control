package com.hitechpestcontrol.bills;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;


public class Frag1 extends Fragment {


/****************************************EditText***********/
    private static EditText tvBill;
    private static EditText tvDate;
    private static EditText tvName;
    private static EditText tvCont;
    private static EditText tvTreat;
    private static EditText tvAmt;
    private static EditText tvChem;
    private static EditText tvTrav;

/*****************************************Strings***********/
    private static String sBill;
    private static String sDate;
    private static String sName;
    private static String sCont;
    private static String sTreat;
    private static String sAmt;
    private static String sChem;
    private static String sTrav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_main, container, false);


        Button myButton = (Button) rootView.findViewById(R.id.btnsub);
        if (myButton != null) {
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tvBill = (EditText) rootView.findViewById(R.id.bill);
                    tvDate = (EditText) rootView.findViewById(R.id.date);
                    tvName = (EditText) rootView.findViewById(R.id.name);
                    tvCont = (EditText) rootView.findViewById(R.id.contact);
                    tvTreat = (EditText) rootView.findViewById(R.id.treat);
                    tvAmt = (EditText) rootView.findViewById(R.id.amt);
                    tvChem = (EditText) rootView.findViewById(R.id.chem);
                    tvTrav = (EditText) rootView.findViewById(R.id.trav);

                    sBill  = tvBill.getText().toString();
                    sDate  = tvDate.getText().toString();
                    sName  = tvName.getText().toString();
                    sCont  = tvCont.getText().toString();
                    sTreat = tvTreat.getText().toString();
                    sAmt   = tvAmt.getText().toString();
                    sChem  = tvChem.getText().toString();
                    sTrav  = tvTrav.getText().toString();
                    try {
                        DatabaseHelp mDbHelper = new DatabaseHelp(getContext());
                        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
                        ContentValues values = new ContentValues();
                        values.put("BILL", sBill);
                        values.put("DATE", sDate);
                        values.put("NAME", sName);
                        values.put("TREATMENT", sTreat);
                        values.put("CONTACT", sCont);
                        values.put("AMOUNT", sAmt);

                        db.insert("MainTable", null, values);

                        int profit;
                        profit = Integer.parseInt(sAmt) - Integer.parseInt(sChem) - Integer.parseInt(sTrav);
                        System.out.println(profit);

                        ContentValues valuesAcc = new ContentValues();
                        values.put("BILL", sBill);
                        values.put("DATE", sDate);
                        values.put("AMOUNT", sAmt);
                        values.put("CHEMICAL", sChem);
                        values.put("TRAVEL", sTrav);
                        values.put("PROFIT", profit);

                        db.insert("AccountTable", null, valuesAcc);
                        Toast.makeText(getContext(), "Done!",
                                Toast.LENGTH_LONG).show();
                    }catch (Exception e)
                    {
                        Toast.makeText(getContext(), e.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
        return rootView;
    }
}
