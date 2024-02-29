package bd;

import bd.core.MeuPreparedStatement;

public class BDSQLServer
{

     // Declaração da constante 'COMANDO', que será utilizada para 
     // realizar operações no banco de dados.
    public static final MeuPreparedStatement COMANDO;

    static
    {
        // Inicialização da variável comando como nulo.
    	MeuPreparedStatement comando = null;

    	try
        {
            // Criação de uma instância de 'MeuPreparedStatement' para interagir 
            // com o banco de dados 'SQLServer'.
            comando =
            new MeuPreparedStatement(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver", // 'Driver JDBC' para 'SQL Server'.
                "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD22326", // URL de conexão com o banco de dados
                "BD22326", "BD22326"); // Nome de usuário e senha do banco de Dados.
        }
        catch (Exception erro)
        {
             // Em caso de erro na conexão, exibe uma mensagem de erro e aborta o programa.
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }

        // Atribui a instância de 'MeuPreparedStatement' criada à constante 'COMANDO'
        COMANDO = comando;
    }
}