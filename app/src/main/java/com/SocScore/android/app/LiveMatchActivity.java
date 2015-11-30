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

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.data.InfractionType;
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
    LiveInput liveInput;
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
        str_team1 = getIntent().getStringExtra("team1");
        str_team2 = getIntent().getStringExtra("team2");
        radio_team1.setText(str_team1);
        radio_team2.setText(str_team2);
        team1 = new Team(str_team1);
        team2 = new Team(str_team2);
//        match = liveInput.createMatch(team1, team2);
//        liveInput.startMatch();
        context = LiveMatchActivity.this;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_players);
        setUpDialog();
        dialog.show();
    }

    public void setUpVariables() {
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

//        button_team1 = (Button) findViewById(R.id.button_team1);
//        button_team2 = (Button) findViewById(R.id.button_team2);
//        button_yellow_card = (Button) findViewById(R.id.button_yellow_card);
//        button_red_card = (Button) findViewById(R.id.button_red_card);
//        button_goal = (Button) findViewById(R.id.button_shots_scored);
//        button_shots = (Button) findViewById(R.id.button_number_of_shots);
//        tv_team1_score = (TextView) findViewById(R.id.tv_team1_score);
//        tv_team2_score = (TextView) findViewById(R.id.tv_team2_score);
//        add_player_data = (EditText) findViewById(R.id.et_player);

    }

    public void setUpDialog()
    {
        AccessManager.authenticate(1234);
        liveInput= (LiveInput)AccessManager.setInputType(ScoreKeeperType.LIVE_INPUT);
        close_dialog = (ImageButton) dialog.findViewById(R.id.close_dialog);
        add_player_to_team = (EditText) dialog.findViewById(R.id.et_add_player);
        add_to_team1 = (Button) dialog.findViewById(R.id.add_to_team1);
        add_to_team2 = (Button) dialog.findViewById(R.id.add_to_team2);
        add_to_team1.setText(str_team1);
        add_to_team2.setText(str_team2);
    }

//    public void createNewMatch()
//    {
//        team1 = new Team(str_team1);
//        team2 = new Team(str_team2);
//        tv_team1.setText(str_team1);
//        tv_team2.setText(str_team2);
//        button_team1.setText(str_team1);
//        button_team2.setText(str_team2);
//        team1 = new Team(str_team1);
//        team2 = new Team(str_team2);
//        button_team1.setSelected(true);
//        button_team1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(210, 140, 56)));
//        chrono.start();
//    }

//    public void buttonColor()
//    {
//        if(button_team1.isSelected())
//        {
//            button_team1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(210, 140, 56)));
//            button_team2.setSelected(false);
//            button_team2.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
//        }
//        else
//        {
//            button_team2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(210, 140, 56)));
//            button_team1.setSelected(false);
//            button_team1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
//        }
//    }

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
//        button_team1.setPressed(true);
//        button_team1.setBackgroundColor(Color.rgb(210, 140, 56));
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
                int i = 0;
                i++;
                team1_score.setText("" + i);
                player.shoots(new LocalDateTime(), true , match.getMATCH_ID());
            }
        }
        for(Player player : players_team2)
        {
            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                int j = 0;
                j++;
                team2_score.setText("" + j);
                player.shoots(new LocalDateTime() , true , match.getMATCH_ID());
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
                                player.shoots(new LocalDateTime() , false , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_yellow:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.YELLOW_CARD , new LocalDateTime() , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_red:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.RED_CARD , new LocalDateTime() , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_penalty:
                        for(Player player : players_team1)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.PENALTY , new LocalDateTime() , match.getMATCH_ID());
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
                                player.shoots(new LocalDateTime() , false , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_yellow:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.YELLOW_CARD , new LocalDateTime() , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_red:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.RED_CARD , new LocalDateTime() , match.getMATCH_ID());
                            }
                        }
                    case R.id.radio_penalty:
                        for(Player player : players_team2)
                        {
                            if(str_player_name.equalsIgnoreCase(player.getPLAYER_NAME()))
                            {
                                player.commitsInfraction(InfractionType.PENALTY , new LocalDateTime() , match.getMATCH_ID());
                            }
                        }
                }

        }
    }
}
