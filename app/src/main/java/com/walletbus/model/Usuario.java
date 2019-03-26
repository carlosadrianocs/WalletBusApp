package com.walletbus.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.walletbus.config.ConfiguracaoFirebase;

public class Usuario {


    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Double saldo = 0.00;
    private int numeroCartao = 12345;

    public Usuario() {

    }

    public void salvar(){
       DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
       firebase.child("usuarios")
               .child(this.idUsuario)
               .setValue(this);

    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
