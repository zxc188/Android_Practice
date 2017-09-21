package com.example.calculator.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.calculator.R;
import com.example.calculator.Responsebody.API_Source;
import com.example.calculator.Responsebody.Length;
import com.example.calculator.Responsebody.Money_API_Source;
import com.example.calculator.Responsebody.Weight_API_Source;
import com.example.calculator.Responsebody.Weight_Body;
import com.example.calculator.fargement.ItemFragment;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Unit_Conversion extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{
    private static int select;
    private EditText ed_val;
    private Spinner spinner_unit;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);
        ed_val = (EditText) findViewById(R.id.ed_unit_input);
        spinner_unit = (Spinner) findViewById(R.id.spinner_unit);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        spinner_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Length_Get(4);
                        break;
                    case 2:
                        Length_Get(2);
                        break;
                    case 3:
                        Length_Get(1);
                        break;
                    case 4:
                        Money_Get(4);
                        break;
                    case 5:
                        Money_Get(5);
                        break;
                    case 6:
                        Weight_Get(4);
                        break;
                    case 7:
                        Weight_Get(3);
                        break;
                    case 8:
                        Weight_Get(2);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                select = -1;
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Map<String, Object> map) {

    }
    public void Length_Get(final int unit){
        String request_url="http://api.avatardata.cn/UnitConvert/Length?key=0c153a338b2a4f11aa5a0a575aa79c62&unit="+unit+"&value="+ed_val.getText().toString();
        StringRequest stringRequest = new StringRequest(request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("menu_length", response.toString());
                Gson gson = new Gson();
                API_Source api_source = gson.fromJson(response, API_Source.class);
                LinkedList<Length> result = api_source.getResult();
                LinkedList<Map<String,Object>> linkedList=new LinkedList<>();
                for(int i=0;i<result.size();i++) {
                    if (i != unit - 1) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("val", result.get(i).getValue());
                        map.put("unit", result.get(i).getName());
                        linkedList.add(map);
                    }
                }
                ItemFragment itemFragment = new ItemFragment(linkedList);
                itemFragment.change(linkedList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
    public void Money_Get(final int pos) {
        String request_url=" http://api.avatardata.cn/Currency/CurrencyList?key=609f0f92450e40bdbe18860e2cf553cf";
        StringRequest stringRequest = new StringRequest(request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("menu_length", response.toString());
                Gson gson = new Gson();
                Money_API_Source api_source = gson.fromJson(response, Money_API_Source.class);
                Log.v("asdufhor", api_source.getResult().getData1().toString());
                LinkedList<Map<String,Object>> linkedList=new LinkedList<>();
                Log.v("menu_length", api_source.getReason());
                String[] BuyPic = {api_source.getResult().getData1().getBuyPic(),/*澳元美元*/
                        api_source.getResult().getData3().getBuyPic(),/*欧元美元*/
                        api_source.getResult().getData4().getBuyPic(),/*英镑美元*/
                        api_source.getResult().getData8().getBuyPic(),/*美元人民币*/
                        api_source.getResult().getData9().getBuyPic(),/*美元港元*/
                        api_source.getResult().getData10().getBuyPic(),/*美元日元*/
                        api_source.getResult().getData13().getBuyPic()/*美元台币*/

                };
                BuyPic[0] = 1 / Double.parseDouble(BuyPic[0]) + "";
                BuyPic[1] = 1 / Double.parseDouble(BuyPic[1]) + "";
                BuyPic[2] = 1 / Double.parseDouble(BuyPic[2]) + "";
                String[] Country = {"澳元", "欧元", "英镑", "人民币", "港元", "日元", "台币"};
                if (pos == 4) {
                    for(int i=0;i<BuyPic.length;i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("val", Double.parseDouble(BuyPic[i]) * Double.parseDouble(ed_val.getText().toString()) + "");
                        map.put("unit", Country[i]);
                        linkedList.add(map);
                    }
                } else {
                    double s = Double.parseDouble(ed_val.getText().toString()) * (1 / Double.parseDouble(BuyPic[3]));
                    for(int i=0;i<BuyPic.length;i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("val", Double.parseDouble(BuyPic[i]) * s + "");
                        map.put("unit", Country[i]);
                        linkedList.add(map);
                    }
                    linkedList.get(3).put("unit", "美元");
                    linkedList.get(3).put("val",s+"");
                }
                ItemFragment itemFragment = new ItemFragment(linkedList);
                itemFragment.change(linkedList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
    public void Weight_Get(final int unit){
        String request_url = "http://api.avatardata.cn/UnitConvert/Weight?key=0c153a338b2a4f11aa5a0a575aa79c62&unit="+unit+"&value="+ed_val.getText().toString();
        StringRequest stringRequest = new StringRequest(request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("menu_length", response.toString());
                Gson gson = new Gson();
                Weight_API_Source api_source = gson.fromJson(response, Weight_API_Source.class);
                LinkedList<Weight_Body> result = api_source.getResult();
                LinkedList<Map<String,Object>> linkedList=new LinkedList<>();
                for(int i=0;i<result.size();i++) {
                    if (i != unit - 1) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("val", result.get(i).getValue());
                        map.put("unit", result.get(i).getName());
                        linkedList.add(map);
                    }
                }
                ItemFragment itemFragment = new ItemFragment(linkedList);
                itemFragment.change(linkedList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

}
