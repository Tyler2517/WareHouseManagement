package com.example.tyler.warehousemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Search_Activate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__activate);
    }

    public void doSearch(View view) {
        onSearchRequested();
    }
}
