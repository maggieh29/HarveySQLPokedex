package com.example.harveypokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class DatabaseActivity extends AppCompatActivity {

    ListView data;
    Cursor mCursor;
    TextView selectedTV;
    Button delete;
    LinkedList<String> pokemon;
    String after = "";


    AdapterView.OnItemClickListener listen = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectedTV.setText("Selected Item: " + pokemon.get(i));
            //selectedTV.setText(" "+ i);

            //parse string for the national number, assign to a string
            String before = pokemon.get(i);

           // boolean h = true;
                for (int d = 0; d < 4; d++){
                    if(Character.isDigit(before.charAt(d))){
                        after = after + before.charAt(d);
                    }
                }

            //Toast.makeText(getApplicationContext(), after, Toast.LENGTH_LONG).show();

        }
    };

    View.OnClickListener del = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String mSelectionClause = SQLContentProvider.COL_NATNUM + " = ? ";

            String[] mSelectionArgs = {after.toString()};

            int mRowsDeleted = getContentResolver().delete(SQLContentProvider.contentURI, mSelectionClause,
                    mSelectionArgs);

            updateList();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        data = findViewById(R.id.DatabaseList);
        data.setOnItemClickListener(listen);

        selectedTV = findViewById(R.id.selected);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(del);


        updateList();

    }

    public void updateList(){
        mCursor = getContentResolver().query(
                SQLContentProvider.contentURI, null, null,
                null, null);
        pokemon = new LinkedList<>();
        if (mCursor != null) {
            mCursor.moveToFirst();
            if (mCursor.getCount() > 0) {
                while(mCursor.isAfterLast() == false) {
                    String nn = mCursor.getString(1);
                    String name = mCursor.getString(2);
                    String spec = mCursor.getString(3);
                    String g = mCursor.getString(4);
                    String h = mCursor.getString(5);
                    String w = mCursor.getString(6);
                    String l = mCursor.getString(7);
                    String hp = mCursor.getString(8);
                    String a = mCursor.getString(9);
                    String d = mCursor.getString(10);
                    pokemon.add(new String(nn + ", " + name + ", " + spec + ", " +
                            g + ", " + h + ", " + w + ", " + l + ", " + hp + ", " + a  + ", " + d));
                    mCursor.moveToNext();
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemon);
        data.setAdapter(adapter);
    }
}