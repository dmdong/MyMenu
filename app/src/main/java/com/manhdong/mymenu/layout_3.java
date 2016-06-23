package com.manhdong.mymenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class layout_3 extends AppCompatActivity {

    AutoCompleteTextView auto;
    ListView sListView;
    List<String> name2;
    MyAdapter adapter;
    //List data;
    //List<String> display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_3);
        initData();
        List<String> autolist = new ArrayList<>(name2);
        auto = (AutoCompleteTextView) findViewById(R.id.autoSuggest);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_layout, autolist);
//        auto.setAdapter(adapter2);
        MyAdapter2 adapter2 = new MyAdapter2(this, R.layout.item_layout , autolist);
        auto.setAdapter(adapter2);

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                auto.setFocusable(true);
//            }
//        });
//        auto.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                auto.setFocusable(false);
//            }
//        });
        sListView = (ListView) findViewById(R.id.slistView);

   //
        adapter = new MyAdapter(this,R.layout.item_layout,name2);
        sListView.setAdapter(adapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem mnSearch = menu.findItem(R.id.mnSearch);
        final SearchView searchView = (SearchView) mnSearch.getActionView();
        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
             //   if (newText != null) {
                    adapter.getFilter().filter(newText);
                    return true;
               // }
//                else {
//                    adapter.notifyDataSetInvalidated();
//                    return false;
//                }
            }
        });
        return true;
    }

    //@Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if(id == R.id.mnSearch){
//
//
//
//
//        }
//
//        return false;
//    }

    private void initData() {
        name2 = new ArrayList<>();
        name2.add("VI");
        name2.add("EN");
        name2.add("KR");
        name2.add("CA");
        name2.add("USA");
        name2.add("IN");
        name2.add("KP");
        name2.add("SP");
        name2.add("IT");
        name2.add("DE");

    }
}
