package baseball;

public class UserThread implements Runnable{

    private Runnable m_userFunction;

    public UserThread(Runnable userFunction) {
        setUserFunction(userFunction);
    }

    public void setUserFunction(Runnable userFunction) {
        this.m_userFunction = userFunction;
    }

    @Override
    public void run() {
        m_userFunction.run();
    }


}
