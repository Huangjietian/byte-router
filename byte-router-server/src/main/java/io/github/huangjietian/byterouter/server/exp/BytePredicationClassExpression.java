package io.github.huangjietian.byterouter.server.exp;

import io.github.huangjietian.byterouter.server.BytePredication;
import io.github.huangjietian.byterouter.server.BytePredicationExpression;
import io.github.huangjietian.byterouter.server.exceptions.UnKnowBytePredicationExpressionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public class BytePredicationClassExpression implements BytePredicationExpression {
    private final static Map<String, BytePredication> CLASS_PREDICATION_MAP = new ConcurrentHashMap<>(2);
    @Override
    public BytePredication getPredication(String expression) throws UnKnowBytePredicationExpressionException {
        BytePredication bytePredication = CLASS_PREDICATION_MAP.get(expression);
        if (bytePredication != null) {
            return bytePredication;
        }
        Class<?> aClass = null;
        try {
            aClass = Class.forName(expression);
        } catch (ClassNotFoundException e) {
            throw new UnKnowBytePredicationExpressionException("The class specified by the class expression does not exist!");
        }
        if (aClass.isInterface()
                || Modifier.isAbstract(aClass.getModifiers())) {
            throw new UnKnowBytePredicationExpressionException("The class specified by the class expression must be a regular class!");
        }
        if (!BytePredication.class.isAssignableFrom(aClass)) {
            throw new UnKnowBytePredicationExpressionException("The class specified by the class expression must implement BytePredication interface!");
        }
        Class<? extends BytePredication> bpClass = (Class<? extends BytePredication>) aClass;
        try {
            bytePredication = bpClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new UnKnowBytePredicationExpressionException("The class specified by the class expression must have a no-argument constructor!");
        }
        CLASS_PREDICATION_MAP.put(expression, bytePredication);
        return bytePredication;
    }

}
