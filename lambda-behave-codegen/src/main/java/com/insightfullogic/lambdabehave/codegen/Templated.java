package com.insightfullogic.lambdabehave.codegen;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

/**
 * .
 */
@Target({TYPE, TYPE_PARAMETER})
public @interface Templated {
}
