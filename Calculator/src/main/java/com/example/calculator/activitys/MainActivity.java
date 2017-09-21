package com.example.calculator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.fargement.Left_Under;
import com.example.calculator.fargement.Left_main;
import com.example.calculator.fargement.Right_main;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements Left_main.OnFragmentInteractionListener, Left_Under.OnFragmentInteractionListener, Right_main.OnFragmentInteractionListener {
    private Stack<Character> Stack_char = new Stack<>();
    private Stack<Double> Stack_dou = new Stack<>();
    private EditText ed_input;
    private Handler handler;
    private boolean is_hight=false;
    private String end = "" ;
    private String hight_str = "" ;
    private String dou="";
    private int pass_input = -1;
    private Thread thread;
    char[] s = {'+', '-', '*', '/', '#'};
    char Prior[][] = {
            {'>', '>', '<', '<', '>'},
            {'>', '>', '<', '<', '>'},
            {'>', '>', '>', '>', '>'},
            {'>', '>', '>', '>', '>'},
            {'<', '<', '<', '<', '='}
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_input = (EditText) findViewById(R.id.ed_input);
        ed_input.setInputType(InputType.TYPE_NULL);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String res = msg.getData().getString("result");
                if (res.equals("error")) {
                    Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_LONG).show();
                } else {
                    ed_input.setText(res);
                    end = res;
                }
            }
        };
    }

    public boolean is_char(char sh) {
        for (int i = 0; i < s.length; i++) {
            if (sh == s[i]) {
                return true;
            }
        }
        return false;
    }

    public String Ed_del(String line) {
        return line.substring(0, line.length() - 1);
    }

    @Override
    public void onFragmentStringaction(String str) {
        if (is_hight) {
            switch (hight_str) {
                case "sin":
                    try {
                        double s = Math.sin(Double.parseDouble(dou));
                        dou = "";
                        hight_str = "";
                        end = end + s;
                        is_hight = false;
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "输入有误", Toast.LENGTH_LONG).show();
                    }

                    break;
            }
        }
        Log.v("onFragmentStringaction", str + "");
        if (str == "ac") {
            ed_input.setText("");
            end = "" ;
        } else if (str == "del") {
            ed_input.setText(Ed_del(ed_input.getText().toString()));
        } else if (str == "=") {
            /*
            * 计算
            * */
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Stack_dou.add(0.0);
                    String line = end;
                    Message mess = new Message();
                    if (line.charAt(0) == '*' || line.charAt(0) == '/') {
                        Bundle bun_error = new Bundle();
                        mess.setData(bun_error);
                        handler.sendMessage(mess);
                        Toast.makeText(getApplicationContext(), "输入有误", Toast.LENGTH_LONG).show();
                        return;
                    }
                    char[] re = (line + "#").toCharArray();
                    Stack_char.add('#');
                    int val = 0;
                    char c, thela, n;
                    double a, b;
                    while (re[val] != '#' || Stack_char.peek() != '#') {
                        c = re[val];
                        if (!is_char(c)) {
                            Log.v("asdfioj", c + "");
                            if (val == 0) {
                                Stack_dou.add(Double.parseDouble(c + ""));
                            } else {
                                if (!is_char(re[val - 1])) {
                                    if (c == '.') {
                                        double o = Stack_dou.pop();
                                        String point_l = "" ;
                                        while (!is_char(re[val + 1])) {
                                            ++val;
                                            point_l = point_l + re[val];
                                        }
                                        Stack_dou.add(o + Double.parseDouble("0." + point_l));
                                    } else {
                                        double h = Stack_dou.pop();
                                        Stack_dou.add(h * 10 + Double.parseDouble(c + ""));
                                    }
                                } else {
                                    Stack_dou.add(Double.parseDouble(c + ""));
                                }
                            }
                            ++val;
                        } else {
                            switch (precede(Stack_char.peek(), c)) {
                                case '<':
                                    Stack_char.add(c);
                                    ++val;
                                    break;
                                case '=':
                                    n = Stack_char.pop();
                                    ++val;
                                    break;
                                case '>':
                                    thela = Stack_char.pop();
                                    b = Stack_dou.pop();
                                    a = Stack_dou.pop();
                                    double g = Operation(a, thela, b);
                                    Stack_dou.add(g);
                                    break;
                            }
                        }
                    }
                    Bundle bun_success = new Bundle();
                    bun_success.putString("result", "" + Stack_dou.peek());
                    mess.setData(bun_success);
                    handler.sendMessage(mess);
                    Log.v("amdfjfierj", Stack_dou.pop() + "");
                }
            });
            thread.start();
        } else {
            ed_input.setText(ed_input.getText().toString() + str);
            end = end + str;
        }
    }

    /*
    * Fragment发动过来的int型处理方法
    * */
    @Override
    public void onFragmentIntaction(String str) {
        if (!is_hight) {
            ed_input.setText(ed_input.getText().toString()+str);
            end = end + str;
            Log.v("adsfuhr", dou);
        } else {
            ed_input.setText(ed_input.getText().toString()+str);
            dou = dou + str;
            Log.v("adsfuhr", dou);
        }
    }


    public double Operation(double a, char t, double b) {
        double result = 0;
        switch (t) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        return result;
    }

    int ReturnOpOrd(char op) {
        int i;
        for (i = 0; i < 5; i++) {
            if (op == s[i]) return i;
        }
        return 0;
    }

    char precede(char Aop, char Bop) {
        return Prior[ReturnOpOrd(Aop)][ReturnOpOrd(Bop)];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            /*
            * api接口处理
            * */
            case R.id.menu_length:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Unit_Conversion.class);
                startActivity(intent);
                break;
        }
        return true;
    }
    /*
    * 又Fragment接口实现
    * */
    @Override
    public void onFragmentonclick(String str) {
        ed_input.setText(ed_input.getText().toString() + str);
        hight_str = str;
        is_hight = true;
    }

    @Override
    public void onFragmentInteraction(String str) {
        ed_input.setText(str);
    }
}
