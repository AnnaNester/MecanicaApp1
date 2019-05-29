package br.edu.up.mecanicaapp.dal;

import android.content.Context;

import java.util.ArrayList;

import br.edu.up.mecanicaapp.model.Cliente;

public class ClienteDAO {

    public static boolean Cadastrar(Cliente cliente, Context context) {
        try {
            Banco b = new Banco(context);
            b.cadastrarCliente(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Cliente Buscar(String cpf, Context context) {
        try {
            Banco b = new Banco(context);
            return b.buscarCliente(cpf);
        } catch (Exception e) {
            return null;
        }
    }

    public static Cliente BuscarPorId(int cliente_id, Context context) {
        try {
            Banco b = new Banco(context);
            return b.buscarClientePorId(cliente_id);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Cliente> Listar(Context context) {
        try {
            Banco b = new Banco(context);
            return b.retornarClientes();
        } catch (Exception e) {
            return null;
        }
    }

    public static long AlterarCliente(Cliente cliente, Context context) {
        Banco b = new Banco(context);

        return b.alterarCliente(cliente);
    }

    public static long ExcluirCliente(Cliente cliente, Context context) {
        Banco b = new Banco(context);

        return b.excluirCliente(cliente);
    }

    public static boolean verificarCliente(String cpfCliente, Context context){
        Banco b = new Banco(context);
        return b.verificarCliente(cpfCliente);
    }

}
