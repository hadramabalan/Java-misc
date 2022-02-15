/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

/**
 *
 * @author martin
 */
/*
 * Naimplementujte třídu Homework3 implementující rozhraní HeapStorage a třídu Heap. Odevzdejte pouze kód těchto dvou tříd.
 */

interface DSAComparable<E> { 

    boolean less(E other); //Vrati true, pokud je this (ostre) mensi nez other, false jinak. 

    E getLeastElement(); // Vrati nejmensi prvek ze vsech prvku typu E (tzn. prvek, ktery je mensi nez jakykoli jiny prvek typu E). 
			 // Vracena hodnota nezavisi na this, jen na E. 

    E getGreatestElement(); // Vrati nejvetsi prvek ze vsech prvku typu E (tzn. prvek, ktery je vetsi nez jakykoli jiny prvek typu E).
			    // Vracena hodnota nezavisi na this, jen na E. 

} 

// Rozhrani HeapStorage reprezentuje pole, do ktereho si halda uklada svoje prvky.

interface HeapStorage<E extends DSAComparable<E>> { 

    int getSize(); // Vrati aktualni velikost HeapStorage. 

    boolean isEmpty(); // Vrati true, pokud je aktualni velikost HeapStorage nulova. 

    E getElement (int index); // Vrati prvek na indexu index (1 <= index <= getSize()). Pokud je index mensi nebo roven nule, vrati 
   			      // nejvetsi prvek. Pokud je index vetsi nez velikost pole, vrati nejmensi prvek. 
			      // V obou pripadech muzete predpokladat, ze v HeapStorage je alespon jeden prvek. 

    void swap (int index, int index2); // Prohodi prvky na indexech index a index2 (1 <= index, index2 <= getSize()). 

    E extractLast(); // Odstrani posledni prvek (tzn. snizi velikost teto HeapStorage) a vrati jeho hodnotu. 

    int insertLast (E element); // Vlozi prvek a vrati jeho index. Muzete predpokladat, ze ve spodnim poli je dost mista. 

} 

class Homework3<E extends DSAComparable<E>> implements HeapStorage<E> 
{ 

    private E[] storage;
    int size;
    int maxsize;
                
    // Vytvori novy objekt HeapStorage nad polem elements, jeho velikost je stejna jako delka pole.     
    Homework3(E[] elements) { 
            this.storage = elements;
            this.size = elements.length;
            this.maxsize = this.size;
    } 

    // metody 

    @Override
    public int getSize() 
    {
         return size;
    }

    @Override
    public boolean isEmpty() 
    {
        return (size == 0);
    }

    @Override
    public E getElement(int index) 
    {
        return storage[index];
    }

    @Override
    public void swap(int index, int index2) 
    {
        E temp = storage[index];
        storage[index] = storage[index2];
        storage[index2] = temp;
    }

    @Override
    public E extractLast() 
    {
        if(size >= 1 && size <= maxsize)
        {
            E temp = storage[--size];
            return temp;
        }
        return null;
    }

    @Override
    public int insertLast(E element) 
    {
        storage[size++] = element;
        return size-1;
    }

} 

// Trida Heap reprezentuje haldu (s maximem ve vrcholu). 

class Heap<E extends DSAComparable<E>> 
{ 
    int heapSize;
    HeapStorage<E> storage; 

    // Vytvori haldu nad danym HeapStorage (tzn. zavola algoritmus build heap). 

    Heap(HeapStorage<E> storage) 
    { 
            this.storage = storage;
            this.heapSize = storage.getSize();
            for(int i = (this.heapSize/2 - 1); i>0; i--) 
            {
                heapify(i);
            }
        

    } 

    // Zavola algoritmus heapify nad uzlem na indexu index. 

    void heapify(int index) 
    { 
        int max = index;
        int left = 2*index + 1;
        int right = 2*index + 2;
        
        if (left <  heapSize && storage.getElement(max).less(storage.getElement(left)))
        {
            max = left;
        }
        
        if (right < heapSize && storage.getElement(max).less(storage.getElement(right)))
        {
            max = right;
        }
        
        if(!(index == max))
        {
            storage.swap(index, max);
            
            heapify(max);
        }
    } 

    // Vlozi do haldy novy prvek. Muzete predpokladat, ze v poli uvnitr HeapStorage na nej misto je. 

    void insert(E element) 
    { 
        heapSize++;
        storage.insertLast(element);
        
        for(int i = (this.heapSize/2 - 1); i>0; i--) 
            {
                heapify(i);
            }

    } 

    // Odstrani a vrati z haldy maximalni prvek. 

    E extractMax() 
    { 
        this.storage.swap(0, --heapSize);
        E temp = storage.extractLast();
        heapify(0);
        return temp;
    }

    // Vrati true, pokud je halda prazdna. 

    boolean isEmpty() 
    { 
      return storage.getSize() == 0;    
    } 
    
    // Pomoci algoritmu trideni haldou vzestupne setridi pole array. 
    static <E extends DSAComparable<E>> void heapsort(E[] array)
    { 
        HeapStorage storage = new Homework3(array);
        Heap heap = new Heap(storage);
        
        while(heap.heapSize>0)
        {
            heap.extractMax();   
        }
    }
}
