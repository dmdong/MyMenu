package com.manhdong.mymenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class layout_2 extends AppCompatActivity {

    ListView listView;
    List nameEn;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_1);
        listView = (ListView) findViewById(R.id.listView);

        initData();
        adapter = new ArrayAdapter<String>(this, R.layout.item_layout, nameEn);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int count;
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
               if (checked){
                   count++;
               }else count--;
               mode.setTitle("Selected "+ count);

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                count = 0;
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.mnDelete){
                    SparseBooleanArray array = new SparseBooleanArray();
                    array = listView.getCheckedItemPositions();

                    List remove = new ArrayList();

                  //  int size = array.size();
                    for (int i = 0; i<array.size(); i++){
                        //keyList.add(array.keyAt(i));
//                        nameEn.remove(array.keyAt(i));
                        if (array.valueAt(i)){
                            remove.add(nameEn.get(array.keyAt(i)));
                        }


                   //     array.delete(array.keyAt(i));
                    }
                    nameEn.removeAll(remove);
            //        nameEn.removeAll(array);


//                    for (int j = 0; j<keyList.size(); j++){
//                        nameEn.remove(keyList.get(j));
//                    }
//                    nameEn.remove(array.keyAt(i));
                    adapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }else if (id == R.id.mnUpdateNew){


                    return true;
                }



                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count = 0;

            }
        });

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

}
