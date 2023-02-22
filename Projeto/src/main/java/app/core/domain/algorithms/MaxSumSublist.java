package app.core.domain.algorithms;

import java.util.Arrays;

/**
 * Class that has the brute force algorithm to calculate the maximum sum contiguous sublist.
 *
 * @author Group 40
 */
public class MaxSumSublist {
    private int[] maxSumSubList;
    private int maximumSum;
    private int startIndex;
    private int endIndex;

    /**
     * Builds an instance of MaxSumSublist
     */
    public MaxSumSublist() {
    }

    /**
     * Function to calculate and return the maximum sum contiguous sublist.
     *
     * @param listToBeAnalyzed the list to be analyzed.
     * @return the maximum sum contiguous sublist.
     */
    public int[] getMaxSumSubList(int[] listToBeAnalyzed) {
        this.maxSumSubList = findMaxSumSublist(listToBeAnalyzed);
        return maxSumSubList;
    }

    /**
     * Gets maximum sum.
     *
     * @return the maximum sum.
     */
    public int getMaximumSum() {
        return this.maximumSum;
    }

    /**
     * Function to perform a brute force algorithm to calculate the maximum sum contiguous sublist.
     *
     * @param listToBeAnalyzed the list to be analyzed.
     * @return the maximum sum contiguous sublist.
     */
    private int[] findMaxSumSublist(int[] listToBeAnalyzed) {
        this.startIndex = 0;
        this.endIndex = 0;
        this.maximumSum = 0;
        int arrayLength = listToBeAnalyzed.length;

        for (int i = 0; i < arrayLength; i++) {
            int currentSum = 0;
            for (int j = i; j < arrayLength; j++) {

                currentSum += listToBeAnalyzed[j];

                if (currentSum > maximumSum) {
                    maximumSum = currentSum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }

        return Arrays.copyOfRange(listToBeAnalyzed, startIndex, endIndex + 1);
    }

    /**
     * Function to return the start index.
     *
     * @return the start index.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Function to return the end index.
     *
     * @return the end index.
     */
    public int getEndIndex() {
        return endIndex;
    }
}
