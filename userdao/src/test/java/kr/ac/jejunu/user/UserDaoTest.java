package kr.ac.jejunu.user;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "kong";
        String password = "123";
        UserDao userDao = new JejuUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "kong2";
        String password = "test123";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new JejuUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }
    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "kong";
        String password = "1111";
        UserDao userDao = new HallaUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        String name = "kong2";
        String password = "test123";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new HallaUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

}
