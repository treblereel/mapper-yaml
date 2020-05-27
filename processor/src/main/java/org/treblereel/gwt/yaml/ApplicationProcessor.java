package org.treblereel.gwt.yaml;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.logger.PrintWriterTreeLogger;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.gwt.yaml.processor.BeanProcessor;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ApplicationProcessor extends AbstractProcessor {

    private final TreeLogger logger = new PrintWriterTreeLogger();
    private final Set<TypeElement> beans = new HashSet<>();

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportedAnnotations().stream()
                .map(Class::getCanonicalName).collect(Collectors.toSet());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (!annotations.isEmpty()) {
            GenerationContext context = new GenerationContext(roundEnvironment, processingEnv);
            roundEnvironment.getElementsAnnotatedWith(YAMLMapper.class)
                    .stream()
                    .map(MoreElements::asType)
                    .forEach(beans::add);
            new BeanProcessor(context, logger, beans).process();
        }
        return false;
    }

    private List<Class<?>> supportedAnnotations() {
        return Arrays.asList(YAMLMapper.class);
    }
}