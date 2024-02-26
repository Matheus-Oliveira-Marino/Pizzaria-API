import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.daos.Produtos;
import bd.dbos.Produto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;
import java.sql.*;

public class AlterarTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener
{
    protected JLabel
            lb_Nome = new JLabel("Insira o novo nome do produto: "),
            lb_Valor = new JLabel("Insira o novo valor: "),
            lb_Descricao = new JLabel("Insira uma nova descricao do produto: "),
            lb_IdCategoria = new JLabel("Insira uma nova categoria: "),
            lb_id = new JLabel("Insira o Id do Produto: ");

    protected JTextField
            txt_Nome = new JTextField(20),
            txt_Valor = new JTextField(20),
            txt_Descricao = new JTextField(20),
            txt_IdCategoria = new JTextField(20),
            txt_Id = new JTextField(20);
    protected  JButton
            btn_confirmar = new JButton("Confirmar"),
            btn_voltar = new JButton("Voltar");

    String [] categ = {"Pizzas","Bebidas","Aperitivos","Sobremesa"};
    protected JComboBox cb_Categoria = new JComboBox<>(categ);

    private String txtProduto,txtValor,txtDescricao,txtCategoria;
    private int idCategoria, idP;
    private double valor;


    public AlterarTela()
    {
        super("Alterar produtos");
        this.setResizable(false);
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);
        //
        // GridLayout layout1 = new GridLayout(1,2,10,10);
        painel.setLayout(layout);
        painel.add(lb_Nome);
        painel.add(txt_Nome);
        painel.add(lb_Valor);
        painel.add(txt_Valor);
        painel.add(lb_Descricao);
        painel.add(txt_Descricao);
        painel.add(lb_IdCategoria);
        painel.add(cb_Categoria);
        painel.add(lb_id);
        painel.add(txt_Id);
        //painel.setLayout(layout1);
        painel.add(btn_confirmar);
        painel.add(btn_voltar);

        super.add(painel);

        btn_confirmar.addActionListener(this);
        btn_voltar.addActionListener(this);

        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(490, 220); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        txtProduto = txt_Nome.getText();

        txtValor = txt_Valor.getText();
        //valor = Double.parseDouble(txt_Valor.getText());

        txtDescricao = txt_Descricao.getText();

        txtCategoria = String.valueOf(cb_Categoria.getSelectedIndex() + 1);
        idCategoria = Integer.parseInt(txtCategoria);

        //idP = Integer.parseInt(txt_Id.getText());

        if(e.getSource() == btn_confirmar)
        {

            if(txtProduto.isEmpty()  || txtValor.isEmpty() || txtDescricao.isEmpty())
            {
                JOptionPane.showMessageDialog(null , "Por favor digite os dados corretamente");
            }

            else
            {
                valor = Double.parseDouble(txtValor);
                idP = Integer.parseInt(txt_Id.getText());

                try {
                    Produtos.alterar(new Produto(txtProduto,valor,txtDescricao,idCategoria,idP));
                    JOptionPane.showMessageDialog(null , "Produto alterado com sucesso");

                }
                catch (Exception erro)
                {
                    System.out.println("Preencha os dados corretamente");
                }
            }

        }

        if (e.getSource() == btn_voltar)
        {
            this.dispose();
            new Janela();
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
