package me.wonwoo.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wonwoolee on 2017. 4. 15..
 */
public class CommandLineGenerator {

  private static final String LOGO = " ___       __   ________  ________   ___       __   ________  ________     \n" +
      "|\\  \\     |\\  \\|\\   __  \\|\\   ___  \\|\\  \\     |\\  \\|\\   __  \\|\\   __  \\    \n" +
      "\\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
      " \\ \\  \\  __\\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\  __\\ \\  \\ \\  \\\\\\  \\ \\  \\\\\\  \\  \n" +
      "  \\ \\  \\|\\__\\_\\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\|\\__\\_\\  \\ \\  \\\\\\  \\ \\  \\\\\\  \\ \n" +
      "   \\ \\____________\\ \\_______\\ \\__\\\\ \\__\\ \\____________\\ \\_______\\ \\_______\\\n" +
      "    \\|____________|\\|_______|\\|__| \\|__|\\|____________|\\|_______|\\|_______|";

  private final TemplateRenderer template;

  public CommandLineGenerator(TemplateRenderer template) {
    this.template = template;
  }

  public String generateCurl(String serviceUrl) {
    Map<String, Object> model = new HashMap<>();
    model.put("logo", LOGO);
    model.put("serviceUrl", serviceUrl);
    model.put("example", template.process("example.txt", model));
    return template.process("curl-example.txt", model);
  }
}
