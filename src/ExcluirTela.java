import bd.daos.Produtos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExcluirTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{
    protected JLabel id = new JLabel("Digite o id: ");
    protected  JTextField exclui = new JTextField(10);
    protected JButton confirmar = new JButton("Confirmar"),
                      voltar = new JButton("Voltar");
    String txtExclui;

    public ExcluirTela()
    {
        super("Excluir produtos");
        this.setResizable(false);

        JPanel painel = new JPanel(new GridLayout(1,0));
        painel.add(id);
        painel.add(exclui);
        painel.add(confirmar);
        painel.add(voltar);

        super.add(painel);

         confirmar.addActionListener(this);
         voltar.addActionListener(this);

        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(390, 66); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       // String txtExclui = exclui.getText();

       if(e.getSource()  == confirmar)
       {
           txtExclui = exclui.getText();
           if(txtExclui.isEmpty())
           {
               JOptionPane.showMessageDialog(null , "Por favor digite  o id");
           }

           else
           {
               int exclu = Integer.parseInt(txtExclui);

               try
               {
                   Produtos.excluir(exclu);
                   JOptionPane.showMessageDialog(null , "Excluido com sucesso");
               }
               catch (Exception erro)
               {
                   erro.printStackTrace();
               }
           }
       }

       if(e.getSource() == voltar)
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
