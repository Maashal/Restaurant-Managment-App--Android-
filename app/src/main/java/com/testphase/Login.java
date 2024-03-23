package com.testphase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button Login;
    DbHandler db;
    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DbHandler(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.BtnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.length() == 0){
                    email.setError("Email Required");
                }
                else if (password.length() == 0){
                    password.setError("Password Required");
                }
                else{

                    String Email = email.getText().toString();
                    String Password = password.getText().toString();
                    if (Email.equals("admin@gmail.com") && Password.equals("admin")){
                        Toast.makeText(Login.this, " Login Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), AdminMain.class));
                    }
                    else {
                        result = db.login(email.getText().toString(), password.getText().toString());
                        if (result){
                            Toast.makeText(Login.this, " Login Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                        }
                    }


                }
            }
        });
    }

}