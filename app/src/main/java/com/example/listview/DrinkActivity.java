package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    TextView textView1,textView2;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        Intent intent = getIntent();
        int drinkNo = intent.getIntExtra("drinkNo",0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(this);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("DRINK",
                                    new String[] {"NAME","DESCRIPTION","FAVOURITE"},
                            "_id = ?",
                                    new String[] {Integer.toString(drinkNo+1)},
                            null,null,null);
        if(cursor.moveToFirst()){
            String nameText = cursor.getString(0);
            String descriptionText = cursor.getString(1);
            if (cursor.getInt(2) == 1){
                checkBox.setChecked(true);
            }
            textView1.setText(nameText);
            textView2.setText(descriptionText);
        }
        cursor.close();
        db.close();
    }
    public void onFavouriteClick(View view){
        int drinkNo = (Integer)getIntent().getIntExtra("drinkNo",0);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        ContentValues contentValues = new ContentValues();
        contentValues.put("FAVOURITE",checkBox.isChecked());
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(DrinkActivity.this);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.update("DRINK",contentValues,"_id = ?",new String[] {Integer.toString(drinkNo+1)});
        db.close();
    }
}
