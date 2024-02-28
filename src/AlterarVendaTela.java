import bd.daos.Produtos;
import bd.daos.Vendas;
import bd.dbos.Venda;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe responsável por criar a interface gráfica para a alteração de vendas.
 */
public class AlterarVendaTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener
{
    // Labels para os campos de entrada.
    protected JLabel
            lb_idPedido =new JLabel("insira o id do pedido a ser alterado: "),
            lb_idProduto = new JLabel("insira o novo idProduto"),
            lb_qtd = new JLabel("Inserir a nova qtd: ");

    // Campos de texto para entrada de dados.     
    protected JTextField
            txt_pedido = new JTextField(20),
            txt_idProduto = new JTextField(20),
            txt_qtd = new JTextField(20);

    // Botões para confirmar e voltar. 
    protected  JButton
            btn_confirmar = new JButton("Confirmar"),
            btn_voltar = new JButton("Voltar");

    // Variáveis para armazenar os dados inseridos pelo usuário.        
    private String txtPedido,txtProduto,txtqtd;
    private double preco, precoTotal;


    /**
     * Construtor da classe que inicializa a interface gráfica.
     */
    public AlterarVendaTela()
    {
         // Chama o construtor da classe 'JFrame', 
         // definindo o título da janela como "Alterar Vendas".
        super("Alterar Vendas");

        // Impede que o usuário redimensione a janela.
        this.setResizable(false);

        // Cria um painel para organizar os componentes da interface.
        JPanel painel = new JPanel();

        // Define o layout do painel como 'GridLayout', com 0 linhas, 
        // 2 colunas e espaçamento de 10 pixels entre as células.
        GridLayout layout = new GridLayout(0, 2, 10, 10);
        
        // Define o layout criado para o painel
        painel.setLayout(layout);

        // Adiciona os componentes de interface (rótulos, campos de texto e botões) 
        // ao painel.
        painel.add(lb_idPedido);
        painel.add(lb_idPedido);
        painel.add(txt_pedido);
        painel.add(lb_idProduto);
        painel.add(txt_idProduto);
        painel.add(lb_qtd);
        painel.add(txt_qtd);
        painel.add(btn_confirmar);
        painel.add(btn_voltar);

        // Adiciona o painel à janela. 
        super.add(painel);

        // Registra os botões para escutar eventos de clique.
        btn_confirmar.addActionListener(this);
        btn_voltar.addActionListener(this);

        this.setLocation(400, 190);  // Define a posição da tela.
        this.setSize(490, 170); // Define o tamanho da tela. 
        this.setVisible(true); // Torna a janela visível.
    }


    /**
     * Método invocado quando ocorre uma ação em um dos botões da tela.
     * @param e O evento de ação que desencadeou a chamada deste método.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Obtém os dados inseridos pelo usuário.
        txtPedido = txt_pedido.getText();
        txtProduto = txt_idProduto.getText();
        txtqtd = txt_qtd.getText();


         // Verifica se a ação foi originada pelo botão de confirmação.
        if(e.getSource() == btn_confirmar)
        {

             // Verifica se algum campo obrigatório está vazio.
            if(txtPedido.isEmpty()||txtProduto.isEmpty() || txtqtd.isEmpty())
            {

                // Exibe uma mensagem de erro se algum campo estiver vazio.
                JOptionPane.showMessageDialog(null , "Por favor digite os dados corretamente");
            }

            else
            {
                try
                {
                    // Obtém o preço do produto.
                    preco = Produtos.getPreco(Integer.valueOf(txtProduto));

                    // Calcula o preço total da venda.
                    precoTotal = preco * Integer.parseInt(txtqtd);
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }

                try {
                    // Realiza a alteração da venda no banco de dados.
                    Vendas.alterar(new Venda(Integer.parseInt(txtProduto),
                    Integer.parseInt(txtqtd),precoTotal,Integer.parseInt(txtPedido)));

                    // Exibe uma mensagem de sucesso após a alteração da venda.
                    JOptionPane.showMessageDialog(null , "Produto alterado com sucesso");
                    
                    // Abre a tela de vendas novamente.
                    new VendasTela();

                    // Fecha a tela atual;
                    this.dispose();
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }
            }
        }

        // Verifica se a ação foi originada pelo botão de voltar.
        if (e.getSource() == btn_voltar)
        {
            // Abre a tela de vendas novamente.
            new VendasTela();

             // Fecha a tela atual.
            this.dispose();
        }
    }


    // Métodos de mouse não utilizados, mantidos devido à implementação 
    // da interface 'MouseListener' e 'MouseMotionListener'.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
