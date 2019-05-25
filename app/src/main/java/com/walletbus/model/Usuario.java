package com.walletbus.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.walletbus.config.ConfiguracaoFirebase;

public class Usuario {


    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Double saldo = 10.00;
    private String categoria = "Estudante";
    private String numeroCartao;
    private String cpfUsuario;

    public Usuario() {

    }

    public void salvar(){
       DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
       firebase.child("usuarios")
               .child(this.idUsuario)
               .setValue(this);

    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
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

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

}
