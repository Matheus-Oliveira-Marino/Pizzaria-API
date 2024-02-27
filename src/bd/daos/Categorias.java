package bd.daos;

import java.sql.*;
import bd.*;
import bd.cora.MeuResultSet;

public class Categorias
{
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
