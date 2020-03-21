package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;

public class Activity2 extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(this);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("DRINK",
                new String[]{"_id","NAME"},
                null, null, null, null, null);
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(Activity2.this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1}, 0);
        listView.setAdapter(cursorAdapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Activity2.this, DrinkActivity.class);
                intent.putExtra("drinkNo", position);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }
}
