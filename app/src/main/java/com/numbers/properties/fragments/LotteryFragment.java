package com.numbers.properties.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.numbers.properties.R;
import com.numbers.properties.activities.WinnerActivity;

import java.security.SecureRandom;

public class LotteryFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private View mainView;
    private ImageButton menu_button;
    private EditText edit_text;
    private Button guess;
    private SharedPreferences inside;
    private SharedPreferences.Editor editor;
    private int clicks = 0;
    private InterstitialAd mInterstitialAd;
    private int temp_clicks;
    public LotteryFragment(DrawerLayout drawerLayout, InterstitialAd mInterstitialAd){
        this.drawerLayout = drawerLayout;
        this.mInterstitialAd = mInterstitialAd;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.lottery_fragment, container, false);
        init();
        return mainView;
    }

    private void init() {
        menu_button = mainView.findViewById(R.id.lottery_menu);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        edit_text = mainView.findViewById(R.id.enter_number_guess_text);
        guess = mainView.findViewById(R.id.guess_button);
        guess.setOnClickListener(GenerateNumberAndDialog());
        inside = getContext().getSharedPreferences("counter", Context.MODE_PRIVATE);
        editor = inside.edit();
    }

    private View.OnClickListener GenerateNumberAndDialog(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final int input_number;
                try {
                    input_number = Integer.parseInt(edit_text.getText().toString());
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), getString(R.string.lottery_dialog_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                int random = GenerateRandomNumber(999999 + 1,99 + 1);
                if (input_number != random){
                  Dismiss(input_number, random);
                }else{
                    SharedPreferences pref = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
                    int count = pref.getInt("was", 0);
                    SharedPreferences.Editor editor2 = pref.edit();
                    int check = count + 1;
                    editor2.putInt("was", check);
                    editor2.apply();
                    if (count < 2){
                        Intent intent = new Intent(getContext(), WinnerActivity.class);
                        startActivity(intent);
                    }else {
                        while (input_number == random){
                            random = GenerateRandomNumber(999999+1, 99 + 1);
                        }
                        Dismiss(input_number, random);
                    }
                }

                clicks++;
                putClicks();
                if (temp_clicks % 2 ==0){
                    //showAds();
                }
            }
        };
    }

    private void Dismiss(int input_number, int random){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle)
                .setCancelable(true)
                .setTitle("Results")
                .setMessage("Your number is " + input_number + "\n" + "System number is " + random);
        builder.show();
    }

    private int GenerateRandomNumber(int max, int min){
        SecureRandom test = new SecureRandom();
        int result = test.nextInt(max - min) + min;
        return result;
    }

    private void putClicks(){
        temp_clicks = inside.getInt("count", 0);
        temp_clicks += clicks;
        editor.putInt("count", temp_clicks);
        editor.apply();
    }

    private void showAds(){
        mInterstitialAd.show();
    }
}
