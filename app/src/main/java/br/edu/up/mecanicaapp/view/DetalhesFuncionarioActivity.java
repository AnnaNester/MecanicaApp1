package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.FuncionarioDAO;
import br.edu.up.mecanicaapp.model.Funcionario;

public class DetalhesFuncionarioActivity extends AppCompatActivity {

    private TextView txtNome, txtCpf;
    private Button btnAlterar, btnExcluir;
    private Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_funcionario);

        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);

        funcionario = FuncionarioDAO.Buscar(getIntent().getStringExtra("cpf"), this);

        txtNome.setText("Nome: " + funcionario.getNome());
        txtCpf.setText("CPF: " + funcionario.getCpf());

    }

    public void btnAlterarClick(View view){

    }

    public void btnExcluirClick(View view){

    }
}
