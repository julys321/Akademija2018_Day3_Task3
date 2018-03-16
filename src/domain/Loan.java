package domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Loan {

    private Date creationDate;


    private int termInYears;
    private String name;


    public BigDecimal interestRate;
    public BigDecimal price;

    public LoanRiskType riskType;

    public boolean isValid() {
        Calendar rightNowTime = Calendar.getInstance();
        Calendar loanExpirationTime = Calendar.getInstance();

        loanExpirationTime.setTime(this.getCreationDate());
        loanExpirationTime.add(Calendar.YEAR, this.getTermInYears());

        if (rightNowTime.before(loanExpirationTime)) {
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal calculateInterest() {
        return /*(*/(interestRate.multiply(price)).divide(new BigDecimal(100))/*).multiply(new BigDecimal(termInYears))*/;
    }

    //get and set
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalLoanCost() {
        return calculateInterest().add(price);
    }

}
