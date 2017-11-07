/**
 * A class that implement the bottom-up heap building algorithm, that operates on an array of numbers and treats
 * the array as a heap. It also implements heapsort algorithm using the bottom-up heap building algorithm.
 * Created by JJ Lim and Daniel Lim on 11/2/17.
 */
public class BottomUpHeap {
    /**
     * Constructor of this class
     */
    public BottomUpHeap () {
    }

    public int leftChildInd(int nodeInd) {
        return 2 * nodeInd + 1;
    }

    public int rightChildInd(int nodeInd) {
        return 2 * nodeInd + 2;
    }

    public int parentInd(int nodeInd) {
        return (int)Math.floor((nodeInd - 1) / 2.0);
    }

    /**
     * Construct a heap from elements of a given array by the bottom-up algorithm
     * @param array to be heapified using bottom-up algorithm
     * @param startInd the starting index of bottom-up heapification
     * @param endInd the ending index of bottom-up heapification
     * @return array that is heapified from startInd to endInd
     */
    private int[] bottomUpHeapify(int [] array, int startInd, int endInd) {
        int lastParentInd = (int)Math.floor((endInd - startInd)/2.0);

        for (int i = lastParentInd; i >= startInd; i--) {
            int currInd = i;
            int currVal = array[currInd];
            boolean isHeap = false;

            while (!isHeap && 2 * (currInd-startInd) < endInd - startInd) {
                int childInd = 2 * (currInd-startInd) + 1 + startInd;
                if (childInd + 1 <= endInd && array[childInd] < array[childInd+1]) {
                    childInd++; //right child is bigger
                }

                if (currVal >= array[childInd]) {
                    isHeap = true;
                }
                else {
                    array[currInd] = array[childInd];
                    currInd = childInd;
                }
            }
            array[currInd] = currVal;
        }
        return array;
    }

    /**
     * Sort an array of integers using heap-sort algorithm
     * @param array of integers
     * @return sorted array
     */
    private int[] heapSort(int [] array) {
        for (int n = array.length-1; n >= 0; n--) {
            bottomUpHeapify(array, 0, n);
            int max = array[0];
            array[0] = array[n];
            array[n] = max;
        }
        return array;
    }

    public static void main(String [] args) {
        int [] array1 = {1, 2, 6, 1, 7,2 ,8, 13, 634, 2, -1, 0};
        BottomUpHeap heap = new BottomUpHeap();

        //test the bottomUpHeapify()
        System.out.print("Heapify [0, len-1]: ");
        int [] heapifiedArray = heap.bottomUpHeapify(array1, 0, array1.length-1);
        for (int i = 0; i < heapifiedArray.length; i++) {
            System.out.print(heapifiedArray[i] + " ");
        }

        System.out.println();

        //test the bottomUpHeapify() with a restricted range
        int [] array2 = {1, 2, 6, 1, 7, 2 ,8, 13, 634, 2, -1, 0};
        heapifiedArray = heap.bottomUpHeapify(array2, 1, array2.length-3);
        System.out.print("Heapify [1, len-3]: ");
        for (int i = 0; i < heapifiedArray.length; i++) {
            System.out.print(heapifiedArray[i] + " ");
        }

        System.out.println();

        //test heapSort

//        *** Run-time Test for Bottom-up Heapification ***
//        A reversely ordered array with 10000000 elements: 36
//        An ordered array with 10000000 elements: 121
//        A reversely ordered array with 20000000 elements: 65
//        An ordered array with 20000000 elements: 180
//        A reversely ordered array with 40000000 elements: 115
//        An ordered array with 40000000 elements: 528

        int [] array3 = {1, 2, 6, 1, 7, 2 ,8, 13, 634, 2, -1, 0};
        int [] sortedArray = heap.heapSort(array3);
        System.out.print("HeapSort: ");
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("*** Run-time Test for Bottom-up Heapification ***");

        int [] reverse10 = new int [10000000];
        for (int i = 0; i < reverse10.length; i++) {
            reverse10[i] = 10000000-i;
        }
        long startTime1 = System.currentTimeMillis();
        heap.bottomUpHeapify(reverse10, 0, reverse10.length-1);
        long endTime1 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 10000000 elements: " + (endTime1 - startTime1));

        int [] ordered10 = new int [10000000];
        for (int i = 0; i < ordered10.length; i++) {
            ordered10[i] = i;
        }
        long startTime2 = System.currentTimeMillis();
        heap.bottomUpHeapify(ordered10, 0, ordered10.length-1);
        long endTime2 = System.currentTimeMillis();
        System.out.println("An ordered array with 10000000 elements: " + (endTime2 - startTime2));

        int [] reverse20 = new int [20000000];
        for (int i = 0; i < reverse20.length; i++) {
            reverse20[i] = 20000000-i;
        }
        long startTime3 = System.currentTimeMillis();
        heap.bottomUpHeapify(reverse20, 0, reverse20.length-1);
        long endTime3 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 20000000 elements: " + (endTime3 - startTime3));

        int [] ordered20 = new int [20000000];
        for (int i = 0; i < ordered20.length; i++) {
            ordered20[i] = i;
        }
        long startTime4 = System.currentTimeMillis();
        heap.bottomUpHeapify(ordered20, 0, ordered20.length-1);
        long endTime4 = System.currentTimeMillis();
        System.out.println("An ordered array with 20000000 elements: " + (endTime4 - startTime4));

        int [] reverse30 = new int [40000000];
        for (int i = 0; i < reverse30.length; i++) {
            reverse30[i] = 400000000-i;
        }
        long startTime5 = System.currentTimeMillis();
        heap.bottomUpHeapify(reverse30, 0, reverse30.length-1);
        long endTime5 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 40000000 elements: " + (endTime5 - startTime5));

        int [] ordered30 = new int [40000000];
        for (int i = 0; i < ordered30.length; i++) {
            ordered30[i] = i;
        }
        long startTime6 = System.currentTimeMillis();
        heap.bottomUpHeapify(ordered30, 0, ordered30.length-1);
        long endTime6 = System.currentTimeMillis();
        System.out.println("An ordered array with 40000000 elements: " + (endTime6 - startTime6));

        System.out.println();
        System.out.println("*** Run-time Test for Heapsort ***");

        //test time efficiency
        //(40000*log2(40000))/(20000*log2(20000)) == 2.14
        //(80000*log2(80000))/(40000*log2(40000)) == 2.13

        int [] reverse1 = new int [20000];
        for (int i = 0; i < reverse1.length; i++) {
            reverse1[i] = 20000-i;
        }
        long startTime7 = System.currentTimeMillis();
        heap.heapSort(reverse1);
        long endTime7 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 20000 elements: " + (endTime7 - startTime7));

        int [] ordered1 = new int [20000];
        for (int i = 0; i < ordered1.length; i++) {
            ordered1[i] = i;
        }
        long startTime8 = System.currentTimeMillis();
        heap.heapSort(ordered1);
        long endTime8 = System.currentTimeMillis();
        System.out.println("An ordered array with 20000 elements: " + (endTime8 - startTime8));

        int [] reverse2 = new int [40000];
        for (int i = 0; i < reverse2.length; i++) {
            reverse2[i] = 40000-i;
        }
        long startTime9 = System.currentTimeMillis();
        heap.heapSort(reverse2);
        long endTime9 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 40000 elements: " + (endTime9 - startTime9));

        int [] ordered2 = new int [40000];
        for (int i = 0; i < ordered2.length; i++) {
            ordered2[i] = i;
        }
        long startTime10 = System.currentTimeMillis();
        heap.heapSort(ordered2);
        long endTime10 = System.currentTimeMillis();
        System.out.println("An ordered array with 40000 elements: " + (endTime10 - startTime10));

        int [] reverse3 = new int [80000];
        for (int i = 0; i < reverse3.length; i++) {
            reverse3[i] = 80000-i;
        }
        long startTime11 = System.currentTimeMillis();
        heap.heapSort(reverse3);
        long endTime11 = System.currentTimeMillis();
        System.out.println("A reversely ordered array with 80000 elements: " + (endTime11 - startTime11));

        int [] ordered3 = new int [80000];
        for (int i = 0; i < ordered3.length; i++) {
            ordered3[i] = i;
        }
        long startTime12 = System.currentTimeMillis();
        heap.heapSort(ordered3);
        long endTime12 = System.currentTimeMillis();
        System.out.println("An ordered array with 80000 elements: " + (endTime12 - startTime12));

//        *** Run-time Test for Heapsort ***
//        A reversely ordered array with 20000 elements: 735
//        An ordered array with 20000 elements: 573
//        A reversely ordered array with 40000 elements: 2302
//        An ordered array with 40000 elements: 2108
//        A reversely ordered array with 80000 elements: 9433
//        An ordered array with 80000 elements: 9011
    }
}
