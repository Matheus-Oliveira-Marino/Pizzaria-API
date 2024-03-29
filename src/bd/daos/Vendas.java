package bd.daos;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbos.Venda;

import javax.swing.*;
import java.sql.SQLException;



/**
 * A classe 'Vendas' fornece métodos para realizar operações relacionadas às vendas 
 * em um banco de dados.
 * Ela interage com a tabela 'Vendas' no banco de dados 'pizzaria'.
 */
public class Vendas
{

     /**
     * Verifica se uma venda está cadastrada no banco de dados com base no seu 'ID'.
     *
     * @param idVenda O 'ID' da venda a ser verificada.
     * @return 'true' se a venda estiver cadastrada, 'false' caso contrário.
     * @throws Exception Se ocorrer um erro durante a verificação.
     */
    public static boolean cadastro (int idVenda) throws Exception
    {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT * FROM pizzaria.VENDAS WHERE idPedido = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idVenda);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar venda");
        }

        return retorno;
    }


      /**
     * Inclui uma nova venda no banco de dados.
     *
     * @param venda O objeto 'Venda' a ser incluído.
     * @throws Exception Se ocorrer um erro durante a inclusão da venda.
     */
    public static void incluir(Venda venda) throws Exception
    {
        if(venda == null)
            throw new Exception("Venda nao fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO pizzaria.Vendas (idProduto,qtd, precoTotal) VALUES (?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,venda.getIdProduto());
            BDSQLServer.COMANDO.setInt(2,venda.getQtd());
            BDSQLServer.COMANDO.setDouble(3,venda.getPrecoTotal());
            //BDSQLServer.COMANDO.setInt(4,venda.getCategoria());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir venda");
        }
    }


     /**
     * Exclui uma venda do banco de dados com base no seu 'ID'.
     *
     * @param idVenda O 'ID' da venda a ser excluída.
     * @throws Exception Se ocorrer um erro durante a exclusão da venda.
     */
    public static void excluir(int idVenda) throws Exception
    {
        if (!cadastro(idVenda)) {
            JOptionPane.showMessageDialog(null , "Id invalido ou nao existente");
            throw new Exception("Nao cadastrado");
        }

        try {
            String sql;

            sql = "DELETE FROM pizzaria.VENDAS WHERE idPedido = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idVenda);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao excluir venda");
        }
    }


    /**
     * Altera os detalhes de uma venda no banco de dados.
     *
     * @param venda O objeto de venda a ser alterado.
     * @throws Exception Se ocorrer algum erro durante a alteração dos dados da venda.
     */
    public static void alterar (Venda venda) throws Exception
    {
        //if (venda == null) throw new Exception("Venda nao fornecido");
        if (!cadastro(venda.getIdVenda()))
        {
            JOptionPane.showMessageDialog(null , "Id invalido ou nao existente");
            throw new Exception("Nao cadastrado");
        }

        try {
            String sql;

            sql = "UPDATE pizzaria.VENDAS SET idProduto = ?, qtd = ?," +
                    " PrecoTotal = ? WHERE iDPedido = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, venda.getIdProduto());
            BDSQLServer.COMANDO.setInt(2,venda.getQtd());
            BDSQLServer.COMANDO.setDouble(3,venda.getPrecoTotal());
            BDSQLServer.COMANDO.setInt(4,venda.getIdVenda());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados de venda");
        }
    }

     /**
     * Obtém uma venda do banco de dados com base no seu 'ID'.
     *
     * @param idVenda O 'ID' da venda a ser recuperada.
     * @return O objeto 'Venda' recuperado.
     * @throws Exception Se ocorrer um erro durante a recuperação da venda.
     */
    public static Venda getVenda(int idVenda) throws Exception
    {
        Venda venda = null;

        try {
            String sql;

            sql = "SELECT * FROM pizzaria.VENDAS WHERE IDVENDA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idVenda);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if (!resultado.first())
                throw new Exception("Nao cadastrado");

            venda = new Venda(//resultado.getInt("IDVENDA"),
                    resultado.getInt("idProduto"),
                    resultado.getInt("qtd"),
                    resultado.getDouble("precoTotal")
            );
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar venda");
        }

        return venda;
    }


    
     /**
     * Obtém todas as vendas cadastradas no banco de dados.
     *
     * @return Um objeto 'MeuResultSet' contendo todas as vendas cadastradas.
     * @throws Exception Se ocorrer um erro durante a recuperação das vendas.
     */
    public static MeuResultSet getVenda () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM pizzaria.VENDAS";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception ("Erro ao recuperar venda");
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
    public static double preco(int idProduto) throws Exception
    {
        System.out.println(idProduto);

        MeuResultSet resultado = Produtos.getProduto();
        @SuppressWarnings("unused")
        String dados = "";

        double preco =  resultado.getDouble("preco");
        System.out.println(preco);

        try {
            String sql;

            sql = "select distinct p.preco from pizzaria.Produtos as p "+
                    "inner join pizzaria.Vendas as v on v.idProduto = p.idProduto "
                    + "where p.idProduto = ?";

            //sql = "select * from pizzaria.Produto where idProduto =?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1,idProduto);

            MeuResultSet prec = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            preco = prec.getFloat("preco");

        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            System.out.println("preco");
        }

        System.out.println(preco);
        return preco;
    }



}
