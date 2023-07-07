import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static int HASHMOD = 5;
    private static LinkedList<Word>[] hashTable = new LinkedList[HASHMOD];
    private static List<Ocorrencia> ocorruncyList = new ArrayList<Ocorrencia>();
    public static int heighestOcurrency = 0;

    public HashTable() {
    }

    public static void insertHash(Word object) {
        int id = (int) (100 * Math.random());
        int hashKey = hashFunction(id);
        hashTable[hashKey] = new LinkedList<Word>();
        hashTable[hashKey].add(object);
    }

    private static void sortHash() {
        for (LinkedList<Word> wordList : hashTable) {
            if (wordList == null) {
                System.out.println();
                continue;
            }
            for (Word wordKey : wordList) {
                for (Ocorrencia ocorrencia : wordKey.ocorrencias) {
                    ocorruncyList.add(ocorrencia);
                }
            }
        }

        Collections.sort(ocorruncyList, new HashComparator());
    }

    public static void printHash(String word) {

        sortHash();

        System.out.println("\n\nPalavra pesquisada: " + word);
        
        for (Ocorrencia oc : ocorruncyList) {
            System.out.print(
                            "\nNome do arquivo: " + oc.filePath
                            + "\n" + "Ocorrencias: " + oc.numOcorrencias
                            + "\n"
                        );
        }
    }

    public static int hashFunction(int id) {
        return id % HASHMOD;
    }
}
