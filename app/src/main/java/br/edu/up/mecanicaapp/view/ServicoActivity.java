package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.up.mecanicaapp.R;

public class ServicoActivity extends AppCompatActivity {
    private Button btnCadastrarServico, btnListarServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
    }

    public void btnCadastrarServicoClick(View view) {

    }

    public void btnListarServicosClick(View view) {
    }
}
