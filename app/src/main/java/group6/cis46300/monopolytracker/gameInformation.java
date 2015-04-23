package group6.cis46300.monopolytracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class gameInformation extends ActionBarActivity {
    ListView listView ;
    ListView moneyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);

        listView = (ListView) findViewById(R.id.list);
        moneyView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        String[] myStrings = intent.getStringArrayExtra("strings");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, myStrings);
        listView.setAdapter(adapter);

        Integer[] moneyTracker = new Integer[myStrings.length];

        for(int i = 0; i < moneyTracker.length; i++)
        {
            moneyTracker[i] = 1500;
        }

       ArrayAdapter<Integer> moneyAdapter = new ArrayAdapter<Integer>(this, R.layout.list_item, R.id.textView, moneyTracker);
       moneyView.setAdapter(moneyAdapter);





//hello how u iz?
        //does it work this time?
//testing again
//values[0] = "Test";
//adapter.notifyDataSetChanged();


// values[0] = "Test2";
// adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_information, menu);
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
