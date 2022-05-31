package com.narvasoft.bd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.android.volley.Response;

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;
    EditText txtUser, txtPwd;
    Button btnsesion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_sesion, container, false);
        txtUser = (EditText) vista.findViewById(R.id.txtuser);
        txtPwd = (EditText) vista.findViewById(R.id.txtpwd);
        btnsesion = (Button) vista.findViewById(R.id.btnsesion);
        rq = Volley.newRequestQueue(getContext());
        btnsesion.setOnClickListener(view -> startSession());
        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Heroe not found "+ error.toString(),Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Heroe not found "+ txtUser.toString(),Toast.LENGTH_SHORT).show();
        User usuario = new User();
        JSONArray jsonArray = response.optJSONArray("data");
        JSONObject jsonObject = null;
        try{
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUsername(jsonObject.optString("username"));
            usuario.setPassword(jsonObject.optString("passsword"));
            usuario.setName(jsonObject.optString("name"));
            usuario.setLast_name(jsonObject.optString("last_name"));
            usuario.setNick(jsonObject.optString("nick"));

        }catch (JSONException e){
            e.printStackTrace();
        }

        Intent intention = new Intent(getContext(), MainActivity2.class);
        intention.putExtra(MainActivity2.nick, usuario.getNick());
        String name = usuario.getName() + ' '+ usuario.getLast_name();
        intention.putExtra(MainActivity2.name, name);
        startActivity(intention);


    }


    void startSession(){
        String url ="https://myappucn2022.000webhostapp.com/session.php?user="+txtUser.getText().toString()+
          "&pwd="+txtPwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,
                url,
                (String) null,
                this,
                this);
        rq.add(jrq);
    }

}