package com.example.tyler.warehousemanagement;

/**
 * Created by Owner on 6/19/2017.
 */
import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadWrite {
    Gson gson = new Gson();

    public String getItem(Context context) {
            try

            {
                InputStream inputreader = context.getAssets().open("Data.txt");
                BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputreader));

                // your code here
                String line = new String();
                ArrayList<String> lines = new ArrayList<String>();
                while ((line = buffreader.readLine()) != null) {
                    lines.add(line);
                }
                return line;
            }
            catch (IOException e) {
                return null;
            }
        }
    }

