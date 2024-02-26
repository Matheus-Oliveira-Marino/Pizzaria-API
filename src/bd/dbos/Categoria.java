package bd.dbos;

public class Categoria
{
    private int idCategoria;
    private String tipoCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    @Override
    public String toString()
    {
        String ret = "";

        ret += "Codigo: "    + this.idCategoria + "\n";
        ret += "Nome: "      + this.tipoCategoria      + "\n";


        return ret;
    }



}
