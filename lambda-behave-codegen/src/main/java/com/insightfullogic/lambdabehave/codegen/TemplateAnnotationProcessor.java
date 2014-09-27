package com.insightfullogic.lambdabehave.codegen;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static javax.lang.model.SourceVersion.RELEASE_8;

/**
 * Annotation Processor used to generate multiple instances of classes which
 * are templated by variable type-safe arguments.
 */
@SupportedAnnotationTypes("com.insightfullogic.lambdabehave.codegen.Templated")
@SupportedSourceVersion(RELEASE_8)
public class TemplateAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Templated.class)
                .forEach(System.out::println);
        return true;
    }

}
