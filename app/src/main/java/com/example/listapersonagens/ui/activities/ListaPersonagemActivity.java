package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Lista estatica com nomes dos personagens
       // List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Chunli"));


    }

    @Override
    protected void onResume() {
        super.onResume();
        PersonagemDAO dao = new PersonagemDAO();

        ListView lista = findViewById(R.id.activity_main_list_personagem);
        List<Personagem> personagens = dao.todos();
        lista.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
               //Coletando as informações do personagem quando é clicado sobre ele na lista
                Personagem personagemEscolhido = personagens.get(posicao);

                Intent goFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                startActivity(goFormulario);
            }
        });
    }

    //Metodo para navegar até o formulario
    public void ChangeFormulario(View view) {
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

    }

}
