package app.core.domain.algorithms;

import app.core.domain.model.LegacySystemFile;

import java.util.List;

/**
 * Interface that have the required methods to the classes which wants to implement a sort algorithm.
 * This promotes polymorphism.
 *
 * @author Group 40
 */
public interface SortDataAlgorithm {

    /**
     * The method that calls the required methods to do the sort process.
     *
     * @param lSFList The LegacySystemFile list.
     * @return The sorted list.
     */
    List<LegacySystemFile> sortData(List<LegacySystemFile> lSFList);
}
