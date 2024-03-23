package com.testphase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name, email, role, cellNo, password;
    Button RegBtn;
    Boolean result;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DbHandler(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        cellNo = findViewById(R.id.cellNo);
        role = findViewById(R.id.role);
        password = findViewById(R.id.password);
        RegBtn = findViewById(R.id.BtnReg);
        
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.length() == 0){
                    name.setError("Name Required");
                }
                else if (role.length() == 0){
                    role.setError("Role Required");
                }
                else if (email.length() == 0){
                    email.setError("Email Required");
                }
                else if (cellNo.length() == 0){
                    cellNo.setError("Cell No Required");
                }
                else if (password.length() == 0){
                    password.setError("Password Required");
                }

                else{

                    result = db.register(name.getText().toString(), email.getText().toString(), role.getText().toString(), cellNo.getText().toString(), password.getText().toString());
                    if (result){
                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), AdminMain.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}