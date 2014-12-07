package arthur.ddmo.mobilebudget.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.LogRecord;

import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.adapters.TransactionAdapter;
import arthur.ddmo.mobilebudget.models.Transaction;

public class MainActivity extends ListActivity {

    private ArrayList<Transaction> transactions;
    private TransactionAdapter transactionAdapter;
    private Runnable transactionListRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactions = new ArrayList<Transaction>();
        transactionAdapter = new TransactionAdapter(this, R.layout.transaction_view, transactions);
        setListAdapter(transactionAdapter);

        transactionListRunnable = new Runnable() {
            @Override
            public void run() {
               transactionListHandler.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(null, transactionListRunnable, "FillTransactionsThread");
        thread.start();
    }

    private Handler transactionListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            transactions.add(new Transaction(20, new Date()));
            transactionAdapter = new TransactionAdapter(MainActivity.this, R.layout.transaction_view, transactions);

            setListAdapter(transactionAdapter);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_transaction) {
            Intent startNewTransactioNActivity = new Intent(getApplicationContext(), EditTransactionActivity.class);
            startActivity(startNewTransactioNActivity);
            return true;
        }
        if (id == R.id.action_open_reports) {
            Intent startReportsActivity = new Intent(getApplicationContext(), ReportsActivity.class);
            startActivity(startReportsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
