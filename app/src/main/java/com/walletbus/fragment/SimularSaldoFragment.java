package com.walletbus.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.walletbus.R;
import com.walletbus.config.ConfiguracaoFirebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimularSaldoFragment extends Fragment {

    private EditText editPassaegns, editDias, editSaldoAtual;
    private RadioButton radioMeia, raioInteira;
    private RadioGroup radioTipo;
    private Button buttonCalcular;
    private TextView textViewResutado;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();


    public SimularSaldoFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simular_saldo, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editSaldoAtual = view.findViewById(R.id.editSaldoAtual);
        editPassaegns = view.findViewById(R.id.editPassagens);
        editDias = view.findViewById(R.id.editDias);
        buttonCalcular = view.findViewById(R.id.button_calcular);
        radioTipo = view.findViewById(R.id.radioTipo);
        textViewResutado = view.findViewById(R.id.resultado_final);

        verificaTipo();
    }

    public void verificaTipo(){
        radioTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radioMeia){
                   //
                }else{
                   //
                }
            }
        });
    }


    public void simularSaldo(){



    }

}
