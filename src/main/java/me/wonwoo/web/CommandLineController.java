package me.wonwoo.web;

import me.wonwoo.generator.CommandLineGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by wonwoolee on 2017. 4. 15..
 */
@Controller
public class CommandLineController {
  private final CommandLineGenerator commandLineGenerator;

  CommandLineController(CommandLineGenerator commandLineGenerator) {
    this.commandLineGenerator = commandLineGenerator;
  }

  @GetMapping("/")
  public ResponseEntity<String> service(
      @RequestHeader(value = HttpHeaders.USER_AGENT, required = false) String userAgent) {
    ResponseEntity.BodyBuilder builder = ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN);
    if(userAgent != null && userAgent.contains("curl")){
      String content = commandLineGenerator.generateCurl(appUrl());
      return builder.eTag(content).body(content);
    }
    return builder.body("hello command line example");
  }

  private String appUrl() {
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder
        .fromCurrentServletMapping();
    return builder.build().toString();
  }
}
