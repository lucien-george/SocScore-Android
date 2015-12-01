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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.data.InfractionType;
import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Match;
import com.SocScore.framework.data.Player;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

import org.joda.time.LocalDateTime;

import java.util.List;

public class LiveMatchActivity extends AppCompatActivity {
    private Chronometer chrono;
//    private Button button_yellow_card;
//    private Button button_red_card;
//    private Button button_goal;
//    private Button button_shots;
//    private TextView tv_team1_score;
//    private TextView tv_team2_score;
//    private EditText add_player_data;
//    private Button create_new_match;
    private String str_team1;
    private String str_team2;
    private Context context = null;
    private Dialog dialog = null;
    private ImageButton close_dialog;
    private EditText add_player_to_team;
    private Button add_to_team1;
    private Button add_to_team2;
    private Team team1;
    private Team team2;
    private LiveInput liveInput;
    private RadioButton radio_team1;
    private RadioButton radio_team2;
    private TextView team1_score;
    private TextView team2_score;
    private Button increment_score;
    private RadioGroup select_team;
    private RadioButton radio_shots;
    private RadioButton radio_yellow;
    private RadioButton radio_red;
    private RadioButton radio_penalty;
    private RadioGroup add_feature;
    private EditText et_player_name;
    private Match match;
    private static int count1 = 1;
    private static int count2 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_match);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_live);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(LiveMatchActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_main:
                        Intent liveInput = new Intent(LiveMatchActivity.this, MainActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_batch:
                        Intent batchInput = new Intent(LiveMatchActivity.this, BatchInputActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(LiveMatchActivity.this, AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        int int_team1_ID = Integer.parseInt(getIntent().getStringExtra("str_team1_ID"));
        int int_team2_ID = Integer.parseInt(getIntent().getStringExtra("str_team2_ID"));
        team1 = LeagueAnalysis.findTeam(int_team1_ID);
        team2 = LeagueAnalysis.findTeam(int_team2_ID);
        str_team1 = team1.getName();
        str_team2 = team2.getName();
        radio_team1.setText(str_team1);
        radio_team2.setText(str_team2);
        liveInput.createMatch(team1, team2);
        liveInput.startMatch();
        context = LiveMatchActivity.this;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_players);
        setUpDialog();
        //dialog.show();
    }

    public void setUpVariables() {
        liveInput = new LiveInput();
        AccessManager.authenticate(1234);
        liveInput= (LiveInput) AccessManager.setInputType(ScoreKeeperType.LIVE_INPUT);
        chrono = (Chronometer) findViewById(R.id.chronometer);
        select_team = (RadioGroup) findViewById(R.id.select_team);
        team1_score = (TextView) findViewById(R.id.tv_team1_score);
        team2_score = (TextView) findViewById(R.id.tv_team2_score);
        increment_score = (Button) findViewById(R.id.increment_score);
        radio_team1 = (RadioButton) findViewById(R.id.radio_team1);
        radio_team2 = (RadioButton) findViewById(R.id.radio_team2);
        radio_shots = (RadioButton) findViewById(R.id.radio_shots);
        radio_yellow = (RadioButton) findViewById(R.id.radio_yellow);
        radio_red = (RadioButton) findViewById(R.id.radio_red);
        et_player_name = (EditText) findViewById(R.id.et_player_match);
        radio_penalty = (RadioButton) findViewById(R.id.radio_penalty);

    }

    public void setUpDialog()
    {
        close_dialog = (ImageButton) dialog.findViewById(R.id.close_dialog);
        add_player_to_team = (EditText) dialog.findViewById(R.id.et_add_player);
        add_to_team1 = (Button) dialog.findViewById(R.id.add_to_team1);
        add_to_team2 = (Button) dialog.findViewById(R.id.add_to_team2);
        add_to_team1.setText(str_team1);
        add_to_team2.setText(str_team2);
    }


    public void addToTeam1(View view)
    {
        team1.addPlayer(new Player(add_player_to_team.getText().toString(), team1.getTEAM_ID()));
        add_player_to_team.setText("");
    }

    public void addToTeam2(View view)
    {
        team2.addPlayer(new Player(add_player_to_team.getText().toString(), team2.getTEAM_ID()));
        add_player_to_team.setText("");
    }

    public void closeDialog(View view)
    {
        dialog.dismiss();
        chrono.start();
    }

    //TODO: add player to team
    //TODO: Create Match in LiveInput


    public void incrementScore(View view)
    {
        List<Player> players_team1 = team1.getPlayers();
        List<Player> players_team2 = team2.getPlayers();
        String str_player_name = et_player_name.getText().toString();
        for(Player player : players_team1)
        {
            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                int i = count1++;
                team1_score.setText("" + i);
                player.shoots(new LocalDateTime(), true , liveInput.getCurrentMatch().getMATCH_ID());
            }
        }
        for(Player player : players_team2)
        {
            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                int j = count2++;
                team2_score.setText("" + j);
                player.shoots(new LocalDateTime() , true , liveInput.getCurrentMatch().getMATCH_ID());
            }
        }
        et_player_name.setText("");
    }

    public void assignFeatureToPlayer(View view) {
        List<Player> players_team1 = team1.getPlayers();
        List<Player> players_team2 = team2.getPlayers();
        String str_player_name = et_player_name.getText().toString();
        int selectedID = select_team.getCheckedRadioButtonId();
        int selectedID2 = add_feature.getCheckedRadioButtonId();
        switch (selectedID)
        {
            case R.id.radio_team1:
                switch (selectedID2)
                {
                    case R.id.radio_shots:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.shoots(new LocalDateTime() , false , liveInput.getCurrentMatch().getMATCH_ID());
                            }
                        }
                    case R.id.radio_yellow:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.YELLOW_CARD , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Yellow card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();

                            }
                        }
                    case R.id.radio_red:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.RED_CARD , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Red card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();
                            }
                        }
                    case R.id.radio_penalty:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.PENALTY , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Penalty card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();
                            }
                        }
                }
            case R.id.radio_team2:
                switch (selectedID2)
                {
                    case R.id.radio_shots:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.shoots(new LocalDateTime() ,false , liveInput.getCurrentMatch().getMATCH_ID());
                            }
                        }
                    case R.id.radio_yellow:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.YELLOW_CARD , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Yellow card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();
                            }
                        }
                    case R.id.radio_red:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.RED_CARD , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Red card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();

                            }
                        }
                    case R.id.radio_penalty:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.PENALTY , new LocalDateTime() , liveInput.getCurrentMatch().getMATCH_ID());
                                Toast.makeText(getApplicationContext(), "Penalty card assigned to " + player.getPLAYER_NAME() , Toast.LENGTH_LONG).show();

                            }
                        }
                }

        }
    }
}
