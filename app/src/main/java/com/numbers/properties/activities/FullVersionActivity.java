package com.numbers.properties.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.numbers.properties.R;

public class FullVersionActivity extends AppCompatActivity {
    private ImageButton close;
    private Button buy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.full_version_activity);
        close = findViewById(R.id.fv_close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buy = findViewById(R.id.buy_button);
        buy.setOnClickListener(Upgrade());
    }

    private View.OnClickListener Upgrade(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
