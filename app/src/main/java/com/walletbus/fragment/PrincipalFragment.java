package com.walletbus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.walletbus.R;
import com.walletbus.activity.RecargaActivity;
import com.walletbus.activity.MapsActivity;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.helper.Base64Custom;
import com.walletbus.model.Usuario;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment {

    private TextView textoSaudacao, textoSaldo;
    private CardView botaorecarga, botaoSimular, botaoMaps, botaoHistorico;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double saldoAtualizado;


    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
        textoSaudacao = rootView.findViewById(R.id.textSaudacao);
        textoSaldo = rootView.findViewById(R.id.textSaldoAtual);
//        recuperarDados(); RECUPERAR DADOS DO USUARIO
        return rootView;


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

            botaoMaps = view.findViewById(R.id.BtnTerminais);
            botaorecarga = view.findViewById(R.id.BtnRecarga);
            botaoSimular = view.findViewById(R.id.BtnSaldo);
            botaoHistorico = view.findViewById(R.id.BtnHistorico);

            botaoMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Intent intent = new Intent(getActivity(), MapsActivity.class); startActivity(intent); }});

            botaorecarga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Intent intent = new Intent(getActivity(), RecargaActivity.class); startActivity(intent);
                }
            });

        botaoSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fr = new SimularSaldoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.addToBackStack("pilha");//Colocando o fragment em uma pilha
                fragmentTransaction.replace(R.id.frameContainer, fr);
                fragmentTransaction.commit();
            }
        });

        botaoHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fr = new HitoricoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.addToBackStack("pilha");//Colocando o fragment em uma pilha
                fragmentTransaction.replace(R.id.frameContainer, fr);
                fragmentTransaction.commit();
            }
        });

    }
    public void recuperarDados() {

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        final DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                try {
                    textoSaudacao.setText("Olá, " + usuario.getNome());
                    saldoAtualizado = usuario.getSaldo();
                    DecimalFormat decimalFormat = new DecimalFormat("0.##");
                    String resultadoFormatada = decimalFormat.format(saldoAtualizado);

                    //Recuperar saldo atualizado na tela
                    textoSaldo.setText( resultadoFormatada );

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void onStart(){
        super.onStart();
        recuperarDados();
        Log.d("Ciclo", "Fragment: Metodo onStart() chamado");

    }



}
