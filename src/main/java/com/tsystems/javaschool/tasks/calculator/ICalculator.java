package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;

public interface ICalculator {
    String evaluate(String statement);
    void modify(int i,double result,ArrayList exit_statement);
}
