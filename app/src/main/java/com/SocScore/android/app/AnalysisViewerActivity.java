package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.data.TeamRankType;

import java.util.ArrayList;
import java.util.List;

public class AnalysisViewerActivity extends AppCompatActivity {

    private RadioGroup team_rank;
    private RadioButton TEAM_ID;
    private RadioButton TEAM_SCORE;
    private RadioButton TOTAL_GOALS;
    private Button show_league;
    private ListView listView;
    private AnalysisViewer analysisViewer = new AnalysisViewer();
    private List<Team> league = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_viewer);
        setUpVariables();

    }

    public void setUpVariables()
    {
        team_rank = (RadioGroup) findViewById(R.id.team_rank_type);
        TEAM_ID = (RadioButton) findViewById(R.id.rank_team_id);
        TEAM_SCORE = (RadioButton) findViewById(R.id.rank_team_score);
        TOTAL_GOALS = (RadioButton) findViewById(R.id.rank_total_goals);
        show_league = (Button) findViewById(R.id.show_league);
        listView = (ListView) findViewById(R.id.listView);
    }

    public void showLeague(View view)
    {
        int selectedID = team_rank.getCheckedRadioButtonId();
        switch(selectedID)
        {
            case (R.id.rank_team_id) :
                Intent teamIDActivity = new Intent(this, AnalysisViewerTeamIDActivity.class);
                startActivity(teamIDActivity);
            case (R.id.rank_team_score) :
                league = analysisViewer.getLeague(TeamRankType.TEAM_SCORE);
                ArrayList<String> rank_team_score = new ArrayList<>();
                for(Team team : league)
                {
                    rank_team_score.add(team.getName());
                }
                ArrayAdapter<String> arrayAdapterScore = new ArrayAdapter<>(this , R.layout.list_view_layout, rank_team_score);
                listView.setAdapter(arrayAdapterScore);
            case (R.id.rank_total_goals) :
                league = analysisViewer.getLeague(TeamRankType.TOTAL_GOALS);
                ArrayList<String> rank_team_total_goals = new ArrayList<>();
                for(Team team : league)
                {
                    rank_team_total_goals.add(team.getName());
                }
                ArrayAdapter<String> arrayAdapterTotalGoals = new ArrayAdapter<>(this , R.layout.list_view_layout, rank_team_total_goals);
                listView.setAdapter(arrayAdapterTotalGoals);
        }
    }
}
