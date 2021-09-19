package com.example.tada;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EntryActivity extends AppCompatActivity {

    private EditText date;
    private AutoCompleteTextView name;
    private EditText travel;
    private EditText lunch;
    private EditText instrument;
    private Spinner sp;

    private Button submit;

    private ArrayList<String> empName;


    //function
    boolean isInputFieldFill(){
        if(date.getText().toString().isEmpty() || name.getText().toString().isEmpty() || travel.getText().toString().isEmpty() || lunch.getText().toString().isEmpty() || instrument.getText().toString().isEmpty() || sp.getSelectedItemPosition()==0){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        //assign value
        date=findViewById(R.id.etDate);
        name=findViewById(R.id.etName);
        travel=findViewById(R.id.etTravel);
        lunch=findViewById(R.id.etLunch);
        instrument=findViewById(R.id.etInstrument);
        sp=findViewById(R.id.etPaid);
        submit=findViewById(R.id.submit);

        //database object
        DB db=new DB(EntryActivity.this);

        //auto suggest Employee name
        Cursor cursor=db.getEmpNameFromDB();
        System.out.println(cursor.getCount());

        empName=new ArrayList<String>(0);
        while (cursor.moveToNext()){
            empName.add(cursor.getString(0));
        }

        //set auto suggest emp name to xml
        ArrayAdapter adapter1 = new ArrayAdapter(EntryActivity.this, android.R.layout.simple_list_item_1,empName);
        name.setThreshold(0);
        name.setAdapter(adapter1);

        //spinner
        String[] s=new String[]{"Select Paid/Unpaid", "Paid", "Unpaid"};
        ArrayAdapter adapter = new ArrayAdapter(EntryActivity.this, android.R.layout.simple_list_item_1,s);
        sp.setAdapter(adapter);


        //button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInputFieldFill()){
                    String sDate,sName,sTravel,sLunch,sInstrument,sPaid;

                    sDate=date.getText().toString().trim();
                    sName=name.getText().toString().trim();
                    sTravel=travel.getText().toString().trim();
                    sLunch=lunch.getText().toString().trim();
                    sInstrument=instrument.getText().toString().trim();
                    sPaid=sp.getSelectedItem().toString();

                    //Database Call
                    String flag=db.insertRecIntoDB(sDate,sName,sTravel,sLunch,sInstrument,sPaid);

                    Toast.makeText(EntryActivity.this,flag,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EntryActivity.this,"Please Input All the Field",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}