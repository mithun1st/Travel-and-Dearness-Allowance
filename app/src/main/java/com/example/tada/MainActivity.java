package com.example.tada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button tadaEntry,tadaHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Mahadi Hassan MITHUN",Toast.LENGTH_SHORT).show();

        //initial button
        tadaEntry=findViewById(R.id.tadaEntry);
        tadaHistory=findViewById(R.id.tadaHistory);


        //entry click listener
        tadaEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, EntryActivity.class);
                startActivity(i);
            }
        });

        //history click listener
        tadaHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);
            }
        });
    }
}