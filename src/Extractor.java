import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Extractor extends Thread {
    private static ArrayDeque<File> explorar = new ArrayDeque<>();
    private File pastaInicial;
    private String[] stopWords = {"de","a","o","que","e","do","da","em","um","para","é","com","não","uma","os","no","se","na","por","mais","as","dos","como","mas","foi","ao","ele","das","tem","à","seu","sua","ou","ser","quando","muito","há","nos","já","está","eu","também","só","pelo","pela","até","isso","ela","entre","era","depois","sem","mesmo","aos","ter","seus","quem","nas","me","esse","eles","estão","você","tinha","foram","essa","num","nem","suas","meu","às","minha","têm","numa","pelos","elas","havia","seja","qual","será","nós","tenho","lhe","deles","essas","esses","pelas","este","fosse","dele","tu","te","vocês","vos","lhes","meus","minhas","teu","tua","teus","tuas","nosso","nossa","nossos","nossas","dela","delas","esta","estes","estas","aquele","aquela","aqueles","aquelas","isto","aquilo","estou","está","estamos","estão","estive","esteve","estivemos","estiveram","estava","estávamos","estavam","estivera","estivéramos","esteja","estejamos","estejam","estivesse","estivéssemos","estivessem","estiver","estivermos","estiverem","hei","há","havemos","hão","houve","houvemos","houveram","houvera","houvéramos","haja","hajamos","hajam","houvesse","houvéssemos","houvessem","houver","houvermos","houverem","houverei","houverá","houveremos","houverão","houveria","houveríamos","houveriam","sou","somos","são","era","éramos","eram","fui","foi","fomos","foram","fora","fôramos","seja","sejamos","sejam","fosse","fôssemos","fossem","for","formos","forem","serei","será","seremos","serão","seria","seríamos","seriam","tenho","tem","temos","tém","tinha","tínhamos", "tinhamos","tinham","tive","teve","tivemos","tiveram","tivera","tivéramos","tenha","tenhamos","tenham","tivesse","tivéssemos", "tivessemos","tivessem","tiver","tivermos","tiverem","terei","terá","teremos","terão","teria","teríamos","teriam"};

    public Extractor(File arquivo)
    {
        this.pastaInicial = arquivo;
    }

    public void extract()
    {
        if (pastaInicial == null)
        {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma pasta para o processamento",
                    "Selecione o arquivo", JOptionPane.WARNING_MESSAGE);
        } else
        {
            explorar.push(pastaInicial);

            while (!explorar.isEmpty())
            {
                File diretorioAtual = explorar.pop();

                if (!diretorioAtual.isDirectory())
                {
                    searchWord(diretorioAtual);
                }
                else 
                {
                    File arquivosDir[] = diretorioAtual.listFiles();

                    for (File arq : arquivosDir)
                    {
                        searchWord(arq);
                    }
                }
            }
        }
    }

    private void searchWord(File arq)
    {
        if (arq.isDirectory())
        {
            explorar.push(arq);
        } else
        {
            if (arq.getAbsolutePath().endsWith(".txt"))
            {
                try
                {
                    FileReader marcaLeitura = new FileReader(arq);

                    BufferedReader bufLeitura = new BufferedReader(marcaLeitura);

                    String linha = null;

                    do
                    {
                        linha = bufLeitura.readLine();
                        if (linha != null)
                        {
                            String[] linhasArray = linha.split(" ");

                            for (String word : linhasArray)
                            {
                                boolean contains = Arrays.stream(stopWords).anyMatch(word::equals);

                                if (!contains)
                                {
                                    Ocorrencia ocorrencia = new Ocorrencia();
                                    Word wordObject = new Word();

                                    ocorrencia.setFilePath(arq.getAbsolutePath());
                                    ocorrencia.setNumOcorrencias(1);

                                    wordObject.addOcorrencia(ocorrencia);;
                                    wordObject.setWord(word);

                                    HashTable.insertHash(wordObject);
                                }
                            }
                        }

                    } while (linha != null);

                    bufLeitura.close();

                }
                catch (FileNotFoundException ex)
                {
                    System.err.println("Arquivo não existe no dir.");
                }
                catch (IOException ex)
                {
                    System.err.println("Seu arquivo esta corrompido");
                }
            }
        }
    }
}