package com.example.calculator.fargement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calculator.R;


public class Left_main extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    private Button bt_one;
    private Button bt_two;
    private Button bt_three;
    private Button bt_four;
    private Button bt_five;
    private Button bt_six;
    private Button bt_seven;
    private Button bt_eight;
    private Button bt_nine;
    private Button bt_zero;

    private Button bt_less;
    private Button bt_except;
    private Button bt_multiply;
    private Button bt_equal;
    private Button bt_ac;
    private Button bt_del;
    private Button bt_add;
    private Button bt_point;

    public Left_main() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_main, container, false);
        /*
        * 初始化控件
        * */
        bt_one = (Button) view.findViewById(R.id.bt_one);
        bt_one.setOnClickListener(this);

        bt_two = (Button) view.findViewById(R.id.bt_two);
        bt_two.setOnClickListener(this);

        bt_two = (Button) view.findViewById(R.id.bt_two);
        bt_two.setOnClickListener(this);

        bt_three = (Button) view.findViewById(R.id.bt_three);
        bt_three.setOnClickListener(this);

        bt_four = (Button) view.findViewById(R.id.bt_four);
        bt_four.setOnClickListener(this);

        bt_five = (Button) view.findViewById(R.id.bt_five);
        bt_five.setOnClickListener(this);

        bt_six = (Button) view.findViewById(R.id.bt_six);
        bt_six.setOnClickListener(this);

        bt_seven = (Button) view.findViewById(R.id.bt_seven);
        bt_seven.setOnClickListener(this);

        bt_eight = (Button) view.findViewById(R.id.bt_eight);
        bt_eight.setOnClickListener(this);

        bt_nine = (Button) view.findViewById(R.id.bt_nine);
        bt_nine.setOnClickListener(this);

        bt_zero = (Button) view.findViewById(R.id.bt_zero);
        bt_zero.setOnClickListener(this);

        bt_multiply = (Button) view.findViewById(R.id.bt_multiply);
        bt_multiply.setOnClickListener(this);

        bt_del = (Button) view.findViewById(R.id.bt_del);
        bt_del.setOnClickListener(this);

        bt_add=(Button) view.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);

        bt_less = (Button) view.findViewById(R.id.bt_less);
        bt_less.setOnClickListener(this);

        bt_ac = (Button) view.findViewById(R.id.bt_ac);
        bt_ac.setOnClickListener(this);

        bt_equal = (Button) view.findViewById(R.id.bt_equal);
        bt_equal.setOnClickListener(this);


        bt_except = (Button) view.findViewById(R.id.bt_except);
        bt_except.setOnClickListener(this);

        bt_point = (Button) view.findViewById(R.id.bt_point);
        bt_point.setOnClickListener(this);

        return view;

    }

    /*
    * 字符控件点击触发
    * */
    public void onStringButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentStringaction(str);
        }
    }

    /*
    * 字符控件点击触发
    * */
    public void onIntButtonPressed(String in) {
        if (mListener != null) {
            mListener.onFragmentIntaction(in);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*
    * 控件监听器
    * */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_one:
                onIntButtonPressed(1+"");
                break;
            case R.id.bt_two:
                onIntButtonPressed(2+"");
                break;
            case R.id.bt_three:
                onIntButtonPressed(3+"");
                break;
            case R.id.bt_four:
                onIntButtonPressed(4+"");
                break;
            case R.id.bt_five:
                onIntButtonPressed(5+"");
                break;
            case R.id.bt_six:
                onIntButtonPressed(6+"");
                break;
            case R.id.bt_seven:
                onIntButtonPressed(7+"");
                break;
            case R.id.bt_eight:
                onIntButtonPressed(8+"");
                break;
            case R.id.bt_nine:
                onIntButtonPressed(9+"");
                break;
            case R.id.bt_zero:
                onIntButtonPressed(0+"");
                break;
            case R.id.bt_add:
                onStringButtonPressed("+");
                break;
            case R.id.bt_less:
                onStringButtonPressed("-");
                break;
            case R.id.bt_except:
                onStringButtonPressed("/");
                break;
            case R.id.bt_multiply:
                onStringButtonPressed("*");
                break;
            case R.id.bt_equal:
                onStringButtonPressed("=");
                break;
            case R.id.bt_ac:
                onStringButtonPressed("ac");
                break;
            case R.id.bt_point:
                onStringButtonPressed(".");
                break;
            case R.id.bt_del:
                onStringButtonPressed("del");
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentStringaction(String str);

        void onFragmentIntaction(String sin);
    }
}
