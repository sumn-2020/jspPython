package com.example.myapp01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity9_1 extends AppCompatActivity {


    TextView tv_disp;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnCall;
    String str = "";
    //String[] str = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        tv_disp = findViewById(R.id.tv_disp);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnCall = findViewById(R.id.btnCall);
        //StringBuffer str = new StringBuffer(); //스트링을 연이어서 붙이는 거


        //btn 눌렀을 때
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn1();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn3();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn4();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn5();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn6();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn7();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn8();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn9();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btn0();
            }
        });

        //btnCall 눌렀을 때
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_btnCall();
            }
        });
    }

    //btn1눌렀을 때
    public void f_btn1() {
        str += btn1.getText().toString();
        tv_disp.setText(str);
    }

    //btn2눌렀을 때
    public void f_btn2() {
        str += btn2.getText().toString();
        tv_disp.setText(str);
    }

    //btn3눌렀을 때
    public void f_btn3() {
        str += btn3.getText().toString();
        tv_disp.setText(str);
    }

    //btn4눌렀을 때
    public void f_btn4() {
        str += btn4.getText().toString();
        tv_disp.setText(str);
    }

    //btn5눌렀을 때
    public void f_btn5() {
        str += btn5.getText().toString();
        tv_disp.setText(str);
    }

    //btn6눌렀을 때
    public void f_btn6() {
        str += btn6.getText().toString();
        tv_disp.setText(str);
    }

    //btn7눌렀을 때
    public void f_btn7() {
        str += btn7.getText().toString();
        tv_disp.setText(str);
    }

    //btn8눌렀을 때
    public void f_btn8() {
        str += btn5.getText().toString();
        tv_disp.setText(str);
    }

    //btn9눌렀을 때
    public void f_btn9() {
        str += btn9.getText().toString();
        tv_disp.setText(str);
    }

    //btn0눌렀을 때
    public void f_btn0() {
        str += btn0.getText().toString();
        tv_disp.setText(str);
    }

    //btnCall 눌렀을 때
    public void f_btnCall() {
        //Log.d("btn2", btn2.getText().toString());
        String msg = tv_disp.getText().toString();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}