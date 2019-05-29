package br.edu.up.mecanicaapp.dal;

import android.content.Context;

import java.util.ArrayList;

import br.edu.up.mecanicaapp.model.OrdemDeServico;

public class OrdemDeServicoDAO {

    public static boolean Cadastrar(OrdemDeServico ordemDeServico, Context context) {
        try {
            Banco b = new Banco(context);
            b.novaOrdemDeServico(ordemDeServico);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static OrdemDeServico Buscar(int id, Context context) {
        try {
            Banco b = new Banco(context);
            return b.buscarOrdemDeServico(id);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<OrdemDeServico> Listar(Context context) {
        try {
            Banco b = new Banco(context);
            return b.retornarOrdens();
        } catch (Exception e) {
            return null;
        }
    }

    public static long AlterarOrdem(OrdemDeServico ordemDeServico, Context context) {
        Banco b = new Banco(context);

        return b.alterarOrdem(ordemDeServico);
    }

    public static long ExcluirOrdem(OrdemDeServico ordemDeServico) {
        Banco b = new Banco(context);

        return b.excluirOrdem(ordemDeServico);
    }

}
