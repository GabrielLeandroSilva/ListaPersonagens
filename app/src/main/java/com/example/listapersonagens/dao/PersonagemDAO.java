package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //Lista para salvar os personagens para persistir os dados
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorId = 1;

    //Metodo para salvar as informações do Personagem
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorId);
        personagens.add(personagemSalvo);
        contadorId++;
    }

    //metodo para edição de personagem da lista
    public void edita(Personagem personagem) {
        Personagem personagemEscolhido = null;
        for (Personagem p: personagens) {
            if(p.getId() == personagem.getId()) {
                personagemEscolhido = p;
            }
        }
        if(personagemEscolhido != null) {
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagem);
        }

    }

    //Metodo para exibir os personagens salvos (todos)
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
