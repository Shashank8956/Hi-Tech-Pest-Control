package com.hitechpestcontrol.bills;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private RecyclerView rclAcc;
    private ImageView emptyImage;
    public CustAdapterAccounts ada;
    private CardView card;
    public ArrayList<AccountsModel> mod = new ArrayList<AccountsModel>();
    private String sum[] = new String[4];
    private int year;
    private String Year, Months[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        card = (CardView) findViewById(R.id.header);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("Month");
        Log.d("Month in Activity2: ", message);

        getInformation(message);
        //View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rclAcc = (RecyclerView) findViewById(R.id.RListAct2);
        emptyImage = (ImageView)findViewById(R.id.empty_list2);

        if(mod.isEmpty()){
            rclAcc.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            emptyImage.setVisibility(View.VISIBLE);
        }else {
            rclAcc.setVisibility(View.VISIBLE);
            card.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.GONE);
            ada = new CustAdapterAccounts(getApplicationContext(), mod, sum);
            ada.notifyDataSetChanged();
            rclAcc.setAdapter(ada);
            rclAcc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
    }

    public void getInformation(String monthIndex)
    {
        DatabaseHelp mDbHelper = new DatabaseHelp(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Log.d("MonthIndex: ", " "+monthIndex);

        Cursor cr = db.rawQuery("SELECT * FROM AccountTable where strftime('%m', DATE) = '"+monthIndex+"' ORDER BY date(Bill) ASC", null);
        cr.moveToFirst();
        if(cr.getCount()==0)
            Log.d("Fuck! It's ", "empty!");
        else {
        do {
            Cursor cName = db.rawQuery("SELECT name from MainTable where Bill = '"+cr.getString(0)+"'", null);
            cName.moveToFirst();
            mod.add(new AccountsModel(Integer.parseInt(cr.getString(0)),
                                      Integer.parseInt(cr.getString(4)),
                                      Integer.parseInt(cr.getString(3)),
                                      Integer.parseInt(cr.getString(5)),
                                      Integer.parseInt(cr.getString(2)),cName.getString(0)));
            cName.close();
        } while (cr.moveToNext());
            cr =db.rawQuery("SELECT sum(Amount), sum(Chemical), sum(Travel), sum(Profit) from AccountTable where strftime('%m', DATE) = '"+monthIndex+"'", null);
            cr.moveToFirst();
            sum[0] = Integer.toString(cr.getInt(0));
            //Log.d("Total Amount: ", sum[0]);
            sum[1] = Integer.toString(cr.getInt(1));
            //Log.d("Total Chemical: ", sum[1]);
            sum[2] = Integer.toString(cr.getInt(2));
            //Log.d("Total Travel: ", sum[2]);
            sum[3] = Integer.toString(cr.getInt(3));
            //Log.d("Total Profit: ", sum[3]);
        }
        cr.close();
        db.close();
    }

}
