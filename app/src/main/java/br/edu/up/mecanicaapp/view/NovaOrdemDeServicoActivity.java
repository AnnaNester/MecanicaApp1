package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.FuncionarioDAO;
import br.edu.up.mecanicaapp.dal.OrdemDeServicoDAO;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.Funcionario;
import br.edu.up.mecanicaapp.model.OrdemDeServico;
import br.edu.up.mecanicaapp.model.Veiculo;

public class NovaOrdemDeServicoActivity extends AppCompatActivity {

    private EditText edtCpfFuncionario, edtPlaca, edtDescricao, edtValor;
    private Button btnLimpar, btnCadastrar;
    private OrdemDeServico ordemDeServico = new OrdemDeServico();
    private Funcionario funcionario = new Funcionario();
    private Veiculo veiculo = new Veiculo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_ordem_de_servico);

        edtCpfFuncionario = findViewById(R.id.edtCpfFuncionario);
        edtPlaca = findViewById(R.id.edtPlaca);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCadastrar = findViewById(R.id.btnCadastrar);

    }

    public void btnLimparClick(View view) {
        edtCpfFuncionario.setText("");
        edtPlaca.setText("");
        edtDescricao.setText("");
        edtValor.setText("");
    }

    public void btnCadastrarClick(View view) {
        funcionario = FuncionarioDAO.Buscar(edtCpfFuncionario.getText().toString(), this);
        veiculo = VeiculoDAO.Buscar(edtPlaca.getText().toString(), this);
        if (edtCpfFuncionario.getText().toString().equals("") ||
                edtPlaca.getText().toString().equals("") ||
                edtDescricao.getText().toString().equals("") ||
                edtValor.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            if (funcionario == null) {
                Toast.makeText(this, "Funcionário não encontrado", Toast.LENGTH_SHORT).show();
            } else if (veiculo == null) {
                Toast.makeText(this, "Veículo não encontrado", Toast.LENGTH_SHORT).show();
            } else {
                ordemDeServico.setCliente_id(veiculo.getCliente_id());
                ordemDeServico.setVeiculo_id(veiculo.getId());
                ordemDeServico.setFuncionario_id(funcionario.getId());
                ordemDeServico.setDescricao(edtDescricao.getText().toString());
                ordemDeServico.setValor(Double.valueOf(edtValor.getText().toString()));
                if (OrdemDeServicoDAO.Cadastrar(ordemDeServico, this)) {
                    startActivity(new Intent(this, ListarOrdensDeServicoActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Não foi possível cadastrar essa ordem de serviço", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
