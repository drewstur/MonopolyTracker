package group6.cis46300.monopolytracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class gameInformation extends ActionBarActivity {
    ListView listView ;
    ListView moneyView;
    Object payer, payee;
    String currentPayer, payStatementOutput, amount;
    boolean needPayer = true, needPayee = true;
    Button pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);

        final TextView payStatement = (TextView)findViewById(R.id.textView2);
        listView = (ListView) findViewById(R.id.list);
        moneyView = (ListView) findViewById(R.id.listView);
        Button payBtn = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        String[] myStrings = intent.getStringArrayExtra("strings");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, myStrings);
        listView.setAdapter(adapter);

        Integer[] moneyTracker = new Integer[myStrings.length];

        for(int i = 0; i < moneyTracker.length-1; i++)
        {
            moneyTracker[i] = 1500;
        }
        //set values for Comm.Chest and Banker
        moneyTracker[moneyTracker.length-2] = 0;
        moneyTracker[moneyTracker.length-1] = 50000;

       ArrayAdapter<Integer> moneyAdapter = new ArrayAdapter<Integer>(this, R.layout.list_item, R.id.textView, moneyTracker);
       moneyView.setAdapter(moneyAdapter);

        //set onclick listeners
        //This is to select the payer, which will be highlighted. Payer = cyan  and payee = yellow
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //checking to see if this is payer or payee
                if (needPayer) {
                    // adapter.notifyDataSetChanged();
                    /*
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                        moneyView.getChildAt(i).setBackgroundColor(Color.WHITE);
                    } */

                    //set payer so we can transfer money though array later
                    payer = listView.getItemAtPosition(position);

                    //updating paystatement with payer
                    payStatementOutput = listView.getItemAtPosition(position).toString();
                    payStatement.setText(payStatementOutput);

                    //updating background of payer to cyan
                    listView.getChildAt(position).setBackgroundColor(Color.CYAN);
                    moneyView.getChildAt(position).setBackgroundColor(Color.CYAN);

                }else{
                    /*
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        listView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                        moneyView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                    } */

                    //changing payee background to yellow
                    listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                    moneyView.getChildAt(position).setBackgroundColor(Color.YELLOW);

                    //set payee to move funds later
                    payee = listView.getItemAtPosition(position);

                    //updating paystatement with payee
                    payStatementOutput = payStatementOutput+listView.getItemAtPosition(position).toString();
                    payStatement.setText(payStatementOutput);

                    //creating pop up input text for user
                    final EditText input = new EditText(gameInformation.this);
                    // set input keyboard to numerical only
                    input.setKeyListener(DigitsKeyListener.getInstance());
                    new AlertDialog.Builder(gameInformation.this)
                            .setTitle("Enter the amount owed.")
                            .setMessage(payStatementOutput)
                            .setView(input)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //set amount to be transfered
                                     amount = input.getText().toString();
                                    //update payStatement
                                     payStatementOutput = payStatementOutput+" $"+amount;
                                     payStatement.setText(payStatementOutput);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                            //if user clicked cancel should probably clear paystatement and set isPayer = 0 to completely restart process
                        }
                    }).show();

                    //need to put button listener for complete transaction
                    //this is where we will change the funds between the users and reset the boolean to true and the paystatement to blank
                }
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set isPayer to false so we know that we don't need them to select a payer
                //maybe add a way to check and make sure that a payer is set like if(payer.getText().toString().trim().length()) != 0)
                //if nothing is selected send the back to make another selection
                needPayer = false;
                payStatementOutput = payStatementOutput+" will pay ";
                payStatement.setText(payStatementOutput);
            }
        });


/*
        moneyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // adapter.notifyDataSetChanged();
                for(int i = 0; i < listView.getChildCount(); i++)
                {
                    moneyView.getChildAt(i).setBackgroundColor(Color.WHITE);
                }
                moneyView.getChildAt(position).setBackgroundColor(Color.YELLOW);
            }
        });
*/


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
