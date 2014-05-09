import controllers.Login;
import models.User;
import models.UserLogin;
import org.junit.Before;
import org.junit.Test;
import play.data.Form;
import play.mvc.Content;
import play.mvc.Http;
import play.test.WithApplication;

import java.util.Collections;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static play.data.Form.form;
import static play.test.Helpers.*;

public class ApplicationTest extends WithApplication {

    final static Form<UserLogin> loginForm = form(UserLogin.class, UserLogin.All.class);
    final static Form<User> signupForm = form(User.class, User.All.class);

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));

		// Initialization of Context...
		Map<String, String> flashData = Collections.emptyMap();
		Map<String, Object> argData = Collections.emptyMap();
		Long id = 2L;
		play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Http.Request request = mock(Http.Request.class);
		Http.Context context = new Http.Context(id, header, request, flashData, flashData, argData);
		Http.Context.current.set(context);
	}

    //Simple Tests where we test that application renders the pages

	@Test
    public void renderIndex() {
        Content html = views.html.index2.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ObservaTerra"); 
    }

    @Test
    public void renderLogin() {
        Content html = views.html.index2.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ObservaTerra");
    }

    @Test
    public void renderObservationsList() {
        Content html = views.html.login.loginform.render(loginForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ObservaTerra");
    }

    @Test
    public void renderProfile() {
        Content html = views.html.profile.render("admin");//Just a test, you should be logged in...
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("div");
    }

    @Test
    public void renderSignUp() {
        Content html = views.html.signup.form.render(signupForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Sign");
    }
    @Test
    public void renderSummary(){
        Content html = views.html.signup.summary.render(new User());//empty user => empty summary, but it renders...
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ObservaTerra");
    }


}
