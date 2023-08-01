package org.example;

public class Pedido {

    private String nomePizza;

    private int qtPizza;

    public Pedido() {}

    public Pedido(String nomePizza, int qtPizza) {
        this.nomePizza = nomePizza;
        this.qtPizza = qtPizza;
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public int getQtPizza() {
        return qtPizza;
    }

    public void setQtPizza(int qtPizza) {
        this.qtPizza = qtPizza;
    }
}
