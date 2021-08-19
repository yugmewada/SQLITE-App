package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{

    private ArrayList<UserDataHandler> userList;
    private Context context;
    DBHelper dbHelper;

    public CustomAdapter(ArrayList<UserDataHandler>List, Context context){
        this.userList= List;
        this.context= context;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }


    @Override
    public Object getItem(int position) {
        return this.userList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.content_data, null);

            holder= new ViewHolder();
            holder.nullView= convertView.findViewById(R.id.null_ID);
            holder.id= convertView.findViewById(R.id.id_TV);
            holder.fname= convertView.findViewById(R.id.fname_TV);
            holder.lname= convertView.findViewById(R.id.lname_TV);
            holder.email= convertView.findViewById(R.id.email_TV);
            holder.password= convertView.findViewById(R.id.password_TV);
            holder.contact= convertView.findViewById(R.id.contact_TV);

            holder.updateButton= convertView.findViewById(R.id.update_BUTTON);
            holder.deleteButton= convertView.findViewById(R.id.delete_BUTTON);

            holder.updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle= new Bundle();
                    bundle.putString("id", holder.id.getText().toString());
                    bundle.putString("fname", holder.fname.getText().toString());
                    bundle.putString("lname", holder.lname.getText().toString());
                    bundle.putString("email", holder.email.getText().toString());
                    bundle.putString("pass", holder.password.getText().toString());
                    bundle.putString("con", holder.contact.getText().toString());

                    Intent intent= new Intent(context,UpdateActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper= new DBHelper(context);
                    dbHelper.deleteData(holder.id.getText().toString());
                    userList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                }
            });

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        UserDataHandler userDataHandler= userList.get(position);

            holder.id.setText(userDataHandler.getId());
            holder.fname.setText(userDataHandler.getFname());
            holder.lname.setText(userDataHandler.getLname());
            holder.email.setText(userDataHandler.getEmail());
            holder.password.setText(userDataHandler.getPassword());
            holder.contact.setText(userDataHandler.getContact());

        return convertView;
    }

    private static class ViewHolder{
        public TextView nullView;
        public TextView id;
        public TextView fname;
        public TextView lname;
        public TextView email;
        public TextView password;
        public TextView contact;

        public Button deleteButton;
        public Button updateButton;
    }
}
