package br.unipar.trabalhobimestralmobile.model;

public class Item {
    private String descricao;
    private int quantidade;
    private Double vlrUnitario;
    private Double vlrTotal;
    private int idPedido;

    public Item() {
    }

    public Item(String descricao, int quantidade, Double vlrUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.vlrUnitario = vlrUnitario;
    }

    public Item(String descricao, int quantidade, Double vlrUnitario, Double vlrTotal, int idPedido) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.vlrUnitario = vlrUnitario;
        this.vlrTotal = vlrTotal;
        this.idPedido = idPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    @Override
    public String toString() {
        return "Item{" +
                "descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", vlrUnitario=" + vlrUnitario +
                ", vlrTotal=" + vlrTotal +
                '}';
    }
}
