package com.numbers.properties.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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

import com.numbers.properties.activities.InfoPropertiesActivity;
import com.numbers.properties.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class InfoFragment extends Fragment {
    private View mainView;
    private DrawerLayout drawerLayout;
    private EditText number_text;
    private Button start_button;
    private ImageButton menu_button;

    //properties

    // 0 - integer
    // 1 - Big Integer
    // 2 - error
    long number = 0;
    boolean error = false;
    boolean is_even;
    boolean is_prime;
    boolean is_semi_prime;
    long first_semi;
    long second_semi;
    Color color;
    String binary;
    String oct;
    String hex;
    boolean is_perfect_square;
    double num_sqrt;
    long left_fib = 0;
    long right_fib = 1;
    boolean is_fib;
    ArrayList<Long> components;

    public InfoFragment(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.info_fragment, container, false);
        init();
        return mainView;
    }

    private void init() {
        number_text = mainView.findViewById(R.id.enter_number_text);
        start_button = mainView.findViewById(R.id.start_button);
        menu_button = mainView.findViewById(R.id.menu_button);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        start_button.setOnClickListener(OnStartClickListener());
    }

    private View.OnClickListener OnStartClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number_txt = number_text.getText().toString();
                number = 0;
                TypeDetermination(number_txt);
                if (!error){
                    final ProgressDialog loader = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
                    loader.setCancelable(false);
                    loader.setTitle(getStringFromResource(R.string.load_title));
                    loader.setMessage(getStringFromResource(R.string.load_message));
                    loader.setIndeterminate(true);
                    loader.show();
                    new Thread(new Runnable(){
                        public void run() {
                            //do the work here
                            is_even = isEven(number);
                            is_prime = isPrime(number);
                            if (is_prime){
                                is_semi_prime = true;
                                first_semi = 1;
                                second_semi = number;
                            }else{
                                is_semi_prime = isSemiPrime(number);
                            }
                            //convert
                            binary = Long.toBinaryString(number);
                            oct = Long.toOctalString(number);
                            hex = Long.toHexString(number);
                            //sqrt
                            is_perfect_square = isPerfectSquare(number);
                            num_sqrt = Math.sqrt(number);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.HALF_DOWN);
                            //df.setRoundingMode(RoundingMode.UNNECESSARY);
                            left_fib = 0;
                            right_fib = 1;
                            is_fib = FindNearbyFibonacci(number);
                            components = new ArrayList<>();
                            if (!is_prime){
                                FindAllComponents(number);
                            }else {
                                components.add((long)1);
                                components.add(number);
                            }
                            // dialog.cancel();
                            Intent intent = new Intent(getContext(), InfoPropertiesActivity.class);
                            intent.putExtra("number", number);
                            intent.putExtra("even", is_even);
                            intent.putExtra("binary", binary);
                            intent.putExtra("octal", oct);
                            intent.putExtra("hex", hex.toUpperCase());
                            intent.putExtra("prime", is_prime);
                            intent.putExtra("semi_prime", is_semi_prime);
                            intent.putExtra("perfect_square", is_perfect_square);
                            intent.putExtra("square_root", df.format(num_sqrt));
                            intent.putExtra("fib", is_fib);
                            intent.putExtra("left_fib", left_fib);
                            intent.putExtra("right_fib", right_fib);
                            intent.putExtra("first_semi", first_semi);
                            intent.putExtra("second_semi", second_semi);
                            if (!is_prime){
                                intent.putExtra("array", components);
                            }else{
                                intent.putExtra("array", new ArrayList<Long>());
                            }

                           // intent.putExtra("perfect", is_perfect);
                            startActivity(intent);
                            if(loader!=null){
                                loader.dismiss();}}
                    }).start();

                }else{
                    Toast.makeText(getContext(), "Error format", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private String getStringFromResource(int id){
        return getResources().getString(id);
    }

    private void TypeDetermination(String number_str){
        try {
            number = Long.parseLong(number_str);
            }catch (NumberFormatException|ClassCastException e2){
                error = true;
            }
    }

    private boolean isEven(long input){
      return input % 2 ==0;
    }

    private boolean isPrime(long input){
        if (input == 2) return true;
        if (input % 2 == 0 || input == 1) return false;
        else {
            for (int i = 3; i < Math.sqrt(input) ; i++) {
                if (input % i == 0) return false;
            }
            return true;
        }
    }

    private boolean isSemiPrime(long input){
        for (int i = 2; i <= Math.sqrt(input) ; i++) {
            if (input % i == 0 && isPrime(i) && isPrime(input/i)) {
                first_semi = i;
                second_semi = input/i;
                return true;
            }
        }
        return false;
    }

    private boolean FindNearbyFibonacci(long input){
        if (input == 0) {
            left_fib = 0;
            right_fib = 1;
            return true;
        }else {
            while (right_fib <= input){
                long temp = right_fib;
                right_fib += left_fib;
                left_fib = temp;
            }
            if (left_fib == input){
                left_fib = right_fib - input;
                return true;
            }else {
                return false;
            }
        }


    }

    private void FindAllComponents(long input){
        if (input == 0) return;
        long temp = input;
        while (temp != 1){
            for (int i = 2; i <= temp ; i++) {
                if (temp % i == 0){
                    components.add((long) i);
                    temp /= i;
                    break;
                }
                if (i > Math.sqrt(temp)){
                    components.add(temp);
                    return;
                }
            }
        }
    }

    private boolean isPerfectSquare(long input){
        double sr = Math.sqrt(input);
        return (sr - Math.floor(sr) == 0) && ((int)sr * (int)sr == input);
    }

}
