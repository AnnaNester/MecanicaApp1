package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.Banco;
import br.edu.up.mecanicaapp.model.Funcionario;
import br.edu.up.mecanicaapp.model.OrdemDeServico;
import br.edu.up.mecanicaapp.model.Veiculo;

public class MainActivity extends AppCompatActivity {

    private Button btnCliente, btnVeiculo, btnFuncionario,btnServico, btnOrdemDeServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCliente = findViewById(R.id.btnCliente);
        btnVeiculo = findViewById(R.id.btnVeiculo);
        btnFuncionario = findViewById(R.id.btnFuncionario);
        btnOrdemDeServico = findViewById(R.id.btnOrdemDeServico);
        btnServico = findViewById(R.id.btnServico);

        Log.v("DIABO", Banco.SQL_CRIAR_TABELA_CLIENTE);

    }

    public void btnClienteClick(View view) {
        startActivity(new Intent(this, ClienteActivity.class));
    }

    public void btnVeiculoClick(View view) {
        startActivity(new Intent(this, VeiculoActivity.class));
    }

    public void btnFuncionarioClick(View view) {
        startActivity(new Intent(this, FuncionarioActivity.class));
    }

    public void btnOrdemDeServicoClick(View view) {
        startActivity(new Intent(this, OrdemDeServicoActivity.class));
    }

    public void btnServicoClick(View view) {
        startActivity(new Intent(this, ServicoActivity.class));
    }
}
