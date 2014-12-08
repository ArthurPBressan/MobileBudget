package arthur.ddmo.mobilebudget.activities;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import arthur.ddmo.mobilebudget.Constants;
import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.adapters.TransactionAdapter;
import arthur.ddmo.mobilebudget.dialogs.ConfirmDeleteDatabaseDialog;
import arthur.ddmo.mobilebudget.models.BudgetTransaction;

public class MainActivity extends ListActivity implements ConfirmDeleteDatabaseDialog.ConfirmDeleteDatabaseListener {

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        BudgetTransaction.deleteAll(BudgetTransaction.class);
        refreshList();
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    private TransactionAdapter transactionAdapter;
    private ArrayList<BudgetTransaction> budgetTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactionAdapter = new TransactionAdapter(this, R.layout.transaction_view, new ArrayList<BudgetTransaction>());
        setListAdapter(transactionAdapter);
        refreshList();
    }

    private Runnable transactionListRunnable = new Runnable() {
        @Override
        public void run() {
            transactionListHandler.sendEmptyMessage(0);
        }
    };

    private Handler transactionListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            budgetTransactions = (ArrayList<BudgetTransaction>) BudgetTransaction.listAll(BudgetTransaction.class);
            transactionAdapter = new TransactionAdapter(MainActivity.this, R.layout.transaction_view, budgetTransactions);
            setListAdapter(transactionAdapter);
        }
    };

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        BudgetTransaction budgetTransaction = budgetTransactions.get(position);
        Intent startEditTransaction = new Intent(getApplicationContext(), EditTransactionActivity.class);
        startEditTransaction.putExtra(Constants.KEY_INTENT_TRANSACTION, budgetTransaction.getId());
        startActivityForResult(startEditTransaction, Constants.KEY_EDIT_TRANSACTION_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new_transaction) {
            Intent startNewTransaction = new Intent(getApplicationContext(), EditTransactionActivity.class);
            startActivityForResult(startNewTransaction, Constants.KEY_EDIT_TRANSACTION_CODE);
            return true;
        }
        if (id == R.id.action_open_reports) {
            Intent startReportsActivity = new Intent(getApplicationContext(), ReportsActivity.class);
            startActivity(startReportsActivity);
            return true;
        }
        if (id == R.id.action_delete_transactions) {
            ConfirmDeleteDatabaseDialog dialog = new ConfirmDeleteDatabaseDialog();
            dialog.show(getFragmentManager(), "delete");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.KEY_EDIT_TRANSACTION_CODE && resultCode == Constants.RESULT_TRANSACTION_OK) {
            refreshList();
        }
    }

    private void refreshList() {
        new Thread(null, transactionListRunnable, "FillTransactionsThread").start();
    }
}
