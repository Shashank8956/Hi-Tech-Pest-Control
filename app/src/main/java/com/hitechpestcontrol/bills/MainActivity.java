package com.hitechpestcontrol.bills;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity{


    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private CustAdapter tempAda;
    private SearchView search;
    private ArrayList<Model> tempMod = new ArrayList<Model>();
    private String tabtitles[] = new String[] { "Home", "Log", "Accounts" }, query = null;
    private EditText edate;
    private String tag;
    private String Year, Months[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_view_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set custom title
        toolbar.setTitle("Hi Tech Pest Control");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),getApplicationContext());
        mPager.setAdapter(mPagerAdapter);

        /*Bundle bundle = new Bundle();
        bundle.putString("params", "My String data");
// set MyFragment Arguments
        Frag1 myObj = new Frag1();
        myObj.setArguments(bundle);*/

        tempMod = new Frag2().getModelList();
        tempAda = new Frag2().getAdapter();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
/*It's worth noting that when using android:onClick on a MenuItem, the function to be called onClick must take one parameter as MenuItem item and not
* View view. The latter is to be used with non item view such as Buttons, TextView, etc.
* Also do not use getActivity in PopupMenu(), use MainActivity.this instead.*/
    public void showPopup(MenuItem item) {
        //Toast.makeText(this, "It's not working!!!", Toast.LENGTH_SHORT).show();
        final View view = findViewById(R.id.action_popup); // SAME ID AS MENU ID
        PopupMenu popup = new PopupMenu(MainActivity.this, view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.backup:
                            backup(view);
                            return true;
                        case R.id.restore:
                            restore(view);
                            return true;
                        case R.id.clear:
                            clear(view);
                            return true;
                        default:
                            return false;
                    }
                }
        });
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    public void chooseYear(View view){
        NumberPicker num = new NumberPicker(this);
        num.setDisplayedValues(new String[] {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"});

    }


    public void Message(View view)
    {
        Context context = getApplicationContext();
        String msg = "Entry added successfully!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast= Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public String getFragmentTag(){
        return tag;
    }

    public void getAccounts(View view)
    {
        Button btn = (Button)view;
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        String monthStr = btn.getText().toString(), sendingValue=null;

        for(int i=0; i<12; i++)
        {
            if(Months[i].equalsIgnoreCase(monthStr))
            {
                if((i+1)<10)
                    sendingValue = "0"+Integer.toString(i+1);
                else
                    sendingValue = Integer.toString(i+1);
                break;
            }
        }
        intent.putExtra("Month", sendingValue);
        intent.putExtra("Year", "2017");
        startActivity(intent);
    }

    void backup(View v){
        Snackbar.make(v, "Backup in progress", Snackbar.LENGTH_LONG).show();
    }


    void restore(View v){
        Snackbar.make(v, "Restoring", Snackbar.LENGTH_LONG).show();
    }

    void clear(final View v){

        String content = "Are you sure you want to delete all records?";
        String title = "Clear all";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(content).setTitle(title);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.app.FragmentManager manager = getFragmentManager();

                ConfirmDialog newFragment = new ConfirmDialog();
                newFragment.show(getFragmentManager(), "Hello");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private Map<Integer, String> mFragmentTags;
        private FragmentManager mFragmentManager;
        private Context mContext;

        public ScreenSlidePagerAdapter(FragmentManager fm, Context con) {
            super(fm);
            mFragmentManager = fm;
            mFragmentTags = new HashMap<Integer, String>();
            mContext = con;
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return new Frag1();
                case 1: return new Frag2();
                case 2: return new Frag3();
                default: return new Frag1();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return tabtitles[pos];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            Object obj = super.instantiateItem(container, position);
            if(obj instanceof Fragment) {
                //Recording the fragment tag here
                Fragment f = (Fragment) obj;
                tag = f.getTag();
                mFragmentTags.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position){
            String tag = mFragmentTags.get(position);
            if(tag == null)
                return null;
            return mFragmentManager.findFragmentByTag(tag);
        }
    }
}
