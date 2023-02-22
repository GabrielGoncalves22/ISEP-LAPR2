package app.core.domain.algorithms;

import app.core.domain.model.Date;
import app.core.domain.model.LegacySystemFile;
import app.core.domain.model.Time;
import app.core.domain.shared.SortDataCriteria;

import java.util.Collections;
import java.util.List;

/**
 * Class that has the quick sort algorithm to sort the data from a list of type LegacySystemFile.
 *
 * @author Group 40
 */
public class QuickSortAlgorithm implements SortDataAlgorithm {

    /**
     * Holds the order criteria and methods.
     */
    private SortDataCriteria sortDataCriteria;

    /**
     * Builds an instance of QuickSortAlgorithm receiving the sort criteria for this instance.
     *
     * @param sortCriteria
     */
    public QuickSortAlgorithm(String sortCriteria) {
        this.sortDataCriteria = new SortDataCriteria(sortCriteria);
    }

    /**
     * Method to sort data present in a LegacySystemFile list type.
     *
     * @param lSFList The LegacySystemFile list.
     * @return The sorted list.
     */
    @Override
    public List<LegacySystemFile> sortData(List<LegacySystemFile> lSFList) {
        int startIndex = 0;

        long startTime = System.currentTimeMillis();

        this.quicksort(lSFList, startIndex, lSFList.size() - 1);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.printf("Execution time in seconds: %.2fs", (double) elapsedTime / 1_000);

        return lSFList;
    }

    /**
     * Method to handle the necessary steps to make quick sort algorithm work.
     *
     * @param lSFList The list to be sorted.
     * @param low     the lowest index of the list we want to start sorting.
     * @param high    The highest position of the list that we want to sort.
     */
    private void quicksort(List<LegacySystemFile> lSFList, int low, int high) {
        if (low < high) {
            int p = partition(lSFList, low, high);
            quicksort(lSFList, low, p - 1);
            quicksort(lSFList, p + 1, high);
        }
    }

    /**
     * The method to swap data between two index in the list.
     *
     * @param lSFList The list where we want to swap the two data in the given indexes.
     * @param i     The index of one element to be swapped.
     * @param j   The index of the other element to be swapped.
     */
    private void swap(List<LegacySystemFile> lSFList, int i, int j) {
        Collections.swap(lSFList, j, i);
    }

    /**
     * This method partitions the array, and upon its execution the variable p stores the position of the pivot after the partitioning.
     *
     * @param lSFList The list that holds the data.
     * @param low     The lowest position in the partition.
     * @param high    The highest position in the partition.
     * @return The pivot index.
     */
    private int partition(List<LegacySystemFile> lSFList, int low, int high) {
        int p = low, j;

        Date lowDate = this.sortDataCriteria.getDateValue(lSFList.get(low));
        Time lowTime = this.sortDataCriteria.getTimeValue(lSFList.get(low));
        for (j = low + 1; j <= high; j++) {
            Date highDate = this.sortDataCriteria.getDateValue(lSFList.get(j));
            Time highTime = this.sortDataCriteria.getTimeValue(lSFList.get(j));

            if (
                    highDate.compareTo(lowDate) < 0
                            || (highDate.compareTo(lowDate) == 0 && highTime.compareTo(lowTime) < 0)
            ) {
                swap(lSFList, ++p, j);
            }
        }

        swap(lSFList, low, p);
        return p;
    }

}
