package me.wonwoo.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by wonwoolee on 2017. 4. 15..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommandLineControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Test
  public void serverCurlTest() {
    ResponseEntity<String> curl = execute("/", String.class, "curl");
    Assert.assertThat(curl.getBody(), containsString("spring boot commandLine example"));
    Assert.assertThat(curl.getBody(), containsString("http://localhost:" + port));
  }

  @Test
  public void serverWebTest() {
    ResponseEntity<String> curl = execute("/", String.class, "Mozilla");
    Assert.assertThat(curl.getBody(), containsString("hello command line example"));
  }


  protected <T> ResponseEntity<T> execute(String contextPath, Class<T> responseType,
                                          String userAgentHeader) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", userAgentHeader);
    return restTemplate.exchange(contextPath, HttpMethod.GET,
        new HttpEntity<Void>(headers), responseType);
  }
}