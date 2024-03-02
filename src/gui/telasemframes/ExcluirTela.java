package gui.telasemframes;
import bd.daos.Produtos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Classe responsável por criar a interface gráfica para a alteração de vendas.
 */
public class ExcluirTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{

    // Declaração dos componentes da interface gráfica.
    protected JLabel id = new JLabel("Digite o id: ");
    protected  JTextField exclui = new JTextField(10);
    protected JButton confirmar = new JButton("Confirmar"),
                      voltar = new JButton("Voltar");


    String txtExclui; // Variável para armazenar o texto digitado no campo de exclusão.


    // Construtor da classe 'ExcluirTela'.
    public ExcluirTela()
    {
        super("Excluir produtos"); // Define o título da janela.
        this.setResizable(false); // Impede que a janela seja redimensionada pelo usuário.


        // Cria um painel para organizar os componentes da interface gráfica.
        JPanel painel = new JPanel(new GridLayout(1,0));


        painel.add(id); // Adiciona o rótulo "Digite o id:" ao painel.
        painel.add(exclui); // Adiciona o campo de texto para inserir o id ao painel.
        painel.add(confirmar); // Adiciona o botão "Confirmar" ao painel.
        painel.add(voltar); // Adiciona o botão "Voltar" ao painel.

        super.add(painel);  // Adiciona o painel à janela.

         confirmar.addActionListener(this);  // Adiciona um ouvinte de ação ao botão "Confirmar".
         voltar.addActionListener(this);  // Adiciona um ouvinte de ação ao botão "Voltar".

        this.setLocation(400, 190); // Define a posição inicial da janela na tela.
        this.setSize(390, 66); // Define o tamanho da janela.
        this.setVisible(true); // Torna a janela visível na tela.
    }


   /**
    * Método para lidar com eventos de botão.
    * 
    * @param e O evento de ação gerado por um componente da interface gráfica.
    */
    @Override
    public void actionPerformed(ActionEvent e)
    {
      
       // Se o evento foi gerado pelo botão "Confirmar".
       if(e.getSource()  == confirmar)
       {
           txtExclui = exclui.getText();  // Obtém o texto digitado no campo de exclusão.

           // Converte o texto para um número inteiro.
           int exclu = Integer.parseInt(txtExclui);

           // Se o campo estiver vazio, exibe uma mensagem de erro 
           // solicitando digitar um 'ID' válido.
           if(txtExclui.isEmpty())
           {
               JOptionPane.showMessageDialog(null , "Por favor digite  o id");
           }

           else
           {

               try
               {
                   // Chama o método de exclusão do produto.
                   Produtos.excluir(exclu);

                   // Exibe uma mensagem de sucesso.
                   JOptionPane.showMessageDialog(null , "Excluido com sucesso");
               }
               catch (Exception erro)
               {
                   erro.printStackTrace();
               }
           }
       }

       // Se o evento foi gerado pelo botão "Voltar",
       // Cria uma nova instância da classe 'Janela' para voltar ao menu principal.
       if(e.getSource() == voltar)
       {
           new Janela(); // Cria uma nova janela principal.
           this.dispose(); // Fecha a janela atual.
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
