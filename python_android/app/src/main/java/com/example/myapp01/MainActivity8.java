package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity8 extends AppCompatActivity {


    EditText et_f, et_l;
    TextView tv_disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        et_f = findViewById(R.id.et_f);
        et_l = findViewById(R.id.et_l);
        tv_disp = findViewById(R.id.tv_disp);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });
    }

    // 별 그리기
    public String drawLine(int cnt) {
        String ret = "";
        for (int i = 0; i < cnt ; i++) {
            ret += "*"; //ret = ret + *
        }
        ret += "\n";
        return ret;
    }


    public void btnClick() {

        int first = Integer.parseInt(et_f.getText().toString());
        int last = Integer.parseInt(et_l.getText().toString());
        String result = "";

       /* Log.d("tag",first+"");
        Log.d("tag",last+"");

       for(int i = first; i <= last ; i++) {
            for(int k = 1; k <= i; k++) {
                result += "*";
            }
           result += "\n";
        }*/

        for(int i=first; i<= last; i++) {
            result += drawLine(i);
        }
        //result += drawLine(1);
        //result += drawLine(2);...

        tv_disp.setText(result);

    }
}