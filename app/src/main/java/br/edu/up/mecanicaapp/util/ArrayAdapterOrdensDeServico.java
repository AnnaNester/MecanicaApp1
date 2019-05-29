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

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.edu.up.mecanicaapp.R;
import br.edu.up.mecanicaapp.dal.ClienteDAO;
import br.edu.up.mecanicaapp.dal.VeiculoDAO;
import br.edu.up.mecanicaapp.model.OrdemDeServico;
import br.edu.up.mecanicaapp.model.Veiculo;
import br.edu.up.mecanicaapp.view.DetalhesOrdemDeServicoActivity;

public class ArrayAdapterOrdensDeServico extends ArrayAdapter<OrdemDeServico> {

    private TextView txtCliente, txtVeiculo, txtPlaca, txtValor;
    private ArrayList<OrdemDeServico> ordensDeServico;
    private Context context;

    public ArrayAdapterOrdensDeServico(@NonNull Context context, ArrayList<OrdemDeServico> ordensDeServico) {
        super(context, 0, ordensDeServico);
        this.ordensDeServico = ordensDeServico;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final OrdemDeServico o = ordensDeServico.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.ordem_de_servico_list_layout, null);
        }

        txtCliente = convertView.findViewById(R.id.txtCliente);
        txtVeiculo = convertView.findViewById(R.id.txtVeiculo);
        txtPlaca = convertView.findViewById(R.id.txtPlaca);
        txtValor = convertView.findViewById(R.id.txtValor);

        txtCliente.setText(ClienteDAO.BuscarPorId(o.getCliente_id(), context).getNome());
        txtVeiculo.setText(VeiculoDAO.BuscarPorId(o.getVeiculo_id(), context).getModelo());
        txtPlaca.setText(VeiculoDAO.BuscarPorId(o.getVeiculo_id(), context).getPlaca());
        txtValor.setText(String.valueOf(o.getValor()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesOrdemDeServicoActivity.class);
                intent.putExtra("id", o.getId());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
