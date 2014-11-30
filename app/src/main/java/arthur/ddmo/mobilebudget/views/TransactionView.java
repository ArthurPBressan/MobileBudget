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

    private final TextView transactionValue;
    private final TextView transactionDate;

    public TransactionView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.transaction_view, this);

        transactionValue = (TextView) findViewById(R.id.transactionValue);
        transactionDate = (TextView) findViewById(R.id.transactionDate);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(context, "asdasdasdasddas", Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}
