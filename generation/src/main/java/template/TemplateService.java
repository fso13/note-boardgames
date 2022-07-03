package template;

import org.apache.tomcat.util.buf.CharsetUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

public class TemplateService {
    private final VelocityEngine velocityEngine = new VelocityEngine();

    public TemplateService() {
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    public String render(PageType type, Map<String, Object> params) {

        Template t = velocityEngine.getTemplate(type.getPath());

        VelocityContext context = new VelocityContext(params);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
