package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.data.TeamRankType;

import java.util.ArrayList;
import java.util.List;

public class AnalysisViewerTeamIDActivity extends AppCompatActivity {
    private ListView listView;
    private List<Team> league = new ArrayList<>();
    private AnalysisViewer analysisViewer = new AnalysisViewer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_viewer_team_id);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_analysis_viewer);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(AnalysisViewerTeamIDActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;
                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        league = analysisViewer.getLeague(TeamRankType.ID);
        ArrayList<String> rank_team_id = new ArrayList<>();
        for(Team team : league)
        {
            rank_team_id.add(team.getName());
        }
        ArrayAdapter<String> arrayAdapterID = new ArrayAdapter<>(this , R.layout.text_view, rank_team_id);
        listView.setAdapter(arrayAdapterID);
    }

    public void setUpVariables()
    {
        listView = (ListView) findViewById(R.id.listView);
    }

}
