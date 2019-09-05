package com.example.codingxproject.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.codingxproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName=(EditText) findViewById(R.id.etName);
        final EditText etUserName=(EditText) findViewById(R.id.etUserName);
        final EditText etPassword=(EditText) findViewById(R.id.etPassword);
        final Button bRegister=(Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String name= etName.getText().toString();
                final String username=etUserName.getText().toString();
                final String password=etPassword.getText().toString();
                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//
                            JSONObject jsonResponse=new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                            //以下不會執行，DEBUG
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
//                                Log.d("test","i'm here.");
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest=new RegisterRequest(name, username, password, responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
//

            }
        });
    }
}
