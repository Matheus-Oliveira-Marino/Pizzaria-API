package bd.dbos;



/**
 * A classe 'Venda' representa uma venda realizada em um sistema de gerenciamento de vendas.
 * Cada venda possui um identificador único, identificador do produto, quantidade e preço total.
 */
public class Venda
{
    private int idPedido; // Identificador único da venda
    private int idProduto; // Identificador do produto vendido
    private int qtd; // Quantidade do produto vendido
    private double precoTotal; // Preço total da venda


     /**
     * Obtém o identificador único da venda.
     *
     * @return O identificador único da venda.
     */
    public int getIdVenda()
    {
        return this.idPedido;
    }


    /**
     * Define o identificador único da venda.
     *
     * @param idVenda O novo identificador único da venda.
     * @throws Exception Se o código fornecido for inválido.
     */
    public void setIdVenda(int idVenda) throws Exception
    {
        if(idVenda <= 0)
            throw new Exception("Codigo invalido");

        this.idPedido = idVenda;
    }


    /**
     * Obtém o identificador do produto vendido.
     *
     * @return O identificador do produto vendido.
     */
    public int getIdProduto()
    {
        return this.idProduto;
    }


     /**
     * Define o identificador do produto vendido.
     *
     * @param idProduto O novo identificador do produto vendido.
     * @throws Exception Se o código fornecido for inválido.
     */
    public void setIdProduto(int idProduto) throws Exception
    {
        if(idProduto <= 0)
            throw new Exception("Codigo invalido");

        this.idProduto = idProduto;
    }


    /**
     * Obtém a quantidade do produto vendido.
     *
     * @return A quantidade do produto vendido.
     */
    public int getQtd() {
        return this.qtd;
    }


    /**
     * Define a quantidade do produto vendido.
     *
     * @param qtd A nova quantidade do produto vendido.
     * @throws Exception Se a quantidade fornecida for inválida.
     */
    public void setQtd(int qtd) throws Exception
    {
        if(qtd == 0)
            throw new Exception("Quantidade invalida");

        this.qtd = qtd;
    }


     /**
     * Obtém o preço total da venda.
     *
     * @return O preço total da venda.
     */
    public double getPrecoTotal()
    {
        return this.precoTotal;
    }


    /**
     * Define o preço total da venda.
     *
     * @param precoTotal O novo preço total da venda.
     * @throws Exception Se o preço fornecido for inválido.
     */
    public void setPrecoTotal(double precoTotal) throws Exception
    {
        if(precoTotal < 0)
            throw new Exception("Preco invalido");

        this.precoTotal = precoTotal;
    }


    /**
     * Construtor da classe Venda.
     *
     * @param idProduto Identificador do produto vendido.
     * @param qtd Quantidade do produto vendido.
     * @param precoTotal Preço total da venda.
     * @param idPedido Identificador único da venda.
     * @throws Exception Se algum dos parâmetros fornecidos for inválido.
     */
    public Venda(int idProduto, int qtd,double precoTotal, int idPedido) throws Exception
    {
        this.setIdVenda(idPedido);
        this.setIdProduto(idProduto);
        this.setPrecoTotal(precoTotal);
        this.setQtd(qtd);
    }


    /**
     * Construtor da classe Venda.
     *
     * @param idProduto Identificador do produto vendido.
     * @param qtd Quantidade do produto vendido.
     * @param precoTotal Preço total da venda.
     * @throws Exception Se algum dos parâmetros fornecidos for inválido.
     */
    public Venda(int idProduto, int qtd, double precoTotal) throws Exception
    {
        this.setIdProduto(idProduto);
        this.setQtd(qtd);
        this.setPrecoTotal(precoTotal);
    }



    /**
     * Retorna uma representação textual da venda.
     *
     * @return Uma string contendo o código da venda, o código do produto, a quantidade e o preço total.
     */
    @Override
    public String toString()
    {
        String ret = "";

        ret += "Codigo venda: " + this.idPedido + "\n";
        ret += "Codigo produto: "    + this.idProduto + "\n";
        ret += "Quantidade: "      + this.qtd      + "\n";
        ret += "PrecoTotal: "     + this.precoTotal     + "\n";


        return ret;
    }


    /**
     * Verifica se a venda é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado.
     * @return 'true' se a venda for igual ao objeto fornecido, 'false' caso contrário.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;

        Venda v = (Venda) obj;

        if (this.idPedido != v.idPedido) return false;
        if (this.idProduto != v.idProduto) return false;
        if (this.qtd != v.qtd) return false;
        if (this.precoTotal != v.precoTotal) return false;



        return true;
    }


    /**
     * Retorna o código de hash da venda.
     *
     * @return O código de hash da venda.
     */
    @Override
    public int hashCode()
    {
        int ret = 22;

        ret *= 17 + Integer.valueOf(this.idPedido).hashCode();
        ret *= 17 + Integer.valueOf(this.idProduto).hashCode();
        ret *= 17 + String.valueOf(this.qtd).hashCode();
        ret *= 17 + Double.valueOf(this.precoTotal).hashCode();



        if(ret < 0) return ret = -ret;

        return ret;
    }


    /**
     * Construtor de cópia da classe Venda.
     *
     * @param v A venda a ser copiada.
     * @throws Exception Se ocorrer algum erro durante a cópia.
     */
    public Venda(Venda v) throws Exception
    {
        this.idProduto = v.idProduto;
        this.idPedido = v.idProduto;
        this.qtd = v.qtd;
        this.precoTotal = v.precoTotal;
    }


    /**
     * Cria uma cópia do objeto Venda.
     *
     * @return Uma cópia do objeto Venda.
     */
    @Override
    public Object clone()
    {
        Venda clone = null;

        try {
            clone = new Venda(this);
        }
        catch (Exception e)
        {

        }

        return clone;
    }

}
