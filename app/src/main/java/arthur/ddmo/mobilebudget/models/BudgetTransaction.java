package arthur.ddmo.mobilebudget.models;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.NumberFormat;
import java.util.List;

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

    private static double outValue(int value) {
        return value/100.;
    }

    public double getValue() {
        return outValue(value);
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

    private static int parseValue(double value) {
        return (int) Math.floor(value * 100);
    }

    public static double getAverageForMonth(int year, int month) {
        List<BudgetTransaction> ls = Select.from(BudgetTransaction.class)
                .where(Condition.prop("year").eq(year), Condition.prop("month").eq(month))
                .list();
        return getAverageFromTransactions(ls);
    }

    public static double getAverageForYear(int year) {
        List<BudgetTransaction> ls = Select.from(BudgetTransaction.class)
                .where(Condition.prop("year").eq(year))
                .list();
        return getAverageFromTransactions(ls);
    }

    private static double getAverageFromTransactions(List<BudgetTransaction> transactions) {
        double sum = 0;
        for (BudgetTransaction transaction : transactions) {
            sum += transaction.getValue();
        }
        return outValue(parseValue(sum));
    }
}
