import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static List<Word> hashTable = new LinkedList<>();

    public HashTable() {
    }

    public static void insertHash(Word object) {
        hashTable.add(object);
    }

    public static void printHash() {
        for (Word wordKey : hashTable) {
            if (wordKey == null) {
                System.out.println();
                continue;
            }
            for (Ocorrencia ocorrencia : wordKey.ocorrencias) {
                System.out.print(
                    "\n"
                    + "Palavra pesquisada: " + wordKey.word 
                    + "\nNome do arquivo: " + ocorrencia.filePath 
                    + "\n" + "Ocorrencias: " + ocorrencia.numOcorrencias);
            }
            System.out.println();
        }

    }
}
