import bd.cora.MeuResultSet;
import bd.daos.Vendas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VisualizacaoVendaTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{
    JLabel id = new JLabel(""),
            idProduto = new JLabel(""),
            qtd = new JLabel(""),
            precoTotal = new JLabel("");

    JTextArea txt_result = new JTextArea(100, 5);

    public VisualizacaoVendaTela()
    {
        super("Visualizacao das Vendas");
        this.setResizable(false);

        JPanel painel = new JPanel(new GridLayout(1,1));
        painel.add(txt_result);
        super.add(painel);

        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        String dados = "";
        try {
            MeuResultSet resultado = Vendas.getVenda();
            dados += "||  " + "idPedido" + "  |  " + "idProduto" + "  |  " + "qtd" + "  |  " + "Preco Total:"  + "  ||" + "\n" + "\n";

            while(resultado.next()) {
                dados += resultado.getInt("idPedido") + " | " +
                        resultado.getInt("idProduto") + " | " +
                        resultado.getInt("qtd") + " | " +
                        resultado.getDouble("precoTotal") + "\n";
            }
            txt_result.setText(dados);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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
