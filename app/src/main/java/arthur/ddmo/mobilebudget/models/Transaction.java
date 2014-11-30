package arthur.ddmo.mobilebudget.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by arthur on 30/11/14.
 */
public class Transaction extends SugarRecord<Transaction> {
    private BigDecimal value;
    private Date date;

    public Transaction() {
    }

    public Transaction(BigDecimal value, Date date) {
        this.value = value;
        this.date = date;
    }
}
