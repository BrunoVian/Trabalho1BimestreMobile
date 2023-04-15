package br.unipar.trabalhobimestralmobile.model;

import java.util.ArrayList;

public class Pedido {
    private int cod;
    private Cliente cliente;
    private ArrayList<Item> listaItens = new ArrayList<>();
    private FormaPgt formaPgtEnum;
    private int desconto;
    private int acrescimo;
    private Double vlrTotalPedido;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Item> getListaItens() {
        return listaItens;
    }

    public void setListaItens(ArrayList<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public FormaPgt getFormaPgtEnum() {
        return formaPgtEnum;
    }

    public void setFormaPgtEnum(FormaPgt formaPgtEnum) {
        this.formaPgtEnum = formaPgtEnum;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(int acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getVlrTotalPedido() {
        return vlrTotalPedido;
    }

    public void setVlrTotalPedido(Double vlrTotalPedido) {
        this.vlrTotalPedido = vlrTotalPedido;
    }
}
