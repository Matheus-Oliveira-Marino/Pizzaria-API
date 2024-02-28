import bd.daos.Produtos;
import bd.daos.Vendas;
import bd.dbos.Venda;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VendasTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener
{
    // Garantir a consistência serial durante a desserialização.
    private static final long serialVersionUID = 1L;

    // Declaração dos botões da interface.
    protected JButton
            btn_adiconarItem = new JButton("Adicionar"),
            btn_verItem = new JButton("Procurar"),
            btn_alterarItem = new JButton("Alterar"),
            btn_excluirItem = new JButton("Excluir"),
            btn_voltar = new JButton("Produto");

    // Declaração dos rótulos da interface.
    protected JLabel
            lb_qtd = new JLabel("Digite a quantidade comprada: "),
            lb_Produto = new JLabel("Digite o Produto: "),
            lb_Resultado = new JLabel("");

    // Declaração dos campos de texto da interface.
    protected JTextField
            txt_qtd = new JTextField(20),
            txt_idProduto = new JTextField(20);

    // Declaração das variáveis para manipulação dos dados da venda.
    protected int idProduto, qtd;
    protected double preco, precoTotal;
    protected String txtIdProduto, txtQtd;

    // Construtor da classe VendasTela
    public VendasTela()
    {
        super("Pizzaria do I AM CRUD");
        this.setResizable(false); // Impede o redimensionamento da janela.
        JPanel painel = new JPanel(); // Cria um painel para os componentes.
        GridLayout layout = new GridLayout(0, 2, 10, 10); // Define o layout do painel.
        painel.setLayout(layout); // Aplica o layout ao painel.
        painel.add(lb_Produto); // Adiciona o rótulo do produto ao painel.
        painel.add(txt_idProduto); // Adiciona o campo de texto para o produto ao painel.
        painel.add(lb_qtd); // Adiciona o rótulo da quantidade ao painel.
        painel.add(txt_qtd); // Adiciona o campo de texto para a quantidade ao painel.
        painel.add(lb_Resultado); // Adiciona o rótulo do resultado ao painel.

        JPanel painelbotao = new JPanel(); // Cria um painel para os botões.
        GridLayout layout1 = new GridLayout(); // Define um novo layout para os botões.
        painelbotao.setLayout(layout1); // Aplica o layout aos botões.
        painelbotao.add(btn_adiconarItem); // Adiciona o botão de adicionar ao painel.
        painelbotao.add(btn_verItem); // Adiciona o botão de procurar ao painel.
        painelbotao.add(btn_alterarItem); // Adiciona o botão de alterar ao painel.
        painelbotao.add(btn_excluirItem); // Adiciona o botão de excluir ao painel.
        painelbotao.add(btn_voltar); // Adiciona o botão de voltar ao painel.

        Container cntForm = this.getContentPane(); // Obtém o container da janela.
        cntForm.setLayout(new BorderLayout()); // Define o layout do container.
        cntForm.add(painel, BorderLayout.WEST); // Adiciona o painel de entrada à esquerda.
        cntForm.add(painelbotao, BorderLayout.SOUTH); // Adiciona o painel de botões na parte inferior.

        // Configura os botões para ouvirem eventos de clique.
        btn_adiconarItem.addActionListener(this);
        btn_alterarItem.addActionListener(this);
        btn_verItem.addActionListener(this);
        btn_excluirItem.addActionListener(this);
        btn_voltar.addActionListener(this);

        // Configurações finais da janela
        this.setLocation(400, 190); // Define a posição inicial da janela.
        this.setSize(490, 160); // Define o tamanho da janela.
        this.setVisible(true); // Torna a janela visível.
    }

    // Método de ação para os botões da interface.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Obtém os valores dos campos de texto.
        txtIdProduto = txt_idProduto.getText();
        int idP = Integer.parseInt(txtIdProduto);
        txtQtd = txt_qtd.getText();

        // Verifica qual botão foi clicado e executa a ação correspondente.
        if (e.getSource() == btn_adiconarItem)
        {
            try {
                // Verifica se os campos estão preenchidos corretamente e realiza a venda.
                if (!Produtos.cadastro(idP) || txtQtd.isEmpty() || txtIdProduto.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!");
                }
                else
                {
                    // Calcula o preço total da venda.
                    try
                    {
                        preco = Produtos.getPreco(Integer.valueOf(txtIdProduto));
                        precoTotal = preco * Integer.parseInt(txtQtd);
                    }
                    catch (Exception erro)
                    {
                        erro.printStackTrace();
                    }

                    // Inclui a venda no banco de dados.
                    try
                    {
                        Vendas.incluir(new Venda(Integer.parseInt(txtIdProduto), Integer.parseInt(txtQtd), precoTotal));
                    }
                    catch (Exception erro)
                    {
                        erro.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, " Venda concluída!");
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            // Limpa os campos de texto após a venda.
            txt_idProduto.setText("");
            txt_qtd.setText("");
        }

        // Abre a tela de alteração de venda.
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

        // Abre a tela de exclusão de venda.
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

        // Abre a tela de visualização de vendas.
        if (e.getSource() == btn_verItem)
        {
            new VisualizacaoVendaTela();
        }

        // Volta para a tela principal.
        if(e.getSource() == btn_voltar)
        {
            new Janela();
            this.dispose();
        }

    }

    // Métodos de mouse não utilizados, mantidos devido à implementação das interfaces 
    //'MouseListener' e 'MouseMotionListener'.
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
