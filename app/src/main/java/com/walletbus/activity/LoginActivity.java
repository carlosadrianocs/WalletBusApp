package com.walletbus.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.walletbus.MainActivity;
import com.walletbus.R;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.model.Usuario;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth autenticacaoLogin;
    private EditText campoEmail, campoSenha;
    private Button  botaoEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.btnEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();


                if (!textoEmail.isEmpty()){

                    if (!textoSenha.isEmpty()){

                    usuario = new Usuario();
                    usuario.setEmail( textoEmail );
                    usuario.setSenha( textoSenha );
                    validarLogin();

                    }else{
                       // Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                        campoSenha.setError("Preenha sua senha!");
                    }

                }else{
                 //   Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                    campoEmail.setError("Preenha o email!");
                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();

    }

    /*-----------------------VALIDANDO USUARIO --------------------------*/

 public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    abrirTelaPrincipal();

                //    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login",Toast.LENGTH_SHORT).show();

                }else{

                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e ){

                        excecao = "Usuário não está cadastrado!";

                    }catch (FirebaseAuthInvalidCredentialsException e){

                        excecao = "E-mail e senha não correspondem a um usuário cadastrado";

                    }catch (Exception e ){
                        excecao = " Erro ao fazer login: " + e.getMessage();
                        e.printStackTrace();
                    }


                        Toast.makeText(LoginActivity.this, excecao,Toast.LENGTH_SHORT).show();



                }
            }
        });

 }
    /*-----------------------VALIDANDO USUARIO --------------------------*/


    /*-----------------------Activiy principal --------------------------*/
public void abrirTelaPrincipal(){
    startActivity(new Intent(this, MainActivity.class));
    finish();

}

/*Chamando as activitys Login e cadastro*/

    public void btnCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }


    /*-----------------------Verificar usuario logado --------------------------*/


    public void verificarUsuarioLogado(){


        autenticacaoLogin = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacaoLogin.getCurrentUser() != null){

            abrirTelaPrincipalValidado();

        }

    }
    public void abrirTelaPrincipalValidado(){
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
//-----------------------------------------------------------------------------------------------------------------//
}
