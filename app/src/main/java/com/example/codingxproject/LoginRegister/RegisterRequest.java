package com.example.codingxproject.LoginRegister;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
//    https://bethhaha.000webhostapp.com/Register.php
    //http://10.0.2.2:80/loginRegister/Register.php
    //http://192.168.210.7:80/loginRegister/Register.php
    private static final String REGISTER_REQUEST_URL="http://192.168.210.7:80/loginRegister/Register.php";//files.000webhost.com:21/Register.php
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
