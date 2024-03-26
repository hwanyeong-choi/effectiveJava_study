package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Assignment {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4 ,5 ,6 ,7 ,8 ,9, 10);
        Comparator<Integer> desc = (o1, o2) -> o2 - o1;
        numberList.sort(desc.reversed());

        System.out.println(numberList);

    }
}
