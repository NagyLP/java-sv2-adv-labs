package hackerRank;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Training {


    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
//        List<Integer> scoreTable = new ArrayList<>(List.of(0, 0));
        int aliceScore = 0;
        int bobScore = 0;
//        int score = 1;
        for (int i = 0; i < a.size(); i++) {
            int diff = a.get(i) - b.get(i);
            if (diff > 0) {
                aliceScore++;
//                scoreTable.set(0, aliceScore);
            }
            if (diff < 0) {
                bobScore++;
//                scoreTable.set(1, bobScore);
            }
//            scoreTable.set(0, a.get(i) > b.get(i) ? aliceScore++
//                    : (scoreTable.set(a.get(i) < b.get(i) ? bobScore++ : bobScore));
        }
//        return scoreTable;
        return List.of(aliceScore, bobScore);
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(List.of(5, 6, 7, 8));
        List<Integer> b = new ArrayList<>(List.of(3, 6, 10, 2));

        System.out.println(compareTriplets(a, b));

    }
}
