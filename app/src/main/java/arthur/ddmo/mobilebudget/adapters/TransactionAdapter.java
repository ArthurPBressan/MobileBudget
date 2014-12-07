package arthur.ddmo.mobilebudget.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.models.BudgetTransaction;

/**
 * Created by arthur on 07/12/14.
 */
public class TransactionAdapter extends ArrayAdapter<BudgetTransaction> {
    private ArrayList<BudgetTransaction> budgetTransactions;

    public TransactionAdapter(Context context, int resource, ArrayList<BudgetTransaction> budgetTransactions) {
        super(context, resource, budgetTransactions);
        this.budgetTransactions = budgetTransactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.transaction_view, null);
        }

        BudgetTransaction budgetTransaction = budgetTransactions.get(position);

        if (budgetTransaction != null) {
            TextView dateTextView = (TextView) convertView.findViewById(R.id.transaction_view_text_date);
            TextView valueTextView = (TextView) convertView.findViewById(R.id.transaction_view_text_value);

            if (valueTextView != null) {
                valueTextView.setText(budgetTransaction.getValueString());
            }

            if (dateTextView != null) {
                dateTextView.setText(budgetTransaction.getDateString());
            }

        }
        return convertView;
    }
}
