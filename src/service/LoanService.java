package service;

import domain.*;
import util.LoanUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService implements LoanServiceInterface {
    private List<Loan> loans;

    public LoanService(List<Loan> loans) {
        this.loans = loans;
    }

    //Task1
    @Override
    public List<Loan> getHighRiskLoans() {
        List<Loan> highRiskLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.riskType.equals(LoanRiskType.HIGH_RISK)) {
                highRiskLoans.add(loan);
            }
        }
        return highRiskLoans;
    }

    @Override
    public BigDecimal getAverageLoanCost() {
        BigDecimal averageLoanCost = new BigDecimal(0);
        for (Loan loan : loans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }
        averageLoanCost = averageLoanCost.divide(new BigDecimal(loans.size()), 2, RoundingMode.HALF_UP);

        return averageLoanCost;
    }

    @Override
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

    @Override
    public BigDecimal getAverageCostOfHighRiskLoans() {
        return getAverageLoanCost(LoanRiskType.HIGH_RISK);
    }

    @Override
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

    //Task 2
    @Override
    public List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        List<Loan> expiredHighRiskVehicleLoans = new ArrayList<>();
        int maximumDuration = 0;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan
                    && loan.riskType.equals(LoanRiskType.HIGH_RISK)
                    && !loan.isValid()) {
                expiredHighRiskVehicleLoans.add(loan);
                if (loan.getTermInYears() > maximumDuration) {
                    maximumDuration = loan.getTermInYears();
                }
            }
        }
        for (Loan loan : expiredHighRiskVehicleLoans) {
            if (loan.getTermInYears() != maximumDuration) {
                expiredHighRiskVehicleLoans.remove(loan);
            }
        }
        return expiredHighRiskVehicleLoans;
    }

    @Override
    public List<Loan> getPersonalRealEstateLoans() {
        List<Loan> personalRealEstateLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan && ((RealEstateLoan) loan).getPurpose().equals(RealEstatePurpose.PERSONAL)) {
                personalRealEstateLoans.add(loan);
            }

        }
        return personalRealEstateLoans;
    }

    @Override
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

    @Override
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

    //Task 3
    @Override
    public List<Loan> getLowRiskHarvesterLoans() {
        List<Loan> lowRiskHarvesterLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof HarvesterLoan) {
                if (loan.riskType.equals(LoanRiskType.LOW_RISK)) {
                    lowRiskHarvesterLoans.add((VehicleLoan) loan);
                }
            }
        }
        return lowRiskHarvesterLoans;
    }

    @Override
    public List<Loan> getExpiredLandLoansInReservation() {
        List<Loan> expiredLandLoansInReservation = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof LandLoan
                    && !loan.isValid()
                    && ((LandLoan) loan).isInReservation) {
                expiredLandLoansInReservation.add((LandLoan) loan);
            }
        }
        return expiredLandLoansInReservation;
    }

    @Override
    public List<Loan> getLoansOfHigherThanAverageDepreciation() {
        List<Loan> loansOfHigherThanAverageDepreciation = new ArrayList<>();
        BigDecimal averageDepreciation = BigDecimal.ZERO;
        LoanUtil util = new LoanUtil();
        int n = 0;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                averageDepreciation = averageDepreciation.add(util.calculateVehicleDepreciation((VehicleLoan) loan));
                n++;
            }
        }
        averageDepreciation = averageDepreciation.divide(new BigDecimal(n), 2, RoundingMode.HALF_UP);

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if(util.calculateVehicleDepreciation((VehicleLoan) loan).compareTo(averageDepreciation) > 0){
                    loansOfHigherThanAverageDepreciation.add(loan);
                }
            }
        }
        return loansOfHigherThanAverageDepreciation;
    }

    public void overrideIntrestRateForVehicleLoan(){
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if(loan.getRiskType().equals(LoanRiskType.HIGH_RISK)) {
                    loan.setInterestRate(loan.getInterestRate().multiply(new BigDecimal(1.5)));
                }
                if(loan.getRiskType().equals(LoanRiskType.LOW_RISK)) {
                    loan.setInterestRate(loan.getInterestRate().multiply(new BigDecimal(0.8)));
                }
            }
        }
    }
}
