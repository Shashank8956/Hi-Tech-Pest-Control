package com.hitechpestcontrol.bills;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;


public class Frag1 extends Fragment {


/****************************************EditText******************************************/
    private static EditText tvBill;
    private  EditText tvDate;
    private static EditText tvName;
    private static EditText tvCont;
    private static EditText tvTreat;
    private static EditText tvAmt;
    private static EditText tvChem;
    private static EditText tvTrav;

/*****************************************Strings******************************************/
    private static String sBill;
    private static String sDate;
    private static String sName;
    private static String sCont;
    private static String sTreat;
    private static String sAmt;
    private static String sChem;
    private static String sTrav;
    private static int sProfit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_main, container, false);
        Button myButton = (Button) rootView.findViewById(R.id.btnsub);
        final EditText editText = (EditText)rootView.findViewById(R.id.date);
        final DatePickerDialog.OnDateSetListener DateSetListener;

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day)
            {
                month = month + 1;
                String date = year+"-"+month+"-"+day;
                editText.setText(date);
            }
        };

        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();
                int mYear = cal.get(Calendar.YEAR);
                int mMonth = cal.get(Calendar.MONTH);
                int mDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light,
                        DateSetListener,
                        mYear,mMonth,mDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

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

                    sProfit = Integer.parseInt(sAmt) - Integer.parseInt(sChem) - Integer.parseInt(sTrav);
                    System.out.println("Profit is:" +sProfit);
                    StringBuilder sbDate = new StringBuilder();
                    for(int i=0; i<sDate.length(); i++)
                    {
                        if(i==5) {
                            if(sDate.charAt(i+1)=='-') {
                                sbDate.append('0');
                                sbDate.append(sDate.charAt(i));
                            }
                            else {
                                sbDate.append(sDate.charAt(i));
                                sbDate.append(sDate.charAt(++i));
                            }
                        }
                        else if(i==7 && sDate.length()==8) {
                            sbDate.append('0');
                            sbDate.append(sDate.charAt(i));
                        }
                        else if(i==8 && sDate.length()==9){
                            sbDate.append("0");
                            sbDate.append(sDate.charAt(i));
                        }
                        else
                            sbDate.append(sDate.charAt(i));
                    }
                    sDate = sbDate.toString();
                    System.out.println(sbDate+"  "+sDate);
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


                        ContentValues valuesAcc = new ContentValues();
                        valuesAcc.put("BILL", sBill);
                        valuesAcc.put("DATE", sDate);
                        valuesAcc.put("AMOUNT", sAmt);
                        valuesAcc.put("CHEMICAL", sChem);
                        valuesAcc.put("TRAVEL", sTrav);
                        valuesAcc.put("PROFIT", sProfit);

                        db.insert("AccountTable", null, valuesAcc);
                        Toast.makeText(getContext(), "Done!",
                                Toast.LENGTH_LONG).show();

                    }catch (Exception e)
                    {
                        Toast.makeText(getContext(), e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                    finally{
                        tvBill.setText(null);
                        tvDate.setText(null);
                        tvName.setText(null);
                        tvCont.setText(null);
                        tvTreat.setText(null);
                        tvAmt.setText(null);
                        tvChem.setText(null);
                        tvTrav.setText(null);
                    }

                }
            });

        }
        return rootView;
    }

}
