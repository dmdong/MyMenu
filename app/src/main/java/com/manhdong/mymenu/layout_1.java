package com.manhdong.mymenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class layout_1 extends AppCompatActivity {

    ListView listView;
    List<String> nameEn;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_1);
        listView = (ListView) findViewById(R.id.listView);

        initData();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameEn);
        listView.setAdapter(adapter);

        //b2 Đăng ký sử dụng context menu cho listview
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floating_menu, menu);
    }

    private void initData() {
        nameEn = new ArrayList<>();
        nameEn.add("VI");
        nameEn.add("EN");
        nameEn.add("KR");
        nameEn.add("CA");
        nameEn.add("USA");
        nameEn.add("IN");
        nameEn.add("KP");
        nameEn.add("SP");
        nameEn.add("IT");
        nameEn.add("DE");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = 0;
        position = contextMenuInfo.position;

        if (id == R.id.mnDel) {
            Toast.makeText(layout_1.this, "Delete", Toast.LENGTH_SHORT).show();
            nameEn.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.mnUpdate) {
            Toast.makeText(layout_1.this, "Update", Toast.LENGTH_SHORT).show();
            return true;
        }

        return true;
    }

}
