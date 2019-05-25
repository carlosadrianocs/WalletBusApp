package com.walletbus.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.walletbus.R;
import com.walletbus.adapter.AdapterMovimentacao;
import com.walletbus.config.ConfiguracaoFirebase;
import com.walletbus.helper.Base64Custom;
import com.walletbus.model.Movimentacao;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HitoricoFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private AdapterMovimentacao adapterMovimentacao;
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference movimentacaoRef;
    private String mesAnoSelecionado;
    private ValueEventListener valueEventListenerMovimentacoes;

    public HitoricoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hitorico, container, false);


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.recyclerMovimentos);

        configuraCalendarView();

        //configurar adapter

        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, getActivity());


        //configurar ReciclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( adapterMovimentacao );

    }

    // TODO - Historico dos meses
    public void configuraCalendarView(){

        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};

        calendarView.setTitleMonths(meses);

        CalendarDay dataAtual = calendarView.getCurrentDate();
        String mesSelecionado = String.format("%02d", (dataAtual.getMonth() + 1));
        mesAnoSelecionado = String.valueOf( mesSelecionado + "" + dataAtual.getYear());

        try {
            movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
            recuperarMovimentacoes();
        }catch (Exception e){
            e.printStackTrace();
        }


        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mesSelecionado = String.format("%02d", (date.getMonth() + 1));
                mesAnoSelecionado = String.valueOf( mesSelecionado + "" + date.getYear());
            }
        });

    }
    // TODO - Recuperando movimentacoes
    public void recuperarMovimentacoes() {

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        movimentacaoRef = firebaseRef.child("movimentacao")
                .child(idUsuario)
                .child(mesAnoSelecionado);

        valueEventListenerMovimentacoes = movimentacaoRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                movimentacoes.clear();

                for (DataSnapshot dados: dataSnapshot.getChildren()){

                    Movimentacao movimentacao = dados.getValue( Movimentacao.class);
//                    Log.i("dados", "retorno: " + movimentacao.getCategoria());
//                    Log.i("dados", "retorno: " + movimentacao.getValor());
//                    Log.i("dados", "retorno: " + movimentacao.getData());
                    movimentacoes.add(movimentacao);

                }

                adapterMovimentacao.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onStart(){
        super.onStart();
        recuperarMovimentacoes();

    }

    @Override
    public void onStop() {
        super.onStop();
        movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
    }

}

