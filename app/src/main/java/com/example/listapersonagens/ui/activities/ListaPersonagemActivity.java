package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {


    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();

    }

    private void configuraLista() {
        //Index do dao para utilização
        ListView lista = findViewById(R.id.activity_main_list_personagem);
        //Referencia para poder acessar os dados como personagens
        final List<Personagem> personagens = dao.todos();
        listaDePersonagens(lista, personagens);
        configuraItemClique(lista);
    }

    private void configuraItemClique(ListView lista) {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                //Coletando as informações do personagem quando é clicado sobre ele na lista
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }

    private void abreFormularioModoEditar(Personagem personagemEscolhido) {
        Intent goFormulario = new Intent(this, FormularioPersonagemActivity.class);
        goFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(goFormulario);
    }

    private void listaDePersonagens(ListView lista, List<Personagem> personagens) {
        lista.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }

    //Metodo para navegar até o formulario
    public void ChangeFormulario(View view) {
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFormulario();
            }
        });

    }

    private void abrirFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

}
