package me.wonwoo.config;

import me.wonwoo.generator.CommandLineGenerator;
import me.wonwoo.generator.TemplateRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wonwoolee on 2017. 4. 15..
 */
@Configuration
public class Config {

  @Bean
  public TemplateRenderer templateRenderer() {
    return new TemplateRenderer();
  }

  @Bean
  public CommandLineGenerator commandLineGenerator () {
    return new CommandLineGenerator(templateRenderer());
  }
}
