package br.unipar.trabalhobimestralmobile.model;

import java.util.ArrayList;

public class Pedido {
    private int cod;
    private Cliente cliente;
    private ArrayList<Item> listaItens = new ArrayList<>();
    private FormaPgt formaPgtEnum;
    private Double vlrTotalPedido;

    private String parcelas;

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


    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }

    public Double getVlrTotalPedido() {
        return vlrTotalPedido;
    }

    public void setVlrTotalPedido(Double vlrTotalPedido) {
        this.vlrTotalPedido = vlrTotalPedido;
    }
}
