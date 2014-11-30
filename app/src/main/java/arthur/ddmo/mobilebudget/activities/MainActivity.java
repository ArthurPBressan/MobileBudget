package arthur.ddmo.mobilebudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import arthur.ddmo.mobilebudget.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout scrollViewContent = (LinearLayout) findViewById(R.id.transactionsScrollViewContainer);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i<50; i++) {
            LinearLayout tv = (LinearLayout) layoutInflater.inflate(R.layout.transaction_view, scrollViewContent);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startEditActivity = new Intent(getApplicationContext(), EditTransactionActivity.class);
                    startEditActivity.putExtra("things", "thing");
                    startActivity(startEditActivity);
                }
            });
        }
    }


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
