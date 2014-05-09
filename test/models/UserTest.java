package models;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static play.test.Helpers.*;

/**
 * Created by Fer on 08/05/2014.
 */
public class UserTest {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
        User.removeAll();
    }


    public void saveUser() {

        User user = new User("admin","admin@observtaerra.com","123456","Spain","Here",21);

        try {
            User userDB = User.create(user);
        } catch (Exception e) { //User already exists
            e.printStackTrace();
        }

        assertEquals(user.all().size(),1);
        assertEquals(User.findByUsername(user.username),user);//Same users...
        assertNotEquals(User.findByUsername(user.username).password,user.password);//Passwords are encrypted...

    }
}
