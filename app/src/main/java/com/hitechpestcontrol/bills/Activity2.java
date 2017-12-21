package com.hitechpestcontrol.bills;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private RecyclerView rclAcc;
    public CustAdapterAccounts ada;
    public ArrayList<AccountsModel> mod = new ArrayList<AccountsModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rclAcc = (RecyclerView) findViewById(R.id.RListAct2);

        getInformation();
        ada = new CustAdapterAccounts(getApplicationContext(), mod);
        ada.notifyDataSetChanged();
        rclAcc.setAdapter(ada);
        rclAcc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = "hahahaha";

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.txtAct2);
        //textView.setText(message);
    }

    public void getInformation()
    {
        //DatabaseHelp mDbHelper = new DatabaseHelp(getApplicationContext());
        //SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String str = "Arrivederci!";
        //Cursor cr = db.rawQuery("Select date, name, treatment, amount from MainTable", null);
        //Cursor cr = db.rawQuery("SELECT * FROM MainTable ORDER BY date(Date) DESC", null);
        //cr.moveToFirst();
        //if(cr.getCount()==0)
        //    Log.d("Fuck! It's ", "empty!");
        //else {
            int i = 0;
            do {
                mod.add(new AccountsModel(i,i,i,i,i,str));
                //Log.d("Lets see the name: ", mod.get(i).getName());
                i++;
            /*date.add(cr.getString(0));
            //System.out.println(cr.getString(0));
            names.add(cr.getString(1));
            //names.add(cr.getString(0));
            treatment.add(cr.getString(2));
            amount.add(cr.getInt(3));*/
                //System.out.println(amount.get(0));
                //System.out.println(amount.get(1));
            } while (i<10);
        //}
        //cr.close();
        //db.close();
    }

}
