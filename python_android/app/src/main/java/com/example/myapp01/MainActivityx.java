package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityx extends AppCompatActivity {


    String com  = "123";
    EditText et;
    TextView tv_disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx);

        et = findViewById(R.id.et);
        tv_disp = findViewById(R.id.tv_disp);


        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClick();
            }
        });

        //컴퓨터 값 랜덤으로 넣어주기
        setComRandom();

    }

    public void setComRandom() {

        int[] arr9 = {1,2,3,4,5,6,7,8,9};

        for (int i = 0; i < 1000; i++) {
            int rnd = (int)(Math.random()*9);
            int a = arr9[0];
            int b = arr9[rnd];
            arr9[0]=b;
            arr9[rnd]=a;
        }

        com = arr9[0] + "" + arr9[1] + "" + arr9[2];
        Log.d("메시지" , com);

    }



    public void myClick() {

        String mine = et.getText().toString();
        int strike = getStrike(mine, com);
        //Log.d("메시지", strike+"");

        int ball = getBall(mine,com);
        //Log.d("메시지", strike+":"+ball);

        String str_old = tv_disp.getText().toString(); //기존에 있는 글자
        String str_new = mine + "  " + strike + "S" + ball + "B\n"; //새 글자 +  스트라이크, 볼 갯수4
        tv_disp.setText(str_old + str_new);
        et.setText(""); //작성된 글 새로 초기화

        //스트라이크 3으로 나오면 성공 메시지 출력
        if(strike ==3) {
            Toast.makeText(getApplicationContext(), mine + "을 맞췄습니다.", Toast.LENGTH_LONG).show();
        }


    }


    public int getStrike(String mine, String com) {


        int ret = 0;

        String m1 = mine.substring(0,1);
        String m2 = mine.substring(1,2);
        String m3 = mine.substring(2,3);

        String c1 = com.substring(0,1);
        String c2 = com.substring(1,2);
        String c3 = com.substring(2,3);

        if(c1.equals(m1)) ret++;
        if(c2.equals(m2)) ret++;
        if(c3.equals(m3)) ret++;

        return ret;


    }


    public int getBall(String mine, String com) {

        int ret = 0;

        String m1 = mine.substring(0,1);
        String m2 = mine.substring(1,2);
        String m3 = mine.substring(2,3);

        String c1 = com.substring(0,1);
        String c2 = com.substring(1,2);
        String c3 = com.substring(2,3);

        if(c1.equals(m2) || c1.equals(m3)) ret++;
        if(c2.equals(m1) || c1.equals(m3)) ret++;
        if(c3.equals(m1) || c1.equals(m2)) ret++;

        return ret;

    }


}