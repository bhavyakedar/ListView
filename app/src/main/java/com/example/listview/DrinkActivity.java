package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        Intent intent = getIntent();
        int drinkNo = intent.getIntExtra("drinkNo",0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(this);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("DRINK",
                                    new String[] {"NAME","DESCRIPTION"},
                            "_id = ?",
                                    new String[] {Integer.toString(drinkNo+1)},
                            null,null,null);
        if(cursor.moveToFirst()){
            String nameText = cursor.getString(0);
            String descriptionText = cursor.getString(1);
            textView1.setText(nameText);
            textView2.setText(descriptionText);
        }
        cursor.close();
        db.close();
    }
}
