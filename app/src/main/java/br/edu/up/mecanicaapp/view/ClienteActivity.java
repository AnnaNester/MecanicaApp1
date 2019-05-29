package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;

public class ClienteActivity extends AppCompatActivity {

    private Button btnCadastrarCliente, btnListarClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
        btnListarClientes = findViewById(R.id.btnListarClientes);

    }

    public void btnCadastrarClienteClick(View view) {
        startActivity(new Intent(this, CadastrarClienteActivity.class));
    }

    public void btnListarClientesClick(View view) {
        if(ClienteDAO.Listar(this) != null) {
            startActivity(new Intent(this, ListarClientesActivity.class));
        }else{
            Toast.makeText(this, "Não há clientes cadastrados", Toast.LENGTH_SHORT).show();
        }
    }
}
