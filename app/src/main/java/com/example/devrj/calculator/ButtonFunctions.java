package com.example.devrj.calculator;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;

class ButtonFunctions {

    /* Check if last element is an operator */
    static boolean lastElementIsOperator(List<String> expression){
        try {
            String lastElement = expression.get(expression.size() - 1);
            switch (lastElement) {
                case "+":
                    return true;
                case "-":
                    return true;
                case "*":
                    return true;
                case "÷":
                    return true;
                default:
                    return false;
            }
        }catch(IndexOutOfBoundsException except){
            return false;
        }
    }

    //Perform multiplication and division
    static ArrayList<String> multDiv(ArrayList<String> expression){
        for (int i = 0, j = 1; i < expression.size() - 1; i++, j++) {
            if (expression.get(i).equals("*")) {
                expression.set(i - 1, String.valueOf(Double.valueOf(expression.get(i - 1)) * Double.valueOf(expression.get(i + 1))));
                expression.remove(i);
                expression.remove(i);
                i = 0;

            } else if (expression.get(i).equals("÷")) {
                expression.set(i - 1, String.valueOf(Double.valueOf(expression.get(i - 1)) / Double.valueOf(expression.get(i + 1))));
                expression.remove(i);
                expression.remove(i);
                i = 0;
            }
        }
        return expression;
    }


    //Perform addition and subtraction
    static ArrayList<String> addSub(ArrayList<String> expression){
        for (int i = 0, j = 1; i < expression.size() - 1; i++, j++) {
            if (expression.get(i).equals("+")) {
                expression.set(i - 1, String.valueOf(Double.valueOf(expression.get(i - 1)) + Double.valueOf(expression.get(i + 1))));
                expression.remove(i);
                expression.remove(i);
                i = 0;

            } else if (expression.get(i).equals("-")) {
                expression.set(i - 1, String.valueOf(Double.valueOf(expression.get(i - 1)) - Double.valueOf(expression.get(i + 1))));
                expression.remove(i);
                expression.remove(i);
                i = 0;

            }
        }
        return expression;
    }

    //Rules for inserting operators [Object[] param in order: String, ArrayList, Button, String]
    @SuppressWarnings(value = "unchecked")  //Use of Object[]
    static Object[] operatorHandler(Object[] arr){
        //Unpack the Object[]
        String tempNum = String.valueOf(arr[0]);
        ArrayList<String> calcExpression = (ArrayList<String>) arr[1];
        Button button = (Button) arr[2];
        String lblDisp = String.valueOf((arr[3]));

        if(!tempNum.isEmpty()) {
            calcExpression.add(tempNum);
        }
        if(calcExpression.isEmpty()){
            tempNum = "";
            //Don't add operator as the first char in expression
        }else {
            if (ButtonFunctions.lastElementIsOperator(calcExpression)) {        //If more than one operator is entered consecutively
                calcExpression.set(calcExpression.size()-1, button.getText().toString());  //Add the most recent to the expression
                lblDisp = lblDisp.substring(0, lblDisp.length()-1);             //And update the display accordingly
                lblDisp += button.getText();
            }else {
                tempNum = "";
                calcExpression.add(button.getText().toString());
                lblDisp += button.getText();
            }
        }
        return new Object[]{tempNum, calcExpression, lblDisp};
    }


    //Rules for inserting numbers [Object[] param in order: String, ArrayList, Button, String]
    @SuppressWarnings(value = "unchecked")  //Use of Object[]
    static Object[] digitHandler(Object[] arr){
        //Unpack the Object[]
        String tempNum = String.valueOf(arr[0]);
        ArrayList<String> calcExpression = (ArrayList<String>) arr[1];
        Button button = (Button) arr[2];
        String lblDisp = String.valueOf((arr[3]));


        if(!lastElementIsOperator(calcExpression)){
            try { //If the last element isn't an operator, append the digit to the string in the element
                calcExpression.set(calcExpression.size() - 1, calcExpression.get(calcExpression.size() - 1) + button.getText());
            }catch (IndexOutOfBoundsException except){
                //Throws if the list is less than two elements in size
                calcExpression.add(button.getText().toString());
            }
        }else{
            calcExpression.add(button.getText().toString());
        }
        lblDisp += button.getText();
        return new Object[]{tempNum, calcExpression, lblDisp};
    }











}

