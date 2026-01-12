/*
 * Copyright © 2026 Treblereel
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

package org.treblereel.gwt.yaml.schema;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.processing.FilerException;
import javax.tools.JavaFileObject;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.exception.GenerationException;
import org.treblereel.gwt.yaml.logger.TreeLogger;

class BeanWriter {

  private final GenerationContext context;
  private final TreeLogger logger;

  BeanWriter(GenerationContext context, TreeLogger logger) {
    this.context = context;
    this.logger = logger;
  }

  void write(String source, String className, String packageName) throws IOException {
    String fullClassName =
        packageName + "." + Character.toUpperCase(className.charAt(0)) + className.substring(1);
    logger.branch(TreeLogger.INFO, "Writing " + fullClassName);
    JavaFileObject builderFile =
        context.getProcessingEnv().getFiler().createSourceFile(fullClassName);
    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
      out.append(source);
    } catch (FilerException e) {
      throw new GenerationException(e);
    }
  }
}
