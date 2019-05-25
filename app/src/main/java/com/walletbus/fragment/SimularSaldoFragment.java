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

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimularSaldoFragment extends Fragment {

    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    private EditText editPass;
    private EditText editQtd;
    private TextView textResuldado;
    private Button btnSimular;


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

        editPass = view.findViewById(R.id.editPass);
        editQtd = view.findViewById(R.id.editQtd);
        textResuldado = view.findViewById(R.id.textResultado);
        btnSimular = view.findViewById(R.id.btnSimular);

        btnSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              textResuldado.setText("Em andamento");

            }
        });


    }

}
