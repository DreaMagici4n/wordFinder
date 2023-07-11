import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static int HASHMOD = 5;
    private static LinkedList<Word>[] hashTable = new LinkedList[HASHMOD];
    private static List<Ocorrencia> ocorruncyList = new ArrayList<Ocorrencia>();

    public HashTable() 
    {

    }

    public static void insertHash(Word object)
    {
        String wordToSearch = object.getWord();

        int hashValue = hashFunction(wordToSearch);

        if(hashTable[hashValue] == null)
        {
            hashTable[hashValue] = new LinkedList<Word>();
            hashTable[hashValue].add(object);
        }
        else
        { //incremeneta as ocorrencias caso seja a mesma palavra
            Word wordObject = searchHash(wordToSearch);

            if ( wordObject != null)
            { // Verificar se esta no mesmo arquivo
                Ocorrencia objectFirstOc = object.getOcorrencias().get(0);
                String objectFilePath = objectFirstOc.getFilePath();

                for (Ocorrencia oc : wordObject.getOcorrencias())
                {
                    String ocFilePath = oc.getFilePath();

                    if(ocFilePath.equals(objectFilePath))
                    { //se estiver no mesmo arquivo
                        oc.incremmentOcorrencia();
                        return;
                    }
                }

                wordObject.addOcorrencia(objectFirstOc);
                return;
            }

            hashTable[hashValue].add(object);
            return;
        }
    }

    private static Word searchHash(String word)
    {
        int hashValue = hashFunction(word);
       
        if(hashTable[hashValue] == null)
        {
          System.out.println("o elemento não se encontra");
          return null;
        }
       
        for(Word wordObject : hashTable[hashValue])
        {
            String objectWord = wordObject.getWord();

            if(objectWord.equals(word))
            {
              return wordObject;
            }
        }
        return null;
    }

    private static void sortHash(Word wordObject)
    {
        for (Ocorrencia oc : wordObject.getOcorrencias())
        {
            ocorruncyList.add(oc);
        }

        Collections.sort(ocorruncyList, new HashComparator());
    }

    public static void printHash(String word)
    {
        if (word != null)
        {
            Word objectWord = searchHash(word);

            if (objectWord != null)
            {
                sortHash(objectWord);

                System.out.println("\n\nPalavra pesquisada: " + word);
                
                for (Ocorrencia oc : ocorruncyList)
                {
                    System.out.print(
                                    "\nNome do arquivo: " + oc.getFilePath()
                                    + "\n" + "Ocorrencias: " + oc.getNumOcorrencia()
                                    + "\n"
                                );
                }
                return;
            }

            System.out.println("\nNao ha nenhuma ocorrencia com essa palavra");
            
        }
    }

    public static int hashFunction(String word)
    {
        int BASE = 256;
        int id = 0;

        for(int i=0; i<word.length(); i++)
        {
            id = (id * BASE + (int)word.charAt(i)) % HASHMOD;
        }
        return id;
    }
}