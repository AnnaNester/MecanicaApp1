package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.util.ArrayAdapterVeiculos;

public class ListarVeiculosActivity extends AppCompatActivity {

    private ListView lstVeiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_veiculos);

        lstVeiculos = findViewById(R.id.lstVeiculos);

        ArrayAdapterVeiculos arrayAdapterVeiculos =
                new ArrayAdapterVeiculos(this,
                        VeiculoDAO.Listar(this));

        lstVeiculos.setAdapter(arrayAdapterVeiculos);

    }
}
