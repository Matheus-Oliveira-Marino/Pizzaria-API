import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.daos.Produtos;
import bd.daos.Vendas;
import bd.dbos.Produto;
import bd.dbos.Venda;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;
import java.sql.*;

public class AlterarVendaTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener
{
    protected JLabel
            lb_idPedido =new JLabel("insira o id do pedido a ser alterado: "),
            lb_idProduto = new JLabel("insira o novo idProduto"),
            lb_qtd = new JLabel("Inserir a nova qtd: ");

    protected JTextField
            txt_pedido = new JTextField(20),
            txt_idProduto = new JTextField(20),
            txt_qtd = new JTextField(20);

    protected  JButton
            btn_confirmar = new JButton("Confirmar"),
            btn_voltar = new JButton("Voltar");

    private String txtPedido,txtProduto,txtqtd;

    private double preco, precoTotal;


    public AlterarVendaTela()
    {
        super("Alterar Vendas");
        this.setResizable(false);
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);
        //
        // GridLayout layout1 = new GridLayout(1,2,10,10);
        painel.setLayout(layout);
        painel.add(lb_idPedido);
        painel.add(txt_pedido);
        painel.add(lb_idProduto);
        painel.add(txt_idProduto);
        painel.add(lb_qtd);
        painel.add(txt_qtd);
        //painel.setLayout(layout1);
        painel.add(btn_confirmar);
        painel.add(btn_voltar);

        super.add(painel);

        btn_confirmar.addActionListener(this);
        btn_voltar.addActionListener(this);

        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(490, 170); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        txtPedido = txt_pedido.getText();
        txtProduto = txt_idProduto.getText();
        txtqtd = txt_qtd.getText();

        if(e.getSource() == btn_confirmar)
        {
            if(txtPedido.isEmpty()||txtProduto.isEmpty() || txtqtd.isEmpty())
            {
                JOptionPane.showMessageDialog(null , "Por favor digite os dados corretamente");
            }

            else
            {
                try
                {
                    preco = Produtos.getPreco(Integer.valueOf(txtProduto));
                    precoTotal = preco * Integer.parseInt(txtqtd);
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }

                try {
                    Vendas.alterar(new Venda(Integer.parseInt(txtProduto),Integer.parseInt(txtqtd),precoTotal,Integer.parseInt(txtPedido)));
                    JOptionPane.showMessageDialog(null , "Produto alterado com sucesso");
                    new VendasTela();
                    this.dispose();
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }
            }
        }

        if (e.getSource() == btn_voltar)
        {
            new VendasTela();
            this.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
