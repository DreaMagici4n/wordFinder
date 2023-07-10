import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static int HASHMOD = 5;
    private static LinkedList<Word>[] hashTable = new LinkedList[HASHMOD];
    private static List<Ocorrencia> ocorruncyList = new ArrayList<Ocorrencia>();

    public HashTable() {
    }

    public static void insertHash(Word object) {
        int hashValue = hashFunction(object.word);

        if(hashTable[hashValue] == null) {
            hashTable[hashValue] = new LinkedList<Word>();
            hashTable[hashValue].add(object);
        }
        else{ //incremeneta as ocorrencias caso seja a mesma palavra
            Word wordObject = searchHash(object.word);

            if ( wordObject != null) { // Verificar se esta no mesmo arquivo

                for (Ocorrencia oc : wordObject.ocorrencias) {
                    if(oc.filePath.equals(object.ocorrencias.get(0).filePath)) { //se estiver no mesmo arquivo
                        oc.numOcorrencias ++;
                        return;
                    }
                }
                
                wordObject.ocorrencias.add(object.ocorrencias.get(0));
                return;
            }

            hashTable[hashValue].add(object);
            return;
        }
    }

    private static Word searchHash(String word){
        int hashValue = hashFunction(word);
       
        if(hashTable[hashValue] == null){
          System.out.println("o elemento não se encontra");
          return null;
        }
       
        for(Word wordObject : hashTable[hashValue]){
            if( wordObject.word.equals(word)){
              return wordObject;
            }
        }
        return null;
      }

    private static void sortHash(Word wordObject) {
        for (Ocorrencia oc : wordObject.ocorrencias) {
            ocorruncyList.add(oc);
        }

        Collections.sort(ocorruncyList, new HashComparator());
    } // OBJETO DA CLASSE WORD. MUDAR

    public static void printHash(String word) {
        if (word != null) {
            sortHash(searchHash(word));

            System.out.println("\n\nPalavra pesquisada: " + word);
            
            for (Ocorrencia oc : ocorruncyList) {
                System.out.print(
                                "\nNome do arquivo: " + oc.filePath
                                + "\n" + "Ocorrencias: " + oc.numOcorrencias
                                + "\n"
                            );
            }
        }
    }

    public static int hashFunction(String word) {
        int BASE = 256; // BASE = 128 (ASCII) ou 256 (ASCII Estendido)
        int id = 0;

        for(int i=0; i<word.length(); i++) {
            id = (id * BASE + (int)word.charAt(i)) % HASHMOD;
        }
        return id;
    }
}