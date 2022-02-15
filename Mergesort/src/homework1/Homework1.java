/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author martin
 */

public class Homework1 implements Mergesort {
    /**
     * @param args the command line arguments
     */
    // vrati nesetridenou kopii prvni poloviny (horni celou cast) pole array

    int[] getFirstHalfOf(int[] array)
    {
        int firstArrSize = (array.length+1)/2;
        int arr[] = new int [firstArrSize];
        for (int i=0; i<firstArrSize; ++i)
        { 
            arr[i] = array[i]; 
        }
        return arr;
    }
    
    int[] getSecondHalfOf(int[] array)
    {
        int firstArrSize = (array.length+1)/2;
        int secondArrSize = array.length - firstArrSize;
        int arr[] = new int [secondArrSize];
        for (int i=0; i<secondArrSize; ++i) 
        {
            arr[i] = array[i + firstArrSize]; 
        }
        return arr;
    }

    int[]merge(int[] firstHalf, int[] secondHalf)
    {
        int[] finalArr = new int [(firstHalf.length + secondHalf.length)];
        int firstCounter = 0;
        int secondCounter = 0;
        for (int i = 0; i < finalArr.length; i++)
        {
            if (firstHalf[firstCounter] <= secondHalf[secondCounter])
            {
                finalArr[i] = firstHalf[firstCounter++];
                if (firstCounter == firstHalf.length)
                {
                    for (int j = i+1; j < finalArr.length; j++)
                    {
                        finalArr[j] = secondHalf[secondCounter++];
                    }
                    break;
                }
            }
            else // second number is smaller
            {
                finalArr[i] = secondHalf[secondCounter++];
                if (secondCounter == secondHalf.length)
                {
                    for (int j = i+1; j < finalArr.length; j++)
                    {
                        finalArr[j] = firstHalf[firstCounter++];
                    }
                    break;
                }
            }
        }
        return finalArr;
    }
    
    // vrati setridenou kopii pole array

    int[]mergesort(int[] array)
    {
        if(array.length > 1)
        {
        int [] firstHalf = getFirstHalfOf(array);
        int [] secondHalf = getSecondHalfOf(array);
        
        firstHalf = mergesort(firstHalf);
        secondHalf = mergesort(secondHalf);
            
        array = merge(firstHalf, secondHalf);    
        }
        return array;
    }
    
    public static void main(String[] args) {
    }  
}
