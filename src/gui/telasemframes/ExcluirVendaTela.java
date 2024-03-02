package gui.telasemframes;
import bd.daos.Vendas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe responsável por criar a interface gráfica para a exclusão de vendas.
 */
public class ExcluirVendaTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{

    // Declaração dos componentes da interface gráfica.
    protected JLabel id = new JLabel("Digite o id: ");
    protected  JTextField exclui = new JTextField(10);
    protected JButton confirmar = new JButton("Confirmar"),
                      voltar = new JButton("Voltar");
    protected String txtExclui; // Variável para armazenar o texto digitado no campo de exclusão.

    // Construtor da classe 'ExcluirVendaTela'.
    public ExcluirVendaTela()
    {
        super("Excluir Vendas"); // Define o título da janela.
        this.setResizable(false); // Impede que a janela seja redimensionada pelo usuário.


        // Cria um painel para organizar os componentes da interface gráfica.
        JPanel painel = new JPanel(new GridLayout(1,0));

        painel.add(id); // Define um layout de grade para o painel.
        painel.add(exclui); // Adiciona o campo de texto para inserir o id ao painel.
        painel.add(confirmar); // Adiciona o botão "Confirmar" ao painel.
        painel.add(voltar); // Adiciona o botão "Voltar" ao painel.

        super.add(painel); // Adiciona o painel à janela.

        confirmar.addActionListener(this);
        voltar.addActionListener(this);

        this.setLocation(400, 190); // Define a posição inicial da janela na tela.
        this.setSize(390, 66); // Define o tamanho da janela.
        this.setVisible(true); // Torna a janela visível na tela.
    }


    /**
     * Método 'actionPerformed' para lidar com eventos de botões.
     * @param e O evento de ação gerado por um componente da interface gráfica.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Se o evento foi gerado pelo botão "Confirmar".
        if(e.getSource()  == confirmar)
        {

            // Obtém o texto digitado no campo de exclusão.
            txtExclui = exclui.getText();

            // Se o campo estiver vazio, envia uma mensagem 
            // solicitando para digitar o ID corretamente.
            if(txtExclui.isEmpty()) 
            {
                JOptionPane.showMessageDialog(null , "Por favor digite  o id");
            }

            else
            {

                // Converte o texto para um número inteiro.
                int exclu = Integer.parseInt(txtExclui);

                try {
                    Vendas.excluir(exclu); // Chama o método de exclusão da venda.
                    JOptionPane.showMessageDialog(null , "Excluido com sucesso"); // Exibe uma mensagem de sucesso.
                    new Janela(); // Cria uma nova instância da classe 'Janela' para voltar ao menu principal.
                    this.dispose(); // Fecha a janela atual.
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }
            }
        }

        // Se o evento foi gerado pelo botão "Voltar".
        if(e.getSource() == voltar)
        {
             // Fecha a janela atual.
            this.dispose();

            // Cria uma nova instância da classe 'VendasTela' para retornar à tela de vendas.
            new VendasTela(); 
        }
    }


    // Métodos de 'MouseListener' e 'MouseMotionListener' que precisam ser implementados, 
    // mas não são utilizados.
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
