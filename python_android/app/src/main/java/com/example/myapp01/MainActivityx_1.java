package com.example.myapp01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityx_1 extends AppCompatActivity {

    EditText et;
    TextView tv_disp;



    int rnd1;
    int rnd2;
    int rnd3;

    String result = "" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx);

        et = findViewById(R.id.et);
        tv_disp = findViewById(R.id.tv_disp);
        Button btn = findViewById(R.id.btn);


        fn_random();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myclick();
            }
        });

    }


    public void fn_random() {
        //1. 랜덤 숫자 3개를 만든다. (while로 같은 경우  다시 랜덤 돌리게하기 )
        rnd1 = (int) (Math.random() * 9) + 1;
        rnd2 = (int) (Math.random() * 9) + 1;
        rnd3 = (int) (Math.random() * 9) + 1;
        while(rnd2 == rnd1) {
            rnd2 = (int) (Math.random() * 9) + 1;
        }
        while(rnd3 == rnd2 || rnd3 == rnd1) {
            rnd3 = (int) (Math.random() * 9) + 1;
        }
         // result += rnd1+"";
        //result += rnd2+"";
        //result += rnd3+"\n";
        //tv_disp.setText(result);
    }



    public void myclick() {
        tv_disp.setText("");


        int strike = 0;
        int ball = 0;


        //2. 사용자가 입력한 값 3개를 받아온다.
        String exTxt = et.getText().toString();
        int spelling1 = Integer.parseInt(exTxt.substring(0, 1));
        int spelling2 = Integer.parseInt(exTxt.substring(1, 2));
        int spelling3 = Integer.parseInt(exTxt.substring(2, 3));


        //3 입력한 값과 숫자 3개를 비교한다.
        if(rnd1 == spelling1) { //첫번째 자리수가 random수와 같으면
            strike++;  // strike 숫자 올리기
        }else if(rnd1 == spelling2 || rnd1 == spelling3) { //다르다?
            ball++; //ball 숫자 올리기
        }

        if(rnd2 == spelling2) { //두번째 자리수가 random수와 같으면
            strike++;  // strike 숫자 올리기
        }else if(rnd2 == spelling1 || rnd2 == spelling3) { //다르다?
            ball++; //ball 숫자 올리기
        }

        if(rnd3 == spelling3) { //세번째 자리수가 random수와 같으면
            strike++;  // strike 숫자 올리기
        }else if(rnd3 == spelling1 || rnd3 == spelling2) { //다르다?
            ball++; //ball 숫자 올리기
        }

        //5. 결과
        if(strike == 0 || ball == 0) {
            result +=  exTxt+ strike + "S," + ball + "B" + "\n";
        }else if(strike == 3) { //세개 다 정답이면 결과에 성공 저장
            result += "성공";
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }else {
            result +=  exTxt + strike + "S," + ball + "B" + "\n";
        }

        //6. 결과 메시지 출력
         if(tv_disp.getText().toString() == null || tv_disp.getText().toString() == "") {
             tv_disp.setText(result);
         }else {
             tv_disp.append(result);
         }



    }
}