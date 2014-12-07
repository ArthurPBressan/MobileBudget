package arthur.ddmo.mobilebudget.models;

import com.orm.SugarRecord;

import java.text.NumberFormat;

/**
 * Created by arthur on 30/11/14.
 */
public class BudgetTransaction extends SugarRecord<BudgetTransaction> {
    private int value;

    private int year;
    private int month;
    private int day;

    public BudgetTransaction() {
    }

    public BudgetTransaction(double value, int year, int month, int day) {
        this.value = parseValue(value);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private String padDate(int date){
        return String.format("%02d", date);
    }

    public String getDateString() {
        return padDate(day) + "/" + padDate(month) + "/" + year;
    }

    public double getValue() {
        return value/100.;
    }

    public String getValueString() {
        double value = getValue();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formatted = formatter.format(value);
        return formatted.replace("$", "");
    }

    @Override
    public String toString() {
        return "BudgetTransaction{" +
                "value=" + value +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setValue(double value) {
        this.value = parseValue(value);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int parseValue(double value) {
        return (int) Math.floor(value * 100);
    }
}
