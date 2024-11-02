package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserControl {
    private static final Object LOCK = new Object();
    private static final Scanner scan = new Scanner(System.in);
    private static List<String> m_CurrentList = new ArrayList<>();
    private static int m_CurrentInt = 0;
    private static UserControl m_UserControl;


    UserControl() {
    }

    public static UserControl getInstance() {
        if (m_UserControl == null) {
            synchronized (LOCK) {
                m_UserControl = new UserControl();
            }

        }
        return m_UserControl;
    }

    public static List<String> getList() {
        synchronized (LOCK) {
            return m_CurrentList;
        }
    }

    public static int getInt() {
        synchronized (LOCK) {
            return m_CurrentInt;
        }
    }

    public UserControl setList() {
        if (scan.hasNextLine()) {
            synchronized (LOCK) {
                m_CurrentList = inputFilter(scan.nextLine());
                return this;
            }
        }
        return this;
    }

    public UserControl setInt() {
        if (scan.hasNextLine()) {
            try {
                m_CurrentInt = Integer.parseInt(scan.nextLine());
                return this;
            }
            // 틀리면 이걸로 하랬으니... 문제가 요구하는 에러
            catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        return this;
    }


    private List<String> inputFilter(String str) {
        // 3개가 아닌 문자를 제시, 0을 제시한 경우, 문자를 제시한 경우, 같은 숫자를 제시한 경우
        //시작할때 검사 ex)1230
        if (str.length() != 3) {
            throw new IllegalArgumentException();
        }
        Stream<String> strStream = Arrays.stream(str.split(""));
        //0 이 아닐경우, 숫자일경우(0~9),중복되는 숫자 제거
        List<String> lTemp = strStream.filter(s -> !s.equals("0"))
                .filter(s -> s.matches("\\d"))
                .distinct()
                .collect(Collectors.toList());
        //필터 후 검사 ex)12
        if (lTemp.size() != 3) {
            throw new IllegalArgumentException();
        }
        return lTemp;
    }


}


