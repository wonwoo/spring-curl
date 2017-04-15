package me.wonwoo.generator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by wonwoolee on 2017. 4. 15..
 */
public class CommandLineGeneratorTests {

  private CommandLineGenerator generator;

  @Before
  public void init() {
    generator = new CommandLineGenerator(new TemplateRenderer());
  }

  @Test
  public void generateTest () {
    String content = generator.generateCurl("http://localhost:8080");
    assertThat(content, containsString("spring boot commandLine example"));
    assertThat(content, containsString("http://localhost:8080"));
  }

}