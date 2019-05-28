package com.walletbus.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.walletbus.R;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.helper.Base64Custom;
import com.walletbus.model.Usuario;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

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
    private Double saldoAtual;


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





        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        final DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                saldoAtual = usuario.getSaldo();

                NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
                String resultadoFormatada = nf.format(saldoAtual);

                //Recuperar saldo atualizado na tela
                textResuldado.setText( resultadoFormatada );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double passagens = Double.parseDouble(editPass.getText().toString());
                double quantidade = Double.parseDouble(editQtd.getText().toString());

                Double resultado = saldoAtual / passagens;
                textResuldado.setText("São Aproximadamente " + resultado + " passagens contando todos os dias úteis");



            }
        });


    }

}
