import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class App {
    public static void main(String args[]) {

        File arquivo = selecionaDiretorioRaiz();
        String word = JOptionPane.showInputDialog("Insira a palavra a ser pesquisada");

        Extractor extractor = new Extractor(arquivo);

        extractor.extract();

        HashTable.printHash(word);
    }

    public static File selecionaDiretorioRaiz() {
        JFileChooser janelaSelecao = new JFileChooser(".");

        janelaSelecao.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File arquivo) {
                return arquivo.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Diretório";
            }
        });

        janelaSelecao.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int acao = janelaSelecao.showOpenDialog(null);

        if (acao == JFileChooser.APPROVE_OPTION) {
            return janelaSelecao.getSelectedFile();
        } else {
            return null;
        }
    }
}