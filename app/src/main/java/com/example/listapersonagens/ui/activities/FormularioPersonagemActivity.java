package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;


public class FormularioPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    private static final String TITULO_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText nomePersonagem;
    private EditText alturaPersonagem;
    private EditText nascimentoPersonagem;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        
        inicializacaoCampos();
        configuraBotao();
        carregaPersonagem();

    }

    private void carregaPersonagem() {
        //Pegar informações de item para trabalhar na lista
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        } else {
            setTitle(TITULO_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        nomePersonagem.setText(personagem.getNome());
        alturaPersonagem.setText(personagem.getAltura());
        nascimentoPersonagem.setText(personagem.getNascimento());
    }

    private void configuraBotao() {
        // Variavel para armazenar informações sobre o botão de salvamento
        Button btnSalvar = findViewById(R.id.button);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencherPersonagem();
        if (personagem.IdValido()) {
            dao.edita(personagem);
        } else {
            dao.salva(personagem);
        }
        finish();
    }

    private void inicializacaoCampos() {
        // Campo de nome do personagem (variavel)
        nomePersonagem = findViewById(R.id.textInputNome);

        // Campo de altura do personagem (variavel)
        alturaPersonagem = findViewById(R.id.editTextAltura);

        // Campo de nascimento do personagem (variavel)
        nascimentoPersonagem = findViewById(R.id.editTextDate);
    }

    private void preencherPersonagem() {

        // Conversão dos valores passando para novas strings (variaveis)
        String nome = nomePersonagem.getText().toString();
        String altura = alturaPersonagem.getText().toString();
        String nascimento = nascimentoPersonagem.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);

    }

}