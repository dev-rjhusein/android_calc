package com.example.devrj.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> calcExpression = new ArrayList<>();
    private String tempNum = "";
    private static boolean solveFlag = false;      //Sets to 'true' when equal sign is pressed.
    private String lblDisp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void display(String dispStr) {
        TextView view = findViewById(R.id.textView);
        if(dispStr.equals("clear")) {
            view.setText("");
        }else {
            String tempStr = view.getText().toString() + dispStr;
            view.setText(tempStr);
        }
    }


    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-. BUTTON FUNCTIONS -.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/
    public void appendOne(View view) {
        Button butOne = findViewById(R.id.buttonOne);
        numButtonOperations(butOne);
        display(butOne.getText().toString());
    }
    public void appendTwo(View view) {
        Button butTwo = findViewById(R.id.buttonTwo);
        numButtonOperations(butTwo);
        display(butTwo.getText().toString());
    }
    public void appendThree(View view) {
        Button butThree = findViewById(R.id.buttonThree);
        numButtonOperations(butThree);
        display(butThree.getText().toString());
    }
    public void appendFour(View view) {
        Button butFour = findViewById(R.id.buttonFour);
        numButtonOperations(butFour);
        display(butFour.getText().toString());
    }
    public void appendFive(View view) {
        Button butFive = findViewById(R.id.buttonFive);
        numButtonOperations(butFive);
        display(butFive.getText().toString());
    }
    public void appendSix(View view) {
        Button butSix = findViewById(R.id.buttonSix);
        numButtonOperations(butSix);
        display(butSix.getText().toString());
    }
    public void appendSeven(View view) {
        Button butSeven = findViewById(R.id.buttonSeven);
        numButtonOperations(butSeven);
        display(butSeven.getText().toString());
    }
    public void appendEight(View view) {
        Button butEight = findViewById(R.id.buttonEight);
        numButtonOperations(butEight);
        display(butEight.getText().toString());
    }
    public void appendNine(View view) {
        Button butNine = findViewById(R.id.buttonNine);
        numButtonOperations(butNine);
        display(butNine.getText().toString());
    }
    public void appendZero(View view) {
        Button butZero = findViewById(R.id.buttonZero);
        numButtonOperations(butZero);
        display(butZero.getText().toString());
    }
    public void appendMultiply(View view) {
        Button butMult = findViewById(R.id.buttonMultiply);
        opButtonOperations(butMult);
        display(butMult.getText().toString());
        Log.d("Current: ", calcExpression.toString());
    }
    public void appendDivide(View view) {
        Button butDiv = findViewById(R.id.buttonDivide);
        opButtonOperations(butDiv);
        display(butDiv.getText().toString());
    }
    public void appendAdd(View view) {
        Button butAdd = findViewById(R.id.buttonAdd);
        opButtonOperations(butAdd);
        display(butAdd.getText().toString());
    }
    public void appendSubtract(View view) {
        Button butSub = findViewById(R.id.buttonSubtract);
        opButtonOperations(butSub);
        display(butSub.getText().toString());
    }
    public void solve(View view){
        Button butEqual = findViewById(R.id.buttonEqual);
        if(ButtonFunctions.lastElementIsOperator(calcExpression)){
            calcExpression.remove(calcExpression.size()-1);
        }
        if(!tempNum.isEmpty()) {
            calcExpression.add(tempNum);
        }
        tempNum = "";
        calcExpression.add(butEqual.getText().toString());


        // CALCULATION MUST FOLLOW ORDER OF OPERATIONS
        //  P.E.
        calcExpression = new ArrayList<>(ButtonFunctions.multDiv(calcExpression));    //  M.D.
        calcExpression = new ArrayList<>(ButtonFunctions.addSub(calcExpression));     //  A.S.

        display("=");
        lblDisp = calcExpression.get(0);    //Display answer to calculator display
        display(lblDisp);
        solveFlag = true;   //Flag resets calculator
    }



    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-. ADDITIONAL BUTTONS -.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/

    public void clearCalc(View view){
        clearCalc();
    }

    public void decimal(){

    }

    public void leftParen(){

    }

    public void rightParen(){

    }

    public void percent(){

    }




    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-. ADDITIONAL OPERATIONS -.-.-.-.-.-.-*/
    /* -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-*/
    @SuppressWarnings(value = "unchecked")  //Use of Object[]
    private void numButtonOperations(Button button){
        if(solveFlag){      //If the 'equal button' was the last button entered
            clearCalc();    //Reset the calculator before moving on
        }
        //Use Object array to pass significant values to a function
        Object[] arr = new Object[]{tempNum, calcExpression, button, lblDisp};

        //Set the array to the returned array
        arr = ButtonFunctions.digitHandler(arr);

        //Set the altered values to those needed.
        tempNum = String.valueOf(arr[0]);
        calcExpression = (ArrayList<String>) arr[1];
        lblDisp = String.valueOf(arr[2]);
    }

    @SuppressWarnings(value = "unchecked")  //Use of Object[]
    private void opButtonOperations(Button button){

        Object[] arr = new Object[]{tempNum, calcExpression, button, lblDisp};
        arr = ButtonFunctions.operatorHandler(arr);
        tempNum = String.valueOf(arr[0]);
        calcExpression = (ArrayList<String>) arr[1]; //arr[1] always returns ArrayList<String> - Ignore warning
        lblDisp = String.valueOf(arr[2]);
        solveFlag = false;
    }

    private void clearCalc(){
        calcExpression.clear();
        tempNum = "";
        lblDisp = "";
        solveFlag = false;
        display("clear");
    }








}