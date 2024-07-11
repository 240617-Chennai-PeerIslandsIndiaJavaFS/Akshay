import org.example.dao.ClientsDAO;
import org.example.dao.UserDAO;
import org.example.models.Clients;
import org.example.models.UserModels;
import org.example.service.ClientsService;
import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersTest {
    public static UserDAO users;
    public static UserService userservices;

    @BeforeAll
    public static void mocking(){
        users=mock();
        userservices=new UserService(users);

    }

    @Test
    public void fetchTest(){
        UserModels.User user1=new UserModels.User(1,"asd","asd@mail.com","1234567890","qwerty",UserModels.UserRole.Associate,UserModels.Status.Active);
        when(userservices.updateUser(user1)).thenReturn(true);

        Assertions.assertEquals(true,userservices.updateUser(user1));
    }
}
