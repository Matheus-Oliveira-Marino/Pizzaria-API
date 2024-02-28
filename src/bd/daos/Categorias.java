package bd.daos;

import java.sql.*;
import bd.*;
import bd.cora.MeuResultSet;


/**
 * A classe 'Categorias' fornece métodos para acessar informações relacionadas 
 * às categorias de produtos em um banco de dados.
 * Ela interage com a tabela 'Categoria' no banco de dados 'pizzaria'.
 */
public class Categorias
{

    /**
     * Obtém informações sobre uma categoria com base no seu 'ID'.
     *
     * @param id O 'ID' da categoria a ser recuperada.
     * @return Um objeto 'MeuResultSet' contendo informações sobre a categoria.
     * @throws Exception Se ocorrer um erro durante a recuperação da categoria.
     */
    public static MeuResultSet getCategoria (int id) throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT c.tipoCategoria " +
                    "FROM pizzaria.Categoria as c where c.idCategoria =?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setInt(1, id);
            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            resultado.next();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception ("Erro ao recuperar produto");
        }

        return resultado;
    }






}
