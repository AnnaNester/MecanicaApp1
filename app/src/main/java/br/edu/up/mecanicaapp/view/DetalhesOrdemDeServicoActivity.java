package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.dal.OrdemDeServicoDAO;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.Cliente;
import br.edu.up.mecanicaapp.model.OrdemDeServico;

public class DetalhesOrdemDeServicoActivity extends AppCompatActivity {

    private TextView txtCliente, txtVeiculo, txtDescricao, txtValor;
    private Button btnAlterar, btnExcluir;
    private OrdemDeServico ordemDeServico = new OrdemDeServico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ordem_de_servico);

        txtCliente = findViewById(R.id.txtCliente);
        txtVeiculo = findViewById(R.id.txtVeiculo);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtValor = findViewById(R.id.txtValor);

        ordemDeServico = OrdemDeServicoDAO.Buscar(Integer.valueOf( getIntent().getExtras().get("id").toString()), this);

        txtCliente.setText("Cliente: " + ClienteDAO.BuscarPorId(ordemDeServico.getCliente_id(), this).getNome());
        txtVeiculo.setText(
                "Veículo: " +
                VeiculoDAO.BuscarPorId(ordemDeServico.getVeiculo_id(), this).getMarca() + " " +
                VeiculoDAO.BuscarPorId(ordemDeServico.getVeiculo_id(), this).getModelo() + ", " +
                VeiculoDAO.BuscarPorId(ordemDeServico.getVeiculo_id(), this).getPlaca()
        );
        txtDescricao.setText("Descrição: " + ordemDeServico.getDescricao());
        txtValor.setText("Valor: " + String.valueOf(ordemDeServico.getValor()));

    }

    public void btnAlterarClick(View view) {
    }

    public void btnExcluirClick(View view) {
    }

}
