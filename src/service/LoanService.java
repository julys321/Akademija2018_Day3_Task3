package service;

import domain.*;
import util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService {
    private List<Loan> loans;

    public LoanService(List<Loan> loans) {
        this.loans = loans;
    }

    //Task 2
    public List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        List<Loan> expiredHighRiskVehicleLoans = new ArrayList<>();
        int maximumDuration=0;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan
                    && loan.riskType.equals(LoanRiskType.HIGH_RISK)
                    && !loan.isValid()) {
                expiredHighRiskVehicleLoans.add((VehicleLoan) loan);
                if(loan.getTermInYears()>maximumDuration){
                    maximumDuration=loan.getTermInYears();
                }
            }
        }
        for (Loan loan : expiredHighRiskVehicleLoans) {
            if(loan.getTermInYears()!= maximumDuration){
                expiredHighRiskVehicleLoans.remove(loan);
            }
        }
        return expiredHighRiskVehicleLoans;
    }

    public List<Loan> getPersonalRealEstateLoans() {
        List<Loan> personalRealEstateLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan && ((RealEstateLoan) loan).getPurpose().equals(RealEstatePurpose.PERSONAL)) {
                personalRealEstateLoans.add(loan);
            }

        }
        return personalRealEstateLoans;
    }

    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        List<Integer> lowRiskVehicleLoansAge = new ArrayList<>();
        VehicleLoan vehicleLoan;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (loan.riskType.equals(LoanRiskType.LOW_RISK)) {
                    vehicleLoan = (VehicleLoan) loan;
                    lowRiskVehicleLoansAge.add(vehicleLoan.getAge());
                }
            }
        }
        Collections.sort(lowRiskVehicleLoansAge);
        return lowRiskVehicleLoansAge.get(lowRiskVehicleLoansAge.size() - 1);
    }

    public List<Loan> getNormalRiskVehicleLoans() {
        List<Loan> normalRiskVehicleLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (loan.riskType.equals(LoanRiskType.NORMAL_RISK)) {
                    normalRiskVehicleLoans.add((VehicleLoan) loan);
                }
            }
        }
        return normalRiskVehicleLoans;
    }


    //Task1
    public List<Loan> getHighRiskLoans() {
        List<Loan> highRiskLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.riskType.equals(LoanRiskType.HIGH_RISK)) {
                highRiskLoans.add(loan);
            }
        }
        return highRiskLoans;
    }

    public BigDecimal getAverageLoanCost() {
        BigDecimal averageLoanCost = new BigDecimal(0);
        for (Loan loan : loans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }
        averageLoanCost = averageLoanCost.divide(new BigDecimal(loans.size()), 2, RoundingMode.HALF_UP);

        return averageLoanCost;
    }

    public BigDecimal getAverageLoanCost(LoanRiskType riskType) {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        BigDecimal amountOfType = BigDecimal.ZERO;
        for (Loan loan : loans) {
            if (loan.riskType.equals(riskType)) {
                averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
                amountOfType = amountOfType.add(BigDecimal.ONE);
            }
        }

        if (!amountOfType.equals(BigDecimal.ZERO)) {
            averageLoanCost = averageLoanCost.divide(amountOfType, 2, RoundingMode.HALF_UP);
        }
        return averageLoanCost;
    }

    public BigDecimal getAverageCostOfHighRiskLoans() {
        return getAverageLoanCost(LoanRiskType.HIGH_RISK);
    }

    public BigDecimal getMaximumPriceOfNonExpiredLoans() {
        List<BigDecimal> nonExpiredLoanPrices = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isValid()) {
                nonExpiredLoanPrices.add(loan.getPrice());
            }
        }
        Collections.sort(nonExpiredLoanPrices);
        return nonExpiredLoanPrices.get(nonExpiredLoanPrices.size() - 1);
    }
}
