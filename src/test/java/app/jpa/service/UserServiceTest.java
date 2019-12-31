package app.jpa.service;

import app.jpa.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;

    @Mock
    UserService userService;

    @Before
    public void setUp() {
        user = new User("Mario");
        when(userService.find((long) 10)).thenReturn(Optional.of(user));
    }

    @Test
    public void findTest(){
        Optional<User> optionalFound = userService.find((long) 10);

        assertThat(optionalFound).isPresent()
                .get()
                .isEqualTo(user);
        verify(userService).find((long) 10);
    }

    @Test
    public void updateTest(){
        userService.update(user);
        verify(userService).update(same(user));
    }
}
