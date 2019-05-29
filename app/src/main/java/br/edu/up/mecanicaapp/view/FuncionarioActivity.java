package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.FuncionarioDAO;

public class FuncionarioActivity extends AppCompatActivity {

    private Button btnCadastrarFuncionario, btnListarFuncionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        btnCadastrarFuncionario = findViewById(R.id.btnCadastrarFuncionario);
        btnListarFuncionarios = findViewById(R.id.btnListarFuncionarios);

    }

    public void btnCadastrarFuncionarioClick(View view) {
        startActivity(new Intent(this, CadastrarFuncionarioActivity.class));
    }

    public void btnListarFuncionariosClick(View view) {
        if(FuncionarioDAO.Listar(this) != null) {
            startActivity(new Intent(this, ListarFuncionariosActivity.class));
        }else{
            Toast.makeText(this, "Não há funcionarios cadastrados", Toast.LENGTH_SHORT).show();
        }
    }
}