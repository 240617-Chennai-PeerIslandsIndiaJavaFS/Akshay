

import org.example.dao.ClientsDAO;
import org.example.models.Clients;
import org.example.service.ClientsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClientTest {
    public static ClientsDAO clients;
    public static ClientsService clientservices;

    @BeforeAll
    public static void mocking(){
        clients=mock();
        clientservices=new ClientsService(clients);

    }

    @Test
    public void fetchTest(){
        Clients clients1=new Clients(1,"Abc","1234567890","abc@example.com");
        when(clientservices.getClientByName("Abc")).thenReturn(clients1);

        Assertions.assertEquals(clients1,clientservices.getClientByName("Abc"));
    }

}
