package com.walletbus.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.walletbus.R;

import mehdi.sakout.aboutpage.AboutPage;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_sobre);

        String desc = "Conheça o WalletBus\n" +
                "O aplicativo de recarga de trasnposte público.\n" +
                "Chega de filas! Agora ficou mais fácil comprar seu crédito.";

        View sobre = new AboutPage(this)
                .setImage(R.drawable.ic_walletbus)
                .setDescription(desc)
                .addGroup("Fale conosco")
                .addEmail("walletbus@gmail.com","Envie um e-mail")
                .addWebsite("walletbus.ga", "Acesse nosso site")
//                .addGroup("Redes sociais")
                .create();

        setContentView( sobre );
    }
}
