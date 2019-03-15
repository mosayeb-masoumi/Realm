package com.example.realmexample;




import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<User> userModel;
    private Context context;


    public MyAdapter(RealmResults<User> all, Context context) {
        this.userModel=all;
        this.context = context;
    }


    public  void deleteAll(){
        UserDAO userDAO=new UserDAO();
        userDAO.deleteAll();
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         User model = userModel.get(position);
         holder.txt_name.setText(model.getName());
         holder.txt_family.setText(model.getFamily());

         holder.itemView.setOnClickListener(view -> {

//             final Dialog dialog = new Dialog(context);
//             dialog.setContentView(R.layout.inflate_row_delete_update);
//             dialog.setTitle("Title...");


            final Dialog dialog = new Dialog(context);
             dialog.setContentView(R.layout.inflate_row_delete_update);
             dialog.setTitle(".....");



//                 Button btn_delete_row = dialog2.findViewById(R.id.buttonDelete_row_delete_update);
//                 Button btn_update_row = dialog2.findViewById(R.id.buttonUpdate_row_delete_update);
//
//                 EditText edtNameRow= dialog2.findViewById(R.id.edt_name_row_delete_update);
//                 EditText edtFamilyRow=dialog2.findViewById(R.id.edt_family_row_delete_update);

//                 edtNameRow.setText(userModel.get(position).getName());
//                 edtFamilyRow.setText(userModel.get(position).getFamily());


             Toast.makeText(context, String.valueOf(userModel.get(position)), Toast.LENGTH_SHORT).show();



             dialog.show();
         });

    }

    @Override
    public int getItemCount() {
        return userModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_family;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txt_name=itemView.findViewById(R.id.row_name);
            txt_family=itemView.findViewById(R.id.row_family);
        }
    }
}
