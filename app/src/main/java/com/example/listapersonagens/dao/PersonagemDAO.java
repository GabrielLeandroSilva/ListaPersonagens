package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //Lista para salvar os personagens para persistir os dados
    private final static List<Personagem> personagens = new ArrayList<>();

    //Metodo para salvar as informações do Personagem
    public void salva(Personagem personagemSalvo) {
        personagens.add(personagemSalvo);
    }

    //Metodo para exibir os personagens salvos (todos)
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
