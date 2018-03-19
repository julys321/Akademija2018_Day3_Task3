package app;

import domain.DomainInitializer;
import domain.Loan;
import domain.Task3DomainInitializer;
import domain.VehicleLoan;
import service.LoanService;
import util.LoanUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientApp {

    public static void main(String[] args) {
        List<Loan> loans = new ArrayList<>(Arrays.asList(getInitializer().initializeLoans()));
        LoanService service = new LoanService(loans);
        LoanUtil util = new LoanUtil();

        System.out.println(loans.get(0).getName());
        System.out.println("getLowRiskHarvesterLoans() there are "+service.getLowRiskHarvesterLoans().size());
        System.out.println("getExpiredLandLoansInReservation() there are "+service.getExpiredLandLoansInReservation().size());
        System.out.println("getLoansOfHigherThanAverageDepreciation() there are "+service.getLoansOfHigherThanAverageDepreciation().size());
    }


    public static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

}
