package br.unipar.trabalhobimestralmobile.model;

import java.util.ArrayList;

public class Pedido {
    private int cod;
    private Cliente cliente;
    private ArrayList<Item> listaItens = new ArrayList<Item>();
    private String formaPgt;
    private Double vlrTotalPedido;
    private String parcelas;

    public Pedido() {

    }

    public Pedido(int cod, Cliente cliente, ArrayList<Item> listaItens) {
        this.cod = cod;
        this.cliente = cliente;
        this.listaItens = listaItens;
    }

    public Pedido(int cod, Cliente cliente, ArrayList<Item> listaItens, String formaPgt, Double vlrTotalPedido, String parcelas) {
        this.cod = cod;
        this.cliente = cliente;
        this.listaItens = listaItens;
        this.formaPgt = formaPgt;
        this.vlrTotalPedido = vlrTotalPedido;
        this.parcelas = parcelas;
    }

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

    public String getFormaPgt() {
        return formaPgt;
    }

    public void setFormaPgt(String formaPgt) {
        this.formaPgt = formaPgt;
    }

    public Double getVlrTotalPedido() {
        return vlrTotalPedido;
    }

    public void setVlrTotalPedido(Double vlrTotalPedido) {
        this.vlrTotalPedido = vlrTotalPedido;
    }

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cod=" + cod +
                ", cliente=" + cliente +
                ", listaItens=" + listaItens +
                ", formaPgt='" + formaPgt + '\'' +
                ", vlrTotalPedido=" + vlrTotalPedido +
                ", parcelas='" + parcelas + '\'' +
                '}';
    }
}
