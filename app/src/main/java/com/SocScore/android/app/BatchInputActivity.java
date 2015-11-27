package com.SocScore.android.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SocScore.framework.data.Team;

public class BatchInputActivity extends AppCompatActivity {

    private TextView tv_create_new_team;
    private FloatingActionButton fab_create_new_team;
    private LinearLayout batch_input_linear_layout;
    private EditText add_team1;
    private EditText add_team2;
    private Button submitTeamBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_input);
        setUpVariables();
        createNewMatchBatch();
    }

    public void setUpVariables()
    {
        tv_create_new_team = (TextView) findViewById(R.id.tv_create_team);
        fab_create_new_team = (FloatingActionButton) findViewById(R.id.fab_create_team);
        batch_input_linear_layout = (LinearLayout) findViewById(R.id.batch_input_linear_layout);
    }

    public void fabCreateNewTeam(View view)
    {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        batch_input_linear_layout.addView(createNewEditText(add_team1) , layoutParams);
        batch_input_linear_layout.addView(createNewEditText(add_team2) , layoutParams);
        batch_input_linear_layout.addView(createNewButton(submitTeamBatch) , layoutParams);
    }

    public void createNewMatchBatch()
    {
        submitTeamBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String team_1 = add_team1.getText().toString();
                String team_2 = add_team2.getText().toString();
                Team team1 = new Team(team_1);
                Team team2 = new Team(team_2);
                //createMatch(team1 , team2 , startTime , endTime)
                //TODO: Create match in Batch Input
            }
        });
    }

    private EditText createNewEditText(EditText editText)
    {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editText = new EditText(this);
        editText.setLayoutParams(layoutParams);
        return editText;
    }
    private Button createNewButton(Button button)
    {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        button = new Button(this);
        button.setLayoutParams(layoutParams);
        return button;
    }

}
