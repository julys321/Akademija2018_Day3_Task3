package app;

import domain.DomainInitializer;
import domain.Loan;
import domain.LoanRiskType;
import domain.Task1DomainInitializer;
import service.LoanService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientApp {

    public static void main(String[] args) {

        List<Loan> loans = new ArrayList<>(Arrays.asList(getInitializer().initializeLoans()));
        LoanService service = new LoanService(loans);


        System.out.println("getAverageLoanCost " + service.getAverageLoanCost());
        System.out.println("getHighRiskLoans There are " + service.getHighRiskLoans().size());
        System.out.println("getAverageLoanCost NORMAL_RISK " + service.getAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println("getAverageLoanCost HIGH_RISK " + service.getAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println("getAverageLoanCost LOW_RISK " + service.getAverageLoanCost(LoanRiskType.LOW_RISK));
        System.out.println("getAverageCostOfHighRiskLoans " + service.getAverageCostOfHighRiskLoans());
        System.out.println("getMaximumPriceOfNonExpiredLoans " + service.getMaximumPriceOfNonExpiredLoans());
    }


    public static DomainInitializer getInitializer() {
        return new Task1DomainInitializer();
    }

}
