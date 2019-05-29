package br.edu.up.mecanicaapp.dal;

import android.content.Context;

import java.util.ArrayList;

import br.edu.up.mecanicaapp.model.Veiculo;

public class VeiculoDAO {

    public static boolean Cadastrar(Veiculo veiculo, Context context) {
        try {
            Banco b = new Banco(context);
            b.cadastrarVeiculo(veiculo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Veiculo Buscar(String placa, Context context) {
        try {
            Banco b = new Banco(context);
            return b.buscarVeiculo(placa);
        } catch (Exception e) {
            return null;
        }
    }

    public static Veiculo BuscarPorId(int veiculo_id, Context context) {
        try {
            Banco b = new Banco(context);
            return b.buscarVeiculoPorId(veiculo_id);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Veiculo> Listar(Context context) {
        try {
            Banco b = new Banco(context);
            return b.retornarVeiculos();
        } catch (Exception e) {
            return null;
        }
    }

    public static long AlterarVeiculo(Veiculo veiculo, Context context) {
        Banco b = new Banco(context);

        return b.alterarVeiculo(veiculo);
    }

    public static long ExcluirVeiculo(Veiculo veiculo) {
        Banco b = new Banco(context);

        return b.excluirVeiculo(veiculo);
    }

}
