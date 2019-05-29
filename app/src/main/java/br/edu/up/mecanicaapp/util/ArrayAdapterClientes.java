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
import br.edu.up.mecanicaapp.model.Cliente;
import br.edu.up.mecanicaapp.view.DetalhesClienteActivity;

public class ArrayAdapterClientes extends ArrayAdapter<Cliente> {

    private TextView txtNome, txtCpf, txtEmail;
    private ArrayList<Cliente> clientes;
    private Context context;

    public ArrayAdapterClientes(@NonNull Context context, ArrayList<Cliente> clientes) {
        super(context, 0, clientes);
        this.context = context;
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Cliente cliente = clientes.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.cliente_list_layout, null);
        }

        txtNome = convertView.findViewById(R.id.txtNome);
        txtCpf = convertView.findViewById(R.id.txtCpf);
        txtEmail = convertView.findViewById(R.id.txtEmail);

        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtEmail.setText(cliente.getEmail());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesClienteActivity.class);
                intent.putExtra("cpf", cliente.getCpf());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
