package ru.android.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity {

    private NumbersButton numbersButton;
    private OperationsButton operationsButton;
    //    private EditText expression;
    private TextView expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        numbersButton = new NumbersButton();
        operationsButton = new OperationsButton();

        initView();
    }

    private void initView() {
        expression = findViewById(R.id.place_result);
        initOperationsButton();
        initButton7Click();
    }

    // button of operation
    private void initOperationsButton() {
        Button buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(buttonMinusClickListener);

        Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(buttonPlusClickListener);

        Button buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(buttonMultiplyClickListener);

        Button buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(buttonDivideClickListener);

    }

    public View.OnClickListener buttonMinusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getMINUS());
        }
    };
    public View.OnClickListener buttonPlusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getPLUS());
        }
    };
    public View.OnClickListener buttonMultiplyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getMULTIPLY());
        }
    };
    public View.OnClickListener buttonDivideClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getDIVIDE());
        }
    };

    // button of numbers
    private void initButton7Click() {
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(button7ClickListener);
    }

    public View.OnClickListener button7ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_7());
        }
    };

    private void setExpressionInTextView(TextView expression, int number) {
        expression.setText(expression.getText() + String.format(Locale.getDefault(), "%d", number));
    }

    private void setExpressionInTextView(TextView expression, char number) {
        expression.setText(expression.getText() + String.format(Locale.getDefault(), "%c", number));
    }

}