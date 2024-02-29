import bd.core.MeuResultSet;
import bd.daos.Categorias; // Importa a classe 'Categorias' do pacote 'bd.daos'.
import bd.daos.Produtos; // Importa a classe 'Produtos' do pacote 'bd.dao's.

import java.awt.*; // Importa o pacote j'ava.awt' para componentes gráficos.
import java.awt.event.*; // Importa o pacote 'java.awt.event' para lidar com eventos.
import javax.swing.*; // Importa o pacote 'javax.swing' para componentes de interface gráfica.


// Declaração da classe 'VisualizacaoTela' que estende 'JFrame' e implementa 
// as interfaces 'MouseListener', 'MouseMotionListener' e 'ActionListener'.
public class VisualizacaoTela extends JFrame implements MouseListener, MouseMotionListener, ActionListener 
{
    // Declaração dos rótulos e da área de texto.
    JLabel id = new JLabel(""), // Rótulo para o id.
            nome = new JLabel(""), // Rótulo para o nome.
            valor = new JLabel(""), // Rótulo para o valor.
            descricao = new JLabel(""), // Rótulo para a descrição.
            idCategoria = new JLabel(""); // Rótulo para o id da categoria.
    JTextArea txt_result = new JTextArea(100, 5); // Área de texto para exibir os resultados.

    // Construtor da classe 'VisualizacaoTela'.
    public VisualizacaoTela()
    {
        super("Visualização dos produtos"); // Chama o construtor da superclasse 'JFrame' com o título da janela.
        this.setResizable(false); // Impede o redimensionamento da janela.

        JPanel painel = new JPanel(new GridLayout(1, 1)); // Cria um painel com layout de grade.
        painel.add(txt_result); // Adiciona a área de texto ao painel.
        super.add(painel); // Adiciona o painel à janela.

        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Adiciona uma borda vazia ao redor do painel.

        // Tenta obter os produtos do banco de dados e exibi-los na área de texto
        try {
            MeuResultSet resultado = Produtos.getProduto(); // Obtém o resultado da consulta ao banco de dados

            String dados = ""; // Inicializa uma string para armazenar os dados.
            dados += "||  " + "idProduto" + "  |  " + "Nome" + "  |  " + "Preco" + "  |  " + "Descricao" + "  |  " + "idCategoria" + "  |  " + "tipo de categoria: " + "  ||" + "\n" + "\n"; // Cria o cabeçalho da tabela
            
            // Itera sobre o resultado e adiciona os dados à string.
            while (resultado.next()) {
                dados += resultado.getInt("idProduto") + " | " +
                        resultado.getString("nome") + " | " +
                        resultado.getDouble("preco") + " | " +
                        resultado.getString("descricao") + " | " +
                        resultado.getInt("idCategoria") + " | " +
                        Categorias.getCategoria(resultado.getInt("idCategoria")).getString("tipoCategoria") + "\n"; // Obtém o tipo de categoria e adiciona à string
            }
            txt_result.setText(dados); // Define o texto da área de texto com os dados obtidos
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o erro, caso ocorra
        }

        txt_result.setEditable(false); // Torna a área de texto não editável

        this.setLocation(400, 190); // Define a posição inicial da janela
        this.setSize(590, 520); // Define o tamanho da janela
        this.setVisible(true); // Torna a janela visível
    }

    // Métodos de ação para os botões (não utilizados nesta classe)
    @Override
    public void actionPerformed(ActionEvent e) {}

    // Métodos de mouse (não utilizados, mantidos devido à implementação 
    // das interfaces 'MouseListener' e 'MouseMotionListener'.
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
