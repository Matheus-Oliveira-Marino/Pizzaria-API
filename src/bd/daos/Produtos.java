package bd.daos;

import java.sql.*;
import bd.*;
import bd.cora.*;
import bd.dbos.*;

import javax.swing.*;

public class Produtos
{
    public static boolean cadastro (int idProduto) throws Exception
    {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT * FROM pizzaria.PRODUTOS WHERE IDPRODUTO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idProduto);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar produto");
        }

        return retorno;
    }

    public static void incluir(Produto produto) throws Exception
    {
        if(produto == null)
            throw new Exception("Produto nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO pizzaria.Produtos (nome,preco,descricao,idCategoria) VALUES (?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1,produto.getNome());
            BDSQLServer.COMANDO.setDouble(2,produto.getPreco());
            BDSQLServer.COMANDO.setString(3,produto.getDescricao());
            BDSQLServer.COMANDO.setInt(4,produto.getCategoria());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir produto");
        }
    }

    public static void excluir(int idProduto) throws Exception
    {
        if (!cadastro(idProduto))
        {
            JOptionPane.showMessageDialog(null , "Id invalido ou nao existente");
            throw new Exception("Nao cadastrado");
        }


        try {
            String sql;

            sql = "DELETE FROM pizzaria.PRODUTOS WHERE IDPRODUTO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idProduto);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao excluir produto");
        }
    }

    public static void alterar (Produto produto) throws Exception
    {
        //if (produto == null) throw new Exception("Produto nao fornecido");
        if (!cadastro(produto.getIdProduto()))
        {
            JOptionPane.showMessageDialog(null , "Id invalido ou nao existente");
            throw new Exception("Nao cadastrado");
        }

        try {
            String sql;

            sql = "UPDATE pizzaria.PRODUTOS SET NOME = ?, PRECO = ?," +
                    " DESCRICAO = ?, IDCATEGORIA = ? WHERE IDPRODUTO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, produto.getNome());
            BDSQLServer.COMANDO.setDouble(2,produto.getPreco());
            BDSQLServer.COMANDO.setString(3,produto.getDescricao());
            BDSQLServer.COMANDO.setInt(4,produto.getCategoria());
            BDSQLServer.COMANDO.setInt(5,produto.getIdProduto());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados de produto");
        }
    }

    public static Produto getProduto(int idProduto) throws Exception
    {
        Produto produto = null;

        try {
            String sql;

            sql = "SELECT * FROM PRODUTOS WHERE IDPRODUTO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idProduto);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if (!resultado.first())
                throw new Exception("Nao cadastrado");

            produto = new Produto(//resultado.getInt("IDPRODUTO"),
                                  resultado.getString("NOME"),
                                  resultado.getDouble("PRECO"),
                                  resultado.getString("DESCRICAO"),
                                  resultado.getInt("CATEGORIA")
            );
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar produto");
        }

        return produto;
    }

    public static MeuResultSet getProduto () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM pizzaria.Produtos";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception ("Erro ao recuperar produto");
        }

        return resultado;
    }

    public static Double getPreco(int idProduto) throws Exception
    {

        try
        {
            String sql;

            sql = "select p.preco from pizzaria.Produtos as p where p.idProduto = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt(1,idProduto);

            MeuResultSet result = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            result.first();

            return result.getDouble("preco");
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception("Erro ao recuperar produto");
        }
    }

    public  static int getId()
    {

        try
        {
            String sql;

            sql = "SELECT idProduto " +
                    "FROM pizzaria.Produtos where ";

            BDSQLServer.COMANDO.prepareStatement (sql);
            MeuResultSet result = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            result.next();

           return result.getInt("IDPRODUTO");
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
        }

        return 0;

    }

}
