package com.example.realmexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CustomDialogAdd extends android.support.v4.app.DialogFragment {

    Context context;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.row, container, false);

        context=getContext();

        EditText edtName =view.findViewById(R.id.edt_name);
        EditText edtFamily =view.findViewById(R.id.edt_family);
        Button btnAdd=view.findViewById(R.id.buttonAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "btnClicked", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
