package domain;

import util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleLoan extends Loan{
    private Date manufactured;
    private String model;
    private int age;
    private int maximumAge;


    //getters and setters
    public Date getManufactured() {
        return manufactured;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        age = (int) DateUtil.differenceInDays(new Date(),this.getManufactured());
        age = age / 365;
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }
}
