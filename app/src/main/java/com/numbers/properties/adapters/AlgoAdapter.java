package com.numbers.properties.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.numbers.properties.AlgoObject;
import com.numbers.properties.R;
import com.numbers.properties.Temp;
import com.numbers.properties.activities.FullVersionActivity;
import com.numbers.properties.fragments.AlgoInfoFragment;

import java.util.ArrayList;

public class AlgoAdapter extends RecyclerView.Adapter<AlgoAdapter.AlhoHolder> {
    private ArrayList<AlgoObject> arrayList;
    private FragmentActivity activity;
    private DrawerLayout drawerLayout;
    public AlgoAdapter(ArrayList<AlgoObject> arrayList, FragmentActivity activity, DrawerLayout drawerLayout){
        this.arrayList = arrayList;
        this.activity = activity;
        this.drawerLayout = drawerLayout;
    }
    @NonNull
    @Override
    public AlhoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlhoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.algo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlhoHolder holder, int position) {
        final AlgoObject temp = arrayList.get(position);
        holder.title.setText(temp.title);
        holder.desc.setText(temp.desc);
        if (temp._id == 10){
            holder.lock.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, FullVersionActivity.class);
                    activity.startActivity(intent);
                }
            });
        }else {
            holder.lock.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlgoInfoFragment fragment = new AlgoInfoFragment(drawerLayout);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", temp._id);
                    bundle.putString("title", temp.title);
                    fragment.setArguments(bundle);
                    Temp.set(5);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AlhoHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private ImageButton lock;
        public AlhoHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.algo_item_title);
            desc = itemView.findViewById(R.id.algo_item_desc);
            lock = itemView.findViewById(R.id.algo_item_lock);
        }
    }
}
