package arthur.ddmo.mobilebudget.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by arthur on 30/11/14.
 */
public class Transaction extends SugarRecord<Transaction> {
    private int value;

    private int year;
    private int month;
    private int day;

    public Transaction() {
    }

    public Transaction(double value, int year, int month, int day) {
        this.value = (int) Math.round(value * 100);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private String padDate(int date){
        return String.format("%02d", date);
    }

    public String getDateString() {
        return padDate(day) + "/" + padDate(month);
    }

    public double getValue() {
        return value/100;
    }

    public String getValueString() {
        double value = getValue();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "value=" + value +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
