import bd.daos.Produtos;
import bd.dbos.Produto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Classe que define a interface gráfica principal da aplicação.
 */
public class Janela extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{

    // Declaração da variável para garantir consistência serial durante a desserialização.
    private static final long serialVersionUID = 1L;

    // Declaração das variáveis para os dados do produto.
    private String txtProduto,txtValor,txtDescricao,txtCategoria;

    // Supressão de aviso para variáveis não utilizadas.
    // Declaração das variáveis para as categorias e o 'idCategoria'.
    @SuppressWarnings("unused")
    private int idCategoria, id = 1;

    // Declaração e inicialização dos botões com seus respectivos nomes.
    protected JButton
            btn_adiconarItem = new JButton("Adicionar"),
            btn_verItem = new JButton("Procurar"),
            btn_alterarItem = new JButton("Alterar"),
            btn_excluirItem = new JButton("Excluir"),
            btn_venda = new JButton("Vender");

    // Declaração e inicialização dos rótulos com suas respectivas mensagens.
    protected JLabel
            lb_Prod = new JLabel("Insira o nome do produto: "),
            lb_Valor = new JLabel("Insira o valor: "),
            lb_Descricao = new JLabel("Insira uma descricao do produto: "),
            lb_Categoria = new JLabel("Insira a categoria: "),
            lb_Resultado = new JLabel("");

    // Declaração e inicialização dos campos de texto com seus respectivos tamanhos.
    protected JTextField
            txt_Adicionar = new JTextField(20),
            txt_Valor = new JTextField(20),
            txt_Descricao = new JTextField(20),
            txt_Categoria = new JTextField(20);
        
    // Declaração e inicialização do array de categorias
    String [] categ = {"Pizzas","Bebidas","Aperitivos","Sobremesa"};

    // Supressão de aviso para tipo genérico JComboBox sem parâmetro de tipo específico.
    // Declaração e inicialização do menu suspenso de 'categorias'.
    @SuppressWarnings("rawtypes")
    protected JComboBox cb_Categoria = new JComboBox<>(categ);
    protected boolean seila = false;

     /**
     * Método construtor da classe Janela, responsável 
     * por inicializar e configurar a interface gráfica.
     */
    public Janela()
    {
        // Configurações da janela principal.
        super("Pizzaria do I AM CRUD"); // chamando o construtor de 'JFrame' e definindo titulo da janela.
        this.setResizable(false);

        // Criação dos painéis para organizar os componentes.
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);

         // Adição dos componentes aos painéis.
        painel.setLayout(layout);
        painel.add(lb_Prod); // Adiciona o rótulo para inserção do nome do produto.
        painel.add(txt_Adicionar); // Adiciona o campo de texto para inserção do nome do produto.
        painel.add(lb_Valor);  // Adiciona o rótulo para inserção do valor do produto.
        painel.add(txt_Valor); // Adiciona o campo de texto para inserção do valor do produto.
        painel.add(lb_Descricao); // Adiciona o rótulo para inserção da descrição do produto.
        painel.add(txt_Descricao); // Adiciona o campo de texto para inserção da descrição do produto.
        painel.add(lb_Categoria); // Adiciona o rótulo para seleção da categoria do produto. 
        painel.add(cb_Categoria); // Adiciona o menu suspenso para seleção da categoria do produto.
        painel.add(lb_Resultado); // Adiciona um rótulo para exibição de resultados.


        JPanel painelbotao = new JPanel();
        GridLayout layout1 = new GridLayout();
        painelbotao.setLayout(layout1);
        painelbotao.add(btn_adiconarItem); // Adiciona o botão para adicionar um item.
        painelbotao.add(btn_verItem); // Adiciona o botão para visualizar itens.
        painelbotao.add(btn_alterarItem); // Adiciona o botão para alterar um item. 
        painelbotao.add(btn_excluirItem);  // Adiciona o botão para excluir um item. 
        painelbotao.add(btn_venda);  // Adiciona o botão para acessar a tela de vendas.

        // Adiciona os painéis à janela principal.
        Container cntForm = this.getContentPane();

        cntForm.setLayout(new BorderLayout());
        cntForm.add(painel, BorderLayout.WEST); // Adiciona o painel de entrada de dados à esquerda. 
        cntForm.add(painelbotao, BorderLayout.SOUTH);  // Adiciona o painel de botões na parte inferior. 

        // Configura os botões para ouvirem eventos de clique.
        btn_adiconarItem.addActionListener(this);
        btn_alterarItem.addActionListener(this);
        btn_verItem.addActionListener(this);
        btn_excluirItem.addActionListener(this);
        btn_venda.addActionListener(this);

        // Configurações finais da janela.
        this.setLocation(400, 190); // Define a posição inicial da janela.
        this.setSize(490, 220); // Define o tamanho da janela.
        this.setVisible(true); // Deixa a janela visivel.
    }



    /**
    * Método invocado quando ocorre uma ação em um componente que este objeto está escutando.
    * 
    * @param e O evento de ação que ocorreu.
    */
    @Override
    public void actionPerformed(ActionEvent e) // Método de ação executado quando um evento de ação ocorre.
    { 
        txtProduto = txt_Adicionar.getText(); // Obtém o texto inserido no campo de texto para o nome do produto.
        txtValor = txt_Valor.getText(); // Obtém o texto inserido no campo de texto para o valor do produto.
        txtDescricao = txt_Descricao.getText(); // Obtém o texto inserido no campo de texto para a descrição do produto.

        // Obtém o índice selecionado no menu suspenso de categorias e adiciona 1 para obter o valor da categoria.
        txtCategoria = String.valueOf(cb_Categoria.getSelectedIndex() + 1); 

        idCategoria = Integer.parseInt(txtCategoria);  // Converte o valor da categoria para inteiro.

         // Verifica se o botão "Adicionar" foi clicado.
         if (e.getSource() == btn_adiconarItem)
        {
            // Verifica se algum dos campos está vazio.
            if (txtProduto.isEmpty() || txtValor.isEmpty() || txtDescricao.isEmpty() && e.getSource() ==  btn_adiconarItem)
            {
                // Exibe uma mensagem de aviso caso algum campo esteja vazio.
                JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!");
            }
            else
            {
                try
                {
                    // Chama o método para incluir um novo produto no banco de dados.
                    Produtos.incluir(new Produto(txtProduto, Double.parseDouble(txtValor), txtDescricao, idCategoria));
                }
                catch (Exception erro)
                {
                    // Imprime o rastreamento do erro, caso ocorra uma exceção.
                    erro.printStackTrace();
                }

                // Exibe uma mensagem de sucesso após a inclusão do produto.
                JOptionPane.showMessageDialog(null, "Mercadoria Adicionada com sucesso!");
            }


            txt_Adicionar.setText(""); // Limpa o campo de texto para o nome do produto.    
            txt_Valor.setText(""); // Limpa o campo de texto para o valor do produto.
            txt_Descricao.setText(""); // Limpa o campo de texto para a descrição do produto.


        }
        // Verifica se o botão "Excluir" foi clicado.
        else if (e.getSource() == btn_excluirItem)
        {
            this.dispose(); // Fecha a janela atual.
            new ExcluirTela(); // Abre uma nova janela para exluir um item.
        }

        // Verifica se o botão "Alterar" foi clicado.
        else if (e.getSource() == btn_alterarItem)
        {
            try {
                this.dispose(); // Fecha a janela atual.
                new AlterarTela(); // Abre uma nova janela para alterar um item.

                txt_Adicionar.setText(""); // Limpa o campo de texto para o nome do produto.
                txt_Valor.setText(""); // Limpa o campo de texto para o valor do produto.
                txt_Descricao.setText(""); // Limpa o campo de texto para o valor do produto.
            }
            catch (Exception erro)
            {
                erro.printStackTrace();
            }



        }

        // Verifica se o botão "Procurar" foi clicado.
        if (e.getSource() == btn_verItem)
        {
            // Abre uma nova janela para visualizar itens.
            new VisualizacaoTela();
        }

        // Verifica se o botão "Vender" foi clicado.
        if(e.getSource() == btn_venda)
        {
            this.dispose(); // Fecha a janela atual.
            new VendasTela(); // Abre uma nova janela para vendas.
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
