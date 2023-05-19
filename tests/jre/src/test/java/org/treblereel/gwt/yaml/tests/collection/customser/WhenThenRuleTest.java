/*
 * Copyright © 2023 Treblereel
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

package org.treblereel.gwt.yaml.tests.collection.customser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class WhenThenRuleTest {

  private static final WhenThenRule_YamlMapperImpl mapper = WhenThenRule_YamlMapperImpl.INSTANCE;

  private static final String yaml =
      "when:"
          + System.lineSeparator()
          + "  - a"
          + System.lineSeparator()
          + "  - b"
          + System.lineSeparator()
          + "then: c"
          + System.lineSeparator()
          + "off:"
          + System.lineSeparator()
          + "  - a"
          + System.lineSeparator()
          + "  - b";

  @Test
  public void test() throws IOException {
    WhenThenRule tested = new WhenThenRule();
    List when = new ArrayList();
    when.add("a");
    when.add("b");

    String[] off = new String[2];
    off[0] = "a";
    off[1] = "b";

    tested.setThen("c");
    tested.setWhen(when);
    tested.setOff(off);

    String result = mapper.write(tested);

    System.out.println(result);

    assertEquals(yaml, result);
    assertEquals(tested, mapper.read(result));
  }
}
