package com.example.tada;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history);

        lv=findViewById(R.id.listViewId);

        //initial Arraylist
        ArrayList<String> date=new ArrayList<String>(0);
        ArrayList<String> name=new ArrayList<String>(0);
        ArrayList<String> travel=new ArrayList<String>(0);
        ArrayList<String> lunch=new ArrayList<String>(0);
        ArrayList<String> instrument=new ArrayList<String>(0);
        ArrayList<Boolean> paid=new ArrayList<Boolean>(0);

        date.add("43/12/21");
        name.add("Mx");
        travel.add("323");
        lunch.add("122");
        instrument.add("612");
        paid.add(true);

        //initial database
        DB db=new DB(HistoryActivity.this);
        Cursor cursor=db.getHistoryFromDB();

        //assign data
        while (cursor.moveToNext()){
            date.add(cursor.getString(0));
            name.add(cursor.getString(1));
            travel.add(cursor.getString(2));
            lunch.add(cursor.getString(3));
            instrument.add(cursor.getString(4));
            paid.add(cursor.getString(5).matches("Paid") ? true : false);
        }

        Adapter adapter =new Adapter(HistoryActivity.this,date,name,travel,lunch,instrument,paid);
        lv.setAdapter(adapter);
    }
}