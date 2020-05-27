/*
 * Copyright 2013 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.treblereel.gwt.yaml.api.exception;

/**
 * Base exception for mapping process
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class YAMLMappingException extends RuntimeException {

    /**
     * <p>Constructor for YAMLMappingException.</p>
     */
    public YAMLMappingException() {
    }

    /**
     * <p>Constructor for YAMLMappingException.</p>
     *
     * @param message a {@link String} object.
     */
    public YAMLMappingException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for YAMLMappingException.</p>
     *
     * @param message a {@link String} object.
     * @param cause   a {@link Throwable} object.
     */
    public YAMLMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for YAMLMappingException.</p>
     *
     * @param cause a {@link Throwable} object.
     */
    public YAMLMappingException(Throwable cause) {
        super(cause);
    }
}
