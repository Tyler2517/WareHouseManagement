package com.example.tyler.warehousemanagement;

/**
 * Created by Tyler on 6/5/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IDTab extends Fragment {
    List<Item> Data;
    List<Item> IDData;

    public IDTab(List<Item> incIDData){
        Data = incIDData;
        IDData = new ArrayList<>(Data);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        // Sorting
        Collections.sort(IDData, new Comparator<Item>() {
            @Override
            public int compare(Item item2, Item item1)
            {

                return  item2.ID.compareTo(item1.ID);
            }
        });
        String[] Practice = new String[IDData.size()];
        for(int i = 0; i < IDData.size();i++) {
            Practice[i] = IDData.get(i).Name + " - " + IDData.get(i).ID + " - " + IDData.get(i).Location + " - " + IDData.get(i).Condition;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Practice);
        ListView list = (ListView) rootView.findViewById(R.id.ListViewTab1);
        list.setAdapter(adapter);
        return rootView;
    }
    private void populateListView() {
    }
}