package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * <p>YAMLSerializationContext interface.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLSerializationContext extends YAMLMappingContext {
    /**
     * <p>isSerializeNulls.</p>
     *
     * @return a boolean.
     */
    boolean isSerializeNulls();

    /**
     * <p>isWriteDatesAsTimestamps.</p>
     *
     * @return a boolean.
     */
    boolean isWriteDatesAsTimestamps();

    /**
     * <p>isWriteDateKeysAsTimestamps.</p>
     *
     * @return a boolean.
     */
    boolean isWriteDateKeysAsTimestamps();

    /**
     * <p>isWriteNullMapValues.</p>
     *
     * @return a boolean.
     */
    boolean isWriteNullMapValues();

    /**
     * <p>isWriteEmptyYAMLArrays.</p>
     *
     * @return a boolean.
     */
    boolean isWriteEmptyYAMLArrays();

    /**
     * <p>isOrderMapEntriesByKeys.</p>
     *
     * @return a boolean.
     */
    boolean isOrderMapEntriesByKeys();

    /**
     * <p>isMapKeyAndValueCanonical.</p>
     *
     * @return a boolean.
     */
    boolean isMapKeyAndValueCanonical();

    /**
     * <p>isWrapCollections.</p>
     *
     * @return a boolean.
     */
    boolean isWrapCollections();

    /**
     * <p>newYAMLWriter.</p>
     *
     * @return a {@link YAMLWriter} object.
     */
    YAMLWriter newYAMLWriter();

    /**
     * <p>traceError.</p>
     *
     * @param value a {@link java.lang.Object} object.
     * @param message a {@link java.lang.String} object.
     * @return a {@link YAMLSerializationException} object.
     */
    YAMLSerializationException traceError(Object value, String message);

    /**
     * <p>traceError.</p>
     *
     * @param value a {@link java.lang.Object} object.
     * @param message a {@link java.lang.String} object.
     * @param writer a {@link YAMLWriter} object.
     * @return a {@link YAMLSerializationException} object.
     */
    YAMLSerializationException traceError(Object value, String message, YAMLWriter writer);

    /**
     * <p>traceError.</p>
     *
     * @param value a {@link java.lang.Object} object.
     * @param cause a {@link java.lang.RuntimeException} object.
     * @return a {@link java.lang.RuntimeException} object.
     */
    RuntimeException traceError(Object value, RuntimeException cause);

    /**
     * <p>traceError.</p>
     *
     * @param value a {@link java.lang.Object} object.
     * @param cause a {@link java.lang.RuntimeException} object.
     * @param writer a {@link YAMLWriter} object.
     * @return a {@link java.lang.RuntimeException} object.
     */
    RuntimeException traceError(Object value, RuntimeException cause, YAMLWriter writer);

    /**
     * <p>defaultParameters.</p>
     *
     * @return a {@link YAMLSerializerParameters} object.
     */
    YAMLSerializerParameters defaultParameters();
}
