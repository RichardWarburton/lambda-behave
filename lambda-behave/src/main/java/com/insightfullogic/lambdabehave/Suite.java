package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.generators.NumberGenerators;
import com.insightfullogic.lambdabehave.impl.reports.Specifiers;

/**
 * <p>
 * Callback interface to allow you to describe a suite of
 * behaviours about some concept,
 * </p>
 *
 * @see com.insightfullogic.lambdabehave.Description
 */
@FunctionalInterface
public interface Suite {

    /**
     * Describe a suite of behaviours.
     *
     * @param name the name of the concept you're specifying.
     * @param behaviours the suite of behaviours you're specifying.
     */
    public static void describe(final String name, final Suite behaviours) {
        Specifiers.describe(name, behaviours);
    }

    /**
     * The callback used to specify a suite of behaviours should
     * implement this method of the interface.
     *
     * @see com.insightfullogic.lambdabehave.Description
     *
     * @param it the description object used to declare individual
     *           specifications as part of this suite.
     */
    public void specifySuite(Description it);

}
