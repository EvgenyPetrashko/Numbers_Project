package com.numbers.properties.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.numbers.properties.PropObject;
import com.numbers.properties.R;
import com.numbers.properties.Temp;
import com.numbers.properties.activities.FullVersionActivity;
import com.numbers.properties.activities.MainActivity;
import com.numbers.properties.fragments.PropInfoFragment;
import com.numbers.properties.fragments.PropertiesFragment;

import java.util.ArrayList;

public class PropAdapter extends RecyclerView.Adapter<PropAdapter.PropHolder> {
    private ArrayList<PropObject> arrayList;
    private Context context;
    private DrawerLayout drawerLayout;
    private FragmentActivity activity;
    public PropAdapter(ArrayList<PropObject> arrayList, Context context, DrawerLayout drawerLayout, FragmentActivity activity){
        this.arrayList = arrayList;
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.activity = activity;
    }
    @NonNull
    @Override
    public PropHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PropHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prop_item_layout , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PropHolder holder, int position) {
        PropObject temp = arrayList.get(position);
        if (temp._id <= 10) {
            holder.lock.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(OnPropertyClickListener(temp.title, temp._id));
        }else {
            holder.lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullVersionActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        holder.title.setText(arrayList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PropHolder extends RecyclerView.ViewHolder {
        private ImageButton lock;
        private TextView title;
        public PropHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.prop_item_title);
            lock = itemView.findViewById(R.id.prop_item_lock);
        }
    }

    private View.OnClickListener OnPropertyClickListener(final String title, final int id){
     return new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             PropInfoFragment fragment = new PropInfoFragment(drawerLayout);
             Bundle bundle = new Bundle();
             bundle.putInt("id",id);
             bundle.putString("title", title);
             fragment.setArguments(bundle);
             Temp.set(3);
                  activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
         }
     };
    }
}
