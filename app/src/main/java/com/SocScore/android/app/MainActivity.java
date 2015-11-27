package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.LiveInput;

public class MainActivity extends AppCompatActivity {

    private Dialog live_input_dialog = null;
    private Dialog live_match_dialog = null;
    private Context context = null;
    private Button create_new_match_live_button;
    private Button access_new_match_live_button;
    private TextView tv_team1;
    private TextView tv_team2;
    private Button button_team1;
    private Button button_team2;
    private TextView tv_team1_score;
    private TextView tv_team2_score;
    private Button league_input;
    private Button button_yellow_card;
    private Button button_red_card;
    private Button button_goal;
    private Button button_shots;
    private EditText add_player_data;
    private LinearLayout live_input_dialog_input_layout;
    private EditText add_team1;
    private EditText add_team2;
    private Button submitTeamLive;
    private Chronometer chrono;
    private ImageButton close_input_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        live_input_dialog = new Dialog(context);
        live_match_dialog = new Dialog(context);
        setUpVariables();
        setUpDialog();
    }


    public void setUpDialog()
    {
        live_input_dialog.setContentView(R.layout.dialog_live_input);
        create_new_match_live_button = (Button) live_input_dialog.findViewById(R.id.create_new_match_button_live_dialog);
        access_new_match_live_button = (Button) live_input_dialog.findViewById(R.id.access_live_match_button_live_dialog);
        live_match_dialog.setContentView(R.layout.dialog_live_match);
        tv_team1 = (TextView) live_match_dialog.findViewById(R.id.team1);
        tv_team2 = (TextView) live_match_dialog.findViewById(R.id.team2);
        button_team1 = (Button) live_match_dialog.findViewById(R.id.button_team1);
        button_team1.setPressed(true);
        button_team2 = (Button) live_match_dialog.findViewById(R.id.button_team2);
        button_team2.setPressed(false);
        button_yellow_card = (Button) live_match_dialog.findViewById(R.id.button_yellow_card);
        button_red_card = (Button) live_match_dialog.findViewById(R.id.button_red_card);
        button_goal = (Button) live_match_dialog.findViewById(R.id.button_shots_scored);
        button_shots = (Button) live_match_dialog.findViewById(R.id.button_number_of_shots);
        add_player_data = (EditText) live_match_dialog.findViewById(R.id.et_player);
        tv_team1_score = (TextView) live_match_dialog.findViewById(R.id.tv_team1_score);
        tv_team2_score = (TextView) live_match_dialog.findViewById(R.id.tv_team2_score);
        chrono = (Chronometer) live_match_dialog.findViewById(R.id.chronometer);
        add_team1 = (EditText) live_input_dialog.findViewById(R.id.et_team1);
        add_team2 = (EditText) live_input_dialog.findViewById(R.id.et_team2);
        close_input_dialog = (ImageButton) live_match_dialog.findViewById(R.id.close_live_match_dialog);
    }

    public void setUpVariables()
    {
        create_new_match_live_button = (Button) findViewById(R.id.create_new_match_button_live_dialog);
        access_new_match_live_button = (Button) findViewById(R.id.access_live_match_button_live_dialog);
        league_input = (Button) findViewById(R.id.league_input);
        live_input_dialog_input_layout = (LinearLayout) findViewById(R.id.live_input_dialog_layout);
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
       live_input_dialog.show();
    }

    public void createNewMatch(View view)
    {
        String team_1 = add_team1.getText().toString();
        String team_2 = add_team2.getText().toString();
        Team team1 = new Team(team_1);
        Team team2 = new Team(team_2);
        LiveInput live_input = new LiveInput();
        live_input.createMatch(team1, team2);
        live_input_dialog.dismiss();
        live_match_dialog.show();
        tv_team1.setText(team_1);
        tv_team2.setText(team_2);
        button_team1.setText(team_1);
        button_team2.setText(team_2);
        chrono.start();
    }

//    private EditText createNewEditText(EditText editText)
//    {
//        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        editText = new EditText(this);
//        editText.setLayoutParams(layoutParams);
//        return editText;
//    }
//    private Button createNewButton(Button button)
//    {
//        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        button = new Button(this);
//        button.setLayoutParams(layoutParams);
//        return button;
//    }

//    public void create_Match_Live()
//    {
//        submitTeamLive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                String team_1 = add_team1.getText().toString();
//                String team_2 = add_team2.getText().toString();
//                Team team1 = new Team(team_1);
//                Team team2 = new Team(team_2);
//                //createMatch(team1 , team2);
//                live_input_dialog.dismiss();
//                live_match_dialog.show();
//                tv_team1.setText(team_1);
//                tv_team2.setText(team_2);
//                button_team1.setText(team_1);
//                button_team2.setText(team_2);
//                chrono.start();
//            }
//        });
//    }
//
//    //TODO: add player to team
//    //TODO: Create Match in LiveInput
//    public Match editMatch(Match match)
//    {
//        return match;
//    }
//
//    public void accessLiveMatch(View view)
//    {
//        //TODO: access live match
//    }
//
//    public void close_live_match_dialog(View view)
//    {
//        live_match_dialog.dismiss();
//    }
//
//    public void selectTeam1(View view)
//    {
//        button_team1.setPressed(true);
//        button_team2.setPressed(false);
////        button_team1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v)
////            {
////                button_team1.setPressed(false);
////            }
////        });
//        //TODO: select team 1 to input data
//    }
//
//    public void selectTeam2(View view)
//    {
//        button_team2.setPressed(true);
//        button_team1.setPressed(false);
////        button_team2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v)
////            {
////                button_team2.setPressed(false);
////            }
////        });
//        //TODO: select team 2 to input data
//    }
//
//    public void addYellowCard(View view)
//    {
//        if(button_team1.isPressed())
//        {
//
//        }
//        else if(button_team2.isPressed())
//        {
//
//        }
//        //TODO: add yellow card incrementation
//    }
//
//    public void addRedCard(View view)
//    {
//        if(button_team1.isPressed())
//        {
//
//        }
//        else if(button_team2.isPressed())
//        {
//
//        }
//        //TODO: add red card incrementation
//    }
//
//    public void addShotScored(View view)
//    {
//        if(button_team1.isPressed())
//        {
//
//        }
//        else if(button_team2.isPressed())
//        {
//
//        }
//        //TODO: add Shots scored incrementation
//    }
//
//    public void addShotsNotScored(View view)
//    {
//        if(button_team1.isPressed())
//        {
//
//        }
//        else if(button_team2.isPressed())
//        {
//
//        }
//        //TODO: add Shots not score incrementation
//    }
}
