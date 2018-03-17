package app;

import domain.DomainInitializer;
import domain.Loan;
import domain.Task3DomainInitializer;
import service.LoanService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientApp {

    public static void main(String[] args) {
        List<Loan> loans = new ArrayList<>(Arrays.asList(getInitializer().initializeLoans()));
        LoanService service = new LoanService(loans);

        System.out.println(loans.get(0).getName());

    }


    public static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

}
