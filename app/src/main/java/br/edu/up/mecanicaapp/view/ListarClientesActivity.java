package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.util.ArrayAdapterClientes;

public class ListarClientesActivity extends AppCompatActivity {

    private ListView lstClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        lstClientes = findViewById(R.id.lstClientes);

        ArrayAdapterClientes arrayAdapterClientes =
                new ArrayAdapterClientes(this,
                        ClienteDAO.Listar(this));

        lstClientes.setAdapter(arrayAdapterClientes);
    }
}
