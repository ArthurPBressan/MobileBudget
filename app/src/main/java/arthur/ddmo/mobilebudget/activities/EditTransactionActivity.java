package arthur.ddmo.mobilebudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import arthur.ddmo.mobilebudget.Constants;
import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.models.BudgetTransaction;

public class EditTransactionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction_view);

        Intent startupIntent = getIntent();
        int transactionId = startupIntent.getIntExtra(Constants.KEY_INTENT_TRANSACTION, -1);
        if (transactionId == -1) {
            setTitle(R.string.title_activity_new_transaction_view);
        } else {
            BudgetTransaction budgetTransaction = BudgetTransaction.findById(BudgetTransaction.class, (long) transactionId);
            DatePicker dp = (DatePicker) findViewById(R.id.transaction_date_picker);
            dp.updateDate(budgetTransaction.getYear(), budgetTransaction.getMonth(), budgetTransaction.getDay());

            TextView valueTV = (TextView) findViewById(R.id.edit_transaction_value);
            valueTV.setText(String.valueOf(budgetTransaction.getValue()));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_transaction) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
