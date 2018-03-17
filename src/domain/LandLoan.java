package domain;

public class LandLoan extends RealEstateLoan {
    public boolean isInReservation;

    public boolean isInReservation() {
        return isInReservation;
    }

    public void setInReservation(boolean inReservation) {
        isInReservation = inReservation;
    }
}
