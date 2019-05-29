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
import br.edu.up.mecanicaapp.model.Funcionario;
import br.edu.up.mecanicaapp.view.DetalhesFuncionarioActivity;

public class ArrayAdapterFuncionarios extends ArrayAdapter<Funcionario> {

    private TextView txtNome, txtCpf;
    private ArrayList<Funcionario> funcionarios;
    private Context context;

    public ArrayAdapterFuncionarios(@NonNull Context context, ArrayList<Funcionario> funcionarios) {
        super(context, 0, funcionarios);
        this.context = context;
        this.funcionarios = funcionarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Funcionario funcionario = funcionarios.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.funcionario_list_layout, null);
        }

        txtNome = convertView.findViewById(R.id.txtNome);
        txtCpf = convertView.findViewById(R.id.txtCpf);

        txtNome.setText(funcionario.getNome());
        txtCpf.setText(funcionario.getCpf());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesFuncionarioActivity.class);
                intent.putExtra("cpf", funcionario.getCpf());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
