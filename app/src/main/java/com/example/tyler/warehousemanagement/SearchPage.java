package com.example.tyler.warehousemanagement;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchPage extends ListActivity {

    public static final String tag = "inside search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("testing", "entered searchPage.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        //Get intent verify action and get query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(query);
            doSearch(query);
        }
    }

    private void doSearch(String query) {

    }
}
