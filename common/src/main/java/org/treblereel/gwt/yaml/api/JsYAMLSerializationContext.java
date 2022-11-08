package org.treblereel.gwt.yaml.api;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/18/20
 */
public class JsYAMLSerializationContext implements YAMLSerializationContext {

    private static final Logger logger = Logger.getLogger("YAMLSerialization");
    /*
     * Serialization options
     */
    private final boolean serializeNulls;
    private final boolean writeDatesAsTimestamps;
    private final boolean writeDateKeysAsTimestamps;
    private final boolean writeNullMapValues;
    private final boolean writeEmptyYAMLArrays;
    private final boolean wrapCollections;
    private final boolean orderMapEntriesByKeys;
    private final boolean mapKeyAndValueCanonical;
    private final boolean wrapExceptions;

    private JsYAMLSerializationContext(boolean serializeNulls, boolean writeDatesAsTimestamps,
                                       boolean writeDateKeysAsTimestamps, boolean writeNullMapValues,
                                       boolean writeEmptyYAMLArrays, boolean orderMapEntriesByKeys,
                                       boolean mapKeyAndValueCanonical, boolean wrapExceptions,
                                       boolean wrapCollections) {
        this.serializeNulls = serializeNulls;
        this.writeDatesAsTimestamps = writeDatesAsTimestamps;
        this.writeDateKeysAsTimestamps = writeDateKeysAsTimestamps;
        this.writeNullMapValues = writeNullMapValues;
        this.writeEmptyYAMLArrays = writeEmptyYAMLArrays;
        this.orderMapEntriesByKeys = orderMapEntriesByKeys;
        this.mapKeyAndValueCanonical = mapKeyAndValueCanonical;
        this.wrapExceptions = wrapExceptions;
        this.wrapCollections = wrapCollections;
    }

    /**
     * <p>builder</p>
     * @return a {@link DefaultYAMLSerializationContext.Builder} object.
     */
    public static JsYAMLSerializationContext.Builder builder() {
        return new JsYAMLSerializationContext.DefaultBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * <p>isSerializeNulls</p>
     * @see DefaultYAMLSerializationContext.Builder#serializeNulls(boolean)
     */
    @Override
    public boolean isSerializeNulls() {
        return serializeNulls;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isWriteDatesAsTimestamps</p>
     * @see DefaultYAMLSerializationContext.Builder#writeDatesAsTimestamps(boolean)
     */
    @Override
    public boolean isWriteDatesAsTimestamps() {
        return writeDatesAsTimestamps;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isWriteDateKeysAsTimestamps</p>
     * @see DefaultYAMLSerializationContext.Builder#writeDateKeysAsTimestamps(boolean)
     */
    @Override
    public boolean isWriteDateKeysAsTimestamps() {
        return writeDateKeysAsTimestamps;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isWriteNullMapValues</p>
     * @see DefaultYAMLSerializationContext.Builder#writeNullMapValues(boolean)
     */
    @Override
    public boolean isWriteNullMapValues() {
        return writeNullMapValues;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isWriteEmptyYAMLArrays</p>
     * @see DefaultYAMLSerializationContext.Builder#writeEmptyYAMLArrays(boolean)
     */
    @Override
    public boolean isWriteEmptyYAMLArrays() {
        return writeEmptyYAMLArrays;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isOrderMapEntriesByKeys</p>
     * @see DefaultYAMLSerializationContext.Builder#orderMapEntriesByKeys(boolean)
     */
    @Override
    public boolean isOrderMapEntriesByKeys() {
        return orderMapEntriesByKeys;
    }

    @Override
    public boolean isMapKeyAndValueCanonical() {
        return mapKeyAndValueCanonical;
    }

    @Override
    public boolean isWrapCollections() {
        return wrapCollections;
    }

    /**
     * {@inheritDoc}
     *
     * <p>newYAMLWriter</p>
     */
    @Override
    public YAMLWriter newYAMLWriter() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error and returns a corresponding exception.
     */
    @Override
    public YAMLSerializationException traceError(Object value, String message) {
        getLogger().log(Level.SEVERE, message);
        return new YAMLSerializationException(message);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error with current writer state and returns a corresponding exception.
     */
    @Override
    public YAMLSerializationException traceError(Object value, String message, YAMLWriter writer) {
        YAMLSerializationException exception = traceError(value, message);
        traceWriterInfo(value, writer);
        return exception;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error and returns a corresponding exception.
     */
    @Override
    public RuntimeException traceError(Object value, RuntimeException cause) {
        getLogger().log(Level.SEVERE, "Error during serialization", cause);
        if (wrapExceptions) {
            return new YAMLSerializationException(cause);
        } else {
            return cause;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error with current writer state and returns a corresponding exception.
     */
    @Override
    public RuntimeException traceError(Object value, RuntimeException cause, YAMLWriter writer) {
        RuntimeException exception = traceError(value, cause);
        traceWriterInfo(value, writer);
        return exception;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLSerializerParameters defaultParameters() {
        return YAMLContextProvider.get().defaultSerializerParameters();
    }

    /**
     * Trace the current writer state
     * @param value current value
     */
    private void traceWriterInfo(Object value, YAMLWriter writer) {
        if (getLogger().isLoggable(Level.INFO)) {
            getLogger().log(Level.INFO, "Error on value <" + value + ">. Current output : <" + writer.getOutput() + ">");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * Builder for {@link YAMLSerializationContext}. To override default settings globally, you can extend this class, modify the
     * default settings inside the constructor and tell the compiler to use your builder instead in your gwt.yaml file :
     * <pre>
     * {@code
     *
     * <replace-with class="your.package.YourBuilder">
     *   <when-type-assignable class="org.dominokit.jacksonapt.YAMLSerializationContext.Builder" />
     * </replace-with>
     *
     * }
     * </pre>
     */
    public static class Builder {

        protected boolean useEqualityForObjectId = false;

        protected boolean serializeNulls = false;

        protected boolean writeDatesAsTimestamps = true;

        protected boolean writeDateKeysAsTimestamps = false;

        protected boolean writeNullMapValues = true;

        protected boolean writeEmptyYAMLArrays = true;

        protected boolean orderMapEntriesByKeys = false;

        protected boolean mapKeyAndValueCanonical = false;

        protected boolean wrapExceptions = true;

        protected boolean wrapCollections = true;

        /**
         * Determines whether Object Identity is compared using
         * true JVM-level identity of Object (false); or, <code>equals()</code> method.
         * Latter is sometimes useful when dealing with Database-bound objects with
         * ORM libraries (like Hibernate).
         * <p>
         * Option is disabled by default; meaning that strict identity is used, not
         * <code>equals()</code>
         * </p>
         * @param useEqualityForObjectId true if should useEqualityForObjectId
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder useEqualityForObjectId(boolean useEqualityForObjectId) {
            this.useEqualityForObjectId = useEqualityForObjectId;
            return this;
        }

        public JsYAMLSerializationContext.Builder mapKeyAndValueCanonical(boolean mapKeyAndValueCanonical) {
            this.mapKeyAndValueCanonical = mapKeyAndValueCanonical;
            return this;
        }

        /**
         * Sets whether object members are serialized when their value is null.
         * This has no impact on array elements. The default is true.
         * @param serializeNulls true if should serializeNulls
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder serializeNulls(boolean serializeNulls) {
            this.serializeNulls = serializeNulls;
            return this;
        }

        /**
         * Determines whether {@link Date} and {@link java.sql.Timestamp} values are to be serialized as numeric timestamps
         * (true; the default), or as textual representation.
         * <p>If textual representation is used, the actual format is
         * Option is enabled by default.
         * @param writeDatesAsTimestamps true if should writeDatesAsTimestamps
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder writeDatesAsTimestamps(boolean writeDatesAsTimestamps) {
            this.writeDatesAsTimestamps = writeDatesAsTimestamps;
            return this;
        }

        /**
         * Feature that determines whether {@link Date}s and {@link java.sql.Timestamp}s used as {@link Map} keys are
         * serialized as timestamps or as textual values.
         * <p>If textual representation is used, the actual format is
         * Option is disabled by default.
         * @param writeDateKeysAsTimestamps true if should writeDateKeysAsTimestamps
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder writeDateKeysAsTimestamps(boolean writeDateKeysAsTimestamps) {
            this.writeDateKeysAsTimestamps = writeDateKeysAsTimestamps;
            return this;
        }

        /**
         * Feature that determines whether Map entries with null values are
         * to be serialized (true) or not (false).
         * <p>
         * Feature is enabled by default.
         * </p>
         * @param writeNullMapValues true if should writeNullMapValues
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder writeNullMapValues(boolean writeNullMapValues) {
            this.writeNullMapValues = writeNullMapValues;
            return this;
        }

        /**
         * Feature that determines whether Container properties (POJO properties
         * with declared value of Collection or array; i.e. things that produce YAML
         * arrays) that are empty (have no elements)
         * will be serialized as empty YAML arrays (true), or suppressed from output (false).
         * <p>
         * Note that this does not change behavior of {@link Map}s, or
         * "Collection-like" types.
         * </p>
         * <p>
         * Feature is enabled by default.
         * </p>
         * @param writeEmptyYAMLArrays true if should writeEmptyYAMLArrays
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder writeEmptyYAMLArrays(boolean writeEmptyYAMLArrays) {
            this.writeEmptyYAMLArrays = writeEmptyYAMLArrays;
            return this;
        }

        /**
         * Feature that determines whether {@link Map} entries are first
         * sorted by key before serialization or not: if enabled, additional sorting
         * step is performed if necessary (not necessary for {@link SortedMap}s),
         * if disabled, no additional sorting is needed.
         * <p>
         * Feature is disabled by default.
         * </p>
         * @param orderMapEntriesByKeys true if should orderMapEntriesByKeys
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder orderMapEntriesByKeys(boolean orderMapEntriesByKeys) {
            this.orderMapEntriesByKeys = orderMapEntriesByKeys;
            return this;
        }

        /**
         * Feature that determines whether gwt-jackson code should catch
         * and wrap {@link RuntimeException}s (but never {@link Error}s!)
         * to add additional information about
         * location (within input) of problem or not. If enabled,
         * exceptions will be caught and re-thrown; this can be
         * convenient both in that all exceptions will be checked and
         * declared, and so there is more contextual information.
         * However, sometimes calling application may just want "raw"
         * unchecked exceptions passed as is.
         * <br>
         * <br>
         * Feature is enabled by default.
         * @param wrapExceptions true if should wrapExceptions
         * @return the builder
         */
        public JsYAMLSerializationContext.Builder wrapExceptions(boolean wrapExceptions) {
            this.wrapExceptions = wrapExceptions;
            return this;
        }

        public JsYAMLSerializationContext.Builder wrapCollections(boolean wrapCollections) {
            this.wrapCollections = wrapCollections;
            return this;
        }

        public final YAMLSerializationContext build() {
            return new JsYAMLSerializationContext(serializeNulls, writeDatesAsTimestamps,
                                                  writeDateKeysAsTimestamps, writeNullMapValues,
                                                  writeEmptyYAMLArrays, orderMapEntriesByKeys, mapKeyAndValueCanonical,
                                                  wrapExceptions, wrapCollections);
        }
    }

    public static class DefaultBuilder extends JsYAMLSerializationContext.Builder {

        private DefaultBuilder() {
        }
    }
}
