package io.github.huangjietian.byterouter.server;

import io.github.huangjietian.byterouter.server.exceptions.UnKnowBytePredicationExpressionException;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public interface BytePredicationExpression {

    BytePredication getPredication(String expression) throws UnKnowBytePredicationExpressionException;

}
