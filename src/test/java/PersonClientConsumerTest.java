import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonClientConsumerTest {

    @Rule
    public PactProviderRuleMk2 weatherProvider = new PactProviderRuleMk2
            ("person_provider", "localhost", 8089, this);

    @Pact(consumer="person_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        return builder
                .given("person data")
                .uponReceiving("a request for Peter Pan")
                .path("/hello/Pan")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("Hello Peter Pan!")
                .toPact();
    }

    @Test
    @PactVerification("person_provider")
    public void shouldFetchWeatherInformation() throws Exception {
        PersonClient client = new PersonClient();
        String personResponse = client.fetchPerson();
        assertThat(personResponse, is("Hello Peter Pan!"));
    }
}
