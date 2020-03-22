package pl.com.pwr.lab0.emptyactivitytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class menu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.menu.option);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        ((TextView)findViewById(R.id.MenuButton)).setText(“Menu”);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:
                ((TextView)findViewById(R.id.options)).setText("Options");
                return true;
            case R.id.favorites:
                ((TextView)findViewById(R.id.favorites)).setText("Favorites");
                return true;
            case R.id.statistics:
                ((TextView)findViewById(R.id.statistics)).setText("Statistics");
                return true;
            case R.id.exit:
                ((TextView)findViewById(R.id.exit)).setText("Exit");
                finish();
                return true;
        }
        return false;
    }

}

