package bd.dbos;

public class Produto implements Cloneable
{
    private int idProduto;
    private String nome;
    private double preco;
    private String descricao;
    private int categoria;

    public int getIdProduto()
    {
        return this.idProduto;
    }

    public void setIdProduto(int idProduto) throws Exception
    {
        if(idProduto <= 0)
            throw new Exception("Codigo invalido");

        this.idProduto = idProduto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws Exception
    {
        if(nome == null || nome.equals(""))
            throw new Exception("Nome invalido");

        this.nome = nome;
    }

    public double getPreco() {return this.preco;}

    public void setPreco(double preco) throws Exception
    {
        if(preco <= 0)
            throw new Exception("Preco invalido");

        this.preco = preco;
    }

    public String getDescricao() {return this.descricao;}

    public void setDescricao(String descricao) throws Exception
    {
        if (descricao == null || descricao.equals(""))
            throw new Exception("er");

        this.descricao = descricao;
    }

    public int getCategoria() {return this.categoria;}
    public void setCategoria(int categoria) throws Exception
    {
        if(categoria <= 0)
            throw new Exception("Categoria invalida");

        this.categoria = categoria;
    }

    public Produto(String nome, double preco, String descricao,int categoria) throws Exception
    {
        //this.setIdProduto(idProduto);
        this.setNome(nome);
        this.setPreco(preco);
        this.setDescricao(descricao);
        this.setCategoria(categoria);
    }

    public Produto(String nome, double preco, String descricao,int categoria, int idProduto) throws Exception
    {
        this.setIdProduto(idProduto);
        this.setNome(nome);
        this.setPreco(preco);
        this.setDescricao(descricao);
        this.setCategoria(categoria);
    }

    @Override
    public String toString()
    {
        String ret = "";

        ret += "Codigo: "    + this.idProduto + "\n";
        ret += "Nome: "      + this.nome      + "\n";
        ret += "Preco: "     + this.preco     + "\n";
        ret += "Descricao: " + this.descricao + "\n";
        ret += "Categoria: " + this.categoria + "\n";

        return ret;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;

        Produto p = (Produto) obj;

        if (this.idProduto != p.idProduto) return false;
        if (this.nome != p.nome) return false;
        if (this.preco != p.preco) return false;
        if (this.descricao != p.descricao) return false;
        if (this.categoria != p.categoria) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int ret = 26;

        ret *= 13 + Integer.valueOf(this.idProduto).hashCode();
        ret *= 13 + String.valueOf(this.nome).hashCode();
        ret *= 13 + Double.valueOf(this.preco).hashCode();
        ret *= 13 + String.valueOf(this.descricao).hashCode();
        ret *= 13 + Integer.valueOf(this.categoria).hashCode();

        if(ret < 0) return ret = -ret;

        return ret;
    }

    public Produto(Produto p) throws Exception
    {
        this.idProduto = p.idProduto;
        this.nome = p.nome;
        this.preco = p.preco;
        this.descricao = p.descricao;
        this.categoria = p.categoria;
    }

    @Override
    public Object clone()
    {
        Produto clone = null;

        try {
            clone = new Produto(this);
        }
        catch (Exception e)
        {

        }

        return clone;
    }
}
