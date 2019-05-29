package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.OrdemDeServicoDAO;

public class OrdemDeServicoActivity extends AppCompatActivity {

    private Button btnNovaOrdemDeServico, btnListarOrdensDeServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordem_de_servico);

        btnNovaOrdemDeServico = findViewById(R.id.btnNovaOrdemDeServico);
        btnListarOrdensDeServico = findViewById(R.id.btnListarOrdensDeServico);

    }

    public void btnNovaOrdemDeServicoClick(View view) {
        startActivity(new Intent(this, NovaOrdemDeServicoActivity.class));
    }

    public void btnListarOrdensDeServicoClick(View view) {
        if (OrdemDeServicoDAO.Listar(this) == null) {
            Toast.makeText(this, "Não há ordens de serviço cadastradas", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, ListarOrdensDeServicoActivity.class));
        }
    }
}
