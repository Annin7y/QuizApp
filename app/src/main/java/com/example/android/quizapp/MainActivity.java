
/* Copyright 2016 Anastasia Annin
 *
 * A Udacity Basics Nanodegree Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.android.quizapp.R.id.radioGroup1;
import static com.example.android.quizapp.R.id.radioGroup2;

public class MainActivity extends AppCompatActivity
{
    private int scoreQuiz;
    String ans1 = "Beijing";
    String ans2 = "Alaska";
    Button calculate;
    private TextView mTextScore;
    private static final String KEY_SCORE= "score";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate = (Button) findViewById(R.id.calculate_score);

        mTextScore = findViewById(R.id.total_score);

        displayTotalScore(0);
        scoreQuiz = 0;

        //Code based on the following example:
        //https://codinginflow.com/tutorials/android/restore-variables-on-configuration-change
        if (savedInstanceState != null)
        {
            scoreQuiz = savedInstanceState.getInt(KEY_SCORE);
            mTextScore.setText(String.valueOf(scoreQuiz));
        }

    }

    private void questionQuiz()
    {
        EditText answer1Field = (EditText) findViewById(R.id.answer1_field);
        String answer1 = answer1Field.getText().toString().trim();

        if (answer1.equalsIgnoreCase(ans1))
        {
            scoreQuiz += 1;
        }

        EditText answer2Field = (EditText) findViewById(R.id.answer2_field);
        String answer2 = answer2Field.getText().toString().trim();

        if (answer2.equalsIgnoreCase(ans2))
        {
            scoreQuiz += 1;
        }

        CheckBox madridCheckBox = (CheckBox) findViewById(R.id.checkbox_madrid);
        boolean hasMadrid = madridCheckBox.isChecked();

        CheckBox valenciaCheckBox = (CheckBox) findViewById(R.id.checkbox_valencia);
        boolean hasValencia = valenciaCheckBox.isChecked();

        CheckBox malagaCheckBox = (CheckBox) findViewById(R.id.checkbox_malaga);
        boolean hasMalaga = malagaCheckBox.isChecked();

        CheckBox sevilleCheckBox = (CheckBox) findViewById(R.id.checkbox_seville);
        boolean hasSeville = sevilleCheckBox.isChecked();

        if (hasMadrid && hasSeville && !hasValencia && !hasMalaga)
        {
            scoreQuiz += 1;
        }

        CheckBox frenchCheckBox = (CheckBox) findViewById(R.id.checkbox_french);
        boolean hasFrench = frenchCheckBox.isChecked();

        CheckBox swahiliCheckBox = (CheckBox) findViewById(R.id.checkbox_swahili);
        boolean hasSwahili = swahiliCheckBox.isChecked();

        CheckBox englishCheckBox = (CheckBox) findViewById(R.id.checkbox_english);
        boolean hasEnglish = englishCheckBox.isChecked();

        CheckBox afrikaansCheckBox = (CheckBox) findViewById(R.id.checkbox_afrikaans);
        boolean hasAfrikaans = afrikaansCheckBox.isChecked();

        if (englishCheckBox.isChecked() && afrikaansCheckBox.isChecked() && !frenchCheckBox.isChecked() && !swahiliCheckBox.isChecked())
        {
            scoreQuiz += 1;
        }

    }

    public void onRadioButtonClicked(View view)
    {
        RadioButton radioNile = (RadioButton) findViewById(R.id.radio_nile);
        if (radioNile.isChecked())
        {
            scoreQuiz += 1;
        }
    }

    public void onRadioButton2Clicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.radio_ecuador:
                if (checked)
                    scoreQuiz += 1;
                break;
        }
    }


    public void calculateScore(View view)
    {
        questionQuiz();
        displayTotalScore(scoreQuiz);

        Toast toast1 = Toast.makeText(this, "Score: " + scoreQuiz + " /6", Toast.LENGTH_SHORT);
        toast1.show();

        Button calculate = (Button) findViewById(R.id.calculate_score);
        calculate.setEnabled(false);
    }

    private void displayTotalScore(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.total_score);
        scoreView.setText(String.valueOf(score));

    }

    /**
     * This method is used to reset the question
     */
    public void resetScore(View v)
    {
        displayTotalScore(0);
        scoreQuiz = 0;

        ((EditText) findViewById(R.id.answer1_field)).getText().clear();
        ((EditText) findViewById(R.id.answer2_field)).getText().clear();
        ((RadioGroup) findViewById(radioGroup1)).clearCheck();
        ((RadioGroup) findViewById(radioGroup2)).clearCheck();
        ((CheckBox) findViewById(R.id.checkbox_madrid)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_valencia)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_malaga)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_seville)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_french)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_swahili)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_english)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkbox_afrikaans)).setChecked(false);
        Button calculate = (Button) findViewById(R.id.calculate_score);
        calculate.setEnabled(true);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(KEY_SCORE, scoreQuiz);
        super.onSaveInstanceState(outState);
    }


}


