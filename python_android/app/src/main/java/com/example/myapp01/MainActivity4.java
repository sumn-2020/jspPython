package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {


    EditText mine, com, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        mine = findViewById(R.id.et_mine);
        com = findViewById(R.id.et_com);
        result = findViewById(R.id.et_result);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });

    }

    public void btnClick() {


        double rnd = Math.random();

        if(rnd > 0.5) {
            com.setText("홀");
        }else {
            com.setText("짝");
        }


        String txtMine = mine.getText().toString();
        String txtCom = com.getText().toString();

        if(txtMine.equals(txtCom)) {
            result.setText("이김");
        }else {
            result.setText("졌음");
        }



    }

}