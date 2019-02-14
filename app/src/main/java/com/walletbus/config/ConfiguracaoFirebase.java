package com.walletbus.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    //retorna a instancia do firebaseDatabase

    public static DatabaseReference getFirebaseDatabase(){
        if (firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;

    }

    //retorna a instancia do Firebase

    public static FirebaseAuth getFirebaseAutenticacao(){
            if (autenticacao == null){

                autenticacao = FirebaseAuth.getInstance();

            }

            return autenticacao;

    }

}
