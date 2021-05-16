package com.numbers.properties.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.numbers.properties.R;

public class AlgoInfoFragment extends Fragment {
    private View mainView;
    private DrawerLayout drawerLayout;
    private ImageButton menu;
    private TextView title;
    private LinearLayout container;
    public AlgoInfoFragment(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.info_item_fragment , container, false);
        init();
        Configure();
        return mainView;
    }

    private void init() {
        menu = mainView.findViewById(R.id.prop_frag_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        title = mainView.findViewById(R.id.prop_frag_item_title);
        container = mainView.findViewById(R.id.prop_frag_content_container);
    }

    private void Configure(){
        Bundle bundle = getArguments();
        int global_id = bundle.getInt("id");
        title.setText(bundle.getString("title", "TITLE"));
        switch (global_id){
            case 0:
                ShowGCD();
                break;
            case 1:
                ShowPrimeAlgo();
                break;
            case 2:
                ShowSieve();
                break;
            case 3:
                ShowFastExp();
                break;
            case 4:
                ShowFibAlg();
                break;
            case 5:
                ShowRGBAlg();
                break;
        }
    }


    //type=0 is text
    //other is image
    private void addViewToContainer(int input, int type){
        View view = null;
        if (type == 0){
            Spanned paste = getSpannedText(getString(input));
            TextView temp = new TextView(getContext());
            temp.setText(paste);
            temp.setTextColor(Color.WHITE);
            temp.setTextSize(18);
            view = temp;
        }else {
            Drawable image = getResources().getDrawable(input);
            ImageView temp = new ImageView(getContext());
            //temp.setBackgroundColor(getResources().getColor(R.color.white));
            temp.setImageDrawable(image);
            view = temp;
        }
        container.addView(view);
    }

    private Spanned getSpannedText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(text);
        }
    }

    private void ShowGCD(){
        addViewToContainer(R.string.gcd_text1,0);
    }

    private void ShowPrimeAlgo(){
        addViewToContainer(R.string.prime_algo_text1, 0);
    }

    private void ShowSieve(){
        addViewToContainer(R.string.sieve_algo_text1,0);
    }

    private void ShowFastExp(){
        addViewToContainer(R.string.fast_exp_text1,0);
    }

    private void ShowFibAlg(){
        addViewToContainer(R.string.fib_main_text1,0);
    }

    private void ShowRGBAlg(){
        addViewToContainer(R.string.rgb_text1,0);
    }
}
