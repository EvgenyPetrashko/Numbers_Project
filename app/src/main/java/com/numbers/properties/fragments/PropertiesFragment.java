package com.numbers.properties.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numbers.properties.PropObject;
import com.numbers.properties.R;
import com.numbers.properties.adapters.PropAdapter;

import java.util.ArrayList;

public class PropertiesFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private View mainView;
    private ImageButton menu_button;
    private RecyclerView rv;
    private ArrayList<PropObject> arrayList;
    private PropAdapter adapter;
    public PropertiesFragment(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mainView = inflater.inflate(R.layout.properties_fragment, container,false);
       init();
       return mainView;
    }

    private void init() {
        menu_button = mainView.findViewById(R.id.prop_menu);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        rv = mainView.findViewById(R.id.prop_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        ArrayListConfigure();
        rv.setAdapter(adapter);
    }

    private void ArrayListConfigure(){
        arrayList = new ArrayList<>();
        String[] prop_paragraphs = getResources().getStringArray(R.array.prop_paragraphs);
        for (int i = 0; i <prop_paragraphs.length ; i++) {
            arrayList.add(new PropObject(prop_paragraphs[i], i));
        }
        adapter = new PropAdapter(arrayList, getContext(), drawerLayout, getActivity());
    }
}
