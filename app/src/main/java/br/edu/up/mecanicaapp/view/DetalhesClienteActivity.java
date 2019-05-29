package br.edu.up.mecanicaapp.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.model.Cliente;

public class DetalhesClienteActivity extends AppCompatActivity {

    private TextView txtNome, txtCpf, txtEmail;
    private Button btnAlterar, btnExcluir;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cliente);

        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtEmail = findViewById(R.id.txtEmail);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnExcluir = findViewById(R.id.btnExcluir);

        cliente = ClienteDAO.Buscar(getIntent().getStringExtra("cpf"), this);

        txtNome.setText("Nome: " + cliente.getNome());
        txtCpf.setText("CPF: " + cliente.getCpf());
        txtEmail.setText("Email: " + cliente.getEmail());
    }

    public void btnAlterarClick(View view){

    }

    public void btnExcluirClick(View view){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mecanica app");
        builder.setMessage("Deseja mesmo excluir " + cliente.getCpf() + "?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!ClienteDAO.verificarCliente(cliente.getCpf(), builder.getContext())){
                    if(ClienteDAO.ExcluirCliente(cliente, builder.getContext()) != -1){
                        Toast.makeText(DetalhesClienteActivity.this, "Cliente excluido", Toast.LENGTH_LONG);
                    }else {
                        Toast.makeText(DetalhesClienteActivity.this, "Cliente não excluido", Toast.LENGTH_LONG);

                    }
                }else {
                    Toast.makeText(DetalhesClienteActivity.this, "Cliente não pode excluido", Toast.LENGTH_LONG);

                }
            }
        });

        builder.setNeutralButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
