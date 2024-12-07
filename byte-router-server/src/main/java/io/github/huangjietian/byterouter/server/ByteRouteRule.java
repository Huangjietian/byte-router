package io.github.huangjietian.byterouter.server;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public interface ByteRouteRule {

    String getRuleName();

    ByteRouteConfiguration[] getByteRouteConfigurations();

    BytePredication[] getBytePredications();

    ByteHandler getByteHandler();
}
