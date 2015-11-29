package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.SocScore.framework.LeagueInput;
import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Team;

import java.util.ArrayList;
import java.util.List;

public class LeagueInputActivity extends AppCompatActivity {

    private LeagueInput leagueInput = new LeagueInput();
    private EditText et_add_remove_team;
    private Button add_team;
    private Button remove_team;
    private String str_add_remove_team;
    private static List<Team> league = new ArrayList<>();
    private Button add_player;
    private EditText add_player_name;
    private EditText add_player_ID;
    private String str_name;
    private int ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_input);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_league);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_batch:
                        Intent leagueInput = new Intent(LeagueInputActivity.this, BatchInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(LeagueInputActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_main:
                        Intent batchInput = new Intent(LeagueInputActivity.this, MainActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(LeagueInputActivity.this , AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });

    }

    public void setUpVariables()
    {
        et_add_remove_team = (EditText) findViewById(R.id.et_add_remove_team);
        add_team = (Button) findViewById(R.id.add_team);
        remove_team = (Button) findViewById(R.id.remove_team);
        add_player = (Button) findViewById(R.id.add_player);
        add_player_name = (EditText) findViewById(R.id.et_add_player_name);
        add_player_ID = (EditText) findViewById(R.id.et_add_player_id);
    }

    public void addTeamToLeague(View view)
    {
        str_add_remove_team = et_add_remove_team.getText().toString();
        leagueInput.addTeamToLeague(str_add_remove_team);
        et_add_remove_team.setText("");
    }

    public int findTeamID(String str)
    {
        league = LeagueAnalysis.getLeague();
        for(Team team : league)
        {
            if(team.getName().contentEquals(str_add_remove_team))
            {
                return team.getTEAM_ID();
            }
            throw new NullPointerException("Could not find team: " + str_add_remove_team);
        }
        return -1;
    }

    public boolean foundTeam(String str)
    {
        if (findTeamID(str) == -1)
        {
            return false;
        }
        return true;
    }

    public void removeTeamFromLeague(View view)
    {
        str_add_remove_team = et_add_remove_team.getText().toString();
        if(foundTeam(str_add_remove_team))
        {
            leagueInput.removeTeamFromLeague(findTeamID(str_add_remove_team));
        }
        else
        {
            throw new NullPointerException("Could not find team: " + str_add_remove_team);
        }
        et_add_remove_team.setText("");
    }

    public void addPlayerToTeam(View view)
    {
        str_name = add_player_name.getText().toString();
        ID = Integer.parseInt(add_player_ID.getText().toString());
        leagueInput.addNewPlayerToTeam(str_name , ID);
    }
}
