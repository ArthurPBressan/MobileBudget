package arthur.ddmo.mobilebudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import arthur.ddmo.mobilebudget.R;
import arthur.ddmo.mobilebudget.models.BudgetTransaction;

public class ReportsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int monthSpinnerId;
    private int yearSpinnerId;

    private int year = 2014;
    private int month = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Spinner month_spinner = (Spinner) findViewById(R.id.month_spinner);
        monthSpinnerId = month_spinner.getId();
        ArrayAdapter<CharSequence> months_adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_dropdown_item);
        month_spinner.setAdapter(months_adapter);
        month_spinner.setOnItemSelectedListener(this);

        Spinner year_spinner = (Spinner) findViewById(R.id.year_spinner);
        yearSpinnerId = year_spinner.getId();
        ArrayAdapter<CharSequence> year_adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(year_adapter);
        year_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int viewId = parent.getId();
        if (viewId == yearSpinnerId) {
            year = Integer.valueOf((String) parent.getItemAtPosition(pos));
        } else if (viewId == monthSpinnerId) {
            month = pos;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void calcAverage(View view) {
        System.out.println(year);
        System.out.println(month);

        double avg;
        String message;
        if (month == 0) {
            avg = BudgetTransaction.getAverageForYear(year);
            message = "Média de gastos para o ano de " + year + ": R$" + avg;
        } else {
            avg = BudgetTransaction.getAverageForMonth(year, month);
            message = "Média de gastos para o mês " + month + " do ano de " + year + ": R$" + avg;
        }

        TextView tv = (TextView) findViewById(R.id.result_text_view);
        tv.setText(message);
    }
}
