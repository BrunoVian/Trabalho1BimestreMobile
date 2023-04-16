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

import br.unipar.trabalhobimestralmobile.model.Item;
import br.unipar.trabalhobimestralmobile.model.Pedido;

public class MainActivity extends AppCompatActivity {

    private int contItem = 1;
    Pedido nvPedido = new Pedido();

    private EditText edCodPedido;
    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private EditText edDescItem;
    private EditText edQntdItem;
    private EditText edVlrUnitItem;
    private EditText edVlrTotalPedido;

    private TextView tvDescItem;
    private TextView tvQntdItem;
    private TextView tvVlrUnitItem;
    private TextView tvItens;

    private Button btAdicionarItem;
    private Button btIncluirItem;

    private RadioButton rbAVista;
    private RadioButton rbAPrazo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVisible(true);

        final int[] numPedido = {1};

        edCodPedido = findViewById(R.id.edCodPedido);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);

        edDescItem = findViewById(R.id.edDescItem);
        edQntdItem = findViewById(R.id.edQntdItem);
        edVlrUnitItem = findViewById(R.id.edVlrUnitItem);
        edVlrTotalPedido = findViewById(R.id.edVlrTotalPedido);

        btAdicionarItem = findViewById(R.id.btAdcItem);
        btIncluirItem = findViewById(R.id.btIncuirItem);

        tvDescItem = findViewById(R.id.tvDescItem);
        tvQntdItem = findViewById(R.id.tvQntdItem);
        tvVlrUnitItem = findViewById(R.id.tvVlrUnitItem);
        tvItens = findViewById(R.id.tvItens);

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
                    numPedido[0] = numPedido[0] +1;
                    //edCodPedido.setText(String.valueOf(numPedido[0]));
                }catch (Exception ex){
                    Log.e("ERRO BTNINLCUIR", ex.getMessage());
                }
            }
        });

        rbAPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rbAVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaTotalAVista();
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

    public void incluirItem(){

        String descricao = edDescItem.getText().toString();

        try {

            String resultado = tvItens.getText().toString();

            if(!descricao.equals("") && !edQntdItem.equals(null) && !edVlrUnitItem.equals(null)){ //valida se campos não estão vazios

                int qntdItem = Integer.parseInt(edQntdItem.getText().toString()); //recebe a quantidade
                Double vlrUnitarioItem = Double.valueOf(edVlrUnitItem.getText().toString()); // recebe o valor unitario

                Double vlrTotalItem = qntdItem * vlrUnitarioItem; //calcula o valor total para salvar no obj

                //cria o obj
                Item nvItem = new Item();
                nvItem.setDescricao(descricao);
                nvItem.setQuantidade(qntdItem);
                nvItem.setVlrUnitario(vlrUnitarioItem);
                nvItem.setVlrTotal(vlrTotalItem);

                tvItens.setVisibility(View.VISIBLE);

                //seta o resultado para exibir a lista
                resultado += "Item " + contItem + " - Descrição: " + descricao.toString()
                        + " | Quantidade: " + String.valueOf(nvItem.getQuantidade()) + " | VlrUnitario: "
                        + nvItem.getVlrUnitario() + " | Valor Total do Item: " + nvItem.getVlrTotal() + "\n\n";

                //exibe a lista
                tvItens.setText(resultado);

                //exibe a msg
                Toast.makeText(this, "Incluído com Sucesso!", Toast.LENGTH_SHORT).show();

                //adiciona mais 1 ao contador de itens da lista
                contItem++;

                //adiciona o item no pedido
                nvPedido.getListaItens().add(nvItem);

                fecharListagem();

            //exibe erro se algum campo estiver vazio
            }else{
                if(descricao.equals("")){
                    edDescItem.setError("Informe a Descrição do Item!");
                }
                if(edVlrUnitItem.getText().toString().equals("")){
                    edVlrUnitItem.setError("Valor Inválido!");
                }
                if(edQntdItem.getText().toString().equals("")){
                    edQntdItem.setError("Quantidade Inválida!");
                }


            }

        }catch (Exception ex){
            Log.e("ERRO NO IF/ELSE INCLUIR ITEM", ex.getMessage());
        }

    }

    public void calculaTotalAPrazo(){


    }

    public void calculaTotalAVista(){

        try {

            if(!nvPedido.getListaItens().isEmpty()) {
                double valorTotalPedido = 0;

                for (int i = 0; i < nvPedido.getListaItens().size(); i++) {
                    valorTotalPedido = valorTotalPedido + nvPedido.getListaItens().get(i).getVlrTotal();
                    edVlrTotalPedido.setText(String.valueOf(valorTotalPedido));
                }

            } else{
                Toast.makeText(this, "Não há itens na lista", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception ex){
            Log.e("ERRO CALCULA TOTAL A PRAZO", ex.getMessage());
        }

    }

}