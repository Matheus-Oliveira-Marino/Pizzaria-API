package bd.dbos;

import bd.daos.Produtos;
import bd.daos.Vendas;

public class Venda
{
    private int idPedido;
    private int idProduto;
    private int qtd;
    private double precoTotal;

    public int getIdVenda()
    {
        return this.idPedido;
    }

    public void setIdVenda(int idVenda) throws Exception
    {
        if(idVenda <= 0)
            throw new Exception("Codigo invalido");

        this.idPedido = idVenda;
    }

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

    public int getQtd() {
        return this.qtd;
    }

    public void setQtd(int qtd) throws Exception
    {
        if(qtd == 0)
            throw new Exception("Quantidade invalida");

        this.qtd = qtd;
    }

    public double getPrecoTotal()
    {
//        try
//        {
//            double preco;
//
//            preco = Produtos.getPreco(Integer.valueOf(txtIdProduto));
//            precoTotal = preco * Integer.parseInt(txtQtd);
//            System.out.println(""+preco+" "+precoTotal);
//
//        }
//        catch (Exception erro)
//        {
//            erro.printStackTrace();
//        }
        return this.precoTotal;
    }

    public void setPrecoTotal(double precoTotal) throws Exception
    {
        if(precoTotal < 0)
            throw new Exception("Preco invalido");

        this.precoTotal = precoTotal;
    }

    public Venda(int idProduto, int qtd,double precoTotal, int idPedido) throws Exception
    {
        this.setIdVenda(idPedido);
        this.setIdProduto(idProduto);
        this.setPrecoTotal(precoTotal);
        this.setQtd(qtd);
    }

    public Venda(int idProduto, int qtd, double precoTotal) throws Exception
    {
        this.setIdProduto(idProduto);
        this.setQtd(qtd);
        this.setPrecoTotal(precoTotal);
    }


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

    public Venda(Venda v) throws Exception
    {
        this.idProduto = v.idProduto;
        this.idPedido = v.idProduto;
        this.qtd = v.qtd;
        this.precoTotal = v.precoTotal;
    }

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
