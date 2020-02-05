import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class test
{
Client client = ClientBuilder.newClient();
Response response = client.target("https://frc-api.firstinspires.org/v2.0/2017/alliances/CMPMO")
  .request(MediaType.TEXT_PLAIN_TYPE)
  .header("Accept", "application/json")
  .get();

System.out.println("status: " + response.getStatus());
System.out.println("headers: " + response.getHeaders());
System.out.println("body:" + response.readEntity(String.class));
}
