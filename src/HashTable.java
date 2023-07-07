import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static int HASHMOD = 5;
    private static LinkedList<Word>[] hashTable = new LinkedList[HASHMOD];
    private static List<Word> hashList = new ArrayList<Word>();
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
                hashList.add(wordKey);
            }
        }

        Collections.sort(hashList, new HashComparator());
    }

    public static void printHash() {

        sortHash();

        for (Word word : hashList) {
            System.out.print(
                            "\n\n"
                            + "Palavra pesquisada: " + word.word
                            + "\nNome do arquivo: " + word.ocorrencias.get(0).filePath
                            + "\n" + "Ocorrencias: " + word.ocorrencias.get(0).numOcorrencias
                            + "\n"
                        );
        }
    }

    public static int hashFunction(int id) {
        return id % HASHMOD;
    }
}
