package arthur.ddmo.mobilebudget.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import arthur.ddmo.mobilebudget.R;

/**
 * Created by arthur on 30/11/14.
 */
public class TransactionView extends LinearLayout {

    private TextView transactionValue;
    private TextView transactionDate;

    public TransactionView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context);
    }

    public TransactionView(Context context) {
        super(context);
        setUp(context);
    }

    private void setUp(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.transaction_view, this);

        transactionValue = (TextView) findViewById(R.id.transactionValue);
        transactionDate = (TextView) findViewById(R.id.transactionDate);
    }
}
