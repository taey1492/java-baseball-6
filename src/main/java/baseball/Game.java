package baseball;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int START = -1;
    private static final int NORMAL = 0;
    private static final int NEW = 50;


//    private static User CurrentUser;

//    private List<String> lUserInput;
    private static final int VERIFICATION = 100;
    private static final int WRONG = 200;
    private static final int CORRECT = 300;
    private static final int WAIT = 400;
    private static final int END = 9999;
    private static List<String> m_RanNum;
    private static int m_UserNum = 1;// 임시지정
    private static int m_State = -1;
    public Game(int Num) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        setUserNum(Num);
        if (m_RanNum == null) {
            m_RanNum = Computer.getComputer().getRanNum();
        }

        for (int i = 0; i < m_UserNum; i++) {

            Runnable runnable = this::play;
            Thread thread = new Thread(new UserThread(runnable));
            thread.start();
        }

    }

    protected void setUserNum(int iNum) {
        m_UserNum = iNum;
    }

    // play in thread
    private void play() {
        User CurrentUser = User.getUser();
        List<String> lCurrentUserInput = new ArrayList<>();
        m_State = START;
        while (true) {

            switch (m_State) {
                case START:
                     CurrentUser.setUserInput();
                    lCurrentUserInput = CurrentUser.getUserInput();
                    m_State = NORMAL;
                    break;

                case NEW:
                    m_RanNum = Computer.getComputer().getRanNum();
                    m_State = START;
                    break;

                case NORMAL:
                    m_State = VERIFICATION;
                    break;

                case VERIFICATION:
                    try {
                        int iStrike = evaluateTurn(lCurrentUserInput, m_RanNum);
                        if (iStrike == 3) {
                            m_State = CORRECT;
                        } else {
                            m_State = WRONG;
                        }
                    } catch (Exception e) {
                        System.out.println("Exception : " + e);
                    }
                    break;

                case WRONG: // 못맞춤
                    m_State = WAIT;
                    break;

                case CORRECT: // 맞춤
                    System.out.println("3 개의 숫자를 모두 맞히셨습니다. 게임 종료 \n게임을 새로 시작하시려면 1, 종료하시려면 2 를 입력하세요");
                    UserControl.getInstance().setInt();
                    if (UserControl.getInt() == 1){
                        m_State = START;
                    } else if (UserControl.getInt() == 2) {
                        m_State = END;
                    }
                    break;

                case WAIT://대기 : thread(여러 사용자 일때 순서 대기)
                    // 사람이 2인 이상이라면 대기하는 로직 작성

                    m_State = START;
                    break;

                case END:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + m_State);
            }

        }


//            if (iStrike == 3) {
//                {
//
//                }
//            }
    }


    //1회게임
    private int evaluateTurn(List<String> userInput, List<String> computerNumbers) {
        int nStrike = strike(userInput, computerNumbers);
        int nBall = ball(userInput, computerNumbers);

        if (nStrike == 0 && nBall == 0) {
            System.out.println("낫싱");
        } else {
            System.out.printf("%d 스트라이크, %d 볼%n", nStrike, nBall);
        }
        return nStrike;
    }


    // #region rule
    private int strike(List<String> userInput, List<String> List) {
        int count = 0, index = 0;
        while (index < 3) {
            if (userInput.get(index).equals(List.get(index))) {
                count++;
            }
            index++;
        }
        return count;
    }

    private int ball(List<String> userInput, List<String> List) {
        int count = 0;
        for (String item : userInput) {
            if (List.contains(item)) {
                count++;
            }
        }
        count = count - strike(userInput, List);
        return count;
    }

    private String nothing(List<String> userInput, List<String> List) {

        if (strike(userInput, List) == 0 && ball(userInput, List) == 0) {
            return "낫싱";
        }
        return null;
    }
    // #endregion
}
