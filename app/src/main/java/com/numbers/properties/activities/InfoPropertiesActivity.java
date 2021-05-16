package com.numbers.properties.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.numbers.properties.R;

import java.util.ArrayList;

public class InfoPropertiesActivity extends AppCompatActivity {
    TextView number_title;
    TextView even;
    TextView binary;
    TextView octal;
    TextView hex;
    TextView perf_square;
    TextView square_root;
    TextView fib;
    TextView left_fib;
    TextView right_fib;
    TextView prime;
    TextView semi_prime_txt;
    ArrayList<Long> components;
    View color_back;
    ImageButton eye_prime;
    ImageButton eye_semi_prime;
    boolean semi_prime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.info_prop_list_fragment);
        init();
        Configure();
    }

    private void init() {
        ImageButton back = findViewById(R.id.button_backX);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              finish();
           }
       });

        number_title = findViewById(R.id.number_title);
        even = findViewById(R.id.even_text);
        binary = findViewById(R.id.binary_text);
        octal = findViewById(R.id.octal_text);
        hex = findViewById(R.id.hexadecimal_text);
        perf_square = findViewById(R.id.perfect_square_text);
        square_root = findViewById(R.id.square_root_text);
        fib = findViewById(R.id.fibonacci_text);
        left_fib = findViewById(R.id.left_fib_text);
        right_fib = findViewById(R.id.right_fib_text);
        prime = findViewById(R.id.prime_text);
        semi_prime_txt = findViewById(R.id.semi_prime_text);
        color_back = findViewById(R.id.color_back);
        eye_prime = findViewById(R.id.watch_prime);
        eye_semi_prime = findViewById(R.id.watch_semi_prime);
    }

    private void Configure(){
        Intent intent = getIntent();
        number_title.setText(Long.toString(intent.getLongExtra("number", 0)));
        even.setText((intent.getBooleanExtra("even", true) ? "Even" : "Odd"));
        binary.setText(intent.getStringExtra("binary"));
        octal.setText(intent.getStringExtra("octal"));
        hex.setText(intent.getStringExtra("hex"));
        perf_square.setText((intent.getBooleanExtra("perfect_square", true) ? "True" : "False"));
        square_root.setText(intent.getStringExtra("square_root"));
        fib.setText((intent.getBooleanExtra("fib", true) ? "True" : "False"));
        left_fib.setText(Long.toString(intent.getLongExtra("left_fib", 0)));
        right_fib.setText(Long.toString(intent.getLongExtra("right_fib", 0)));
        prime.setText((intent.getBooleanExtra("prime", true)) ? "True" : "False");
        semi_prime = intent.getBooleanExtra("semi_prime", true);
        semi_prime_txt.setText((semi_prime) ? "True" : "False");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color color = Color.valueOf((int)intent.getLongExtra("number", 0));
            color_back.setBackgroundColor(Color.rgb(color.red(), color.green(), color.blue()));
        }
        components = (ArrayList<Long>) intent.getSerializableExtra("array");
        if (components.size() != 0){
            eye_prime.setOnClickListener(ShowComponentsListener());
        }else{
            eye_prime.setVisibility(View.INVISIBLE);
        }
        if (semi_prime){
            eye_semi_prime.setOnClickListener(ShowSemiFactorsListener(intent.getLongExtra("first_semi", 0), intent.getLongExtra("second_semi", 0)));

        }else {
            eye_semi_prime.setVisibility(View.INVISIBLE);
        }
    }

    private View.OnClickListener ShowComponentsListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoPropertiesActivity.this, R.style.AppCompatAlertDialogStyle)
                        .setTitle(getString(R.string.components_title))
                        .setCancelable(true)
                        .setMessage(getMessageFromArray(components));
                builder.show();
            }
        };
    }

    private View.OnClickListener ShowSemiFactorsListener(final Long first_semi, final Long second_semi){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoPropertiesActivity.this, R.style.AppCompatAlertDialogStyle)
                        .setCancelable(true)
                        .setTitle(getString(R.string.semi_factors))
                        .setMessage(getString(R.string.first_semi_factor) + first_semi + "\n" + getString(R.string.second_semi_factor) + second_semi);
                builder.show();
            }
        };
    }

    private String getMessageFromArray(ArrayList<Long> arrayList){
        String result = "";
        for (int i = 0; i < arrayList.size() ; i++) {
            if (i != arrayList.size() - 1){
                result += arrayList.get(i) + ", ";
            }else {
                result += arrayList.get(i);
            }
        }
        return result;
    }


}
