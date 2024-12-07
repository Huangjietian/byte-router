package io.github.huangjietian.byterouter.server.exp;

import io.github.huangjietian.byterouter.server.BytePredication;
import io.github.huangjietian.byterouter.server.BytePredicationExpression;
import io.github.huangjietian.byterouter.server.exceptions.UnKnowBytePredicationExpressionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public class BytePredicationBeanExpression implements BytePredicationExpression {

    private final BeanFactory beanFactory;

    public BytePredicationBeanExpression(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public BytePredication getPredication(String expression) throws UnKnowBytePredicationExpressionException {
        try {
            return beanFactory.getBean(expression, BytePredication.class);
        } catch (BeansException e) {
            throw new UnKnowBytePredicationExpressionException("The bean expression specifies a bean name that does not exist!");
        }
    }

}
