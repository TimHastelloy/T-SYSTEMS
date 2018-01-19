package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator implements ICalculator{

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        //Reversive Polish Notation
        ArrayList<String> exit_statement = new ArrayList<String>();
        Stack<String> operator = new Stack<String>();
        String digit = "";
        try {
            for (int i = 0; i < statement.length(); i++) {
                switch (statement.charAt(i))                                         //Priority:  */(high),+-(middle),()-low
                {
                    case '+':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        if ((operator.empty())) {
                            operator.push("+");
                        } else {
                            if ((operator.peek().equals("+")) || (operator.peek().equals("-")) || (operator.peek().equals("*")) || (operator.peek().equals("/"))) {
                                exit_statement.add(operator.pop());
                                operator.push("+");
                            } else {
                                operator.push("+");
                            }
                        }
                        break;
                    case '-':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        if ((operator.empty())) {
                            operator.push("-");
                        } else {
                            if ((operator.peek().equals("+")) || (operator.peek().equals("-")) || (operator.peek().equals("*")) || (operator.peek().equals("/"))) {
                                exit_statement.add(operator.pop());
                                operator.push("-");
                            } else {
                                operator.push("-");
                            }
                        }
                        break;
                    case '*':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        if ((operator.empty())) {
                            operator.push("*");
                        } else {
                            if ((operator.peek().equals("*")) || (operator.peek().equals("/"))) {
                                exit_statement.add(operator.pop());
                                operator.push("*");
                            } else {
                                operator.push("*");
                            }
                        }
                        break;
                    case '/':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        if ((operator.empty())) {
                            operator.push("/");
                        } else {
                            if ((operator.peek().equals("*")) || (operator.peek().equals("/"))) {
                                exit_statement.add(operator.pop());
                                operator.push("/");
                            } else {
                                operator.push("/");
                            }
                        }
                        break;
                    case '(':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        operator.push("(");
                        break;
                    case ')':
                        if (!digit.equals("")) {
                            exit_statement.add(digit);
                            digit = "";
                        }
                        while (operator.peek() != "(") {
                            exit_statement.add(operator.pop());
                        }
                        operator.pop();
                        break;
                    default:
                        digit = digit + Character.toString(statement.charAt(i));
                        break;
                }
            }
            if (!digit.equals("")) {
                exit_statement.add(digit);
                digit = "";
            }
            while (!operator.empty()) {
                exit_statement.add(operator.pop());
            }
            double result;
            if( exit_statement.size()==0) return null; //If string is empty
            while (exit_statement.size() != 1) {
                for (int i = 0; i < exit_statement.size(); i++) {
                    if (exit_statement.get(i) == "+") {
                        result = Double.parseDouble(exit_statement.get(i - 2)) + Double.parseDouble(exit_statement.get(i - 1));
                        modify(i,result, exit_statement);
                        break;
                    } else {
                        if (exit_statement.get(i) == "-") {
                            result = Double.parseDouble(exit_statement.get(i - 2)) - Double.parseDouble(exit_statement.get(i - 1));
                            modify(i,result, exit_statement);
                            break;
                        } else {
                            if (exit_statement.get(i) == "*") {
                                result = Double.parseDouble(exit_statement.get(i - 2)) * Double.parseDouble(exit_statement.get(i - 1));
                                modify(i,result, exit_statement);
                                break;
                            } else {
                                if (exit_statement.get(i) == "/") {
                                    result = Double.parseDouble(exit_statement.get(i - 2)) / Double.parseDouble(exit_statement.get(i - 1));
                                    modify(i,result, exit_statement);
                                    break;
                                } else {
                                    if((exit_statement.get(i) == ")")||(exit_statement.get(i) == "(")) return null;
                                }
                            }
                        }
                    }
                }
            }
            if (exit_statement.get(0).equals("Infinity")) return null;
            String parts[]=exit_statement.get(0).split("\\.");
            if(parts[1].length()>1) {return parts[0]+"."+parts[1];}
            else //If number is integer and double part is not needed
            { return parts[0];}
        }
         catch (Exception e)
        {
            return null;
        }
    }
    public void modify(int i,double result, ArrayList exit_statement)
    {
        exit_statement.set(i, Double.toString(result));
        exit_statement.remove(i - 1);
        exit_statement.remove(i - 2);
    }
}
