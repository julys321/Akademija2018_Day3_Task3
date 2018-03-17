package app;

import domain.DomainInitializer;
import domain.Loan;
import domain.Task2DomainInitializer;
import service.LoanService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientApp {

    public static void main(String[] args) {
        List<Loan> loans = new ArrayList<>(Arrays.asList(getInitializer().initializeLoans()));
        LoanService service = new LoanService(loans);

        System.out.println("getNormalRiskVehicleLoans there are  " + service.getNormalRiskVehicleLoans().size());
        System.out.println("getMaximumAgeOfLowRiskLoanedVehicles  " + service.getMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println("getPersonalRealEstateLoans there are  " + service.getPersonalRealEstateLoans().size());
        System.out.println("getExpiredHighRiskVehicleLoansOfHighestDuration there are  " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().size()
                + " and highest duration is " + (service.getExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears()));
    }


    public static DomainInitializer getInitializer() {
        return new Task2DomainInitializer();
    }

}
