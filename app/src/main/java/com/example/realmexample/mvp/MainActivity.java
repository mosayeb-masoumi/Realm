package com.example.realmexample.mvp;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.realmexample.MyAdapter;
import com.example.realmexample.R;
import com.example.realmexample.User;
import com.example.realmexample.UserDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    FloatingActionButton fab ,fabDelete;
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<User> userList;


    UserDAO userDAO=new UserDAO();

    String name,family;
    EditText edt_name,edt_family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        presenter.attachView(context, this);

          bindView();

          fab.setOnClickListener(view -> addUser());
          fabDelete.setOnClickListener(view -> adapter.deleteAll());

        //show all list
        userDAO.findAll();
//        userDAO.deleteAll();



//        adapter=new MyAdapter(userList,getApplicationContext());
        adapter=new MyAdapter(userDAO.findAll(),getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);



    }

    private void addUser() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_add_dialog);
        dialog.setTitle("Title...");

        Button btn_add = dialog.findViewById(R.id.buttonAdd);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edt_name = dialog.findViewById(R.id.edt_name);
                edt_family = dialog.findViewById(R.id.edt_family);

                name =edt_name.getText().toString();
                family=edt_family.getText().toString();


                User user = new User();
                user.setName(name);
                user.setFamily(family);

                userDAO.update(user);

                edt_name.setText("");
                edt_family.setText("");

            }
        });

        dialog.show();
    }


    private void bindView() {
        fab=findViewById(R.id.fab);
        fabDelete=findViewById(R.id.fabDelete);
        recyclerView=findViewById(R.id.recyclerView);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }

}
