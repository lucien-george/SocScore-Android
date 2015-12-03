package com.SocScore.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.data.TeamRankType;

import java.util.ArrayList;
import java.util.List;

public class AnalysisViewerTeamGoalsActivity extends AppCompatActivity {

    private ListView listView;
    private List<Team> league = new ArrayList<>();
    private AnalysisViewer analysisViewer = new AnalysisViewer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_viewer_team_goals);
        setUpVariables();
        league = analysisViewer.getLeague(TeamRankType.TOTAL_GOALS);
        TeamAdapter teamAdapter = new TeamAdapter(league , AnalysisViewerTeamGoalsActivity.this);
        listView.setAdapter(teamAdapter);

    }

    public void setUpVariables()
    {
        listView = (ListView) findViewById(R.id.listView);
    }

    public void close_live_dialog(View view)
    {
        Intent intent = new Intent(this , AnalysisViewerActivity.class);
        startActivity(intent);
    }

    private class TeamAdapter extends ArrayAdapter<Team>
    {
        private List<Team> league_team;
        private Context context;
        private int layout_id;
        public TeamAdapter(List<Team> league , Context ctx)
        {
            super(ctx, R.layout.list_view_layout, league);
            this.league_team = league;
            this.context = ctx;
            this.layout_id = R.layout.list_view_layout;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_view_layout , parent , false);
            }
            TextView tv_name = (TextView) convertView.findViewById(R.id.team_name);
            TextView tv_id = (TextView) convertView.findViewById(R.id.team_id);
            Team team = league_team.get(position);
            tv_name.setText(team.getName());
            tv_id.setText("Total goals : " + team.getTotalGoalsScored());
            return convertView;
        }
    }
}
