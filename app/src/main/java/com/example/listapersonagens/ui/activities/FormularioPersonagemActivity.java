package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        //Metodo para utilizar o icone de check para salvar dados do personagem
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Metodo para clicar no check e salvar os dados do personagem
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_personagem_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //Organização do formulario do personagem
        inicializacaoCampos();
        //Metodo para utilizar o evento de click do botão para salvar
        configuraBotao();
        //Metodo para construção do formulario
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
        //Metodo para associar as variaveis nome, altura e nascimento para serem setados
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

        //Criação de mascara para o campo altura
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(alturaPersonagem, smfAltura);
        alturaPersonagem.addTextChangedListener(mtwAltura);

        //Criação de mascara para o campo nascimento
        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(nascimentoPersonagem, smfNascimento);
        nascimentoPersonagem.addTextChangedListener(mtwNascimento);

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