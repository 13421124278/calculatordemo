package com.example.administrator.jisuanqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    //初始化控件；
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;
    Button btn_clear;
    Button btn_delete;
    Button btn_add;
    Button btn_minus;
    Button btn_multi;
    Button btn_divide;
    EditText et;
    boolean clear_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件并添加监听器；
        btn_0 = (Button)findViewById(R.id.btn0);
        btn_1 = (Button)findViewById(R.id.btn1);
        btn_2 = (Button)findViewById(R.id.btn2);
        btn_3 = (Button)findViewById(R.id.btn3);
        btn_4 = (Button)findViewById(R.id.btn4);
        btn_5 = (Button)findViewById(R.id.btn5);
        btn_6 = (Button)findViewById(R.id.btn6);
        btn_7 = (Button)findViewById(R.id.btn7);
        btn_8 = (Button)findViewById(R.id.btn8);
        btn_9 = (Button)findViewById(R.id.btn9);
        btn_point = (Button)findViewById(R.id.btn_point);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_multi = (Button)findViewById(R.id.btn_multiply);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_delete = (Button)findViewById(R.id.btn_del);

        et = (EditText)findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        et.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String str = et.getText().toString();
        switch (v.getId()){
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag = false;
                    et.setText("");
                }
                et.setText( str+""+ ((Button)v).getText() );
                break;
            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag = false;
                    et.setText("");
                }
                et.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_clear:
                clear_flag = false;
                et.setText("");
                break;
            case R.id.btn_del:
                if(clear_flag){
                    clear_flag = false;
                    et.setText("");
                }else if(!str.equals(""))
                {
                    et.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
            default:
                break;
        }
    }
    private void getResult(){
        String temp = et.getText().toString();
        //第一种情况，没有输入；
        if(temp.equals(""))
        {
            return;
        }
        //第二种情况，只输入数字；
        if(!temp.contains(" "))
        {
            return;
        }
        if(clear_flag){
            clear_flag  = false;
            return;
        }
        clear_flag = true;
        //第三种情况，输入数字和运算符；
        double result = 0;
        String s1 = temp.substring(0,temp.indexOf(" "));
        String op = temp.substring(temp.indexOf(" ")+1,temp.indexOf(" ")+2);
        String s2 = temp.substring(temp.indexOf(" ")+3);

        if((!s1.equals("")) && (!s2.equals("")))
        {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if(op.equals("+"))
            {
                result = d1+d2;
            }
            else if(op.equals("-"))
            {
                result = d1-d2;
            }
            else if(op.equals("×"))
            {
                result = d1*d2;
            }
            else if(op.equals("÷"))
            {
                if(d2==0)
                {
                    et.setText("illegal");
                }
                else
                {
                    result = d1/d2;
                }
            }
            if((!s1.contains("."))&&(!s2.contains("."))){
                int i = (int)result;
                et.setText(i+"");
            }else{
                et.setText(result+"");
            }
        }
        else if((!s1.equals(""))&&(s2.equals("")))
        {
            et.setText(temp);
        }
        else if(s1.equals("")&&(!s2.equals("")))
        {
            double d2 = Double.parseDouble(s2);
            if(op.equals("+"))
            {
                result = 0+d2;
            }
            else if(op.equals("-"))
            {
                result = 0-d2;
            }
            else if(op.equals("×"))
            {
                result = 0;
            }
            else if(op.equals("÷"))
            {
                result = 0;
            }
            if(!s2.contains(".")){
                int i = (int)result;
                et.setText(i+"");
            }else{
                et.setText(result+"");
            }
        }
        else
        {
            et.setText("");
        }
    }
}
