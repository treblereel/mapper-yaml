package org.treblereel.gwt.yaml.api;

import java.io.IOException;

import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * <p>YAMLDeserializationContext interface.</p>
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLDeserializationContext extends YAMLMappingContext {

    /**
     * <p>isFailOnUnknownProperties.</p>
     * @return a boolean.
     */
    boolean isFailOnUnknownProperties();

    /**
     * <p>isAcceptSingleValueAsArray.</p>
     * @return a boolean.
     */
    boolean isAcceptSingleValueAsArray();

    /**
     * <p>isUseSafeEval.</p>
     * @return a boolean.
     */
    boolean isUseSafeEval();

    /**
     * <p>isReadUnknownEnumValuesAsNull.</p>
     * @return a boolean.
     */
    boolean isReadUnknownEnumValuesAsNull();

    /**
     * <p>isUseBrowserTimezone.</p>
     * @return a boolean.
     */
    boolean isUseBrowserTimezone();

    /**
     * <p>newYAMLReader.</p>
     * @param input a {@link String} object.
     * @return a {@link YAMLReader} object.
     */
    YAMLReader newYAMLReader(String input) throws IOException;

    /**
     * <p>traceError.</p>
     * @param message a {@link String} object.
     * @return a {@link YAMLDeserializationException} object.
     */
    YAMLDeserializationException traceError(String message);

    /**
     * <p>traceError.</p>
     * @param message a {@link String} object.
     * @param reader a {@link YAMLReader} object.
     * @return a {@link YAMLDeserializationException} object.
     */
    YAMLDeserializationException traceError(String message, YAMLReader reader);

    /**
     * <p>traceError.</p>
     * @param cause a {@link RuntimeException} object.
     * @return a {@link RuntimeException} object.
     */
    RuntimeException traceError(RuntimeException cause);

    /**
     * <p>traceError.</p>
     * @param cause a {@link RuntimeException} object.
     * @param reader a {@link YAMLReader} object.
     * @return a {@link RuntimeException} object.
     */
    RuntimeException traceError(RuntimeException cause, YAMLReader reader);

    /**
     * <p>defaultParameters.</p>
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters defaultParameters();
}
