import bd.daos.Produtos;
import bd.dbos.Produto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Janela extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{

    private static final long serialVersionUID = 1L;
    private String txtProduto,txtValor,txtDescricao,txtCategoria;

    @SuppressWarnings("unused")
    private int idCategoria, id = 1;

    protected JButton
            btn_adiconarItem = new JButton("Adicionar"),
            btn_verItem = new JButton("Procurar"),
            btn_alterarItem = new JButton("Alterar"),
            btn_excluirItem = new JButton("Excluir"),
            btn_venda = new JButton("Vender");


    protected JLabel
            lb_Prod = new JLabel("Insira o nome do produto: "),
            lb_Valor = new JLabel("Insira o valor: "),
            lb_Descricao = new JLabel("Insira uma descricao do produto: "),
            lb_Categoria = new JLabel("Insira a categoria: "),
            lb_Resultado = new JLabel("");

    protected JTextField
            txt_Adicionar = new JTextField(20),
            txt_Valor = new JTextField(20),
            txt_Descricao = new JTextField(20),
            txt_Categoria = new JTextField(20);

    String [] categ = {"Pizzas","Bebidas","Aperitivos","Sobremesa"};

    @SuppressWarnings("rawtypes")
    protected JComboBox cb_Categoria = new JComboBox<>(categ);
    protected boolean seila = false;

    public Janela()
    {
        super("Pizzaria do I AM CRUD");
        this.setResizable(false);
        JPanel painel = new JPanel();
        GridLayout layout = new GridLayout(0, 2, 10, 10);
        painel.setLayout(layout);
        painel.add(lb_Prod);
        painel.add(txt_Adicionar);
        painel.add(lb_Valor);
        painel.add(txt_Valor);
        painel.add(lb_Descricao);
        painel.add(txt_Descricao);
        painel.add(lb_Categoria);
        //painel.add(txt_Categoria);
        painel.add(cb_Categoria);
        painel.add(lb_Resultado);


        JPanel painelbotao = new JPanel();
        GridLayout layout1 = new GridLayout();
        painelbotao.setLayout(layout1);
        painelbotao.add(btn_adiconarItem);
        painelbotao.add(btn_verItem);
        painelbotao.add(btn_alterarItem);
        painelbotao.add(btn_excluirItem);
        painelbotao.add(btn_venda);


        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(painel, BorderLayout.WEST);
        cntForm.add(painelbotao, BorderLayout.SOUTH);


        btn_adiconarItem.addActionListener(this);
        btn_alterarItem.addActionListener(this);
        btn_verItem.addActionListener(this);
        btn_excluirItem.addActionListener(this);
        btn_venda.addActionListener(this);


        this.setLocation(400, 190); // Deixa a tela no meio
        this.setSize(490, 220); // Tamanho da tela
        this.setVisible(true); // Deixa a janela visivel
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        txtProduto = txt_Adicionar.getText();
        txtValor = txt_Valor.getText();
        txtDescricao = txt_Descricao.getText();
        txtCategoria = String.valueOf(cb_Categoria.getSelectedIndex() + 1);
        idCategoria = Integer.parseInt(txtCategoria); //tipo int



         if (e.getSource() == btn_adiconarItem)
        {
            if (txtProduto.isEmpty() || txtValor.isEmpty() || txtDescricao.isEmpty() && e.getSource() ==  btn_adiconarItem)
            {
                JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!");
            }
            else
            {
                try
                {
                    Produtos.incluir(new Produto(txtProduto, Double.parseDouble(txtValor), txtDescricao, idCategoria));
                }
                catch (Exception erro)
                {
                    erro.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Mercadoria Adicionada com sucesso!");
            }


            txt_Adicionar.setText("");
            txt_Valor.setText("");
            txt_Descricao.setText("");


        }
        else if (e.getSource() == btn_excluirItem)
        {
            this.dispose();
            new ExcluirTela();

//                JOptionPane.showMessageDialog(null ,
//                        "Mercadoria Excluida com sucesso!");
        }

        else if (e.getSource() == btn_alterarItem)
        {
            try {
                this.dispose();
                new AlterarTela();

                txt_Adicionar.setText("");
                txt_Valor.setText("");
                txt_Descricao.setText("");
            }
            catch (Exception erro)
            {
                erro.printStackTrace();
            }



        }

        if (e.getSource() == btn_verItem)
        {
            new VisualizacaoTela();
        }

        if(e.getSource() == btn_venda)
        {
            this.dispose();
            new VendasTela();
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
