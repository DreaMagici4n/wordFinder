import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import javax.swing.JOptionPane;

public class Extractor extends Thread {
    static ArrayDeque<File> explorar = new ArrayDeque<>();
    private File pastaInicial;

    public Extractor(File arquivo) {
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
                    searchWord(diretorioAtual);
                } else {
                    File arquivosDir[] = diretorioAtual.listFiles();

                    for (File arq : arquivosDir) {
                        searchWord(arq);
                    }
                }
            }
        }
    }

    private void searchWord(File arq) {
        if (arq.isDirectory()) {
            explorar.push(arq);
        } else {
            if (arq.getAbsolutePath().endsWith(".txt")) {
                try {
                    FileReader marcaLeitura = new FileReader(arq);

                    BufferedReader bufLeitura = new BufferedReader(marcaLeitura);

                    String linha = null;

                    do {
                        linha = bufLeitura.readLine();
                        if (linha != null) {
                            String[] linhasArray = linha.split(" ");

                            for (String word : linhasArray) {
                                if (word.length() > 2) {
                                    Ocorrencia ocorrencia = new Ocorrencia();
                                    Word wordObject = new Word();

                                    ocorrencia.filePath = arq.getAbsolutePath();
                                    ocorrencia.numOcorrencias = 1;

                                    wordObject.ocorrencias.add(ocorrencia);
                                    wordObject.word = word;

                                    HashTable.insertHash(wordObject);
                                }
                            }
                        }

                    } while (linha != null);

                    bufLeitura.close();

                } catch (FileNotFoundException ex) {
                    System.err.println("Arquivo não existe no dir.");
                } catch (IOException ex) {
                    System.err.println("Seu arquivo esta corrompido");
                }
            }
        }
    }
}