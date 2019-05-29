package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.Cliente;

public class BuscarCpfActivity extends AppCompatActivity {

    private EditText edtCpf;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cpf);

        edtCpf = findViewById(R.id.edtCpf);
        btnBuscar = findViewById(R.id.btnBuscar);

    }

    public void btnBuscarClick(View view) {
        if (edtCpf.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha o CPF", Toast.LENGTH_SHORT).show();
        } else {
            Cliente cliente = ClienteDAO.Buscar(edtCpf.getText().toString(), this);
            if (cliente == null) {
                Toast.makeText(this, "CPF não encontrado", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, CadastrarVeiculoActivity.class);
                intent.putExtra("cpf", cliente.getCpf());
                startActivity(intent);
                finish();
            }
        }
    }
}
