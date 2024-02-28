import bd.daos.Produtos;
import bd.dbos.Produto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Classe para a criação da tela de alteração de produtos.
 */
public class AlterarTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener
{
    // Labels para os campos de entrada.
    protected JLabel
            lb_Nome = new JLabel("Insira o novo nome do produto: "),
            lb_Valor = new JLabel("Insira o novo valor: "),
            lb_Descricao = new JLabel("Insira uma nova descricao do produto: "),
            lb_IdCategoria = new JLabel("Insira uma nova categoria: "),
            lb_id = new JLabel("Insira o Id do Produto: ");

    // Campos de texto para entrada de dados.        
    protected JTextField
            txt_Nome = new JTextField(20),
            txt_Valor = new JTextField(20),
            txt_Descricao = new JTextField(20),
            txt_IdCategoria = new JTextField(20),
            txt_Id = new JTextField(20);

    // Botões para confirmar e voltar.  
    protected  JButton
            btn_confirmar = new JButton("Confirmar"),
            btn_voltar = new JButton("Voltar");

    // Array de categorias.        
    String [] categ = {"Pizzas","Bebidas","Aperitivos","Sobremesa"};
    
    // ComboBox para seleção de categoria
    //  Permite aos usuários selecionar uma opção a partir de uma lista 
    // predefinida de opções. 
    @SuppressWarnings("rawtypes")
    protected JComboBox cb_Categoria = new JComboBox<>(categ);

    // Variáveis para armazenar os dados dos campos de entrada
    private String txtProduto,txtValor,txtDescricao,txtCategoria;
    private int idCategoria, idP;
    private double valor;


    
    /**
     * Construtor da classe para inicializar a interface gráfica.
     */
    public AlterarTela()
    {
        super("Alterar produtos");
        this.setResizable(false);
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);
       
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

    /**
 * Método invocado quando ocorre uma ação em um dos botões da tela.
 * @param e O evento de ação que desencadeou a chamada deste método.
 */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Obtém o texto inserido no campo de texto para o nome do produto.
        txtProduto = txt_Nome.getText();

        // Obtém o texto inserido no campo de texto para o valor do produto.
        txtValor = txt_Valor.getText();

        // Obtém o texto inserido no campo de texto para a descrição do produto.
        txtDescricao = txt_Descricao.getText();

        // Obtém a categoria selecionada no 'combobox' 
        // e converte seu índice para a identificação da categoria.
        txtCategoria = String.valueOf(cb_Categoria.getSelectedIndex() + 1);

        // Converte a identificação da categoria para o tipo inteiro
        idCategoria = Integer.parseInt(txtCategoria);

        // Verifica se a ação foi originada pelo botão de confirmação.
        if (e.getSource() == btn_confirmar) 
        {
            // Verifica se algum dos campos obrigatórios está vazio.
            if (txtProduto.isEmpty() || txtValor.isEmpty() || txtDescricao.isEmpty()) 
            {
                // Exibe uma mensagem de erro se algum campo estiver vazio.
                JOptionPane.showMessageDialog(null, "Por favor digite os dados corretamente");
        
            } 
            else 
            {
                // Se todos os campos estiverem preenchidos, continua com o processo de alteração do produto.
                // Converte o valor para o tipo 'double'.
                valor = Double.parseDouble(txtValor);

                // Obtém o ID do produto a ser alterado
                idP = Integer.parseInt(txt_Id.getText());

                try 
                {
                    // Chama o método para alterar o produto no banco de dados, passando os novos dados.
                    Produtos.alterar(new Produto(txtProduto, valor, txtDescricao, idCategoria, idP));

                    // Exibe uma mensagem de sucesso após a alteração do produto.
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
                } 
                catch (Exception erro)
                {
                    // Exibe uma mensagem de erro se ocorrer algum problema durante a alteração do produto
                    System.out.println("Preencha os dados corretamente");
                }
            }
        }

        // Verifica se a ação foi originada pelo botão de voltar.
        if (e.getSource() == btn_voltar) 
        {
            // Fecha a janela atual.
            this.dispose();
            // Abre a janela principal novamente.
            new Janela();
        }
}

     // Métodos de 'MouseListener' e 'MouseMotionListener' que precisam ser implementados, 
    // mas não são utilizados.
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
