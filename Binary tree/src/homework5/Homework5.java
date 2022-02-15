
import java.util.*;

/**
 *
 * @author martin
 */

// Trida Tree reprezentuje binarni vyhledavaci strom, ve kterem pro kazdy uzel n plati
// n.left == null || n.left.contents.less(n.contents) a
// n.right == null || n.right.contents.greater(n.contents).

class Tree<E extends DSAComparable<E>> 
{

    Node<E> root;
    int size = 0;

    // Vrati minimum z tohoto stromu nebo null, pokud je strom prazdny.

    E minimum() 
    {
       return subtreeMin(root);
    }

    // Vrati minimum ze zadaneho podstromu nebo null, pokud je podstrom prazdny.

    E subtreeMin(Node<E> n) 
    {
        if(n == null) return null;
        
        while (n.left != null) 
        {
            n = n.left;
        }
        
        return n.contents;     
    }

    // Vrati maximum z tohoto podstromu nebo null, pokud je podstrom prazdny.

    E maximum() 
    {
        return subtreeMax(root);
    }

    // Vrati maximum ze zadaneho podstromu nebo null, pokud je podstrom prazdny.

    E subtreeMax(Node<E> n) 
    {
        if(n == null) return null;
        
        while (n.right != null) {
            n = n.right;
        }

        return n.contents;
    }

    // Vlozi prvek do stromu (duplicity jsou zakazane)

    void insert(E elem)
    {
        root = insert(elem, root);
    }

    // Projde strom a vrati: // - uzel s hodnotou elem, pokud existuje, // - null pokud uzel s hodnotou elem existuje

    Node<E> find(E elem)
    {
        if(root == null) return null;
        Node<E> n = root;

        while (!n.contents.equal(elem)) 
        {
            if (n.contents.less(elem)) 
            {
                if (n.right != null) 
                {
                    n = n.right;
                } else
                {
                    return null;
                }                
            } else 
            {
                if (n.left != null) 
                {
                    n = n.left;
                } else {
                    return null;
                }
            }
        }
        return n;
    }

    // Vrati true, pokud tento strom obsahuje prvek elem.

    boolean contains(E elem)
    {
        if (find(elem) == null) return false;
        return true;
    }

     // Odstrani vyskyt prvku elem z tohoto stromu.
    void remove(E elem) 
    {
        root = remove(elem, root);
    }
    
    
    private Node<E> remove(DSAComparable x, Node<E> t) 
    {
        if (t = null) {
            return t;
        }
        if (x.less(t.contents)) {
            t.left = remove(x, t.left);
            return t;
        } else if (x.greater(t.contents)) {
            t.right = remove(x, t.right);
            return t;
        } else if (t.left != null && t.right != null) {
            t.contents = subtreeMin(t.right);
            t.right = remove(t.contents, t.right);
            return t;
        } else {
            if (nodeExists(t.left)) {
                t = t.left;
            } else {
                t = t.right;
            }
            return t;
        }
    }

    // Vrati iterator pres cely strom (od nejmensiho po nejvetsi). Metoda remove() nemusí být implementována

    Iterator<E> iterator() 
    {
        Iterator<E> iter = new Iterator<E>() 
        {
            private int currentindex = 0;
            private List<E> array = getInOrder();

            @Override
            public E next() {
                currentindex = currentindex + 1;
                return array.get(currentindex - 1);
            }
            
            @Override
            public boolean hasNext() {
                if (currentindex >= array.size() || (array.size() == 0)) {
                    return false;
                }
                return true;
            }

            @Override
            public void remove() {
            }
        };

        return iter;
    }

    // Vrati korenovy uzel tohoto stromu.

    Node<E> getRootNode() 
    {
        return root;
    }
    
    private Node<E> insert(DSAComparable<E> x, Node<E> t) 
    {
        if (t == null) 
        {
            t = new Node<E>((E) x, null);
            return t;
        } else if (x.less(t.contents)) 
        {
            t.left = insert(x, t.left);
            return t;
        } else if (x.greater(t.contents)) 
        {
            t.right = insert(x, t.right);
            return t;
        } else {
            return t;
        }
    }
    
    private List<E> getInOrder() 
    {
        boolean done = false;
        Stack<Node<E>> stack = new Stack<Node<E>>();
        List<E> list = new ArrayList<E>();
        stack.clear();
        Node<E> current = root;
        while (!done) 
        {
            if (!(current == null)) {
                stack.push(current);
                current = current.left;
            } else if (current != null) 
            {
                if (!(stack.empty())) 
                    current = stack.pop();
                    list.add(current.contents);
                    current = current.right;
                } else {
                    done = true;
                }
            }
        }

        return list;

    }

}