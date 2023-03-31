package kr.ac.jejunu.user;

public class DaoFactory {
    private ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }

    public UserDao getUserDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
}
