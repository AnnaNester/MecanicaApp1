package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.FuncionarioDAO;
import br.edu.up.mecanicaapp.util.ArrayAdapterFuncionarios;

public class ListarFuncionariosActivity extends AppCompatActivity {

    private ListView lstFuncionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_funcionarios);

        lstFuncionarios = findViewById(R.id.lstFuncionarios);

        ArrayAdapterFuncionarios arrayAdapterFuncionarios =
                new ArrayAdapterFuncionarios(this,
                        FuncionarioDAO.Listar(this));

        lstFuncionarios.setAdapter(arrayAdapterFuncionarios);

    }
}
