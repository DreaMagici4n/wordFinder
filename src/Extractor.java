import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import javax.swing.JOptionPane;

public class Extractor extends Thread {
    static ArrayDeque<File> explorar = new ArrayDeque<>();
    private String wordToSearch;
    private File pastaInicial;

    public Extractor(String word, File arquivo) {
        this.wordToSearch = word;
        this.pastaInicial = arquivo;
    }

    public void extract() {
        if (pastaInicial == null) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma pasta para o processamento",
                    "Selecione o arquivo", JOptionPane.WARNING_MESSAGE);
        } else {
            explorar.push(pastaInicial);

            while (!explorar.isEmpty()) {

                File diretorioAtual = explorar.pop();

                if (!diretorioAtual.isDirectory()) {
                    searchWord(diretorioAtual, this.wordToSearch);
                } else {
                    File arquivosDir[] = diretorioAtual.listFiles();

                    for (File arq : arquivosDir) {
                        searchWord(arq, this.wordToSearch);
                    }
                }
            }
        }
    }

    private void searchWord(File arq, String wordToSearch) {
        if (arq.isDirectory()) {
            explorar.push(arq);
        } else {
            if (arq.getAbsolutePath().endsWith(".txt")) {
                try {
                    FileReader marcaLeitura = new FileReader(arq);

                    BufferedReader bufLeitura = new BufferedReader(marcaLeitura);

                    String linha = null;

                    int cont = 0;

                    do {
                        linha = bufLeitura.readLine();
                        if (linha != null) {
                            String[] linhasArray = linha.split(" ");

                            for (String word : linhasArray) {
                                if (word.matches("(.*)" + wordToSearch + "(.*)"))
                                    cont++;
                            }
                        }

                    } while (linha != null);

                    bufLeitura.close();
                    
                    Ocorrencia ocorrencia = new Ocorrencia();
                    Word word = new Word();

                    ocorrencia.filePath = arq.getAbsolutePath();
                    ocorrencia.numOcorrencias = cont;

                    word.ocorrencias.add(ocorrencia);
                    word.word = wordToSearch;

                    HashTable.insertHash(word);

                    HashTable.heighestOcurrency = cont > HashTable.heighestOcurrency ? cont : HashTable.heighestOcurrency;

                } catch (FileNotFoundException ex) {
                    System.err.println("Arquivo não existe no dir.");
                } catch (IOException ex) {
                    System.err.println("Seu arquivo esta corrompido");
                }
            }
        }
    }
}