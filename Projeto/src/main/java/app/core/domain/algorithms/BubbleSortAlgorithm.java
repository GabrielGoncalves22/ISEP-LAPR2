package app.core.domain.algorithms;

import app.core.domain.model.Date;
import app.core.domain.model.LegacySystemFile;
import app.core.domain.model.Time;
import app.core.domain.shared.SortDataCriteria;

import java.util.Collections;
import java.util.List;

/**
 * Class that has the bubble sort algorithm to sort the data from a list of type LegacySystemFile.
 *
 * @author Group 40
 */
public class BubbleSortAlgorithm implements SortDataAlgorithm {

    /**
     * Holds the order criteria and methods.
     */
    private SortDataCriteria sortDataCriteria;

    /**
     * Builds an instance of BubbleSortAlgorithm receiving the sort criteria for this instance.
     *
     * @param sortCriteria
     */
    public BubbleSortAlgorithm(String sortCriteria) {
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

        long startTime = System.currentTimeMillis();

        this.bubbleSort(lSFList);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.printf("Execution time in seconds: %.2fs", (double) elapsedTime / 1_000);

        return lSFList;
    }

    /**
     * Method to sort the list using the algorithm bubble sort.
     *
     * @param lSFList The list to be sorted.
     */
    private void bubbleSort(List<LegacySystemFile> lSFList) {

        for (int i = 0; i < lSFList.size() - 1; i++) {
            for (int j = 0; j < lSFList.size() - i - 1; j++) {
                Date actualDate = this.sortDataCriteria.getDateValue(lSFList.get(j));
                Time actualTime = this.sortDataCriteria.getTimeValue(lSFList.get(j));
                Date nextDate = this.sortDataCriteria.getDateValue(lSFList.get(j + 1));
                Time nextTime = this.sortDataCriteria.getTimeValue(lSFList.get(j + 1));

                if (
                        nextDate.compareTo(actualDate) < 0
                                || (nextDate.compareTo(actualDate) == 0 && nextTime.compareTo(actualTime) < 0)
                ) {
                    this.swap(lSFList, j, j+1);
                }
            }
        }

    }

    /**
     * The method to swap data between two index in the list.
     *
     * @param lSFList The list where we want to swap the two data in the given indexes.
     * @param i       The index of one element to be swapped.
     * @param j       The index of the other element to be swapped.
     */
    private void swap(List<LegacySystemFile> lSFList, int i, int j) {
        Collections.swap(lSFList, j, i);
    }

}
