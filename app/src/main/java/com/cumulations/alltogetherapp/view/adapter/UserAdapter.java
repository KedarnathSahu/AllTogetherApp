package com.cumulations.alltogetherapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cumulations.alltogetherapp.R;
import com.cumulations.alltogetherapp.model.Record;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<Record> records;

    public UserAdapter(Context context) {
        this.context = context;
        this.records=new ArrayList<Record>();
    }


    public void updateDataSetChange(List<Record> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        Record record = records.get(i);
        userViewHolder.tv_first_name.setText(record.getFirstName());
        userViewHolder.tv_last_name.setText(record.getLastName());
        userViewHolder.tv_age.setText("" + record.getAge());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_first_name;
        TextView tv_last_name;
        TextView tv_age;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_first_name = itemView.findViewById(R.id.tv_f_name);
            tv_last_name = itemView.findViewById(R.id.tv_l_name);
            tv_age = itemView.findViewById(R.id.tv_age);
        }
    }
}
