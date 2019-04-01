package com.walletbus.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.walletbus.R;
import com.walletbus.helper.DateCustom;

public class RecargaActivity extends AppCompatActivity {

    private EditText editValorRecarga;
    private TextView campoData;
    private Button buttonRecarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editValorRecarga = findViewById(R.id.editValorRecarga);
        campoData = findViewById(R.id.editData);
        buttonRecarga = findViewById(R.id.btnrecarregar);

        //Preencher o campo com data atual
        campoData.setText(DateCustom.dataAtual());



    }
}
