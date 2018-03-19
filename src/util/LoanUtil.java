package util;

import domain.VehicleLoan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class LoanUtil {
    public BigDecimal calculateVehicleDepreciation(VehicleLoan loan) {
        BigDecimal vehicleDepreciation, yearsInUse;

        yearsInUse = (new BigDecimal(util.DateUtil.differenceInDays(new Date(), ((VehicleLoan) loan).getManufactured()))).divide(new BigDecimal(365), 0, RoundingMode.DOWN);
        vehicleDepreciation = loan.getPrice().multiply(yearsInUse).divide(new BigDecimal(((VehicleLoan) loan).getMaximumAge()), 2, RoundingMode.HALF_UP);
        //System.out.println(vehicleDepreciation+" "+loan.getPrice()+" "+vehicleDepreciation.compareTo(loan.getPrice()));
        if (vehicleDepreciation.compareTo(loan.getPrice()) > 0)
        return loan.getPrice();
        else
        return vehicleDepreciation;
    }
}
