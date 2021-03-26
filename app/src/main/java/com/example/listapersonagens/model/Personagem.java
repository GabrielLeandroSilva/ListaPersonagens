package com.example.listapersonagens.model;

public class Personagem {
    //Variaveis para guardar a informação de nome, altura e nascimento para manipulação do get e set
    private final String nome;
    private final String altura;
    private final String nascimento;


    public Personagem(String nome, String altura, String nascimento) {
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return nome;
    }

    //Metodo para retornar o nascimento guardado
   /* public String getNascimento() {
        return nascimento;
    }*/
    //Metodo para retornar o nome guardado
   /* public String getNome() {
        return nome;
    }*/
    //Metodo para retornar o altura guardado
   /* public String getAltura() {
        return altura;
    }*/
}
