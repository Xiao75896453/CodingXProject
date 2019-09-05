package com.example.codingxproject.LoginRegister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

//    https://bethhaha.000webhostapp.com/Login.php
    //http://10.0.2.2:80/loginRegister/Login.php  (自己的模擬器)
    //http://192.168.210.7:80/loginRegister/Login.php  (真機和別人的模擬器)
    private static final String LOGIN_REQUEST_URL="http://192.168.210.7:80/loginRegister/Login.php";//files.000webhost.com:21/Register.php
    private Map<String,String> params;

    public LoginRequest(String username, String password, Response.Listener<String>listener){
        super(Method.POST,LOGIN_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
