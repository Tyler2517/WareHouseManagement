package com.example.tyler.warehousemanagement;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends ListActivity {

    public static final String tag = "inside search";
    public List<Item> Inventory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(tag, "entered searchPage.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                getString(R.string.preferences_file), Context.MODE_PRIVATE);
        String dataToSearch = sharedPreferences.getString("data", "data is empty");
        Inventory = JSONConv(dataToSearch);

        //Get intent verify action and get query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(query);
            List<Item> Results = doSearch(query);
            String[] Practice = new String[Results.size()];
            for(int i = 0; i < Results.size();i++) {
                Practice[i] = Results.get(i).Name + " - " + Results.get(i).ID + " - " + Results.get(i).Location + " - " + Results.get(i).Condition;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Practice);
            setListAdapter(adapter);
        }
    }

    private List<Item> doSearch(String query) {
        List<Item> Results = new ArrayList<>();
        for (int i = 0; i < Inventory.size(); i++) {
            String tempName = Inventory.get(i).Name;
            String tempID = Inventory.get(i).ID;
            String tempCond = Inventory.get(i).Condition;
            String tempLoc = Inventory.get(i).Location;
            if (query.equalsIgnoreCase(tempName)) {
                Results.add(Inventory.get(i));
            }
            else if (query.equals(tempID)) {
                Results.add(Inventory.get(i));
            }
            else if (query.equalsIgnoreCase(tempCond)) {
                Results.add(Inventory.get(i));
            }
            else if (query.equalsIgnoreCase(tempLoc)) {
                Results.add(Inventory.get(i));
            }
        }
        return Results;
    }

    public List<Item> JSONConv(String item){
        List<Item> Inventory = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(item);
            JSONArray inventory = object.getJSONArray("Warehouse");
            for (int i = 0; i < inventory.length(); i++) {
                Item temp = new Item();
                JSONObject jsonInventory = inventory.getJSONObject(i);
                temp.Name = jsonInventory.getString("Name");
                temp.ID = jsonInventory.getString("ID");
                temp.Condition = jsonInventory.getString("Condition");
                temp.Location = jsonInventory.getString("Location");
                Inventory.add(i,temp);
                //Toast.makeText(MainActivity.this, Inventory.get(0).Name, Toast.LENGTH_SHORT).show();
            }
            return Inventory;
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SearchPage.this, "Failure to load JSON", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e)
        {
            Toast.makeText(SearchPage.this, "Failure to load crap", Toast.LENGTH_SHORT).show();
        }

        return Inventory;
    }
}
