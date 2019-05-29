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
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.Cliente;
import br.edu.up.mecanicaapp.model.Veiculo;

public class CadastrarVeiculoActivity extends AppCompatActivity {

    private TextView txtProprietario;
    private EditText edtMarca, edtModelo, edtCor, edtAno, edtPlaca;
    private Button btnLimpar, btnCadastrar;
    Cliente cliente = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_veiculo);

        txtProprietario = findViewById(R.id.txtProprietario);
        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtCor = findViewById(R.id.edtCor);
        edtAno = findViewById(R.id.edtAno);
        edtPlaca = findViewById(R.id.edtPlaca);

        cliente = ClienteDAO.Buscar(getIntent().getStringExtra("cpf"), this);

        txtProprietario.setText("Proprietário: " + cliente.getNome());

    }

    public void btnLimparClick(View view) {
        edtMarca.setText("");
        edtModelo.setText("");
        edtCor.setText("");
        edtAno.setText("");
        edtPlaca.setText("");
    }

    public void btnCadastrarClick(View view) {
        if (edtMarca.getText().toString().equals("") ||
                edtModelo.getText().toString().equals("") ||
                edtCor.getText().toString().equals("") ||
                edtAno.getText().toString().equals("") ||
                edtPlaca.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            if (VeiculoDAO.Buscar(edtPlaca.getText().toString(), this) != null) {
                Toast.makeText(this, "Essa placa já foi cadastrada", Toast.LENGTH_SHORT).show();
            } else {
                Veiculo v = new Veiculo();
                v.setMarca(edtMarca.getText().toString());
                v.setModelo(edtModelo.getText().toString());
                v.setCor(edtCor.getText().toString());
                v.setAno(Integer.valueOf(edtAno.getText().toString()));
                v.setPlaca(edtPlaca.getText().toString().toUpperCase());
                v.setCliente_id(cliente.getId());
                if(VeiculoDAO.Cadastrar(v, this)){
                    startActivity(new Intent(this, ListarVeiculosActivity.class));
                }
            }
        }
    }
}
