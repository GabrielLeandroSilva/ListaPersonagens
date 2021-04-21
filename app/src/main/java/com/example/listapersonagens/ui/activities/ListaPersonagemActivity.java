package com.example.listapersonagens.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {


    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeFormulario();
        configuraLista();

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    //Metodo para remover personagem se clicar e segurar
    private void remove(Personagem personagem) {
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    //Metodo para exibir caixa de menu para remover item da lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
       // menu.add("Remover");
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }

    //Metodo para obter a variavel e função de remover
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);

    }

    //Metodo para exibir caixa de menu questionando se deve remover personagem
    private void configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null).show();
        }
    }

    //Referencia para poder acessar os dados como personagens
    private void configuraLista() {
        //Index do dao para utilização
        ListView lista = findViewById(R.id.activity_main_list_personagem);
        configuraAdapter(lista);
        configuraItemClique(lista);
        registerForContextMenu(lista);
    }

    //Coletando as informações do personagem quando é clicado sobre ele na lista
    private void configuraItemClique(ListView lista) {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }

    //Metodo para navegar para o formulario para editar informações
    private void abreFormularioModoEditar(Personagem personagemEscolhido) {
        Intent goFormulario = new Intent(this, FormularioPersonagemActivity.class);
        goFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(goFormulario);
    }

    //Metodo para configuração do adapter
    private void configuraAdapter(ListView lista) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lista.setAdapter(adapter);
    }

    //Metodo para navegar até o formulario
    private void ChangeFormulario() {
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });

    }

    //Metodo para abir o formulario (navegação)
    private void abrirFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

}
