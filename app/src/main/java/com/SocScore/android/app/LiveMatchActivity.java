package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.SocScore.framework.data.Match;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.LiveInput;

public class LiveMatchActivity extends AppCompatActivity {
    private Chronometer chrono;
    private TextView tv_team1;
    private TextView tv_team2;
    private Button button_team1;
    private Button button_team2;
    private Button button_yellow_card;
    private Button button_red_card;
    private Button button_goal;
    private Button button_shots;
    private TextView tv_team1_score;
    private TextView tv_team2_score;
    private EditText add_player_data;
    private Button create_new_match;
    String str_team1;
    String str_team2;
    private Context context = null;
    private Dialog dialog = null;
    private ImageButton close_dialog;
    private EditText add_player_to_team;
    private Button add_to_team1;
    private Button add_to_team2;
    private Team team1;
    private Team team2;
    LiveInput liveInput = new LiveInput();
    int i = 0;
    int j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_match);
        setUpVariables();
        str_team1 = getIntent().getStringExtra("team1");
        str_team2 = getIntent().getStringExtra("team2");
        createNewMatch();
        context = LiveMatchActivity.this;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_players);
        setUpDialog();
        //dialog.show();
    }

    public void setUpVariables() {
        chrono = (Chronometer) findViewById(R.id.chronometer);
        tv_team1 = (TextView) findViewById(R.id.team1);
        tv_team2 = (TextView) findViewById(R.id.team2);
        button_team1 = (Button) findViewById(R.id.button_team1);
        button_team2 = (Button) findViewById(R.id.button_team2);
        button_yellow_card = (Button) findViewById(R.id.button_yellow_card);
        button_red_card = (Button) findViewById(R.id.button_red_card);
        button_goal = (Button) findViewById(R.id.button_shots_scored);
        button_shots = (Button) findViewById(R.id.button_number_of_shots);
        tv_team1_score = (TextView) findViewById(R.id.tv_team1_score);
        tv_team2_score = (TextView) findViewById(R.id.tv_team2_score);
        add_player_data = (EditText) findViewById(R.id.et_player);

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

    public void createNewMatch()
    {
        tv_team1.setText(str_team1);
        tv_team2.setText(str_team2);
        button_team1.setText(str_team1);
        button_team2.setText(str_team2);
        team1 = new Team(str_team1);
        team2 = new Team(str_team2);
        liveInput.createMatch(team1, team2);
        chrono.start();
    }

//    public void addToTeam1(View view)
//    {
//        Map<String , Player> playerMap1 = new HashMap<>();
//        this.i = i++;
//        playerMap1.put("player " + i , new Player(add_player_to_team.getText().toString() , team1.getTEAM_ID()));
//        team1.addPlayer(playerMap1.get(i));
//        (playerMap1.get(i)).startMatch();
//    }
//
//    public void addToTeam2(View view)
//    {
//        Map<String , Player> playerMap2 = new HashMap<>();
//        this.j = j++;
//        playerMap2.put("player " + i, new Player(add_player_to_team.getText().toString(), team1.getTEAM_ID()));
//        team1.addPlayer(playerMap2.get(i));
//        (playerMap2.get(j)).startMatch();
//    }
//
//    public void closeDialog(View view)
//    {
//        liveInput.createMatch(team1, team2);
//        team1.startMatch();
//        team2.startMatch();
//        liveInput.startMatch();
//        dialog.dismiss();
//        button_team1.setPressed(true);
//        button_team1.setBackgroundColor(Color.rgb(210, 140, 56));
//        chrono.start();
//    }




    //TODO: add player to team
    //TODO: Create Match in LiveInput
    public Match editMatch(Match match)
    {
        return match;
    }

    public void accessLiveMatch(View view)
    {
        //TODO: access live match
    }

    public void selectTeam1(View view)
    {
        button_team1.setPressed(true);
        button_team1.setBackgroundColor(Color.rgb(210, 140, 56));
        button_team2.setPressed(false);
        button_team1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
//        button_team1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                button_team1.setPressed(false);
//            }
//        });
        //TODO: select team 1 to input data
    }

    public void selectTeam2(View view)
    {
        button_team2.setPressed(true);
        button_team1.setBackgroundColor(Color.rgb(210, 140, 56));
        button_team1.setPressed(false);
        button_team1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
//        button_team2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                button_team2.setPressed(false);
//            }
//        });
        //TODO: select team 2 to input data
    }

    public void addYellowCard(View view)
    {
        if(button_team1.isPressed())
        {

        }
        else if(button_team2.isPressed())
        {

        }
        //TODO: add yellow card incrementation
    }

    public void addRedCard(View view)
    {
        if(button_team1.isPressed())
        {

        }
        else if(button_team2.isPressed())
        {

        }
        //TODO: add red card incrementation
    }

    public void addShotScored(View view)
    {
        if(button_team1.isPressed())
        {

        }
        else if(button_team2.isPressed())
        {

        }
        //TODO: add Shots scored incrementation
    }

    public void addShotsNotScored(View view)
    {
        if(button_team1.isPressed())
        {

        }
        else if(button_team2.isPressed())
        {

        }
        //TODO: add Shots not score incrementation
    }


}
