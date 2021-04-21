package com.masjidtrpl.masjidku;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText user,email,pass,pass1;
    Button daftar;
    DatabaseReference reference;
    ProgressBar progressBar;

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


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(user.getText().toString()) || isEmpty(email.getText().toString()) || isEmpty(pass.getText().toString()) || isEmpty(pass1.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child("user").child("akun").push()
                            .setValue(new ModelSignUp(user.getText().toString(), email.getText().toString(), pass.getText().toString(), pass1.getText().toString()))
                            .addOnSuccessListener(SignUpActivity.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Tersimpan!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
}