package bd.dbos;


/**
 * A classe 'Produto' representa um produto em um sistema de gerenciamento de vendas.
 * Cada produto possui um identificador único: 'nome', 'preço', 'descrição' e 'categoria'.
 */
public class Produto implements Cloneable
{
    private int idProduto; // Identificador único do produto
    private String nome; // Nome do produto
    private double preco; // Preço do produto
    private String descricao; // Descrição do produto
    private int categoria; // Categoria do produto



     /**
     * Obtém o identificador único do produto.
     *
     * @return O identificador único do produto.
     */
    public int getIdProduto() {return this.idProduto;}


     /**
     * Define o identificador único do produto.
     *
     * @param idProduto O novo identificador único do produto.
     * @throws Exception Se o código fornecido for inválido.
     */
    public void setIdProduto(int idProduto) throws Exception
    {
        if(idProduto <= 0)
            throw new Exception("Codigo invalido");

        this.idProduto = idProduto;
    }


    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNome() {return this.nome;}
    


     /**
     * Define o nome do produto.
     *
     * @param nome O novo nome do produto.
     * @throws Exception Se o nome fornecido for inválido.
     */
    public void setNome(String nome) throws Exception
    {
        if(nome == null || nome.equals(""))
            throw new Exception("Nome invalido");

        this.nome = nome;
    }


      /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPreco() {return this.preco;}


    
    /**
     * Define o preço do produto.
     *
     * @param preco O novo preço do produto.
     * @throws Exception Se o preço fornecido for inválido.
     */
    public void setPreco(double preco) throws Exception
    {
        if(preco <= 0)
            throw new Exception("Preco invalido");

        this.preco = preco;
    }


     /**
     * Obtém a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricao() {return this.descricao;}


    /**
     * Define a descrição do produto.
     *
     * @param descricao A nova descrição do produto.
     * @throws Exception Se a descrição fornecida for inválida.
     */
    public void setDescricao(String descricao) throws Exception
    {
        if (descricao == null || descricao.equals(""))
            throw new Exception("er");

        this.descricao = descricao;
    }


    
    /**
     * Obtém a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public int getCategoria() {return this.categoria;}

     /**
     * Define a categoria do produto.
     *
     * @param categoria A nova categoria do produto.
     * @throws Exception Se a categoria fornecida for inválida.
     */
    public void setCategoria(int categoria) throws Exception
    {
        if(categoria <= 0)
            throw new Exception("Categoria invalida");

        this.categoria = categoria;
    }

     /**
     * Construtor da classe Produto.
     *
     * @param nome Nome do produto.
     * @param preco Preço do produto.
     * @param descricao Descrição do produto.
     * @param categoria Categoria do produto.
     * @throws Exception Se algum dos parâmetros fornecidos for inválido.
     */
    public Produto(String nome, double preco, String descricao,int categoria) throws Exception
    {
        //this.setIdProduto(idProduto);
        this.setNome(nome);
        this.setPreco(preco);
        this.setDescricao(descricao);
        this.setCategoria(categoria);
    }


    /**
     * Construtor da classe Produto.
     *
     * @param nome Nome do produto.
     * @param preco Preço do produto.
     * @param descricao Descrição do produto.
     * @param categoria Categoria do produto.
     * @param idProduto Identificador único do produto.
     * @throws Exception Se algum dos parâmetros fornecidos for inválido.
     */
    public Produto(String nome, double preco, String descricao,int categoria, int idProduto) throws Exception
    {
        this.setIdProduto(idProduto);
        this.setNome(nome);
        this.setPreco(preco);
        this.setDescricao(descricao);
        this.setCategoria(categoria);
    }


    
    /**
     * Retorna uma representação textual do produto.
     *
     * @return Uma string contendo o código, nome, preço, descrição e categoria do produto.
     */
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


    
    /**
     * Verifica se o produto é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado.
     * @return 'true' se o produto for igual ao objeto fornecido, 'false' caso contrário.
     */
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


     /**
     * Retorna o código de hash do produto.
     *
     * @return O código de hash do produto.
     */
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


    /**
     * Construtor de cópia da classe Produto.
     *
     * @param p O produto a ser copiado.
     * @throws Exception Se ocorrer um erro durante a cópia.
     */
    public Produto(Produto p) throws Exception
    {
        this.idProduto = p.idProduto;
        this.nome = p.nome;
        this.preco = p.preco;
        this.descricao = p.descricao;
        this.categoria = p.categoria;
    }


    /**
     * Cria uma cópia do objeto Produto.
     *
     * @return Uma cópia do objeto Produto.
     */
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
