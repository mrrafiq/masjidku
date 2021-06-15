package com.masjidtrpl.masjidku;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText user,email,pass,pass1;
    Button daftar;
    DatabaseReference reference;
    FirebaseAuth auth;
    String getEmail, getPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pass1 = findViewById(R.id.passwordConfirm);
        daftar = findViewById(R.id.btnDaftar);


        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(email.getText().toString()) || isEmpty(pass.getText().toString()) || isEmpty(pass1.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.getText().toString().equals(pass1.getText().toString())){
                        cekDataUser();
                    } else{
                        Toast.makeText(SignUpActivity.this, "Password Tidak boleh berbeda", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void cekDataUser() {
        getEmail = email.getText().toString();
        getPass = pass.getText().toString();

        if (getPass.length() < 8){
            Toast.makeText(this, "Panjang password kurang dari 8 karakter", Toast.LENGTH_SHORT).show();
        } else{
            createUserAccount();
        }
    }

    private void createUserAccount() {
        auth.createUserWithEmailAndPassword(getEmail, getPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Sign Up Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                            finish();
                        } else{
                            Toast.makeText(SignUpActivity.this, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
}