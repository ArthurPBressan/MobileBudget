package arthur.ddmo.mobilebudget.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by arthur on 30/11/14.
 */
public class Transaction extends SugarRecord<Transaction> {
    private int value;
    

    public Transaction() {
    }

    public Transaction(int value, Date date) {
        this.value = value;
    }

    public static Transaction[] getTransactionsByYear(int year) {
        return new Transaction[0];
    }

    public static Transaction[] getTransactionsByMonth(int year, int month) {
        Transaction[] yearTransactions = getTransactionsByYear(year);
        return new Transaction[0];
    }

    public int getValue() {
        return value;
    }
}
