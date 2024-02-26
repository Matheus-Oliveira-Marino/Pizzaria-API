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
import java.io.*;
import java.util.*;
import java.sql.*;

public class VendasTela extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{

    private static final long serialVersionUID = 1L;

    protected JButton
            btn_adiconarItem = new JButton("Adicionar"),
            btn_verItem = new JButton("Procurar"),
            btn_alterarItem = new JButton("Alterar"),
            btn_excluirItem = new JButton("Excluir"),
            btn_voltar = new JButton("Produto");


    protected JLabel
            lb_qtd = new JLabel("Digite a quantidade comprada: "),
            lb_Produto = new JLabel("Digite o Produto: "),
            lb_Resultado = new JLabel("");

    protected JTextField
            txt_qtd = new JTextField(20),
            txt_idProduto = new JTextField(20);

    protected int idProduto,qtd;
    protected double preco, precoTotal;
    protected String txtIdProduto, txtQtd;


    public VendasTela()
    {
        super("Pizzaria do I AM CRUD");
        this.setResizable(false);
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);
        painel.setLayout(layout);
        painel.add(lb_Produto);
        painel.add(txt_idProduto);
        painel.add(lb_qtd);
        painel.add(txt_qtd);
        painel.add(lb_Resultado);


        JPanel painelbotao = new JPanel();
        GridLayout layout1 = new GridLayout();
        painelbotao.setLayout(layout1);
        painelbotao.add(btn_adiconarItem);
        painelbotao.add(btn_verItem);
        painelbotao.add(btn_alterarItem);
        painelbotao.add(btn_excluirItem);
        painelbotao.add(btn_voltar);


        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(painel, BorderLayout.WEST);
        cntForm.add(painelbotao, BorderLayout.SOUTH);


        btn_adiconarItem.addActionListener(this);
        btn_alterarItem.addActionListener(this);
        btn_verItem.addActionListener(this);
        btn_excluirItem.addActionListener(this);
        btn_voltar.addActionListener(this);


        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(490, 160); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        txtIdProduto = txt_idProduto.getText();
        int idP = Integer.parseInt(txtIdProduto);
        txtQtd = txt_qtd.getText();

        if (e.getSource() == btn_adiconarItem)
        {
            try {
                if ( !Produtos.cadastro(idP) || txtQtd.isEmpty() || txtIdProduto.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!");
                }

                else
                {
                    try
                    {
                        preco = Produtos.getPreco(Integer.valueOf(txtIdProduto));
                        precoTotal = preco * Integer.parseInt(txtQtd);
                    }
                    catch (Exception erro)
                    {
                        erro.printStackTrace();
                    }

                    try
                    {
                        Vendas.incluir(new Venda(Integer.parseInt(txtIdProduto),Integer.parseInt(txtQtd), precoTotal));
                    }
                    catch (Exception erro)
                    {
                        erro.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, " Venda concluida!");
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            txt_idProduto.setText("");
            txt_qtd.setText("");
        }

         if (e.getSource() == btn_alterarItem)
        {
            try
            {
                new AlterarVendaTela();
                this.dispose();
            }
            catch (Exception erro)
            {
                erro.printStackTrace();
            }
        }

        if (e.getSource() == btn_excluirItem)
        {
            try
            {
                new ExcluirVendaTela();
                this.dispose();
            }
            catch (Exception erro)
            {
                erro.printStackTrace();
            }
        }

        if (e.getSource() == btn_verItem)
        {
            new VisualizacaoVendaTela();
        }

        if(e.getSource() == btn_voltar)
        {
            new Janela();
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
