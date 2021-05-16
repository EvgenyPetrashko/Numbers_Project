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

import com.numbers.properties.AlgoObject;
import com.numbers.properties.R;
import com.numbers.properties.adapters.AlgoAdapter;

import java.util.ArrayList;

public class AlgorithmsFragment extends Fragment {
    private View mainView;
    private ImageButton menu_button;
    private DrawerLayout drawerLayout;
    private RecyclerView rv;
    private ArrayList<AlgoObject> arrayList;
    private AlgoAdapter adapter;
    public AlgorithmsFragment(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.algorithms_fragment, container, false);
        init();
        return mainView;
    }

    private void init() {
        menu_button = mainView.findViewById(R.id.algo_menu);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             drawerLayout.open();
            }
        });
        rv = mainView.findViewById(R.id.algo_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        AdapterInitialization();
    }

    private void AdapterInitialization(){
        arrayList = new ArrayList<>();
        String[] array_title = getResources().getStringArray(R.array.algo_paragraphs_title);
        String[] array_desc = getResources().getStringArray(R.array.algo_paragraphs_desc);
        for (int i = 0; i < array_title.length ; i++) {
            arrayList.add(new AlgoObject(array_title[i], array_desc[i], i));
        }
        adapter = new AlgoAdapter(arrayList, getActivity(), drawerLayout);
        rv.setAdapter(adapter);
    }
}
