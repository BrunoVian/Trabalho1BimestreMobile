package br.unipar.trabalhobimestralmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText edVlrTotalItem;

    private TextView tvDescItem;
    private TextView tvQntdItem;
    private TextView tvVlrUnitItem;
    private TextView tvVlrTotalItem;
    private TextView tvItens;

    private Button btAdicionarItem;
    private Button btIncluirItem;


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
        edVlrTotalItem = findViewById(R.id.edVlrTotalItem);

        btAdicionarItem = findViewById(R.id.btAdcItem);
        btIncluirItem = findViewById(R.id.btIncuirItem);

        tvDescItem = findViewById(R.id.tvDescItem);
        tvQntdItem = findViewById(R.id.tvQntdItem);
        tvVlrTotalItem = findViewById(R.id.tvVlrTotalItem);
        tvVlrUnitItem = findViewById(R.id.tvVlrUnitItem);
        tvItens = findViewById(R.id.tvItens);

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
                    fecharListagem();
                    numPedido[0] = numPedido[0] +1;
                    edCodPedido.setText(String.valueOf(numPedido[0]));
                }catch (Exception ex){
                    Log.e("ERRO", ex.getMessage());
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
            edVlrTotalItem.setVisibility(View.VISIBLE);
            tvVlrTotalItem.setVisibility(View.VISIBLE);
            btIncluirItem.setVisibility(View.VISIBLE);

    }

    public void fecharListagem(){
        edDescItem.setVisibility(View.GONE);
        tvDescItem.setVisibility(View.GONE);
        edQntdItem.setVisibility(View.GONE);
        tvQntdItem.setVisibility(View.GONE);
        edVlrUnitItem.setVisibility(View.GONE);
        tvVlrUnitItem.setVisibility(View.GONE);
        edVlrTotalItem.setVisibility(View.GONE);
        tvVlrTotalItem.setVisibility(View.GONE);
        btIncluirItem.setVisibility(View.GONE);
    }

    public void incluirItem(){

        String descricao = edDescItem.getText().toString();
        int qntdItem = Integer.parseInt(edQntdItem.getText().toString());
        Double vlrUnitarioItem = Double.valueOf(edVlrUnitItem.getText().toString());
        Double vlrTotalItem = qntdItem * vlrUnitarioItem;

        Item nvItem = new Item();
        nvItem.setDescricao(descricao);
        nvItem.setQuantidade(qntdItem);
        nvItem.setVlrUnitario(vlrUnitarioItem);
        nvItem.setVlrTotal(vlrTotalItem);

        try {
            String resultado = tvItens.getText().toString();

            if(!descricao.equals("")){

                resultado += "Item " + contItem + " - Descrição: " + descricao.toString()
                        + " | Quantidade: " + String.valueOf(nvItem.getQuantidade()) + " | VlrUnitario: "
                        + nvItem.getVlrUnitario() + " | Valor Total do Item: " + nvItem.getVlrTotal() + "\n\n";

                tvItens.setText(resultado);
                Toast.makeText(this, "Incluído com Sucesso!", Toast.LENGTH_SHORT).show();
                contItem++;

                nvPedido.getListaItens().add(nvItem);

            } else{
                if(descricao.equals("")){
                    edDescItem.setError("Informe a Descrição do Item!");
                }
                if(qntdItem <= 0){
                    edQntdItem.setError("Quantidade Inválida!");
                }
                if(vlrUnitarioItem <=0 ){
                    edVlrUnitItem.setError("Valor Inválido!");
                }
            }

        }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }



    }


}