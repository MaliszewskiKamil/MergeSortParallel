import com.company.MergeSort;
import com.company.ParallelMergeSort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ParallelMergeSortTests {
    protected static Integer[] data_set;
    protected static int cores;
    protected static ParallelMergeSort parallelMergeSort;


    @Before
    public void setup() {
        parallelMergeSort = new ParallelMergeSort();
        data_set = new Integer[8];
        cores = Runtime.getRuntime().availableProcessors();
        data_set[0] = 6;
        data_set[1] = 16;
        data_set[2] = 9;
        data_set[3] = 6;
        data_set[4] = 75;
        data_set[5] = 2;
        data_set[6] = 12;
        data_set[7] = 33;
    }

    @Test
    public void testMergeSortResultInOrder(){
        Integer[] listToSort = data_set;

        assertEquals((Integer)6, listToSort[0]);
        assertEquals((Integer)33, listToSort[7]);

        MergeSort.sort(listToSort);

        assertEquals((Integer)2, listToSort[0]);
        assertEquals((Integer)75, listToSort[7]);
        assertEquals((Integer)2, listToSort[0]);
    }
    @Test
    public void testParallelSortResultInOrder(){
        Integer[] listToSort = data_set;

        assertEquals((Integer)6, listToSort[0]);
        assertEquals((Integer)33, listToSort[7]);

        parallelMergeSort.parallelMergeSort(listToSort, cores);

        assertEquals((Integer)2, listToSort[0]);
        assertEquals((Integer)75, listToSort[7]);
        assertEquals((Integer)2, listToSort[0]);
    }

    @Test
    public void testIfMergeCanSortSizeOne(){
        Integer[] listToSort = new Integer[1];
        listToSort[0] = 7;
        MergeSort.sort(listToSort);
    }

    @Test
    public void testIfParallelCanSortSizeOne(){
        Integer[] listToSort = new Integer[1];
        listToSort[0] = 7;
        parallelMergeSort.parallelMergeSort(listToSort, cores);
    }

    @Test
    public void testIfResultsOfBothSortsMatch(){
        Integer[] listParallel = data_set;
        Integer[] listSequencial = data_set;

        assertEquals(listParallel[0], listSequencial[0]);

        parallelMergeSort.parallelMergeSort(listParallel, cores);
        MergeSort.sort(listSequencial);

        assertEquals(listParallel[0], listSequencial[0]);
        assertEquals(listParallel[4], listSequencial[4]);
        assertEquals(listParallel[6], listSequencial[6]);



    }




}
