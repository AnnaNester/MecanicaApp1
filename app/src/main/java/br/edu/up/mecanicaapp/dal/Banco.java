package br.edu.up.mecanicaapp.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.up.mecanicaapp.model.Cliente;
import br.edu.up.mecanicaapp.model.Funcionario;
import br.edu.up.mecanicaapp.model.OrdemDeServico;
import br.edu.up.mecanicaapp.model.Servico;
import br.edu.up.mecanicaapp.model.Veiculo;

public class Banco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "Mecanica.db";
    public static final int VERSAO_BANCO = 3;

    public static final String TIPO_TEXTO = " TEXT";
    public static final String TIPO_INTEIRO = " INTEGER";
    public static final String TIPO_FLOAT = " REAL";
    public static final String VIRGULA = ", ";

    public static final String[] COLUNAS_CLIENTE = new String[]{
            Contrato.TabelaCliente._ID,
            Contrato.TabelaCliente.NOME,
            Contrato.TabelaCliente.CPF,
            Contrato.TabelaCliente.EMAIL
    };
    public static final String[] COLUNAS_VEICULO = new String[]{
            Contrato.TabelaVeiculo._ID,
            Contrato.TabelaVeiculo.MARCA,
            Contrato.TabelaVeiculo.MODELO,
            Contrato.TabelaVeiculo.COR,
            Contrato.TabelaVeiculo.ANO,
            Contrato.TabelaVeiculo.PLACA,
            Contrato.TabelaVeiculo.CLIENTE_ID,
    };
    public static final String[] COLUNAS_FUNCIONARIO = new String[]{
            Contrato.TabelaFuncionario._ID,
            Contrato.TabelaFuncionario.NOME,
            Contrato.TabelaFuncionario.CPF,
    };

    public static final String[] COLUNAS_SERVICO = new String[]{
            Contrato.TabelaServico._ID,
            Contrato.TabelaServico.NOME,
            Contrato.TabelaServico.DESCRICAO,
            Contrato.TabelaServico.VALOR,
    };

    public static final String[] COLUNAS_ORDEMDESERVICO = new String[]{
            Contrato.TabelaOrdemDeServico._ID,
            Contrato.TabelaOrdemDeServico.CLIENTE_ID,
            Contrato.TabelaOrdemDeServico.VEICULO_ID,
            Contrato.TabelaOrdemDeServico.FUNCIONARIO_ID,
            Contrato.TabelaOrdemDeServico.DESCRICAO,
            Contrato.TabelaOrdemDeServico.VALOR,
    };

    public static final String SQL_CRIAR_TABELA_CLIENTE =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaCliente.NOME_TABELA +
                    "(" +
                    Contrato.TabelaCliente._ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaCliente.NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaCliente.CPF + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaCliente.EMAIL + TIPO_TEXTO +
                    ");";

    public static final String SQL_DELETAR_TABELA_CLIENTE =
            "DROP TABLE IF EXISTS " + Contrato.TabelaCliente.NOME_TABELA;

    public long cadastrarCliente(Cliente c) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(Contrato.TabelaCliente.NOME, c.getNome());
        dados.put(Contrato.TabelaCliente.CPF, c.getCpf());
        dados.put(Contrato.TabelaCliente.EMAIL, c.getEmail());

        return db.insert(Contrato.TabelaCliente.NOME_TABELA, null, dados);
    }

    public Cliente buscarCliente(String cpf) {
        SQLiteDatabase db = getReadableDatabase();
        String where = Contrato.TabelaCliente.CPF + " = ?";
        String[] valoresWhere = new String[]{cpf};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaCliente.NOME_TABELA,
                COLUNAS_CLIENTE,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Cliente c = new Cliente();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCpf(cursor.getString(2));
            c.setEmail(cursor.getString(3));
            return c;
        }
        return null;
    }

    public Cliente buscarClientePorId(int cliente_id) {
        SQLiteDatabase db = getReadableDatabase();
        String where = Contrato.TabelaCliente._ID + " = ?";
        String[] valoresWhere = new String[]{Integer.toString(cliente_id)};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaCliente.NOME_TABELA,
                COLUNAS_CLIENTE,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Cliente c = new Cliente();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCpf(cursor.getString(2));
            c.setEmail(cursor.getString(3));
            return c;
        }
        return null;
    }

    public ArrayList<Cliente> retornarClientes() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaCliente.NOME_TABELA,
                COLUNAS_CLIENTE,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Cliente c = new Cliente();
                c.setId(cursor.getInt(0));
                c.setNome(cursor.getString(1));
                c.setCpf(cursor.getString(2));
                c.setEmail(cursor.getString(3));
                clientes.add(c);
            } while (cursor.moveToNext());
            return clientes;
        }
        return null;
    }

    public long excluirCliente(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaCliente.CPF + " = ?";
        String[] argumentos = {cliente.getCpf()};

        return db.delete(Contrato.TabelaCliente.NOME_TABELA, condicao, argumentos);
    }

    public long alterarCliente (Cliente cliente)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaCliente.NOME, cliente.getNome());
        values.put(Contrato.TabelaCliente.EMAIL, cliente.getEmail());

        String condicao = Contrato.TabelaCliente.CPF + " = ?";
        String[] argumentos = {cliente.getCpf()};

        return db.update(Contrato.TabelaCliente.NOME_TABELA, values, condicao, argumentos);
    }

    public boolean verificarCliente(String cpfCliente){
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = new String[]{
                Contrato.TabelaCliente._ID,
                Contrato.TabelaCliente.CPF
        };
        String where = Contrato.TabelaCliente.NOME_TABELA + " = ?";
        String[] valoresWhere = new String[]{
                cpfCliente
        };

        Cursor cursor = db.query(
                Contrato.TabelaCliente.NOME_TABELA,
                colunas,
                where,
                valoresWhere,
                null,
                null,
                null

        );
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public static final String SQL_CRIAR_TABELA_VEICULO =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaVeiculo.NOME_TABELA +
                    "(" +
                    Contrato.TabelaVeiculo._ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaVeiculo.MARCA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaVeiculo.MODELO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaVeiculo.COR + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaVeiculo.ANO + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaVeiculo.PLACA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaVeiculo.CLIENTE_ID + TIPO_INTEIRO +
                    ");";

    public static final String SQL_DELETAR_TABELA_VEICULO =
            "DROP TABLE IF EXISTS " + Contrato.TabelaVeiculo.NOME_TABELA;

    public long cadastrarVeiculo(Veiculo v) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(Contrato.TabelaVeiculo.MARCA, v.getMarca());
        dados.put(Contrato.TabelaVeiculo.MODELO, v.getModelo());
        dados.put(Contrato.TabelaVeiculo.COR, v.getCor());
        dados.put(Contrato.TabelaVeiculo.ANO, v.getAno());
        dados.put(Contrato.TabelaVeiculo.PLACA, v.getPlaca());
        dados.put(Contrato.TabelaVeiculo.CLIENTE_ID, v.getCliente_id());

        return db.insert(Contrato.TabelaVeiculo.NOME_TABELA, null, dados);
    }

    public Veiculo buscarVeiculo(String placa) {
        SQLiteDatabase db = getReadableDatabase();
        String where = Contrato.TabelaVeiculo.PLACA + " = ?";
        String[] valoresWhere = new String[]{placa};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaVeiculo.NOME_TABELA,
                COLUNAS_VEICULO,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Veiculo v = new Veiculo();
            v.setId(cursor.getInt(0));
            v.setMarca(cursor.getString(1));
            v.setModelo(cursor.getString(2));
            v.setCor(cursor.getString(3));
            v.setAno(cursor.getInt(4));
            v.setPlaca(cursor.getString(5));
            v.setCliente_id(cursor.getInt(6));
            return v;
        }
        return null;
    }

    public Veiculo buscarVeiculoPorId(int veiculo_id) {
        SQLiteDatabase db = getReadableDatabase();
        String where = Contrato.TabelaVeiculo._ID + " = ?";
        String[] valoresWhere = new String[]{String.valueOf(veiculo_id)};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaVeiculo.NOME_TABELA,
                COLUNAS_VEICULO,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Veiculo v = new Veiculo();
            v.setId(cursor.getInt(0));
            v.setMarca(cursor.getString(1));
            v.setModelo(cursor.getString(2));
            v.setCor(cursor.getString(3));
            v.setAno(cursor.getInt(4));
            v.setPlaca(cursor.getString(5));
            v.setCliente_id(cursor.getInt(6));
            return v;
        }
        return null;
    }

    public ArrayList<Veiculo> retornarVeiculos() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaVeiculo.NOME_TABELA,
                COLUNAS_VEICULO,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Veiculo v = new Veiculo();
                v.setId(cursor.getInt(0));
                v.setMarca(cursor.getString(1));
                v.setModelo(cursor.getString(2));
                v.setCor(cursor.getString(3));
                v.setAno(cursor.getInt(4));
                v.setPlaca(cursor.getString(5));
                v.setCliente_id(cursor.getInt(6));
                veiculos.add(v);
            } while (cursor.moveToNext());
            return veiculos;
        }
        return null;
    }

    public long alterarVeiculo (Veiculo v) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaVeiculo.MODELO, v.getModelo());
        values.put(Contrato.TabelaVeiculo.MARCA, v.getMarca());
        values.put(Contrato.TabelaVeiculo.ANO, v.getAno());
        values.put(Contrato.TabelaVeiculo.COR, v.getCor());
        values.put(Contrato.TabelaVeiculo.PLACA, v.getPlaca());
        values.put(Contrato.TabelaVeiculo.CLIENTE_ID, v.getCliente_id());

        String condicao = Contrato.TabelaVeiculo.PLACA + " = ?";
        String[] argumentos = {
                v.getPlaca()
        };

        return db.update(Contrato.TabelaVeiculo.NOME_TABELA, values,
                condicao, argumentos);
    }

    public long excluirVeiculo (Veiculo v){
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaVeiculo.PLACA + " = ?";
        String[] argumentos = { v.getPlaca()};

        return db.delete(Contrato.TabelaVeiculo.NOME_TABELA, condicao, argumentos);
    }

    public static final String SQL_CRIAR_TABELA_FUNCIONARIO =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaFuncionario.NOME_TABELA +
                    "(" +
                    Contrato.TabelaFuncionario._ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaFuncionario.NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaFuncionario.CPF + TIPO_TEXTO +
                    ");";

    public static final String SQL_DELETAR_TABELA_FUNCIONARIO =
            "DROP TABLE IF EXISTS " + Contrato.TabelaFuncionario.NOME_TABELA;

    public long cadastrarFuncionario(Funcionario f) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(Contrato.TabelaCliente.NOME, f.getNome());
        dados.put(Contrato.TabelaCliente.CPF, f.getCpf());

        return db.insert(Contrato.TabelaFuncionario.NOME_TABELA, null, dados);
    }

    public Funcionario buscarFuncionario(String cpf) {
        SQLiteDatabase db = getReadableDatabase();
        String where = Contrato.TabelaFuncionario.CPF + " = ?";
        String[] valoresWhere = new String[]{cpf};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaFuncionario.NOME_TABELA,
                COLUNAS_FUNCIONARIO,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Funcionario f = new Funcionario();
            f.setId(cursor.getInt(0));
            f.setNome(cursor.getString(1));
            f.setCpf(cursor.getString(2));
            return f;
        }
        return null;
    }

    public ArrayList<Funcionario> retornarFuncionarios() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaFuncionario.NOME_TABELA,
                COLUNAS_FUNCIONARIO,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Funcionario f = new Funcionario();
                f.setId(cursor.getInt(0));
                f.setNome(cursor.getString(1));
                f.setCpf(cursor.getString(2));
                funcionarios.add(f);
            } while (cursor.moveToNext());
            return funcionarios;
        }
        return null;
    }

    public long alterarFuncionario (Funcionario f) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaFuncionario.NOME, f.getNome());

        String condicao = Contrato.TabelaFuncionario.CPF + " = ?";
        String[] argumentos = {
                f.getCpf()
        };

        return db.update(Contrato.TabelaFuncionario.NOME_TABELA, values,
                condicao, argumentos);
    }

    public long excluirFuncionario (Funcionario f) {
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaFuncionario.CPF + " = ?";
        String[] argumentos = {
                f.getCpf()
        };
        return db.delete(Contrato.TabelaFuncionario.NOME_TABELA,
                condicao, argumentos);
    }
    }

    public static final String SQL_CRIAR_TABELA_SERVICO =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaServico.NOME_TABELA +
                    "(" +
                    Contrato.TabelaServico._ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaServico.NOME + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaServico.DESCRICAO + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaServico.VALOR + TIPO_INTEIRO +
                    ");";

    public static final String SQL_DELETAR_TABELA_SERVICO =
            "DROP TABLE IF EXISTS " + Contrato.TabelaServico.NOME_TABELA;

    public long novoServico(Servico s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(Contrato.TabelaServico._ID, s.getId());
        dados.put(Contrato.TabelaServico.NOME, s.getNome());
        dados.put(Contrato.TabelaServico.DESCRICAO, s.getDescricao());
        dados.put(Contrato.TabelaServico.VALOR, s.getValor());

        return db.insert(Contrato.TabelaServico.NOME_TABELA, null, dados);
    }

    public Servico buscarServico(int id) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Servico> servicos = new ArrayList<Servico>();
        String where = Contrato.TabelaServico._ID + " = ?";
        String[] valoresWhere = new String[]{Integer.toString(id)};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaServico.NOME_TABELA,
                COLUNAS_SERVICO,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Servico s = new Servico();
            s.setId(cursor.getInt(0));
            s.setNome(cursor.getString(1));
            s.setDescricao(cursor.getString(2));
            s.setValor(cursor.getFloat(3));
            return s;
        }
        return null;
    }

    public ArrayList<Servico> retornarServico() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Servico> servicos = new ArrayList<Servico>();

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaServico.NOME_TABELA,
                COLUNAS_SERVICO,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Servico s = new Servico();
                s.setId(cursor.getInt(0));
                s.setNome(cursor.getString(1));
                s.setDescricao(cursor.getString(2));
                s.setValor(cursor.getFloat(3));
                servicos.add(s);
            } while (cursor.moveToNext());
            return servicos;
        }
        return null;
    }

    public long alterarServico(Servico servico) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaServico.NOME, servico.getNome());
        values.put(Contrato.TabelaServico.DESCRICAO, servico.getDescricao());
        values.put(Contrato.TabelaServico.VALOR, servico.getValor());

        String condicao = Contrato.TabelaServico._ID + " = ?";
        String[] argumentos = {
                String.valueOf(servico.getId())
        };

        return db.update(Contrato.TabelaServico.NOME_TABELA, values,
                condicao, argumentos);
    }

    public long excluirServico (Servico servico) {
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaServico._ID + " = ?";
        String[] argumentos = {
                String.valueOf(servico.getId())
        };
        return db.delete(Contrato.TabelaServico.NOME_TABELA,
                condicao, argumentos);
    }

    public static final String SQL_CRIAR_TABELA_ORDEMDESERVICO =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaOrdemDeServico.NOME_TABELA +
                    "(" +
                    Contrato.TabelaOrdemDeServico._ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaOrdemDeServico.CLIENTE_ID + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaOrdemDeServico.VEICULO_ID + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaOrdemDeServico.FUNCIONARIO_ID + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaOrdemDeServico.DESCRICAO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaOrdemDeServico.VALOR + TIPO_FLOAT +
                    ");";

    public static final String SQL_DELETAR_TABELA_ORDEMDESERVICO =
            "DROP TABLE IF EXISTS " + Contrato.TabelaOrdemDeServico.NOME_TABELA;

    public long novaOrdemDeServico(OrdemDeServico o) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(Contrato.TabelaOrdemDeServico.FUNCIONARIO_ID, o.getFuncionario_id());
        dados.put(Contrato.TabelaOrdemDeServico.CLIENTE_ID, o.getVeiculo_id());
        dados.put(Contrato.TabelaOrdemDeServico.VEICULO_ID, o.getVeiculo_id());
        dados.put(Contrato.TabelaOrdemDeServico.DESCRICAO, o.getDescricao());
        dados.put(Contrato.TabelaOrdemDeServico.VALOR, o.getValor());

        return db.insert(Contrato.TabelaOrdemDeServico.NOME_TABELA, null, dados);
    }

    public OrdemDeServico buscarOrdemDeServico(int id) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<OrdemDeServico> ordensDeServico = new ArrayList<OrdemDeServico>();
        String where = Contrato.TabelaOrdemDeServico._ID + " = ?";
        String[] valoresWhere = new String[]{Integer.toString(id)};

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaOrdemDeServico.NOME_TABELA,
                COLUNAS_ORDEMDESERVICO,
                where,
                valoresWhere,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            OrdemDeServico o = new OrdemDeServico();
            o.setId(cursor.getInt(0));
            o.setCliente_id(cursor.getInt(1));
            o.setVeiculo_id(cursor.getInt(2));
            o.setFuncionario_id(cursor.getInt(3));
            o.setDescricao(cursor.getString(4));
            o.setValor(cursor.getDouble(5));
            return o;
        }
        return null;
    }

    public ArrayList<OrdemDeServico> retornarOrdens() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<OrdemDeServico> ordemDeServicos = new ArrayList<OrdemDeServico>();

        // Cursor recebe os dados que tem no banco
        Cursor cursor = db.query(
                Contrato.TabelaOrdemDeServico.NOME_TABELA,
                COLUNAS_ORDEMDESERVICO,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                OrdemDeServico o = new OrdemDeServico();
                o.setId(cursor.getInt(0));
                o.setDescricao(cursor.getString(1));
                o.setValor(cursor.getFloat(2));
                o.setCliente_id(cursor.getInt(3));
                o.setVeiculo_id(cursor.getInt(4));
                o.setFuncionario_id(cursor.getInt(5));
                ordemDeServicos.add(o);
            } while (cursor.moveToNext());
            return ordemDeServicos;
        }
        return null;
    }

    public long alterarOrdem (OrdemDeServico ordemDeServico) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaOrdemDeServico.DESCRICAO, ordemDeServico.getDescricao());
        values.put(Contrato.TabelaOrdemDeServico.CLIENTE_ID, ordemDeServico.getCliente_id());
        values.put(Contrato.TabelaOrdemDeServico.VEICULO_ID, ordemDeServico.getVeiculo_id());
        values.put(Contrato.TabelaOrdemDeServico.FUNCIONARIO_ID, ordemDeServico.getFuncionario_id());
        values.put(Contrato.TabelaOrdemDeServico.VALOR, ordemDeServico.getValor());

        String condicao = Contrato.TabelaOrdemDeServico._ID + " = ?";
        String[] argumentos = {
                String.valueOf(ordemDeServico.getId())
        };

        return db.update(Contrato.TabelaOrdemDeServico.NOME_TABELA, values,
                condicao, argumentos);
    }

    public long excluirOrdem (OrdemDeServico ordemDeServico) {
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaOrdemDeServico._ID + " = ?";
        String[] argumentos = {
                String.valueOf(ordemDeServico.getId())
        };
        return db.delete(Contrato.TabelaOrdemDeServico.NOME_TABELA,
                condicao, argumentos);
    }

    public Banco(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CRIAR_TABELA_CLIENTE);
        db.execSQL(SQL_CRIAR_TABELA_VEICULO);
        db.execSQL(SQL_CRIAR_TABELA_FUNCIONARIO);
        db.execSQL(SQL_CRIAR_TABELA_SERVICO);
        db.execSQL(SQL_CRIAR_TABELA_ORDEMDESERVICO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deleta as tabelas
        db.execSQL(SQL_DELETAR_TABELA_CLIENTE);
        db.execSQL(SQL_DELETAR_TABELA_VEICULO);
        db.execSQL(SQL_DELETAR_TABELA_FUNCIONARIO);
        db.execSQL(SQL_DELETAR_TABELA_SERVICO);
        db.execSQL(SQL_DELETAR_TABELA_ORDEMDESERVICO);

        // Cria as tabelas
        db.execSQL(SQL_CRIAR_TABELA_CLIENTE);
        db.execSQL(SQL_CRIAR_TABELA_VEICULO);
        db.execSQL(SQL_CRIAR_TABELA_FUNCIONARIO);
        db.execSQL(SQL_CRIAR_TABELA_SERVICO);
        db.execSQL(SQL_CRIAR_TABELA_ORDEMDESERVICO);
    }

}
