package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        if (inputNumbers.size()>254) throw new CannotBuildPyramidException();
        try {
            Collections.sort(inputNumbers);
            int count_row = 1;
            int count_numb = inputNumbers.size();
            while ((count_numb - count_row > 0)) {
                count_numb -= count_row;
                count_row++;
            }
            int count_col = count_row + (count_row - 1);
            int[][] pyramid = new int[count_row][count_col];
            int center_index = (count_col / 2);
            int i = 0;
            Iterator<Integer> it = inputNumbers.iterator();
            while ((i <= count_row - 1)) {
                int digit_index = center_index;
                for (int j = 0; j <= i; j++) {
                    if (it.hasNext()) {
                        pyramid[i][digit_index] = it.next();
                    }
                    digit_index += 2;
                }
                i++;
                center_index--;
            }

            return pyramid;
        } catch (Exception e)
        {
            throw new CannotBuildPyramidException();
        }
    }
}
