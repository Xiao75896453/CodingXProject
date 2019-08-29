package com.example.codingxproject.LoginRegister;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="https://bethhaha.000webhostapp.com/Register.php";//files.000webhost.com:21/Register.php
    private Map<String,String> params;

    public RegisterRequest(String name, String username, String password, Response.Listener<String>listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
