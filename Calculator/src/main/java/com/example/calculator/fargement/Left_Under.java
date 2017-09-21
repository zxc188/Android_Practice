package com.example.calculator.fargement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.R;


public class Left_Under extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText ed_a;
    private EditText ed_b;
    private EditText ed_c;
    private Button bt_cal;
    private TextView tx_result;
    private Thread thread;
    public Left_Under() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_right_, container, false);
        ed_a = (EditText) view.findViewById(R.id.ed_a);
        ed_b = (EditText) view.findViewById(R.id.ed_b);
        ed_c = (EditText) view.findViewById(R.id.ed_c);
        tx_result = (TextView) view.findViewById(R.id.text_result);
        bt_cal = (Button) view.findViewById(R.id.bt_cal);
        bt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(ed_a.getText().toString());
                double b = Double.parseDouble(ed_b.getText().toString());
                double c = Double.parseDouble(ed_c.getText().toString());
                double s = Math.pow(b*b-4*a*c,0.5);
                double x1 = (-b + s) / 2 * a;
                double x2 = (-b - s) / 2 * a;
                onButtonPressed("x1=" + x1 + "   x2=" + x2);
                ed_a.setText("");
                ed_b.setText("");
                ed_c.setText("");
            }
        });
        return view;
    }
    public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentInteraction(str);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);
    }
}
