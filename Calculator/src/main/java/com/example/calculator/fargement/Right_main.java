package com.example.calculator.fargement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calculator.R;


public class Right_main extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private Button bt_sin;
    private Button bt_cos;
    private Button bt_tan;
    private Button bt_csin;
    private Button bt_ccos;
    private Button bt_ctan;
    private Button bt_xx;
    private Button bt_xxx;
    private Button bt_med_x;
    private Button bt_log;
    private Button bt_ln;
    private Button bt_pi;
    private Button bt_e_x_lessone;
    private Button bt_e;
    private Button bt_upx;

    public Right_main() {

    }


    public static Right_main newInstance(String param1, String param2) {
        Right_main fragment = new Right_main();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_right_main, container, false);
        bt_sin = (Button) view.findViewById(R.id.bt_sin);
        bt_sin.setOnClickListener(this);
        bt_cos = (Button) view.findViewById(R.id.bt_cos);
        bt_cos.setOnClickListener(this);
        bt_tan = (Button) view.findViewById(R.id.bt_tan);
        bt_tan.setOnClickListener(this);
        bt_csin = (Button) view.findViewById(R.id.bt_csin);
        bt_csin.setOnClickListener(this);
        bt_ccos = (Button) view.findViewById(R.id.bt_ccos);
        bt_ccos.setOnClickListener(this);
        bt_ctan = (Button) view.findViewById(R.id.bt_ctan);
        bt_ctan.setOnClickListener(this);
        bt_xx = (Button) view.findViewById(R.id.bt_xx);
        bt_xx.setOnClickListener(this);
        bt_xxx = (Button) view.findViewById(R.id.bt_xxx);
        bt_xxx.setOnClickListener(this);
        bt_med_x = (Button) view.findViewById(R.id.bt_med_x);
        bt_med_x.setOnClickListener(this);
        bt_log = (Button) view.findViewById(R.id.bt_log);
        bt_log.setOnClickListener(this);
        bt_ln = (Button) view.findViewById(R.id.bt_ln);
        bt_ln.setOnClickListener(this);
        bt_pi = (Button) view.findViewById(R.id.bt_pi);
        bt_pi.setOnClickListener(this);
        bt_e_x_lessone = (Button) view.findViewById(R.id.bt_x_lessone);
        bt_e_x_lessone.setOnClickListener(this);
        bt_e = (Button) view.findViewById(R.id.bt_e);
        bt_e.setOnClickListener(this);
        bt_upx = (Button) view.findViewById(R.id.bt_upx);
        bt_upx.setOnClickListener(this);
        return view;
    }


    public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentonclick(str);
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


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_sin:
                onButtonPressed("sin");
                break;
        }
    }


    public interface OnFragmentInteractionListener {

        void onFragmentonclick(String str);
    }
}
