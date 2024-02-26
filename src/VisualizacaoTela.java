import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.daos.Categorias;
import bd.daos.Produtos;
import bd.dbos.Categoria;
import bd.dbos.Produto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;
import java.sql.*;

public class VisualizacaoTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{
    JLabel id = new JLabel(""),
            nome = new JLabel(""),
            valor = new JLabel(""),
            descricao = new JLabel(""),
            idCategoria = new JLabel("");

    JTextArea txt_result = new JTextArea(100, 5);

    public VisualizacaoTela()
    {
        super("Visualizacao dos produtos");
        this.setResizable(false);

        JPanel painel = new JPanel(new GridLayout(1,1));
        painel.add(txt_result);
        super.add(painel);


        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));


        try {
            MeuResultSet resultado = Produtos.getProduto();



            String dados = "";
            dados += "||  " + "idProduto" + "  |  " + "Nome" + "  |  " + "Preco" + "  |  " + "Descricao" + "  |  " + "idCategoria" + "  |  " + "tipo de categoria: " + "  ||" + "\n" + "\n";
            while(resultado.next()) {
                dados += resultado.getInt("idProduto") + " | " +
                        resultado.getString("nome") + " | " +
                        resultado.getDouble("preco") + " | " +
                        resultado.getString("descricao") + " | " +
                        resultado.getInt("idCategoria") + " | " +
                       Categorias.getCategoria(resultado.getInt("idCategoria")).getString("tipoCategoria")+ "\n";
            }
            txt_result.setText(dados);
        } catch (Exception e) { e.printStackTrace(); }

        txt_result.setEditable(false);


        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(590, 520); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

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
