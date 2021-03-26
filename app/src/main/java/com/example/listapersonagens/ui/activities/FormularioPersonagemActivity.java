package com.example.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;


public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulario de Personagens");

        PersonagemDAO dao = new PersonagemDAO();

        // Campo de nome do personagem (variavel)
        EditText nomePersonagem = findViewById(R.id.textInputNome);

        // Campo de altura do personagem (variavel)
        EditText alturaPersonagem = findViewById(R.id.editTextAltura);

        // Campo de nascimento do personagem (variavel)
        EditText nascimentoPersonagem = findViewById(R.id.editTextDate);



        // Variavel para armazenar informações sobre o botão de salvamento
        Button btnSalvar = findViewById(R.id.button);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Conversão dos valores passando para novas strings (variaveis)
                String nome =   nomePersonagem.getText().toString();
                String altura =   alturaPersonagem.getText().toString();
                String nascimento =   nascimentoPersonagem.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);

                //Navegação para a tela de lista de personagens
                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                //Toast para exibir todas as informações inputados pelo usuario sobre o novo personagem
                /*Toast.makeText(
                        FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " +
                                personagemSalvo.getAltura() + " - " +
                                personagemSalvo.getNascimento(),
                        Toast.LENGTH_SHORT).show();*/

                new Personagem(nome, altura, nascimento);



            }
        });

    }
}