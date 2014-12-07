package arthur.ddmo.mobilebudget.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.models.Transaction;

/**
 * Created by arthur on 07/12/14.
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {
    private ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, int resource, ArrayList<Transaction> transactions) {
        super(context, resource, transactions);
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.transaction_view, null);
        }

        Transaction transaction = transactions.get(position);

        if (transaction != null) {
            TextView dateTextView = (TextView) convertView.findViewById(R.id.transaction_view_text_date);
            TextView valueTextView = (TextView) convertView.findViewById(R.id.transaction_view_text_value);

            if (valueTextView != null) {
                valueTextView.setText(transaction.getValueString());
            }

            if (dateTextView != null) {
                dateTextView.setText(transaction.getDateString());
            }

        }
        return convertView;
    }
}
