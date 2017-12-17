package com.hitechpestcontrol.bills;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class Frag2 extends Fragment implements SearchView.OnQueryTextListener{
    private RecyclerView rcl;
    public CustAdapter ada;
    private String[] columns = {"DATE", "NAME", "TREATMENT", "AMOUNT"};
    private ArrayList<Model> mod = new ArrayList<>();
    private String MainQuery = null;
    private Context mContext;


    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rcl = (RecyclerView) view.findViewById(R.id.RList);

        getInformation();
        //cr.moveToNext();

        ada = new CustAdapter(getActivity(), mod);
        ada.notifyDataSetChanged();
        rcl.setAdapter(ada);
        rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.line_divider));
        rcl.addItemDecoration(dividerItemDecoration);
        //rcl.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rcl.setHasFixedSize(true);

        return view;
    }

    public void RefreshList(ArrayList<Model> tempMod) {
        mod = tempMod;
        ada.notifyDataSetChanged();
    }

    public ArrayList<Model> getModelList()
    {
        return mod;
    }

    public CustAdapter getAdapter()
    {
        return ada;
    }

    public void setSearchQuery(String query)
    {
        MainQuery = query;
    }

    public void getInformation()
    {
        DatabaseHelp mDbHelper = new DatabaseHelp(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //Cursor cr = db.rawQuery("Select date, name, treatment, amount from MainTable", null);
        Cursor cr = db.rawQuery("SELECT Date, Name, Treatment, Amount FROM MainTable ORDER BY date(Date) DESC", null);
        cr.moveToFirst();
        if(cr.getCount()==0)
            Log.d("Fuck! It's ", "empty!");
        else {
            int i = 0;
            do {
                mod.add(new Model(cr.getString(1), cr.getString(2), cr.getString(0), cr.getInt(3)));
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
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Log.d("Looking for: ", newText);
        ada.getFilter().filter(newText);
        return true;
    }
}
