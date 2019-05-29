package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.up.mecanicaapp.R;

public class VeiculoActivity extends AppCompatActivity {

    private Button btnCadastrarVeiculo, btnListarVeiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        btnCadastrarVeiculo = findViewById(R.id.btnCadastrarVeiculo);
        btnListarVeiculos = findViewById(R.id.btnListarVeiculos);

    }

    public void btnCadastrarVeiculoClick(View view) {
        startActivity(new Intent(this, BuscarCpfActivity.class));
    }

    public void btnListarVeiculosClick(View view) {
            startActivity(new Intent(this, ListarVeiculosActivity.class));
    }
}
