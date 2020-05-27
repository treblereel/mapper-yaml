package org.treblereel.gwt.yaml.api;

import java.util.Set;

import org.treblereel.gwt.yaml.api.deser.bean.IdentityDeserializationInfo;
import org.treblereel.gwt.yaml.api.deser.bean.TypeDeserializationInfo;

/**
 * <p>YAMLDeserializerParameters interface.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLDeserializerParameters {
    /**
     * <p>getPattern.</p>
     *
     * @return a {@link String} object.
     */
    String getPattern();

    /**
     * <p>setPattern.</p>
     *
     * @param pattern a {@link String} object.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters setPattern(String pattern);

    /**
     * <p>getLocale.</p>
     *
     * @return a {@link String} object.
     */
    String getLocale();

    /**
     * <p>setLocale.</p>
     *
     * @param locale a {@link String} object.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters setLocale(String locale);

    /**
     * <p>getIgnoredProperties.</p>
     *
     * @return a {@link Set} object.
     */
    Set<String> getIgnoredProperties();

    /**
     * <p>addIgnoredProperty.</p>
     *
     * @param ignoredProperty a {@link String} object.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters addIgnoredProperty(String ignoredProperty);

    /**
     * <p>isIgnoreUnknown.</p>
     *
     * @return a boolean.
     */
    boolean isIgnoreUnknown();

    /**
     * <p>setIgnoreUnknown.</p>
     *
     * @param ignoreUnknown a boolean.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters setIgnoreUnknown(boolean ignoreUnknown);

    /**
     * <p>getIdentityInfo.</p>
     *
     * @return a {@link IdentityDeserializationInfo} object.
     */
    IdentityDeserializationInfo getIdentityInfo();

    /**
     * <p>setIdentityInfo.</p>
     *
     * @param identityInfo a {@link IdentityDeserializationInfo} object.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters setIdentityInfo(IdentityDeserializationInfo identityInfo);

    /**
     * <p>getTypeInfo.</p>
     *
     * @return a {@link TypeDeserializationInfo} object.
     */
    TypeDeserializationInfo getTypeInfo();

    /**
     * <p>setTypeInfo.</p>
     *
     * @param typeInfo a {@link TypeDeserializationInfo} object.
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters setTypeInfo(TypeDeserializationInfo typeInfo);
}
