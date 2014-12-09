package arthur.ddmo.mobilebudget.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arthur.ddmo.mobilebudget.Constants;
import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.dialogs.ConfirmDeleteDatabaseDialog;
import arthur.ddmo.mobilebudget.dialogs.ConfirmDeleteSingleTransactionDialog;
import arthur.ddmo.mobilebudget.models.BudgetTransaction;

public class EditTransactionActivity extends Activity implements ConfirmDeleteSingleTransactionDialog.ConfirmDeleteTransactionListener {
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        BudgetTransaction transaction = BudgetTransaction.findById(BudgetTransaction.class, transactionId);
        transaction.delete();
        dialog.dismiss();
        setResult(Constants.RESULT_TRANSACTION_OK);
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    private long transactionId;

    private EditText valueTextView;
    private DatePicker transactionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction_view);

        Intent startupIntent = getIntent();
        transactionId = startupIntent.getLongExtra(Constants.KEY_INTENT_TRANSACTION, -1);

        valueTextView = (EditText) findViewById(R.id.edit_transaction_value);
        transactionDate = (DatePicker) findViewById(R.id.transaction_date_picker);

        if (transactionId == -1) {
            setTitle(R.string.title_activity_new_transaction_view);
            Button btDelete = (Button) findViewById(R.id.delete_transaction_button);
            btDelete.setVisibility(View.GONE);
        } else {
            BudgetTransaction budgetTransaction = BudgetTransaction.findById(BudgetTransaction.class, (long) transactionId);
            transactionDate.updateDate(budgetTransaction.getYear(), budgetTransaction.getMonth(), budgetTransaction.getDay());
            valueTextView.setText(String.valueOf(budgetTransaction.getValue()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_transaction_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_save_transaction) {
            String valueString = valueTextView.getText().toString();
            if (valueString.equals("")) {
                Toast t = Toast.makeText(getApplicationContext(), R.string.toast_invalid_transaction_value, Toast.LENGTH_SHORT);
                t.show();
                return true;
            }
            double value = Double.parseDouble(valueString);
            int year = transactionDate.getYear();
            int month = transactionDate.getMonth() + 1;
            int day = transactionDate.getDayOfMonth();
            if (transactionId == -1) {
                BudgetTransaction transaction = new BudgetTransaction(value, year, month, day);
                transaction.save();
            } else {
                BudgetTransaction transaction = BudgetTransaction.findById(BudgetTransaction.class, (long) transactionId);
                transaction.setDay(day);
                transaction.setMonth(month);
                transaction.setYear(year);
                transaction.setValue(value);
                transaction.save();
            }
            setResult(Constants.RESULT_TRANSACTION_OK);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteTransaction(View view) {
        ConfirmDeleteSingleTransactionDialog dialog = new ConfirmDeleteSingleTransactionDialog();
        dialog.show(getFragmentManager(), "delete");
    }
}
