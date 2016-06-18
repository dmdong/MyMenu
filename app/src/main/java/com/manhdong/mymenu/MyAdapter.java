package com.manhdong.mymenu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Saphiro on 6/13/2016.
 */
public class MyAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;
    List<String> name2; //data original
    List<String> data;



    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = objects;
        name2 = new ArrayList<>(data);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
        }
        TextView text = (TextView) convertView;
        text.setText(data.get(position));
        return convertView;
    }



        @Override
    public Filter getFilter() {

       return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filter = new FilterResults();
                List<String> temp = new ArrayList<>();
                if (constraint !=null){
                    String input = constraint.toString().toUpperCase();
                    input = Normalizer.normalize(input, Normalizer.Form.NFD);
                    input = input.replaceAll("\\p{M}", "");
                    Log.d("input", input);
                    input = input.replaceAll("[đ|Đ]", "D");

                    for (String s : name2) {

                        if (s.toUpperCase().contains(input)){
                            temp.add(s);
                        }
                    }
                    filter.values = temp;
                    filter.count = temp.size();
                    notifyDataSetInvalidated();

                }
                //else notifyDataSetInvalidated();


                return filter;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                List<String> temp = new ArrayList<>();
                temp = (List<String>) results.values;
                if (results !=null && results.count>0){
                    data.clear();
                    data.addAll(temp);
                    notifyDataSetChanged();
                }
                else
                {
                    if (constraint!=null){
                      //  data.clear();
                        Toast.makeText(getContext(), "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                    }
                  //  not
//                    notifyDataSetInvalidated();
                     //  setNotifyOnChange(false);
                    //notifyDataSetChanged();
                }
//                notifyDataSetChanged();

            }
        };

    }
}
