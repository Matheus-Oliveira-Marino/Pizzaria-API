package bd.dbos;


/**
 * A classe 'Categoria' representa uma categoria de produtos.
 * Cada categoria possui um identificador único e um nome.
 */

public class Categoria
{
    private int idCategoria; // Identificador único da categoria
    private String tipoCategoria; // Nome da categoria


    /**
     * Obtém o identificador único da categoria.
     *
     * @return O identificador único da categoria.
     */
    public int getIdCategoria() {
        return idCategoria;
    }


     /**
     * Define o identificador único da categoria.
     *
     * @param idCategoria O novo identificador único da categoria.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }


     /**
     * Obtém o nome da categoria.
     *
     * @return O nome da categoria.
     */
    public String getTipoCategoria() {
        return tipoCategoria;
    }


    /**
     * Define o nome da categoria.
     *
     * @param tipoCategoria O novo nome da categoria.
     */
    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }


    
    /**
     * Retorna uma representação textual da categoria.
     *
     * @return Uma string contendo o código e o nome da categoria.
     */
    @Override
    public String toString()
    {
        String ret = "";

        ret += "Codigo: "    + this.idCategoria + "\n";
        ret += "Nome: "      + this.tipoCategoria      + "\n";


        return ret;
    }



}
