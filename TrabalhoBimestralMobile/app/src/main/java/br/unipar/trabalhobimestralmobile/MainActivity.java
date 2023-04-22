package br.unipar.trabalhobimestralmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unipar.trabalhobimestralmobile.model.Cliente;
import br.unipar.trabalhobimestralmobile.model.Item;
import br.unipar.trabalhobimestralmobile.model.Pedido;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

    private ArrayList<Item> listaItens = new ArrayList<Item>();
    private double valorTotalPedido = 0;

    private int contItem = 1;
    final int[] numPedido = {1};

    private EditText edCodPedido;
    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private EditText edDescItem;
    private EditText edQntdItem;
    private EditText edVlrUnitItem;
    private EditText edVlrTotalPedido;
    private EditText edQntParcelas;

    private TextView tvDescItem;
    private TextView tvQntdItem;
    private TextView tvVlrUnitItem;
    private TextView tvItens;
    private TextView tvParcelas;

    private TextView tvInfoParcelas;

    private Button btAdicionarItem;
    private Button btIncluirItem;
    private Button btBuscarPedido;
    private Button btGerarParcelas;
    private Button btConcluirPedido;

    private RadioButton rbAVista;
    private RadioButton rbAPrazo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVisible(true);

        edCodPedido = findViewById(R.id.edCodPedido);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);

        edDescItem = findViewById(R.id.edDescItem);
        edQntdItem = findViewById(R.id.edQntdItem);
        edVlrUnitItem = findViewById(R.id.edVlrUnitItem);
        edVlrTotalPedido = findViewById(R.id.edVlrTotalPedido);
        edQntParcelas = findViewById(R.id.edQntParcelas);

        btAdicionarItem = findViewById(R.id.btAdcItem);
        btIncluirItem = findViewById(R.id.btIncuirItem);
        btBuscarPedido = findViewById(R.id.btBuscarPedido);
        btGerarParcelas = findViewById(R.id.btGerarParcelas);
        btConcluirPedido = findViewById(R.id.btConcluirPedido);

        tvDescItem = findViewById(R.id.tvDescItem);
        tvQntdItem = findViewById(R.id.tvQntdItem);
        tvVlrUnitItem = findViewById(R.id.tvVlrUnitItem);
        tvItens = findViewById(R.id.tvItens);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvInfoParcelas = findViewById(R.id.tvInfoParcelas);

        rbAPrazo = findViewById(R.id.rbAPrazo);
        rbAVista = findViewById(R.id.rbAVista);

        edCodPedido.setText(String.valueOf(numPedido[0]));

        btAdicionarItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirListagem();
                }
            });

        btIncluirItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    incluirItem();
                }catch (Exception ex){
                    Log.e("ERRO BTNINLCUIR", ex.getMessage());
                }
            }
        });

        btConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaPedido();
                edCodPedido.setText(String.valueOf(numPedido[0]));
            }
        });

        rbAPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaTotalAPrazo();
            }
        });

        rbAVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaTotalAVista();
            }
        });

        btBuscarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    buscaPedido();
                    Log.i("TESTE", listaPedidos.get(2).toString());
                }catch (Exception ex){
                    Log.e("ERRO BTNBUSCA PEDIDO", ex.getMessage());
                }
            }
        });

    }

    public void abrirListagem() {

            edDescItem.setVisibility(View.VISIBLE);
            tvDescItem.setVisibility(View.VISIBLE);
            edQntdItem.setVisibility(View.VISIBLE);
            tvQntdItem.setVisibility(View.VISIBLE);
            edVlrUnitItem.setVisibility(View.VISIBLE);
            tvVlrUnitItem.setVisibility(View.VISIBLE);
            btIncluirItem.setVisibility(View.VISIBLE);

    }

    public void fecharListagem(){
        edDescItem.setVisibility(View.GONE);
        tvDescItem.setVisibility(View.GONE);
        edQntdItem.setVisibility(View.GONE);
        tvQntdItem.setVisibility(View.GONE);
        edVlrUnitItem.setVisibility(View.GONE);
        tvVlrUnitItem.setVisibility(View.GONE);
        btIncluirItem.setVisibility(View.GONE);
    }

    public void limpaCampos(){
        edDescItem.setText(null);
        edQntdItem.setText(null);
        edVlrUnitItem.setText(null);
    }

    public void incluirItem(){

        String descricao = edDescItem.getText().toString();
        String resultado = tvItens.getText().toString();

        try {
            if(!descricao.equals("") && !edQntdItem.equals(null) && !edVlrUnitItem.equals(null)){

                int qntdItem = Integer.parseInt(edQntdItem.getText().toString());
                Double vlrUnitarioItem = Double.valueOf(edVlrUnitItem.getText().toString());

                Double vlrTotalItem = qntdItem * vlrUnitarioItem;

                Item nvItem = new Item();
                nvItem.setDescricao(descricao);
                nvItem.setQuantidade(qntdItem);
                nvItem.setVlrUnitario(vlrUnitarioItem);
                nvItem.setVlrTotal(vlrTotalItem);
                nvItem.setIdPedido(Integer.parseInt(edCodPedido.getText().toString()));

                tvItens.setVisibility(View.VISIBLE);

                resultado += "Item " + contItem + " - Descrição: " + descricao.toString()
                        + " | Quantidade: " + String.valueOf(nvItem.getQuantidade()) + " | VlrUnitario: R$ "
                        + String.format("%.2f", nvItem.getVlrUnitario()) + " | Valor Total do Item: R$ " + String.format("%.2f", nvItem.getVlrTotal()) + "\n\n";

                tvItens.setText(resultado);

                Toast.makeText(this, "Incluído com Sucesso!", Toast.LENGTH_SHORT).show();

                contItem++;

                listaItens.add(nvItem);

                limpaCampos();

                fecharListagem();

            }else{
                if(descricao.equals("") || edDescItem.getText().toString() == null){
                    edDescItem.setError("Informe a Descrição do Item!");
                }
                if(edVlrUnitItem.getText().toString().equals("") || Integer.parseInt(edVlrUnitItem.getText().toString()) <=0 ){
                    edVlrUnitItem.setError("Valor Inválido!");
                }
                if(edQntdItem.getText().toString().equals("") || Integer.parseInt(edQntdItem.getText().toString()) <=0 ){
                    edQntdItem.setError("Quantidade Inválida!");
                }
            }

        }catch (Exception ex){
            Log.e("ERRO NO IF/ELSE INCLUIR ITEM", ex.getMessage());
        }

    }

    public void calculaTotalAPrazo(){

        Double exibValorTotalPedido;

        try {

            if(!listaItens.isEmpty()) {

                for (int i = 0; i < listaItens.size(); i++) {
                    if(listaItens.get(i).getIdPedido() == Integer.parseInt(edCodPedido.getText().toString())){
                        valorTotalPedido = valorTotalPedido + listaItens.get(i).getVlrTotal();
                    }
                }

                exibValorTotalPedido = valorTotalPedido + (valorTotalPedido * 0.05);

                valorTotalPedido = 0;
                edVlrTotalPedido.setText(String.valueOf(exibValorTotalPedido));

                tvParcelas.setVisibility(View.VISIBLE);
                tvInfoParcelas.setVisibility(View.VISIBLE);
                edQntParcelas.setVisibility(View.VISIBLE);
                btGerarParcelas.setVisibility(View.VISIBLE);

                btGerarParcelas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int qntParcelas = 0;
                        Double vlrParcelas;
                        String parcelas = "";

                        if(!edQntParcelas.getText().toString().equals("")){

                            qntParcelas = Integer.parseInt(edQntParcelas.getText().toString());

                            vlrParcelas = exibValorTotalPedido/qntParcelas;

                            for(int i = 0; i<qntParcelas;i++){
                                parcelas += "Parcela " + (i + 1) + " - R$ " + String.format("%.2f", vlrParcelas) + "\n";
                            }
                            tvParcelas.setText(parcelas);
                        }
                    }
                });

            } else{
                Toast.makeText(this, "Não há itens na lista", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception ex){
            Log.e("ERRO CALCULAR TOTAL A PRAZO", ex.getMessage());
        }

    }

    public void calculaTotalAVista(){

        Double exibValorTotalPedido;

        tvInfoParcelas.setVisibility(View.GONE);
        edQntParcelas.setVisibility(View.GONE);
        btGerarParcelas.setVisibility(View.GONE);

        try {

            if(!listaItens.isEmpty()) {

                for (int i = 0; i < listaItens.size(); i++) {
                    if(listaItens.get(i).getIdPedido() == Integer.parseInt(edCodPedido.getText().toString())) {
                        valorTotalPedido = valorTotalPedido + listaItens.get(i).getVlrTotal();
                    }
                }
                    exibValorTotalPedido = valorTotalPedido - (valorTotalPedido * 0.05);
                    valorTotalPedido = 0;

                    edVlrTotalPedido.setText(String.valueOf(exibValorTotalPedido));

            } else{
                Toast.makeText(this, "Não há itens na lista", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception ex){
            Log.e("ERRO CALCULA TOTAL A VISTA", ex.getMessage());
        }

    }

    public void limpaPedido(){

        edQntParcelas.setText(null);
        edQntdItem.setText(null);
        edVlrTotalPedido.setText(null);
        edDescItem.setText(null);
        edCpfCliente.setText(null);
        edNomeCliente.setText(null);

        tvParcelas.setText(null);
        tvItens.setText(null);

        rbAPrazo.setChecked(false);
        rbAVista.setChecked(false);

        tvInfoParcelas.setVisibility(View.GONE);
        edQntParcelas.setVisibility(View.GONE);
        btGerarParcelas.setVisibility(View.GONE);

    }

    public void finalizaPedido() {

        int codPedido = Integer.parseInt(edCodPedido.getText().toString());
        String nome = edNomeCliente.getText().toString();
        String cpf = edCpfCliente.getText().toString();
        String parcelas = tvParcelas.getText().toString();
        String formaPgt = "";

        if(rbAVista.isChecked()){
            formaPgt = "À Vista";
        } else if (rbAPrazo.isChecked()){
            formaPgt = "A Prazo";
        }

        if (!nome.equals("") && !cpf.equals("") && !formaPgt.equals("") && !edVlrTotalPedido.getText().toString().equals("") && !listaItens.isEmpty()) {

            Cliente cliente = new Cliente(nome, cpf);
            Pedido pedido = new Pedido(codPedido, cliente, listaItens, formaPgt, Double.parseDouble(edVlrTotalPedido.getText().toString()), parcelas);
            listaPedidos.add(pedido);

            Toast.makeText(this, "Pedido n° " + edCodPedido.getText().toString() + " cadastrado com sucesso!", Toast.LENGTH_LONG).show();

            numPedido[0] = numPedido[0] + 1;
            contItem = 1;
            limpaPedido();

        } else {
            if (nome.equals("")) {
                edNomeCliente.setError("Informe o Nome!");
            }
            if (cpf.equals("")) {
                edCpfCliente.setError("Informe o CPF!");
            }
            if (rbAPrazo.isChecked() == false && rbAVista.isChecked() == false) {
                Toast.makeText(this, "Informe uma forma de pagamento!", Toast.LENGTH_LONG).show();
            }
            if (listaItens.size() == 0) {
                Toast.makeText(this, "Não há pedidos na lista!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void buscaPedido(){

        int numPedidoBusca = Integer.parseInt(edCodPedido.getText().toString());
        String resultado = "";
        boolean existePedido = false;
        int i = 0;

        for(int posicao = 0; posicao<listaPedidos.size();posicao++){
            if(numPedidoBusca == listaPedidos.get(posicao).getCod()) {
                existePedido = true;
                i = posicao;
                break;
            }
        }

        if(numPedidoBusca >= 1 && existePedido == true) {

                if (listaPedidos.get(i).getCod() == Integer.parseInt(edCodPedido.getText().toString())) {

                    edNomeCliente.setText(listaPedidos.get(i).getCliente().getNome());
                    edCpfCliente.setText(listaPedidos.get(i).getCliente().getCpf());

                    for (int j = 0; j < listaPedidos.get(i).getListaItens().size(); j++) {
                        if(listaItens.get(j).getIdPedido() == Integer.parseInt(edCodPedido.getText().toString())) {
                            resultado += "Descrição: " + listaPedidos.get(i).getListaItens().get(j).getDescricao()
                                    + " | Quantidade: " + String.valueOf(listaPedidos.get(i).getListaItens().get(j).getQuantidade()) + " | VlrUnitario: R$ "
                                    + String.format("%.2f", listaPedidos.get(i).getListaItens().get(j).getVlrUnitario()) + " | Valor Total do Item: R$ "
                                    + String.format("%.2f", listaPedidos.get(i).getListaItens().get(j).getVlrTotal()) + "\n\n";
                        }
                    }
                    tvItens.setText(resultado);

                    if(listaPedidos.get(i).getFormaPgt() == "À Vista"){
                        rbAVista.setChecked(true);
                        edVlrTotalPedido.setText(String.valueOf(listaPedidos.get(i).getVlrTotalPedido()));
                        tvInfoParcelas.setVisibility(View.GONE);
                    }

                    if(listaPedidos.get(i).getFormaPgt() == "A Prazo"){
                        rbAPrazo.setChecked(true);
                        edVlrTotalPedido.setText(String.valueOf(listaPedidos.get(i).getVlrTotalPedido()));
                        tvInfoParcelas.setVisibility(View.VISIBLE);
                        tvInfoParcelas.setText(String.valueOf(listaPedidos.get(i).getParcelas()));
                    }

            }

            } else if(numPedidoBusca < 1){
                Toast.makeText(this, "Informe um valor maior que zero", Toast.LENGTH_SHORT).show();
            } else if(existePedido == false){
                Toast.makeText(this, "O pedido " + numPedidoBusca + " não existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

