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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class gameInformation extends ActionBarActivity {
    ListView listView ;
    ListView moneyView;
    Integer payer, payee;
    String  payStatementOutput, amount;
    boolean needPayee, needPayer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);

        final TextView payStatement = (TextView)findViewById(R.id.textView2);
        listView = (ListView) findViewById(R.id.list);
        moneyView = (ListView) findViewById(R.id.listView);
        Button payBtn = (Button) findViewById(R.id.button);
        Button completeBtn = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        final String[] myStrings = intent.getStringArrayExtra("strings");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, myStrings);
        listView.setAdapter(adapter);

        final Integer[] moneyTracker = new Integer[myStrings.length];

        for(int i = 0; i < moneyTracker.length-1; i++)
        {
            moneyTracker[i] = 1500;
        }
        //set values for Comm.Chest and Banker
        moneyTracker[moneyTracker.length-2] = 0;
        moneyTracker[moneyTracker.length-1] = 50000;

       final ArrayAdapter<Integer> moneyAdapter = new ArrayAdapter<Integer>(this, R.layout.list_item, R.id.textView, moneyTracker);
       moneyView.setAdapter(moneyAdapter);

        //set onclick listeners
        //This is to select the payer, which will be highlighted. Payer = cyan  and payee = yellow
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //checking to see if this is payer or payee
                if (needPayer) {
                    payStatement.setText("");
                    //set payer so we can transfer money though array later
                    payer = position;

                    //updating paystatement with payer
                    payStatementOutput = listView.getItemAtPosition(position).toString();
                    payStatement.setText(payStatementOutput);

                    //clear color incase switching payer
                    for(int i = 0; i < listView.getChildCount(); i++)
                    {
                        listView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                        moneyView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                    }
                    //updating background of payer to cyan
                    listView.getChildAt(position).setBackgroundColor(Color.CYAN);
                    moneyView.getChildAt(position).setBackgroundColor(Color.CYAN);

                }else if(needPayee){
                    needPayee = false;
                    //changing payee background to yellow
                    listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                    moneyView.getChildAt(position).setBackgroundColor(Color.YELLOW);

                    //set payee to move funds later
                    payee = position;

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
                                    if (!amount.equals(""))
                                    {
                                        payStatementOutput = payStatementOutput + " $" + amount;
                                        payStatement.setText(payStatementOutput);
                                    }
                                    else
                                    {
                                        for (int i = 0; i < listView.getChildCount(); i++) {
                                            listView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                                            moneyView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                                        }


                                        //clear transaction statement
                                        payStatementOutput = "";
                                        payStatement.setText(payStatementOutput);
                                        //clear amount
                                        amount = "0";
                                        //set payer true so we start the process over
                                        needPayer = true;
                                        needPayee = true;
                                        payStatement.setText("Must enter a value");

                                    }
                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //change the views back to white background for next transaction
                                    for (int i = 0; i < listView.getChildCount(); i++) {
                                        listView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                                        moneyView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                                    }


                                    //clear transaction statement
                                    payStatementOutput = "";
                                    payStatement.setText(payStatementOutput);
                                    //clear amount
                                    amount = "0";
                                    //set payer true so we start the process over
                                    needPayer = true;
                                    needPayee = true;


                                }
                            }).show();
                }
            }
        });


        completeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               if(needPayer == false && needPayee == false)
                if(moneyTracker[payer] - Integer.valueOf(amount) >= 0)
                {
                    //change payer and payee values in the array
                    moneyTracker[payer] = moneyTracker[payer] - Integer.valueOf(amount);
                    moneyTracker[payee] = moneyTracker[payee] + Integer.valueOf(amount);
                    //change the views back to white background for next transaction
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        listView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                        moneyView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                    }
                    //clear transaction statement
                    payStatementOutput = "";
                    payStatement.setText(payStatementOutput);
                    //clear amount
                    amount = "0";
                    //set payer true so we start the process over
                    needPayer = true;
                    needPayee = true;
                    //redraw the adapters to show changes in the players values
                    moneyAdapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                }
                else{

                    for (int i = 0; i < listView.getChildCount(); i++) {
                        listView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                        moneyView.getChildAt(i).setBackgroundColor(Color.rgb(238, 238, 238));
                    }

                    payStatement.setText(myStrings[payer] + " does not have enough funds");
                    //clear amount
                    amount = "0";
                    //set payer true so we start the process over
                    needPayer = true;
                    needPayee = true;
                }

            }
        }
        );

        payBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set isPayer to false so we know that we don't need them to select a payer
                //maybe add a way to check and make sure that a payer is set like if(payer.getText().toString().trim().length()) != 0)
                //if nothing is selected send the back to make another selection

                if( needPayer == true)
                {
                    needPayer = false;
                    payStatementOutput = payStatementOutput + " will pay ";
                    payStatement.setText(payStatementOutput);
                    needPayee = true;
                }


            }
        });
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
