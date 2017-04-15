package me.wonwoo.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

import static com.samskivert.mustache.Mustache.compiler;

/**
 * Created by wonwoolee on 2017. 4. 15..
 */
@Slf4j
public class TemplateRenderer {

  private final Compiler mustache;

  public TemplateRenderer(Compiler mustache) {
    this.mustache = mustache;
  }

  public TemplateRenderer() {
    this(compiler().withLoader(mustacheTemplateLoader()));
  }

  private static Mustache.TemplateLoader mustacheTemplateLoader() {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    String prefix = "classpath:/templates/";
    Charset charset = Charset.forName("UTF-8");
    return name -> new InputStreamReader(
        resourceLoader.getResource(prefix + name).getInputStream(), charset);
  }

  public String process(String name, Map<String, ?> model) {
    try {
      Template template = loadTemplate(name);
      return template.execute(model);
    }
    catch (Exception e) {
      log.error("Cannot render: " + name, e);
      throw new IllegalStateException("Cannot render template", e);
    }
  }

  private Template loadTemplate(String name) {
    try {
      return mustache.compile(mustache.loader.getTemplate(name));
    }
    catch (Exception e) {
      throw new IllegalStateException("Cannot load template " + name, e);
    }
  }
}
