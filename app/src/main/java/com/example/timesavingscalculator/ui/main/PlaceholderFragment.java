package com.example.timesavingscalculator.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.timesavingscalculator.R;

/**
 * A placeholder fragment containing a simple view.
 */

// TODO: 24/09/2019  accept float as input
// TODO: 24/09/2019  restrict the output to only so many decimal places

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private TextView thirdInput, fourthInput, thirdOutput, fourthOutput, fifthOutput;
    private EditText editText4;

    ////
    private TextView textView10, textView11, textView12, textView13, textView14;
    private EditText editText, editText2, editText3;
    private float editTextCounter, editText2Counter, editText3Counter, editText4Counter;

    private boolean isItSecondScreen;

    ////

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);

        thirdInput = root.findViewById(R.id.thirdTextInput);
        fourthInput = root.findViewById(R.id.fourthTextInput);

        thirdOutput = root.findViewById(R.id.thirdTextOutput);
        fourthOutput = root.findViewById(R.id.fourthTextOutput);
        fifthOutput = root.findViewById(R.id.fifthTextOutput);

        editText4 = root.findViewById(R.id.editText4);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                //if s = 1 set them up one way, otherwise set them up different way. Do it for all the variables.
                if (s.equals("1")){
                    thirdInput.setText("average awake time in hours:");
                    fourthInput.setText("");
                    thirdOutput.setText("Output in awake days:");
                    fourthOutput.setText("Output in awake weeks:");
                    fifthOutput.setText("Output in awake years:");
                    editText4.setVisibility(View.GONE); //makes it invisible as it's not needed
                    editText3Counter = 16;
                    editText3.setText("16");
                    isItSecondScreen = false;

                } else if (s.equals("2")) {
                    thirdInput.setText("average work day in hours:");
                    fourthInput.setText("average shifts per month:");
                    thirdOutput.setText("Output in work days:");
                    fourthOutput.setText("Output in work weeks:");
                    fifthOutput.setText("Output in work years:");
                    editText4.setVisibility(View.VISIBLE);
                    editText3Counter = 9;
                    editText3.setText("9");
                    editText4Counter = 23; //based on your data from excel
                    editText4.setText("23");
                    isItSecondScreen = true;
                }
            }
        });

        textView10 = root.findViewById(R.id.textView10);
        textView11 = root.findViewById(R.id.textView11);
        textView12 = root.findViewById(R.id.textView12);
        textView13 = root.findViewById(R.id.textView13);
        textView14 = root.findViewById(R.id.textView14);
        editText = root.findViewById(R.id.editText);
        editText2 = root.findViewById(R.id.editText2);
        editText3 = root.findViewById(R.id.editText3);
        editText4 = root.findViewById(R.id.editText4);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    editTextCounter = Float.valueOf(editable.toString());
                    updateNeededCounters();
                } else {
                    editTextCounter = 0;
                }
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    editText2Counter = Float.valueOf(editable.toString());
                    updateNeededCounters();
                } else {
                    editText2Counter = 0;
                }
            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    editText3Counter = Float.valueOf(editable.toString());
                    updateNeededCounters();
                } else {
                    editText3Counter = 0;
                }
            }
        });

        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    editText4Counter = Float.valueOf(editable.toString());
                    updateNeededCounters();
                } else {
                    editText4Counter = 0;
                }
            }
        });

        return root;
    }

    private void updateNeededCounters() {

        double numberOfDaysInAnAverageYear = 365.2422;
        double averageNumberOfWeeksInAYear = 52.1775;

        DecimalFormat decimalPattern = new DecimalFormat("#.#####");

        if (editTextCounter != 0 && editText2Counter != 0
                && editText3Counter != 0  && editText4Counter != 0 && isItSecondScreen) {
            textView10.setText(decimalPattern.format((editTextCounter*12)*
                    editText2Counter*editText4Counter));

            textView11.setText(decimalPattern.format(
                    Float.valueOf(textView10.getText().toString())/60.0));

            textView12.setText(decimalPattern.format(
                    Float.valueOf(textView11.getText().toString())/editText3Counter));

            textView13.setText(decimalPattern.format(
                    Float.valueOf(textView12.getText().toString())/5)); //5 working days in a week

            textView14.setText(decimalPattern.format(
                    Float.valueOf(textView13.getText().toString())
                            /averageNumberOfWeeksInAYear));

        } else if ( editTextCounter != 0 && editText2Counter != 0
                && editText3Counter != 0  && !isItSecondScreen) {
            textView10.setText(decimalPattern.format(editTextCounter*
                    editText2Counter*numberOfDaysInAnAverageYear));

            textView11.setText(decimalPattern.format(
                    Float.valueOf(textView10.getText().toString())/60.0));

            textView12.setText(decimalPattern.format(
                    Float.valueOf(textView11.getText().toString())/editText3Counter));

            textView13.setText(decimalPattern.format(
                    Float.valueOf(textView12.getText().toString())/7));

            textView14.setText(decimalPattern.format(
                    Float.valueOf(textView13.getText().toString())
                            /averageNumberOfWeeksInAYear));
        }
    }
}