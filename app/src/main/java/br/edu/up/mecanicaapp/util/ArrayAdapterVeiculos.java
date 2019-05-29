package br.edu.up.mecanicaapp.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.model.Veiculo;
import br.edu.up.mecanicaapp.view.DetalhesVeiculoActivity;

public class ArrayAdapterVeiculos extends ArrayAdapter<Veiculo> {

    private TextView txtModelo, txtCor, txtPlaca;
    private ArrayList<Veiculo> veiculos;
    private Context context;

    public ArrayAdapterVeiculos(@NonNull Context context, ArrayList<Veiculo> veiculos) {
        super(context, 0, veiculos);
        this.context = context;
        this.veiculos = veiculos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Veiculo veiculo = veiculos.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.veiculo_list_layout, null);
        }

        txtModelo = convertView.findViewById(R.id.txtModelo);
        txtCor = convertView.findViewById(R.id.txtCor);
        txtPlaca = convertView.findViewById(R.id.txtPlaca);

        txtModelo.setText(veiculo.getModelo());
        txtCor.setText(veiculo.getCor());
        txtPlaca.setText(veiculo.getPlaca());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesVeiculoActivity.class);
                intent.putExtra("placa", veiculo.getPlaca());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
