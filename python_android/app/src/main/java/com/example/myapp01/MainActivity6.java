package com.example.myapp01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {



    EditText et_dan;
    TextView tv_disp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        et_dan = findViewById(R.id.et_dan);
        tv_disp = findViewById(R.id.tv_disp);

        //클릭이벤트
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });

    }


    public void btnClick() {

        String dan = et_dan.getText().toString();
        int idan = Integer.parseInt(dan);
        String txt = "";

        txt += idan + "*" + 1 + "="+(idan*1)+"\n";
        txt += idan + "*" + 2 + "="+(idan*2)+"\n";
        txt += idan + "*" + 3 + "="+(idan*3)+"\n";
        txt += idan + "*" + 4 + "="+(idan*4)+"\n";
        txt += idan + "*" + 5 + "="+(idan*5)+"\n";
        txt += idan + "*" + 6 + "="+(idan*6)+"\n";
        txt += idan + "*" + 7 + "="+(idan*7)+"\n";
        txt += idan + "*" + 8 + "="+(idan*8)+"\n";
        txt += idan + "*" + 9 + "="+(idan*9)+"\n";

        tv_disp.setText(txt);




    }

}