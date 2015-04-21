package group6.cis46300.monopolytracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class currentGame extends ActionBarActivity {

    String player1, player2, player3, player4, player5, player6, player7, player8;
    int counter = 0;
    String[] players = new String[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);

        final EditText namePlayer1 = (EditText) findViewById(R.id.namePlayer1);
        final EditText namePlayer2 = (EditText) findViewById(R.id.namePlayer2);
        final EditText namePlayer3 = (EditText) findViewById(R.id.namePlayer3);
        final EditText namePlayer4 = (EditText) findViewById(R.id.namePlayer4);
        final EditText namePlayer5 = (EditText) findViewById(R.id.namePlayer5);
        final EditText namePlayer6 = (EditText) findViewById(R.id.namePlayer6);
        final EditText namePlayer7 = (EditText) findViewById(R.id.namePlayer7);
        final EditText namePlayer8 = (EditText) findViewById(R.id.namePlayer8);

        Button startGame = (Button) findViewById(R.id.startGame);

        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if((namePlayer1.getText().toString()) != "") {
                    player1 = (namePlayer1.getText().toString());
                    players[0] = player1;
                }
                if((namePlayer2.getText().toString()) != "") {
                    player2 = (namePlayer2.getText().toString());
                    players[1] = player2;
                }
                if((namePlayer3.getText().toString()) != "") {
                    player3 = (namePlayer3.getText().toString());
                    players[2] = player1;
                }
                if((namePlayer4.getText().toString()) != "") {
                    player4 = (namePlayer4.getText().toString());
                    counter++;
                }
                if((namePlayer5.getText().toString()) != "") {
                    player5 = (namePlayer5.getText().toString());
                    counter++;
                }
                if((namePlayer6.getText().toString()) != "") {
                    player6 = (namePlayer6.getText().toString());
                    counter++;
                }
                if((namePlayer7.getText().toString()) != "") {
                    player7 = (namePlayer7.getText().toString());
                    counter++;
                }
                if((namePlayer8.getText().toString()) != "") {
                    player8 = (namePlayer8.getText().toString());
                    counter++;
                }


                String[] players = new String[counter];
                for(int i = 0; i < counter-1; i++)
                {
                    players[i] = player[i]
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_current_game, menu);
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
