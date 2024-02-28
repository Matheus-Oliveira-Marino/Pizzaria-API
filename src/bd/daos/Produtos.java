package bd.daos;

import java.sql.*;
import bd.*;
import bd.cora.*;
import bd.dbos.*;

import javax.swing.*;


/**
 * A classe 'Produtos' fornece métodos para realizar operações relacionadas aos produtos 
 * em um banco de dados.
 * Ela interage com a tabela 'Produtos' no banco de dados 'pizzaria'.
 */
public class Produtos
{

    /**
     * Verifica se um produto está cadastrado no banco de dados com base no seu 'ID'.
     *
     * @param idProduto O 'ID' do produto a ser verificado.
     * @return 'true' se o produto estiver cadastrado, 'false' caso contrário.
     * @throws Exception Se ocorrer um erro durante a verificação.
     */
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


     /**
     * Inclui um novo produto no banco de dados.
     *
     * @param produto O objeto 'Produto' a ser incluído.
     * @throws Exception Se ocorrer um erro durante a inclusão do produto.
     */
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


     /**
     * Exclui um produto do banco de dados com base no seu 'ID'.
     *
     * @param idProduto O 'ID' do produto a ser excluído.
     * @throws Exception Se ocorrer um erro durante a exclusão do produto ou se o produto não estiver cadastrado.
     */
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

      /**
     * Altera os dados de um produto no banco de dados.
     *
     * @param produto O objeto 'Produto' com os novos dados a serem atualizados.
     * @throws Exception Se ocorrer um erro durante a atualização dos dados do produto
     *                   ou se o produto não estiver cadastrado.
     */
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


     /**
     * Obtém um produto do banco de dados com base no seu 'ID'.
     *
     * @param idProduto O 'ID' do produto a ser obtido.
     * @return O objeto 'Produto' correspondente ao 'ID' fornecido.
     * @throws Exception Se ocorrer um erro durante a busca do produto ou se o produto não estiver cadastrado.
     */
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


    /**
     * Obtém todos os produtos cadastrados no banco de dados.
     *
     * @return Um objeto 'MeuResultSet' contendo todos os produtos cadastrados.
     * @throws Exception Se ocorrer um erro durante a recuperação dos produtos.
     */
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


      /**
     * Obtém o preço de um produto com base no seu 'ID'.
     *
     * @param idProduto O 'ID' do produto.
     * @return O preço do produto.
     * @throws Exception Se ocorrer um erro durante a recuperação do preço.
     */
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


    /**
     * Obtém o 'ID' do próximo produto a ser cadastrado no banco de dados.
     *
     * @return O 'ID' do próximo produto.
     */
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
