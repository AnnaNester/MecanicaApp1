package br.edu.up.mecanicaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.model.Cliente;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText txtNome, txtCpf, txtEmail;
    private Button btnLimpar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtEmail = findViewById(R.id.txtEmail);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnLimpar = findViewById(R.id.btnLimpar);

    }

    public void btnLimparClick(View view) {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
    }

    public void btnCadastrarClick(View view) {
        if(txtNome.getText().toString().equals("") || txtCpf.getText().toString().equals("") || txtEmail.getText().toString().equals("")){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else{
            if(ClienteDAO.Buscar(txtCpf.getText().toString(), this) == null) {
                Cliente c = new Cliente();
                c.setNome(txtNome.getText().toString());
                c.setCpf(txtCpf.getText().toString());
                c.setEmail(txtEmail.getText().toString());
                if (ClienteDAO.Cadastrar(c, this)) {
                    startActivity(new Intent(this, ListarClientesActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Não foi possível cadastrar o cliente", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "CPF já cadastrado no sistema", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
