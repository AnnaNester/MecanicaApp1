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
import br.edu.up.mecanicaapp.model.Funcionario;

public class CadastrarFuncionarioActivity extends AppCompatActivity {

    private EditText txtNome, txtCpf;
    private Button btnLimpar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_funcionario);

        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnLimpar = findViewById(R.id.btnLimpar);

    }

    public void btnLimparClick(View view) {
        txtNome.setText("");
        txtCpf.setText("");
    }

    public void btnCadastrarClick(View view) {
        if(txtNome.getText().toString().equals("") || txtCpf.getText().toString().equals("")){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else{
            if(FuncionarioDAO.Buscar(txtCpf.getText().toString(), this) == null) {
                Funcionario f = new Funcionario();
                f.setNome(txtNome.getText().toString());
                f.setCpf(txtCpf.getText().toString());
                if (FuncionarioDAO.Cadastrar(f, this)) {
                    startActivity(new Intent(this, ListarFuncionariosActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Não foi possível cadastrar o funcionario", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "CPF já cadastrado no sistema", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
