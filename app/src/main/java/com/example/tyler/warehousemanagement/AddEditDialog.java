package com.example.tyler.warehousemanagement;


import android.view.LayoutInflater;
import android.view.View;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.ViewGroup;

/**
 * Created by Owner on 6/14/2017.
 */

public class AddEditDialog extends DialogFragment {
    int mNum;

    static AddEditDialog newInstance(int num) {
        AddEditDialog f = new AddEditDialog();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");
        setStyle(STYLE_NORMAL, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_edit, container, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }}
