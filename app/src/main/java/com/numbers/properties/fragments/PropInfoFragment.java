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

public class PropInfoFragment extends Fragment {
    private View mainView;
    private DrawerLayout drawerLayout;
    private ImageButton menu;
    private TextView title;
    private LinearLayout container;
    private int id;
    public PropInfoFragment(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.info_item_fragment, container, false);
        init();
        Configure();
        return mainView;
    }

    private void init(){
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
        title.setText(bundle.getString("title", "Title"));
        id = bundle.getInt("id", 0);
        switch (id){
            case 0:
                ShowDivisibility();
                break;
            case 1:
                ShowSequences();
                break;
            case 2:
                ShowNumberSystems();
                break;
            case 3:
               ShowNumberKinds();
                break;
            case 4:
                ShowTrigProp();
                break;
            case 5:
                ShowLogProp();
                break;
            case 6:
                ShowPowProp();
                break;
            case 7:
                ShowDerProp();
                break;
            case 8:
                ShowIntegralProp();
                break;
            case 9:
                ShowProbability();
                break;
        }

    }

    private TextView getTextView(int id, int margin_start, int margin_top, int margin_end, int margin_bottom){
        Spanned paste = getSpannedText(getString(id));
        TextView temp = new TextView(getContext());
        temp.setText(paste);
        temp.setTextColor(Color.WHITE);
        temp.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin_start,margin_top,margin_end,margin_bottom);
        temp.setLayoutParams(params);
        return temp;
    }

    private ImageView getImageView(int id, int margin_start, int margin_top, int margin_end, int margin_bottom){
        Drawable image = getResources().getDrawable(id);
        ImageView temp = new ImageView(getContext());
        //temp.setBackgroundColor(getResources().getColor(R.color.invisible));
        temp.setBackgroundColor(getResources().getColor(R.color.white));
        temp.setImageDrawable(image);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin_start,margin_top,margin_end,margin_bottom);
        temp.setLayoutParams(params);
        return temp;
    }

    private void addViewToContainer(View input){
        container.addView(input);
    }

    private Spanned getSpannedText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(text);
        }
    }

    private void ShowDivisibility(){
        addViewToContainer(getTextView(R.string.div_crit_text, 0 , 0 ,0, 0));

    }

    private void ShowSequences(){
        addViewToContainer(getTextView(R.string.sequences_1, 0 ,0 ,0 ,0));
        addViewToContainer(getImageView(R.drawable.arithmetic_progression_main_formula, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequences_2, 0 , 0 ,0,0));
        addViewToContainer(getImageView(R.drawable.arithmetic_progression_sum_formaula1, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequences_3, 0, 0,0, 0));
        addViewToContainer(getImageView(R.drawable.arithmetic_progression_sum_formaula2, 0 ,0, 0, 0));
        addViewToContainer(getTextView(R.string.sequences_4, 0, 50 , 0 ,0));
        addViewToContainer(getImageView(R.drawable.geometric_progression_main_formula, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequnces_5, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequence_6, 0 , 0 ,0, 0));
        addViewToContainer(getImageView(R.drawable.geometric_progression_formula1, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequence_7, 0,0,0,0));
        addViewToContainer(getImageView(R.drawable.geometric_progression_formula2,0,0,0,0));
        addViewToContainer(getTextView(R.string.sequence_8, 0, 50, 0, 0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_main_formula, 0, 0, 0 ,0));
        addViewToContainer(getTextView(R.string.sequence_9, 0 ,0,0,0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_formula1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_formula2, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequence_10,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_formula3, 0, 0, 0, 0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_formula4, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.sequence_11, 0,0,0,0));
        addViewToContainer(getImageView(R.drawable.fibonacci_sequence_formula5,0,0,0,0));
    }

    private void ShowNumberSystems(){
        addViewToContainer(getTextView(R.string.num_systems_text, 0 ,0,0,0));
    }

    private void ShowNumberKinds(){
        addViewToContainer(getTextView(R.string.num_kinds_text1,0,0,0,0));
        addViewToContainer(getTextView(R.string.num_kinds_text2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.kind_num_f1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.kind_num_f2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.kind_num_f3,0,0,0,0));
        addViewToContainer(getTextView(R.string.num_kinds_text3,0,0,0,0));
    }

    private void ShowTrigProp(){
        addViewToContainer(getTextView(R.string.trig_text1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f6,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f7,0,0,0,0));
        addViewToContainer(getTextView(R.string.trig_text2, 0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f8,0,0,0,0));
        addViewToContainer(getTextView(R.string.trig_text3, 0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f9,0,0,0,0));
        addViewToContainer(getTextView(R.string.trig_text4, 0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f10,0,0,0,0));
        addViewToContainer(getTextView(R.string.trig_text5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f11,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f12,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f13,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f14,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f15,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f16,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f17,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.trig_f18,0,0,0,0));
    }

    private void ShowLogProp(){
        addViewToContainer(getTextView(R.string.log_text1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f1, 0, 0, 0, 0));
        addViewToContainer(getTextView(R.string.log_text2,0 ,0,0,0));
        addViewToContainer(getTextView(R.string.log_text3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f6,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f7,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f8,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f9,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.log_f10,0,0,0,0));
    }

    private void ShowPowProp(){
        addViewToContainer(getTextView(R.string.power_text1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f6,0,0,0,0));
        addViewToContainer(getTextView(R.string.power_text2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f7,0,0,0,0));
        addViewToContainer(getTextView(R.string.power_text3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.power_f8,0,0,0,0));
    }

    private void ShowDerProp(){
        addViewToContainer(getTextView(R.string.deriv_text1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f1,0,0,0,0));
        addViewToContainer(getTextView(R.string.deriv_text2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f2,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f18,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f6,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f8,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f9,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f10,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f11,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f12,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f13,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f14,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f15,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f16,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f17,0,0,0,0));
        addViewToContainer(getTextView(R.string.deriv_text4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f19,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f20,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f21,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f22,0,0,0,0));
        addViewToContainer(getTextView(R.string.deriv_text5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f23,0,0,0,0));
        addViewToContainer(getTextView(R.string.deriv_text6,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.deriv_f24,0,0,0,0));
    }

    private void ShowIntegralProp(){
    addViewToContainer(getTextView(R.string.integral_text1,0,0,0,0));
    addViewToContainer(getImageView(R.drawable.intg_f1,0,0,0,0));
    addViewToContainer(getTextView(R.string.integral_text2,0,0,0,0));
    addViewToContainer(getTextView(R.string.integral_text3,0,0,0,0));
    addViewToContainer(getImageView(R.drawable.intg_f2,0,0,0,0));
        addViewToContainer(getTextView(R.string.integral_text4,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f3,0,0,0,0));
        addViewToContainer(getTextView(R.string.integral_text5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f4,0,0,0,0));
        addViewToContainer(getTextView(R.string.integral_text6,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f5,0,0,0,0));
        addViewToContainer(getTextView(R.string.integral_text7,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f6,0,0,0,0));
        addViewToContainer(getTextView(R.string.integral_text7_,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f7_,0,0,0,0));

        addViewToContainer(getTextView(R.string.integral_text8,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f7,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f8,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f8_,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f9,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f10,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f11,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f12,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f13,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f14,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f15,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f16,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f17,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f18,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.intg_f19,0,0,0,0));
    }

    private void ShowProbability(){
        addViewToContainer(getTextView(R.string.prob_text1,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f1,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text2,0,0,0,0));

        addViewToContainer(getTextView(R.string.prob_text3,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f2,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text4,0,0,0,0));

        addViewToContainer(getTextView(R.string.prob_text5,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f3,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text6,0,0,0,0));

        addViewToContainer(getTextView(R.string.prob_text7,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f4,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text8,0,0,0,0));

        addViewToContainer(getTextView(R.string.prob_text9,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f5,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text10,0,0,0,0));

        addViewToContainer(getTextView(R.string.prob_text11,0,0,0,0));
        addViewToContainer(getImageView(R.drawable.perm_f6,0,0,0,0));
        addViewToContainer(getTextView(R.string.prob_text12,0,0,0,0));
    }

}
