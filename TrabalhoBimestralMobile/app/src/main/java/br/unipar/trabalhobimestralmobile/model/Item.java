package br.unipar.trabalhobimestralmobile.model;

public class Item {
    private String descricao;
    private int quantidade;
    private Double vlrUnitario;
    private Double vlrTotal;

    public Item() {
    }

    public Item(String descricao, int quantidade, Double vlrUnitario, Double vlrTotal) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.vlrUnitario = vlrUnitario;
        this.vlrTotal = vlrTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }
}
