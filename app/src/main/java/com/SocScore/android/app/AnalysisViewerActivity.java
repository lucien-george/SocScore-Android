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

public class AnalysisViewerActivity extends AppCompatActivity {

    private RadioGroup team_rank;
    private RadioButton TEAM_ID;
    private RadioButton TEAM_SCORE;
    private RadioButton TOTAL_GOALS;
    private Button show_league;
    private ListView listView;
    private AnalysisViewer analysisViewer = new AnalysisViewer();
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
        Intent listViewActivity = new Intent(AnalysisViewerActivity.this, ListViewActivity.class);
        Bundle rank_bundle = new Bundle();
        ArrayList<Team> rank_team_list = new ArrayList<Team>();
        ArrayAdapter<Team> arrayAdapter = new ArrayAdapter<Team>(this , android.R.layout.simple_list_item_1 , rank_team_list);
        int selectedID = team_rank.getCheckedRadioButtonId();
        switch(selectedID)
        {
            case (R.id.rank_team_id) :
                rank_team_list = (ArrayList<Team>) analysisViewer.getLeague(TeamRankType.ID);
                listView.setAdapter(arrayAdapter);
//                rank_bundle.putSerializable("rank_team_list" , (Serializable) rank_team_list);
//                startActivity(listViewActivity);
            case (R.id.rank_team_score) :
                rank_team_list = (ArrayList<Team>) analysisViewer.getLeague(TeamRankType.TEAM_SCORE);
                listView.setAdapter(arrayAdapter);
//                listViewActivity = new Intent(AnalysisViewerActivity.this, ListViewActivity.class);
//                rank_bundle.putSerializable("rank_team_list", (Serializable) rank_team_list);
                startActivity(listViewActivity);
            case (R.id.rank_total_goals) :
                rank_team_list = (ArrayList<Team>) analysisViewer.getLeague(TeamRankType.TOTAL_GOALS);
                listView.setAdapter(arrayAdapter);
//                listViewActivity = new Intent(AnalysisViewerActivity.this, ListViewActivity.class);
//                rank_bundle.putSerializable("rank_team_list", (Serializable) rank_team_list);
//                startActivity(listViewActivity);
        }
    }
}
