package service;

import domain.Loan;
import domain.LoanRiskType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService {
    private List<Loan> loans;

    public LoanService(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> getHighRiskLoans() {
        List<Loan> highRiskLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.riskType == LoanRiskType.HIGH_RISK) {
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
            if (loan.riskType == riskType) {
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
