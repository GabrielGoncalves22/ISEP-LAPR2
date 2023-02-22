package app.core.controller;

import app.core.domain.algorithms.MaxSumSublist;
import app.core.domain.model.*;
import app.core.domain.store.ArrivalSNSUserStore;
import app.core.domain.store.VaccinationCenterStore;
import app.core.domain.store.VaccineAdministrationStore;

import java.util.List;

/**
 * Represents the type Analyze center performance controller.
 *
 * @author Group 40
 */

public class AnalyzeCenterPerformanceController {
    private Company company;
    private VaccinationCenterStore vcStore;
    private ArrivalSNSUserStore auStore;
    private VaccineAdministrationStore vaStore;
    private VaccinationCenter vc;
    private AnalyzePerformance ap;
    private MaxSumSublist mss;
    private List<ArrivalSNSUser> listAU;
    private List<VaccineAdministration> listVA;

    /**
     * Builds an instance of AnalyzeCenterPerformanceController.
     *
     * @param vcId the vc id.
     */
    public AnalyzeCenterPerformanceController(int vcId) {
        this(App.getInstance().getCompany());
        this.vcStore = company.getVaccinationCenterStore();
        vc = vcStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of AnalyzeCenterPerformanceController receiving the company.
     *
     * @param company the company.
     */
    public AnalyzeCenterPerformanceController(Company company) {
        this.company = company;
    }

    /**
     * Validate if total time is an integer divisor of the time interval to be analyzed.
     *
     * @param m the interval minute.
     * @return if is integer divisor.
     */
    public boolean validateTimeIntervalAnalyze(int m) {
        int minutesTotalCenter = Time.calculateTimeDifferenceMinute(vc.getOpeningHours(), vc.getClosingHours());
        return minutesTotalCenter % m == 0;
    }

    /**
     * Gets arrival sns user list.
     *
     * @param intendedDate the intended date.
     */
    public void getArrivalSNSUserList(Date intendedDate) {
        this.auStore = this.company.getArrivalSNSUserStore();
        this.listAU = this.auStore.getArrivalSNSUserList(intendedDate, vc);
    }

    /**
     * Gets vaccine administration list.
     *
     * @param intendedDate the intended date.
     */
    public void getVaccineAdministrationList(Date intendedDate) {
        this.vaStore = this.company.getVaccineAdministrationStore();
        this.listVA = this.vaStore.getVaccineAdministrationList(intendedDate, vc);
    }

    /**
     * Function to calculate and return the maximum sum contiguous sublist.
     *
     * @param m m the interval minute.
     * @return the maximum sum contiguous sublist.
     */
    public int[] getMaxSumSubList(int m) {
        this.ap = new AnalyzePerformance(vc);
        int[] differenceList = ap.createDifferenceList(listAU, listVA, m);

        this.mss = new MaxSumSublist();
        int[] maxSumSubList = mss.getMaxSumSubList(differenceList);
        return mss.getMaxSumSubList(maxSumSubList);
    }

    /**
     * Gets maximum sum.
     *
     * @return the maximum sum.
     */
    public int getMaxSum() {
        return mss.getMaximumSum();
    }

    /**
     * Function to construct a string representing the time interval of the maximum sum contiguous sublist.
     *
     * @param intendedDate the intended date.
     * @param m            the interval minute.
     * @return a string representing the time interval of the maximum sum contiguous sublist.
     */
    public String timeIntervalMaxSumSubList(Date intendedDate, int m) {
        return ap.timeInterval(mss.getStartIndex(), mss.getEndIndex(), intendedDate, m);
    }
}
