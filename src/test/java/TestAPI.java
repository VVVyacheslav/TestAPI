import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestAPI {

    @Test
    public void firstTest () throws Exception {
        URL url = new URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10&sort=volume_24h&sort_dir=desc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-CMC_PRO_API_KEY", "b2594b0a-21c0-44ae-ae1d-8146c038052c");
        connection.setReadTimeout(499);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();
    }

    @Test (dependsOnMethods = {"firstTest"}, threadPoolSize = 8, invocationCount = 8)
    public void secondTest () throws Exception {
        new TestAPI().firstTest();

        /*
        URL url = new URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10&sort=volume_24h&sort_dir=desc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-CMC_PRO_API_KEY", "b2594b0a-21c0-44ae-ae1d-8146c038052c");
        connection.setReadTimeout(499);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();*/
    }
}
