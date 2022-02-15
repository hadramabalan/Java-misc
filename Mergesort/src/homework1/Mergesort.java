


interface Mergesort { 

    // vrati nesetridenou kopii prvni poloviny (horni celou cast) pole array

    int[] getFirstHalfOf(int[] array); 
    // vrati nesetridenou kopii druhe poloviny (dolni celou cast) pole array

    int[]getSecondHalfOf(int[] array); 

    // slije prvky v firstHalf a secondHalf do jednoho pole a vrati ho

    int[]merge(int[] firstHalf, int[] secondHalf); 

    // vrati setridenou kopii pole array

    int[]mergesort(int[] array); 

}