package service;

import domain.Loan;
import domain.LoanRiskType;

import java.math.BigDecimal;
import java.util.List;

public interface LoanServiceInterface {

    //Task1
    List<Loan> getHighRiskLoans();

    BigDecimal getAverageLoanCost();

    BigDecimal getAverageLoanCost(LoanRiskType riskType);

    BigDecimal getAverageCostOfHighRiskLoans();

    BigDecimal getMaximumPriceOfNonExpiredLoans();

    //Task 2
    List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration();

    List<Loan> getPersonalRealEstateLoans();

    int getMaximumAgeOfLowRiskLoanedVehicles();

    List<Loan> getNormalRiskVehicleLoans();


    //Task3
    List<Loan> getLowRiskHarvesterLoans();

    List<Loan> getExpiredLandLoansInReservation();

    List<Loan> getLoansOfHigherThanAverageDepreciation();

}
