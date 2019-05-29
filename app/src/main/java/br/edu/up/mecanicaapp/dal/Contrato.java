package br.edu.up.mecanicaapp.dal;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Contrato {

    private Contrato() {}

    //Declarar todas as classes que vão virar tabela no banco

    public static class TabelaCliente implements BaseColumns{
        public static final String NOME_TABELA = "tblClientes";
        public static final String NOME = "nome";
        public static final String CPF = "cpf";
        public static final String EMAIL = "email";
    }

    public static class TabelaFuncionario implements BaseColumns{
        public static final String NOME_TABELA = "tblFuncionarios";
        public static final String NOME = "nome";
        public static final String CPF = "cpf";
    }

    public static class TabelaVeiculo implements BaseColumns{
        public static final String NOME_TABELA = "tblVeiculos";
        public static final String PLACA = "placa";
        public static final String MARCA = "marca";
        public static final String MODELO = "modelo";
        public static final String COR = "cor";
        public static final String ANO = "ano";
        public static final String CLIENTE_ID= "cliente_id";
    }

    public static class TabelaServico implements BaseColumns{
        public static final String NOME_TABELA = "tblServicos";
        public static final String NOME = "nome";
        public static final String DESCRICAO = "descricao";
        public static final String VALOR = "valor";
    }

    public static class TabelaOrdemDeServico implements BaseColumns{
        public static final String NOME_TABELA = "tblOrdemDeServico";
        public static final String CLIENTE_ID = "cliente_id";
        public static final String VEICULO_ID = "veiculo_id";
        public static final String FUNCIONARIO_ID = "funcionario_id";
        public static final String SERVICO_ID = "servico_id";
        public static final String DESCRICAO = "descricao";
        public static final String VALOR = "valor";
    }


}
