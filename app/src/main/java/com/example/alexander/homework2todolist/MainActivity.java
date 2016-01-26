package com.example.alexander.homework2todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        ListView itemsList = (ListView) findViewById(R.id.itemsList);
        itemsList.setAdapter(listAdapter);

        itemsList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        listAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list_items", items);

        EditText addedText = (EditText) findViewById(R.id.newItemText);
        outState.putString("added_text", "" + addedText.getText());
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        items = inState.getStringArrayList("list_items");

        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        ListView itemsList = (ListView) findViewById(R.id.itemsList);
        itemsList.setAdapter(listAdapter);

        String addingText = inState.getString("added_text");
        EditText addedText = (EditText) findViewById(R.id.newItemText);
        addedText.setText(addingText);
    }

    public void addItem(View view) {
        ListView itemsList = (ListView) findViewById(R.id.itemsList);
        EditText newItem = (EditText) findViewById(R.id.newItemText);

        items.add("" +newItem.getText());
        listAdapter.notifyDataSetChanged();

        newItem.setText("");
    }
}
