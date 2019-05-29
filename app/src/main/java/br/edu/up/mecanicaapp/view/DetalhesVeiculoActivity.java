package br.edu.up.mecanicaapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.Veiculo;

public class DetalhesVeiculoActivity extends AppCompatActivity {

    private TextView txtProprietario, txtMarca, txtModelo, txtCor, txtAno, txtPlaca;
    private Button btnAlterar, btnExcluir;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_veiculo);

        txtProprietario = findViewById(R.id.txtProprietario);
        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtCor = findViewById(R.id.txtCor);
        txtAno = findViewById(R.id.txtAno);
        txtPlaca = findViewById(R.id.txtPlaca);

        veiculo = VeiculoDAO.Buscar(getIntent().getStringExtra("placa"), this);

        txtProprietario.setText("Proprietário: " + ClienteDAO.BuscarPorId(veiculo.getCliente_id(), this).getNome());
        txtMarca.setText("Marca: " + veiculo.getMarca());
        txtModelo.setText("Modelo: " + veiculo.getModelo());
        txtCor.setText("Cor: " + veiculo.getCor());
        txtAno.setText("Ano: " + veiculo.getAno());
        txtPlaca.setText("Placa: " + veiculo.getPlaca());

    }

    public void btnAlterarClick(View view){

    }

    public void btnExcluirClick(View view){

    }

}
