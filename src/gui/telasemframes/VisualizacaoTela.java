package gui.telasemframes;
import bd.core.MeuResultSet; // Importa a classe 'MeuResultSet' do pacote 'bd.core'.
import bd.daos.Categorias; // Importa a classe 'Categorias' do pacote 'bd.daos'.
import bd.daos.Produtos; // Importa a classe 'Produtos' do pacote 'bd.daos'.
import java.awt.*; // Importa o pacote j'ava.awt' para componentes gráficos.
import java.awt.event.*; // Importa o pacote 'java.awt.event' para lidar com eventos.
import javax.swing.*; // Importa o pacote 'javax.swing' para componentes de interface gráfica.


// Declaração da classe 'VisualizacaoTela' que estende 'JFrame' e implementa 
// as interfaces 'MouseListener', 'MouseMotionListener' e 'ActionListener'.
public class VisualizacaoTela  extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{
    JLabel id = new JLabel(""), // Rótulo para o id.
            nome = new JLabel(""), // Rótulo para o nome.
            valor = new JLabel(""), // Rótulo para o valor.
            descricao = new JLabel(""), // Rótulo para a descrição.
            idCategoria = new JLabel(""); // Rótulo para o id da categoria.

            JTextArea txt_result = new JTextArea(100, 5); // Área de texto para exibir os resultados.


    // Construtor da classe 'VisualizacaoTela'.        
    public VisualizacaoTela()
    {
        // Chama o construtor da superclasse 'JFrame' com o título da janela.
        super("Visualizacao dos produtos");

        // Impede o redimensionamento da janela.
        this.setResizable(false);

        JPanel painel = new JPanel(new GridLayout(1,1));  // Cria um painel com layout de grade.
        painel.add(txt_result); // Adiciona a área de texto ao painel.
        super.add(painel); // Adiciona o painel à janela.


        // Adiciona uma borda vazia ao redor do painel.
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));


        try 
        {   // Tenta obter os produtos do banco de dados e exibi-los na área de texto.
            MeuResultSet resultado = Produtos.getProduto();



            String dados = "";  // Inicializa uma string para armazenar os dados.
            dados += "||  " + "idProduto" + "  |  " + "Nome" + "  |  " + "Preco" + "  |  " + "Descricao" + "  |  " + "idCategoria" + "  |  " + "tipo de categoria: " + "  ||" + "\n" + "\n";
            
            // Itera sobre o resultado e adiciona os dados à string.
            while(resultado.next()) 
            {
                dados += resultado.getInt("idProduto") + " | " +
                        resultado.getString("nome") + " | " +
                        resultado.getDouble("preco") + " | " +
                        resultado.getString("descricao") + " | " +
                        resultado.getInt("idCategoria") + " | " +

                       // Obtém o tipo de categoria do produto e adiciona à string. 
                       Categorias.getCategoria(resultado.getInt("idCategoria")).getString("tipoCategoria")+ "\n";
            }
            // Define o texto da área de texto com os dados obtidos.
            txt_result.setText(dados);

        } 
        catch (Exception e) { e.printStackTrace(); }

        // Torna a área de texto não editável.
        txt_result.setEditable(false);


        this.setLocation(400, 190); // Define a posição inicial da janela.
        this.setSize(590, 520); // Define o tamanho da janela.
        this.setVisible(true); // Torna a janela visível.
    }


    // Métodos de mouse não utilizados, mantidos devido à implementação 
    // das interfaces 'MouseListener' e 'MouseMotionListener'.
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
