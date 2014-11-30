package arthur.ddmo.mobilebudget.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import arthur.ddmo.mobilebudget.R;

/**
 * Created by arthur on 30/11/14.
 */
public class TransactionView extends LinearLayout {

    private final TextView labelValue;
    private final TextView transactionValue;
    private final TextView labelDate;
    private final TextView transactionDate;
    private final Button buttonEditTransaction;

    public TransactionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.transaction_view, this);

        labelValue = (TextView) findViewById(R.id.labelValue);
        transactionValue = (TextView) findViewById(R.id.transactionValue);
        labelDate = (TextView) findViewById(R.id.labelDate);
        transactionDate = (TextView) findViewById(R.id.transactionDate);

        buttonEditTransaction = (Button) findViewById(R.id.buttonEditTransaction);
    }
}
