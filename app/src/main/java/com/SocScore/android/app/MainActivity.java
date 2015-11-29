package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Dialog live_input_dialog = null;
    private Context context = null;
    private Button create_new_match_live_button;
    private Button access_new_match_live_button;
    private Button league_input;
    private EditText add_team1;
    private EditText add_team2;
    private Button submitTeamLive;
    private ImageButton close_input_dialog;
    String str_team1;
    String str_team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        live_input_dialog = new Dialog(context);
        setUpVariables();
        setUpDialog();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(MainActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(MainActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_batch:
                        Intent batchInput = new Intent(MainActivity.this, BatchInputActivity.class);
                        startActivity(batchInput);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
    }


    public void setUpDialog()
    {
        live_input_dialog.setContentView(R.layout.dialog_live_input);
        create_new_match_live_button = (Button) live_input_dialog.findViewById(R.id.create_new_match_button_live_dialog);
        access_new_match_live_button = (Button) live_input_dialog.findViewById(R.id.access_live_match_button_live_dialog);
        add_team1 = (EditText) live_input_dialog.findViewById(R.id.et_team1);
        add_team2 = (EditText) live_input_dialog.findViewById(R.id.et_team2);
    }

    public void setUpVariables()
    {
        create_new_match_live_button = (Button) findViewById(R.id.create_new_match_button_live_dialog);
        access_new_match_live_button = (Button) findViewById(R.id.access_live_match_button_live_dialog);
        league_input = (Button) findViewById(R.id.league_input);
    }

    public void enterBatchInput(View view)
    {
        Intent batchMode = new Intent(this , BatchInputActivity.class);
        startActivity(batchMode);
    }

    public void enterLiveInput(View view)
    {
        live_input_dialog.show();
    }

    public void close_live_dialog(View view)
    {
        live_input_dialog.dismiss();
    }

    public void accessLeagueInput(View view)
    {
       Intent leagueInput = new Intent(this , LeagueInputActivity.class);
        startActivity(leagueInput);
    }

    public void createNewMatch(View view)
    {
        str_team1 = add_team1.getText().toString();
        str_team2 = add_team2.getText().toString();
//        Team team1 = new Team(team_1);
//        Team team2 = new Team(team_2);
//        LiveInput live_input = new LiveInput();
//        live_input.createMatch(team1, team2);
//        live_match_dialog.show();
        Intent live_match_activity = new Intent(this, LiveMatchActivity.class);
        live_match_activity.putExtra("team1" , str_team1);
        live_match_activity.putExtra("team2" , str_team2);
        live_input_dialog.dismiss();
        startActivity(live_match_activity);
//        tv_team1.setText(team_1);
//        tv_team2.setText(team_2);
//        button_team1.setText(team_1);
//        button_team2.setText(team_2);
//        chrono.start();
    }

    public void accessAnalysisViewer(View view)
    {
        Intent analysisViewer = new Intent(this , AnalysisViewerActivity.class);
        startActivity(analysisViewer);
    }
}
