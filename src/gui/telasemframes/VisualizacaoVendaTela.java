package gui.telasemframes;
import bd.core.MeuResultSet; // Importa a classe 'MeuResultSet' do pacote 'bd.core'.
import bd.daos.Vendas; // Importa a classe 'Vendas' do pacote 'bd.daos'.
import java.awt.*; // Importa o pacote 'java.awt' para componentes gráficos.
import java.awt.event.*; // Importa o pacote 'java.awt.event' para lidar com eventos.
import javax.swing.*; // Importa o pacote 'javax.swing' para componentes de interface gráfica.


// Declaração da classe 'VisualizacaoVendaTela' que estende 'JFrame' e implementa as interfaces 
// 'MouseListener', 'MouseMotionListener' e 'ActionListener'.
public class VisualizacaoVendaTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{
    // Declaração dos rótulos e da área de texto.
    JLabel id = new JLabel(""), // Rótulo para o id.
            idProduto = new JLabel(""), // Rótulo para o id do produto.
            qtd = new JLabel(""), // Rótulo para a quantidade.
            precoTotal = new JLabel(""); // Rótulo para o preço total.

    JTextArea txt_result = new JTextArea(100, 5); // Área de texto para exibir os resultados.


    // Construtor da classe VisualizacaoVendaTela
    public VisualizacaoVendaTela()
    {
         super("Visualização das Vendas"); // Chama o construtor da superclasse 'JFrame' com o título da janela.
        this.setResizable(false); // Impede o redimensionamento da janela.

        JPanel painel = new JPanel(new GridLayout(1, 1)); // Cria um painel com layout de grade.
        painel.add(txt_result); // Adiciona a área de texto ao painel.
        super.add(painel); // Adiciona o painel à janela.

        // Adiciona uma borda vazia ao redor do painel.
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

       // Inicializa uma string vazia para armazenar os dados.
        String dados = "";
        try {
            MeuResultSet resultado = Vendas.getVenda(); // Obtém o resultado da consulta ao banco de dados.
            dados += "||  " + "idPedido" + "  |  " + "idProduto" + "  |  " + "qtd" + "  |  " + "Preco Total:"  + "  ||" + "\n" + "\n";

            // Itera sobre o resultado e adiciona os dados à string.
            while(resultado.next()) {
                dados += resultado.getInt("idPedido") + " | " +
                        resultado.getInt("idProduto") + " | " +
                        resultado.getInt("qtd") + " | " +

                        // Adiciona os dados de cada venda à string.
                        resultado.getDouble("precoTotal") + "\n"; 
            }
            txt_result.setText(dados);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        txt_result.setEditable(false); // Torna a área de texto não editável.

        this.setLocation(400, 190); // Define a posição inicial da janela.
        this.setSize(590, 520); // Define o tamanho da janela.
        this.setVisible(true); // Torna a janela visível.
    }

    // Métodos de mouse não utilizados, mantidos devido à implementação das interfaces 
    // 'MouseListener' e 'MouseMotionListener'.
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
