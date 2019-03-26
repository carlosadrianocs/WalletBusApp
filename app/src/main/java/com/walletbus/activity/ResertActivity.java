package com.walletbus.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.walletbus.R;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.model.Usuario;

import es.dmoral.toasty.Toasty;

import static com.google.android.gms.internal.measurement.zzsl.init;

public class ResertActivity extends AppCompatActivity {


    private FirebaseAuth autenticacao;
    private EditText emailReset;
    private Button botaoReset;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resert);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        emailReset = findViewById(R.id.editEmailReset);
        botaoReset = findViewById(R.id.btnReset);

        botaoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = emailReset.getText().toString();


                if (!textoEmail.isEmpty()) {

                    reset();

                } else {
                    //   Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                    emailReset.setError("Preenha o email!");
                }

            }
        });

    }

    public void reset() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.sendPasswordResetEmail(
                emailReset.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(ResertActivity.this, "Recuperação de acesso. E-mail enviado",
                                    Toasty.LENGTH_LONG).show();

                        } else {
                            String excecao = "";

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {

                                excecao = "Usuário não está cadastrado!";

                            } catch (Exception e) {
                                excecao = "Falhou! Tente novamente: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toasty.error(ResertActivity.this, excecao, Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}