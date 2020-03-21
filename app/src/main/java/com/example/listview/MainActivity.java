package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView list, list_favourite;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        list_favourite = (ListView) findViewById(R.id.list_favourite);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View itemView, int position, long id) {
                if(position==0)
                {
                    Intent intent = new Intent(MainActivity.this,Activity2.class);
                    startActivity(intent);
                }
            }
        };
        list.setOnItemClickListener(itemClickListener);
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        cursor = db.query("DRINK",
                        new String[] {"_id","NAME"},
                "FAVOURITE = 1",
                null, null,null,null);
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(MainActivity.this,
                                      android.R.layout.simple_list_item_1,cursor,
                                      new String[] {"NAME"},
                                      new int[] {android.R.id.text1},0);
        list_favourite.setAdapter(cursorAdapter);
    }
    @Override
    public void onRestart(){
        super.onRestart();
        list_favourite = (ListView) findViewById(R.id.list_favourite);
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor newCursor = db.query("DRINK",
                new String[] {"_id","NAME"},
                "FAVOURITE = 1",
                null, null,null,null);
        CursorAdapter adapter = (CursorAdapter) list_favourite.getAdapter();
        adapter.changeCursor(newCursor);
        cursor = newCursor;
    }
}
