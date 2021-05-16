package com.numbers.properties.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.numbers.properties.Bun;
import com.numbers.properties.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WinnerActivity extends AppCompatActivity {
    private EditText mail_text;
    private Button send;
    DatabaseReference ref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.winner_activity_layout);
        ref = FirebaseDatabase.getInstance().getReference().child("winners");
        init();
    }

    private void init() {
        mail_text = findViewById(R.id.enter_mail_text);
        send = findViewById(R.id.send_mail_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mail_text.getText().toString();
                if (!mail.equals(" ")){
                    @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    Bun bun = new Bun(mail, getDate(), android_id);
                    String unique = mail.replace(".", "_").replace("#", "_").replace("$", "_").replace("[", "_").replace("]", "_") + android_id;
                    try {
                        DatabaseReference myref = ref.child(unique);
                        myref.setValue(bun);
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_LONG).show();
                    }catch (DatabaseException e){
                        Toast.makeText(v.getContext(), "Wrong e-mail format", Toast.LENGTH_LONG).show();
                    }
                }
                finish();
            }
        });
    }

    private String getDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

}
