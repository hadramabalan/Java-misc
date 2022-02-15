import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

class DSAHashTable<K,V> {

    int size;
    int countPairs;
    Set<Pair<K, V> >[] table;

    
    // Vytvori prazdnou instanci DSAHashTable, delka vnitrniho pole je nastavena na size, obsah vnitrniho pole je inicializovan na prazdne mnoziny.

    DSAHashTable(int size) 
    {
        table =  (HashSet<Pair<K, V> >[]) new HashSet<?>[size];
        this.size = size;
        countPairs = 0;

        // Initialize the table
        int i = 0;
        while(i < size)
        {
            table[i++] = new HashSet<Pair<K, V>>();
        }
    }

    
    // Ulozi dvojici (key, value) do rozptylovaci tabulky. Pokud uz v tabulce je jina dvojice se stejnym klicem, je smazana. 
    // Klic ani hodnota nesmi byt null. Pokud by pocet dvojic v tabulce po vykonani put mel vzrust nad dvojnasobek delky vnitrniho pole,
    // vnitrni pole zdvojnasobi.
    
    void put(K key, V value) 
    {
        if(!(key == null) && !(value == null))
        {
            if(!(get(key) == null))
            {
                remove(key);
            }

            Pair<K, V> tempPair = new Pair<K, V>(key, value);
            table[getIndexOf(key)].add(tempPair);
            countPairs++;

            
            if(!isBigEnough()) 
            {
                resize(2*size);
            }
        }
    }


    // Vrati hodnotu asociovanou s danym klicem nebo null, pokud dany klic v tabulce neni.

    V get(K key) 
    {
        V val = null;

        if(!(key == null))
        {
            for(Pair<K, V> currentPair : table[getIndexOf(key)])
            {
                if(currentPair.key.equals(key))
                {
                    val = currentPair.value;
                    break;
                }
            }
        }

        return val;
    }

    
    
    // Smaze dvojici s danym klicem. Pokud v tabulce dany klic neni, nedela nic.

    void remove(K key) 
    {
        Iterator temp;
        temp = table[getIndexOf(key)].iterator();

        if(!(key == null)){
            while (temp.hasNext())
            {
                Pair<K,V> nextPair = (Pair<K, V>) temp.next();
                if(nextPair.key.equals(key))
                {
                    table[getIndexOf(key)].remove(nextPair);
                    countPairs--;
                    break;
                }
            }
        }
    }

    // Vrati vnitrni pole.

    Set<Pair<K,V>>[] getArray() 
    {
        return table;
    }

    // Pro dany klic vrati index v poli. Jako hashovaci funkce se pouzije key.hashCode.

    int getIndexOf(K key)
    {
       return Math.abs(key.hashCode())%size;
    }

    // Pokud je pocet prvku mensi nebo roven dvojnasobku delky vnitrniho pole, vrati true, jinak vrati false.

    boolean isBigEnough() 
    {
        return countPairs <= 2*size;
    }

    // Zmeni delku vnitrniho pole, nainicializuje jej prazdnymi mnozinami a zkopiruje do nej vsechny dvojice.

    void resize(int newSize) 
    {
        Iterator temp;
        temp = iterator();

        table = (HashSet<Pair<K, V> >[]) new HashSet<?>[newSize];
        countPairs =  0;
        size = newSize;

        int i = 0;
        while(i < size)
        {
            table[i++] = new HashSet<Pair<K, V>>();
        }

        while (temp.hasNext()){
            Pair<K, V> pair = (Pair<K, V>) temp.next();
            put(pair.key, pair.value);
        }
    }

    // Vrati iterator pres vsechny dvojice v tabulce. Iterator nemusi mit implementovanou metodu remove.

    Iterator<Pair<K,V>> iterator() 
    {
        return new Iterator<Pair<K, V>>() {
            Set<Pair<K, V>>[] tableTwo = table;
            int tableTwoSize = size;
            int i = 0;
            Iterator<Pair<K, V>> temp = tableTwo[i].iterator();

            public boolean hasNext() {
                if(temp.hasNext()){
                    return true;
                }
                i++;

                if(!(i < tableTwoSize)){
                    return false;

                }else{
                    temp = tableTwo[i].iterator();
                    return hasNext();
                }

            }

            public Pair<K, V> next() {
                return temp.next();
            }
        };
    }

}