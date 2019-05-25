package com.walletbus.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.walletbus.R;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.helper.Base64Custom;
import com.walletbus.helper.MaskEditUtil;
import com.walletbus.model.Usuario;

import es.dmoral.toasty.Toasty;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha, campoCpf, campoNumero;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    // TODO - onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            campoNome = findViewById(R.id.editTextNome);
            campoCpf = findViewById(R.id.editTextCpf);
            campoNumero = findViewById(R.id.editTextNumero);
            campoEmail = findViewById(R.id.editTextEmail);
            campoSenha = findViewById(R.id.editTextSenha);
            botaoCadastrar = findViewById(R.id.buttonCadastro);

            campoCpf.addTextChangedListener(MaskEditUtil.mask(campoCpf, MaskEditUtil.FORMAT_CPF));


        // TODO Validar Campos

        botaoCadastrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validando campos preenchidos

                String textoNome = campoNome.getText().toString();
                String textoCpf = campoCpf.getText().toString();
                String textoNumero = campoNumero.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoNome.isEmpty()){

                    if (!textoCpf.isEmpty()){

                        if (!textoNumero.isEmpty()){

                            if (!textoEmail.isEmpty()){

                        if (!textoSenha.isEmpty()){


                            usuario = new Usuario();

                            usuario.setNome( textoNome );
                            usuario.setCpfUsuario( textoCpf );
                            usuario.setNumeroCartao( textoNumero );
                            usuario.setEmail( textoEmail );
                            usuario.setSenha( textoSenha );

                            cadastrarUsuario();




                        }else{
                            // Toast.makeText(CadastroActivity.this, "Preencha sua senha!", Toast.LENGTH_SHORT).show();
                            campoSenha.setError("Preenha sua senha!");
                        }

                    }else{
                        // Toast.makeText(CadastroActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                        campoEmail.setError("Preencha o email!");
                    }
                        }else{
                            // Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                            campoNumero.setError("Preencha seu com o Numero do seu Cartão!"); }

                        }else{
                            // Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                            campoCpf.setError("Preencha seu CPF!"); }

                }else{
                   // Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                    campoNome.setError("Preencha o nome!");

                }

            }
        });


    }


    // TODO Cadastro do Usuario
    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        Task<AuthResult> authResultTask = autenticacao.createUserWithEmailAndPassword(

                usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario( idUsuario);
                    usuario.salvar();
                    finish();

                } else {

                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){

                        excecao = "Digite uma senha mais forte!";

                    }catch (FirebaseAuthInvalidCredentialsException e ){

                        excecao = "Digite um E-mail válido!";

                    }catch (FirebaseAuthUserCollisionException e){

                        excecao = "Esta conta já foi cadastrada";

                    }catch (FirebaseNetworkException e){
                        excecao = "Verifique sua conexão";

                    }catch (Exception e ){
                        excecao = " Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toasty.error(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
