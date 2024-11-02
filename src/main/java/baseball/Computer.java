package baseball;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {

    private static Computer computerInstance;
    private List<String> lRanNum;
    private Computer() {
        this.lRanNum = makeRanNum();
    }

    public static Computer getComputer() {
        if (computerInstance == null) {
            computerInstance = new Computer();
        }
        return computerInstance;
    }


    public List<String> getRanNum() {
        return new ArrayList<>(lRanNum);
    }


    private List<String> makeRanNum() {
        List<String> list = new ArrayList<>();
        while (list.size() < 3) {
            String strRanNum = String.valueOf(Randoms.pickNumberInRange(1, 9));
            if (list.contains(strRanNum)) {
                continue;
            }
            list.add(strRanNum);
        }
        return list;
    }

}
