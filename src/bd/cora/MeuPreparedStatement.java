package bd.cora;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;


/**
 * Representa uma implementação personalizada de um statement preparado para interagir com um banco de dados.
 */
public class MeuPreparedStatement extends MeuStatement implements PreparedStatement
{
    protected PreparedStatement comando=null;

    /**
     * Constrói um novo MeuPreparedStatement.
     *
     * @param drv   O driver para a conexão com o banco de dados.
     * @param strCon    A string de conexão para o banco de dados.
     * @param usr   O nome de usuário para a conexão com o banco de dados.
     * @param senha A senha para a conexão com o banco de dados.
     * @throws ClassNotFoundException Se a classe do driver especificado não puder ser encontrada.
     * @throws SQLException           Se ocorrer um erro de acesso ao banco de dados.
     */
    public MeuPreparedStatement (String drv,
                                 String strCon,
                                 String usr,
                                 String senha)
                                 throws ClassNotFoundException,
                                        SQLException
    {
        super (drv, strCon, usr, senha);
    }


    /**
     * Prepara a instrução SQL para execução.
     *
     * @param sql a consulta SQL a ser preparada.
     * @throws SQLException se ocorrer um erro de acesso ao banco de dados.
     */
    public void prepareStatement (String sql) throws SQLException
    {
        this.comando = conexao.prepareStatement (
                       sql,
                       ResultSet.TYPE_SCROLL_INSENSITIVE,
                       ResultSet.CONCUR_READ_ONLY);
    }


    /**
     * Comita a transação atual.
     *
     * @throws SQLException se ocorrer um erro de acesso ao banco de dados.
     */
    public void commit () throws SQLException
    {
        this.conexao.commit ();
    }


    /**
     * Faz rollback da transação atual.
     *
     * @throws SQLException se ocorrer um erro de acesso ao banco de dados.
     */
    public void rollback () throws SQLException
    {
        this.conexao.rollback ();
    }

    /**
     * Adiciona um lote de comandos SQL para execução em lote.
     *
     * @throws SQLException Se ocorrer um erro ao adicionar o lote de comandos.
     */

    public void addBatch () throws SQLException
    {
        this.comando.addBatch ();
    }


     /**
     * Limpa os parâmetros da declaração atualmente definidos.
     *
     * @throws SQLException Se ocorrer um erro ao limpar os parâmetros.
     */
    public void clearParameters () throws SQLException
    {
        this.comando.clearParameters ();
    }


     /**
     * Fecha a declaração e libera todos os recursos associados a ela.
     *
     * @throws SQLException Se ocorrer um erro ao fechar a declaração.
     */
    public void close () throws SQLException
    {
        for (ResultSet r:this.resultados)
            r.close ();

        for (Statement s:this.comandos)
            s.close ();

        if (this.comando!=null)
            this .comando.close ();

        super.comando.close ();
        super.conexao.close ();
    }


      /**
     * Executa a instrução SQL atual, que pode ser qualquer tipo de instrução SQL.
     *
     * @return 'true' se o primeiro resultado for um objeto 'ResultSet', 'false' se for uma atualização de contador 
     * ou se não houver resultados.
     * @throws SQLException se ocorrer um erro ao executar a instrução SQL.
     */
    public boolean execute () throws SQLException
    {
        return this.comando.execute ();
    }


     /**
     * Executa a instrução SQL atual e retorna o resultado como um objeto' ResultSe't.
     *
     * @return o objeto 'ResultSet' gerado pela consulta.
     * @throws SQLException se ocorrer um erro ao executar a consulta.
     */
    public ResultSet executeQuery () throws SQLException
    {
        ResultSet resultado = this.comando.executeQuery ();

        MeuResultSet retorno = new MeuResultSet (this.comando, resultado);

        this.resultados.add (retorno);
        this.comandos  .add (this.comando);

        this.comando = null;

        return retorno;
    }


    /**
     * Executa a instrução SQL atual, que pode ser uma instrução 'INSERT', 'UPDATE' ou 'DELETE'
     * ou uma instrução SQL que não retorna nada.
     *
     * @return o número de linhas afetadas.
     * @throws SQLException se ocorrer um erro ao executar a instrução SQL.
     */
    public int executeUpdate () throws SQLException
    {
        return this.comando.executeUpdate ();
    }


     /**
     * Retorna um objeto que contém metadados sobre o 'ResultSet' retornado pela execução da instrução SQL atual.
     *
     * @return um objeto 'ResultSetMetaData'.
     * @throws SQLException se ocorrer um erro ao obter os metadados.
     */
    public ResultSetMetaData getMetaData () throws SQLException
    {
        return this.comando.getMetaData ();
    }


    /**
     * Retorna um objeto que contém metadados sobre os parâmetros da instrução SQL atual.
     *
     * @return um objeto 'ParameterMetaData'.
     * @throws SQLException se ocorrer um erro ao obter os metadados dos parâmetros.
     */
    public ParameterMetaData getParameterMetaData () throws SQLException
    {
        return this.comando.getParameterMetaData ();
    }


    /**
    * Define o parâmetro especificado como um objeto 'Array'.
    *
    * @param parameterIndex o índice do parâmetro;
    * @param x  o objeto 'Array' a ser definido;
    * @throws SQLException se ocorrer um erro ao definir o parâmetro;
    */
    public void setArray (int parameterIndex, Array x) throws SQLException
    {
        this.comando.setArray (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres 'ASCII'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x  o fluxo de entrada de caracteres 'ASCII' a ser definido
    * @throws SQLException se ocorrer um erro ao definir o parâmetro
    */
    public void setAsciiStream (int parameterIndex, InputStream x) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres ASCII com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x  o fluxo de entrada de caracteres 'ASCII' a ser definido.
    * @param length  o comprimento máximo do fluxo de entrada.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setAsciiStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x, length);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres 'ASCII' com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro
    * @param x   o fluxo de entrada de caracteres 'ASCII' a ser definido.
    * @param length  o comprimento máximo do fluxo de entrada.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setAsciiStream (int parameterIndex, InputStream x, long length) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x, length);
    }


    /**
    * Define o parâmetro especificado como um 'BigDecimal'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o 'BigDecimal' a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBigDecimal (int parameterIndex, BigDecimal x) throws SQLException
    {
        this.comando.setBigDecimal (parameterIndex, x);
    }

    /**
    * Define o parâmetro especificado como um fluxo de bytes binários.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o fluxo de bytes binários a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBinaryStream (int parameterIndex, InputStream x) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de bytes binários com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o fluxo de bytes binários a ser definido.
    * @param length         o comprimento máximo do fluxo de bytes binários.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBinaryStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x, length);
    }


    /**
    * Define o parâmetro especificado como um fluxo de bytes binários com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o fluxo de bytes binários a ser definido.
    * @param length         o comprimento máximo do fluxo de bytes binários.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBinaryStream (int parameterIndex, InputStream x, long length) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x, length);
    }


    /**
    * Define o parâmetro especificado como um objeto 'Blob'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o Blob a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBlob (int parameterIndex, Blob x) throws SQLException
    {
        this.comando.setBlob (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de entrada de dados que representa um 'Blob'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param inputStream    o fluxo de entrada de dados a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBlob (int parameterIndex, InputStream inputStream) throws SQLException
    {
        this.comando.setBlob (parameterIndex, inputStream);
    }


    /**
    * Define o parâmetro especificado como um fluxo de entrada de dados que representa um 'Blob' 
    * com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param inputStream    o fluxo de entrada de dados a ser definido.
    * @param length         o comprimento máximo do 'Blob'.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBlob (int parameterIndex, InputStream inputStream, long length) throws SQLException
    {
        this.comando.setBlob (parameterIndex, inputStream, length);
    }


    /**
    * Define o parâmetro especificado como um valor booleano.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor booleano a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setBoolean (int parameterIndex, boolean x) throws SQLException
    {
        this.comando.setBoolean (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor byte.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor byte a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setByte (int parameterIndex, byte x) throws SQLException
    {
        this.comando.setByte (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um array de bytes.
    *
    * @param parameterIndex o índice do parâmetro
    * @param x              o array de bytes a ser definido
    * @throws SQLException se ocorrer um erro ao definir o parâmetro
    */
    public void setBytes (int parameterIndex, byte[] x) throws SQLException
    {
        this.comando.setBytes (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o leitor de caracteres a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setCharacterStream (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o leitor de caracteres a ser definido.
    * @param length         o comprimento máximo do fluxo de caracteres.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setCharacterStream (int parameterIndex, Reader reader, int length) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader, length);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres com um comprimento máximo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o leitor de caracteres a ser definido.
    * @param length         o comprimento máximo do fluxo de caracteres.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setCharacterStream (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader, length);
    }


    /**
    * Define o parâmetro especificado como um objeto 'Clob'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o Clob a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setClob (int parameterIndex, Clob x) throws SQLException
    {
        this.comando.setClob (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um leitor de caracteres que representa um 'Clob'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o leitor de caracteres a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setClob (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setClob (parameterIndex, reader);
    }

    /**
     * Define o parâmetro especificado como um leitor de caracteres que representa um 'Clob' 
     * com um comprimento máximo.
     *
     * @param parameterIndex o índice do parâmetro.
     * @param reader         o leitor de caracteres a ser definido.
     * @param length         o comprimento máximo do Clob.
     * @throws SQLException se ocorrer um erro ao definir o parâmetro.
     */
    public void setClob (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setClob (parameterIndex, reader, length);
    }


    /**
    * Define o parâmetro especificado como uma data SQL.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              a data a ser definida.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
     */
    public void setDate (int parameterIndex, Date x) throws SQLException
    {
        this.comando.setDate (parameterIndex, x);
    }

    /**
    * Define o parâmetro especificado como uma data SQL usando um objeto 'Calendar'
    * para especificar o fuso horário.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              a data a ser definida.
    * @param cal            o objeto Calendar contendo o fuso horário.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setDate (int parameterIndex, Date x, Calendar cal) throws SQLException
    {
        this.comando.setDate (parameterIndex, x, cal);
    }

    /**
    * Define o parâmetro especificado como um valor 'double'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor double a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setDouble (int parameterIndex, double x) throws SQLException
    {
        this.comando.setDouble (parameterIndex, x);
    }


    /**
     * Define o parâmetro especificado como um valor 'float'.
     *
     * @param parameterIndex o índice do parâmetro.
     * @param x              o valor 'float' a ser definido.
     * @throws SQLException se ocorrer um erro ao definir o parâmetro.
     */
    public void setFloat (int parameterIndex, float x) throws SQLException
    {
        this.comando.setFloat (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor 'int'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor 'int' a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setInt (int parameterIndex, int x) throws SQLException
    {
        this.comando.setInt (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor long.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor long a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */    
    public void setLong (int parameterIndex, long x) throws SQLException
    {
        this.comando.setLong (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres 'Unicode'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param value          o objeto 'Reader' contendo o fluxo de caracteres 'Unicode'.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNCharacterStream (int parameterIndex, Reader value) throws SQLException
    {
        this.comando.setNCharacterStream (parameterIndex, value);
    }


    /**
    * Define o parâmetro especificado como um fluxo de caracteres 'Unicode', 
    * especificando o comprimento do fluxo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param value          o objeto Reader contendo o fluxo de caracteres 'Unicode'.
    * @param length         o comprimento do fluxo de caracteres.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNCharacterStream (int parameterIndex, Reader value, long length) throws SQLException
    {
        this.comando.setNCharacterStream (parameterIndex, value, length);
    }


    /**
     * Define o parâmetro especificado como um 'NClob'.
     *
     * @param parameterIndex o índice do parâmetro.
     * @param value          o objeto 'NClob' a ser definido.
     * @throws SQLException se ocorrer um erro ao definir o parâmetro.
     */
    public void setNClob (int parameterIndex, NClob value) throws SQLException
    {
        this.comando.setNClob (parameterIndex, value);
    }


    /**
    * Define o parâmetro especificado como um 'NClob', especificando um objeto 'Reader'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o objeto 'Reader' contendo os dados do 'NClob'.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNClob (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setNClob (parameterIndex, reader);
    }


    /**
    * Define o parâmetro especificado como um 'NClob', especificando 
    * um objeto 'Reader' e o comprimento do 'NClob'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param reader         o objeto 'Reader' contendo os dados do 'NClob'.
    * @param length         o comprimento do 'NClob'.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNClob (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setNClob (parameterIndex, reader, length);
    }


    /**
    * Define o parâmetro especificado como uma cadeia de caracteres 'Unicode'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param value          o valor da cadeia de caracteres Unicode a ser definido.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNString (int parameterIndex, String value) throws SQLException
    {
        this.comando.setNString (parameterIndex, value);
    }


    /**
    * Define o parâmetro especificado como nulo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param sqlType        o tipo SQL do parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNull (int parameterIndex, int sqlType) throws SQLException
    {
        this.comando.setNull (parameterIndex, sqlType);
    }

    
    /**
    * Define o parâmetro especificado como nulo, especificando o tipo SQL e o nome do tipo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param sqlType        o tipo SQL do parâmetro.
    * @param typeName       o nome do tipo.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setNull (int parameterIndex, int sqlType, String typeName) throws SQLException
    {
        this.comando.setNull (parameterIndex, sqlType, typeName);
    }


    /**
    * Define o parâmetro especificado como o valor do objeto fornecido.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o objeto a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setObject (int parameterIndex, Object x) throws SQLException
    {
        this.comando.setObject (parameterIndex, x);
    }


    /**
     * Define o parâmetro especificado como o valor do objeto fornecido, 
     * especificando o tipo SQL do parâmetro.
     *
     * @param parameterIndex o índice do parâmetro.
     * @param x              o objeto a ser definido como parâmetro.
     * @param targetSqlType  o tipo SQL do parâmetro.
     * @throws SQLException se ocorrer um erro ao definir o parâmetro.
     */
    public void setObject (int parameterIndex, Object x, int targetSqlType) throws SQLException
    {
        this.comando.setObject (parameterIndex, x, targetSqlType);
    }

    /**
    * Define o parâmetro especificado como o valor do objeto fornecido, especificando 
    * o tipo SQL do parâmetro e a escala ou o comprimento.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o objeto a ser definido como parâmetro.
    * @param targetSqlType  o tipo SQL do parâmetro.
    * @param scaleOrLength  a escala (para tipos numéricos) ou o comprimento (para tipos de caracteres) do parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setObject (int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException
    {
        this.comando.setObject (parameterIndex, x, targetSqlType, scaleOrLength);
    }


    /**
    * Define o parâmetro especificado como uma referência SQL.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              a referência SQL a ser definida como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setRef (int parameterIndex, Ref x) throws SQLException
    {
        this.comando.setRef (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um ID de linha SQL.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o ID de linha a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setRowId (int parameterIndex, RowId x) throws SQLException
    {
        this.comando.setRowId (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor inteiro curto (short).
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor inteiro curto a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setShort (int parameterIndex, short x) throws SQLException
    {
        this.comando.setShort (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor 'XML SQL'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param xmlObject      o objeto 'XML SQL' a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setSQLXML (int parameterIndex, SQLXML xmlObject) throws SQLException
    {
        this.comando.setSQLXML (parameterIndex, xmlObject);
    }


    /**
    * Define o parâmetro especificado como uma cadeia de caracteres.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              a cadeia de caracteres a ser definida como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setString (int parameterIndex, String x) throws SQLException
    {
        this.comando.setString (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor de tempo SQL.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor de tempo a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setTime (int parameterIndex, Time x) throws SQLException
    {
        this.comando.setTime (parameterIndex, x);
    }


    /**
    * Define o parâmetro especificado como um valor de tempo SQL, utilizando o calendário fornecido.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor de tempo a ser definido como parâmetro.
    * @param cal            o calendário a ser usado para converter o tempo.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setTime (int parameterIndex, Time x, Calendar cal) throws SQLException
    {
        this.comando.setTime (parameterIndex, x, cal);
    }



    /**
    * Define o parâmetro especificado como um valor de data e hora SQL.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor de data e hora a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setTimestamp (int parameterIndex, Timestamp x) throws SQLException
    {
        this.comando.setTimestamp (parameterIndex, x);
    }


    /**
     Define o parâmetro especificado como um valor de data e hora SQL, utilizando o calendário fornecido.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o valor de data e hora a ser definido como parâmetro.
    * @param cal            o calendário a ser usado para converter a data e hora.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setTimestamp (int parameterIndex, Timestamp x, Calendar cal) throws SQLException
    {
        this.comando.setTimestamp (parameterIndex, x, cal);
    }


    /**
    * Define o parâmetro especificado como um fluxo 'Unicode' de entrada, fornecendo o comprimento do fluxo.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o fluxo de entrada Unicode a ser definido como parâmetro.
    * @param length         o comprimento do fluxo.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    * @deprecated Este método está obsoleto e seu uso é desencorajado
    */
    
    public void setUnicodeStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setUnicodeStream (parameterIndex, x, length);
    }


    /**
    * Define o parâmetro especificado como uma 'URL'.
    *
    * @param parameterIndex o índice do parâmetro.
    * @param x              o URL a ser definido como parâmetro.
    * @throws SQLException se ocorrer um erro ao definir o parâmetro.
    */
    public void setURL (int parameterIndex, URL x) throws SQLException
    {
        this.comando.setURL (parameterIndex, x);
    }


     /**
     * Retorna um código de hash para este objeto.
     *
     * @return O código de hash para este objeto.
     */
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret + (this.comando==null?0:this.comando.hashCode());

        return ret;
    }

    /**
     * Verifica se este objeto é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @SuppressWarnings("unlikely-arg-type")
    public boolean equals (Object obj)
    {
        if (obj==null)
            return false;

        if (this==obj)
            return true;

        if (this.getClass()==obj.getClass())
        {
			PreparedStatement ps = (PreparedStatement)obj;

			if (!super.equals(ps))
			    return false;

            MeuPreparedStatement mps = (MeuPreparedStatement)obj;

            if ((this.comando==null && mps.comando!=null) ||
                (this.comando!=null && mps.comando==null))
                return false;

            if (this.comando!=null && mps.comando!=null && this.comando.equals(mps.comando))
                return false;

            return true;
        }

        return false;
    }


    /**
     * Retorna uma representação em String deste objeto.
     *
     * @return Uma representação em String deste objeto.
     */
    public String toString ()
    {
		String ret = "Herdado: "+super.toString()+"\n";

		ret += "Comando: "+this.comando;

		return ret;
    }

    // O método 'compareTo' que, por vezes obrigatório, não está
    // sendo implementado porque, tendo dois objetos instanciados
    // desta classe, não faria sentido pensar em qual deles é o
    // maior ou em qual deles é o menor.

    // Os métodos 'clone' e 'construtor de cópia', por vezes obrigatórios,
    // não estão sendo implementados porque, não são necessários, já
    // que esta classe não possui métodos que alterem variáveis
    // declaradas ('private' ou 'protected').
}