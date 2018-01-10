import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PersonClient {

    public String fetchPerson() throws IOException {
        String personServiceUrl = "http://localhost:8089";
        String endpoint = "/hello";
        String firstName = "Peter";
        String url = String.format("%s%s/%s", personServiceUrl, endpoint, firstName);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().get().url(url).build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

}
