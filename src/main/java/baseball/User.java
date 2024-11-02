package baseball;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<String> m_UserInput = new ArrayList<>();

    public User() {
    }

    public static User getUser() {
        //여러 user thread 추가..할수있으니..
        return new User();
    }

    public List<String> getUserInput(){
        return m_UserInput;
    }

    public User setUserInput() {
        System.out.print("숫자를 입력해 주세요 : ");
        UserControl.getInstance().setList();
        this.m_UserInput = UserControl.getList();
        return this;
    }
//
//    private List<String> inputFilter(String str) {
//        // 3개가 아닌 문자를 제시, 0을 제시한 경우, 문자를 제시한 경우, 같은 숫자를 제시한 경우
//        //시작할때 검사 ex)1230
//        if (str.length() != 3) {
//            IllegalArgumentException();
//        }
//        Stream<String> strStream = Arrays.stream(str.split(""));
//        //0 이 아닐경우, 숫자일경우(0~9),중복되는 숫자 제거
//        List<String> lTemp = strStream.filter(s -> !s.equals("0"))
//                .filter(s -> s.matches("\\d"))
//                .distinct()
//                .collect(Collectors.toList());
//        //필터 후 검사 ex)12
//        if (lTemp.size() != 3) {
//            IllegalArgumentException();
//        }
//        return lTemp;
//    }
//
//    private void IllegalArgumentException() {
//        System.out.println("IllegalArgumentException 발생");
//        throw new IllegalArgumentException();
//    }


}

