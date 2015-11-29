package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.BatchInput;

import org.joda.time.LocalDateTime;
import org.joda.time.format.ISODateTimeFormat;

public class BatchInputActivity extends AppCompatActivity {

    private FloatingActionButton fab_create_new_team;
    private EditText et_team1;
    private EditText et_team2;
    private EditText et_start_time_hours;
    private EditText et_end_time_hours;
    private EditText et_start_time_minutes;
    private EditText et_end_time_minutes;
    private String str_team1;
    private String str_team2;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    Team team1;
    Team team2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_input);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_batch);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(BatchInputActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(BatchInputActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_main:
                        Intent batchInput = new Intent(BatchInputActivity.this, MainActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(BatchInputActivity.this , AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        fab_create_new_team = (FloatingActionButton) findViewById(R.id.fab_create_team);
        fab_create_new_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_team1 = et_team1.getText().toString();
                team1 = new Team(str_team1);
                str_team2 = et_team2.getText().toString();
                team2 = new Team(str_team2);
                startTime = LocalDateTime.parse(et_start_time_hours.getText().toString() + " : " + et_start_time_minutes.getText().toString() , ISODateTimeFormat.basicTime());
                endTime = LocalDateTime.parse(et_end_time_hours.getText().toString() + " : " + et_end_time_minutes.getText().toString(), ISODateTimeFormat.basicTime());
                BatchInput batchInput = new BatchInput();
                batchInput.createMatch(team1 , team2 , startTime , endTime);
            }
        });
    }

    public void setUpVariables()
    {
        et_team1 = (EditText) findViewById(R.id.et_team1);
        et_team2 = (EditText) findViewById(R.id.et_team2);
        et_start_time_hours = (EditText) findViewById(R.id.et_start_time_hours);
        et_end_time_hours = (EditText) findViewById(R.id.et_end_time_hours);
        et_start_time_minutes = (EditText) findViewById(R.id.et_start_time_minutes);
        et_end_time_minutes = (EditText) findViewById(R.id.et_end_time_minutes);
    }

}
