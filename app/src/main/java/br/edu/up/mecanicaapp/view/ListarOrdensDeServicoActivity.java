package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.OrdemDeServicoDAO;
import br.edu.up.mecanicaapp.util.ArrayAdapterOrdensDeServico;

public class ListarOrdensDeServicoActivity extends AppCompatActivity {

    private ListView lstOrdensDeServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ordens_de_servico);

        lstOrdensDeServico = findViewById(R.id.lstOrdensDeServico);

        ArrayAdapterOrdensDeServico arrayAdapterOrdensDeServico =
                new ArrayAdapterOrdensDeServico(this,
                        OrdemDeServicoDAO.Listar(this));

        lstOrdensDeServico.setAdapter(arrayAdapterOrdensDeServico);

    }
}
