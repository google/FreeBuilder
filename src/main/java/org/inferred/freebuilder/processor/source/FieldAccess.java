/*
 * Copyright 2017 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.inferred.freebuilder.processor.source;

import java.util.Objects;

public class FieldAccess extends ValueType implements Excerpt {

  private final String fieldName;

  public FieldAccess(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void addTo(SourceBuilder source) {
    IdKey key = new IdKey(fieldName);
    if (source.scope().canStore(key)) {
      // In method scope; we may need to qualify the field reference
      Object idOwner = source.scope().putIfAbsent(key, this);
      if (idOwner != null && !idOwner.equals(this)) {
        source.add("this.");
      }
    }
    source.add(fieldName);
  }

  public Excerpt on(Object obj) {
    if (Objects.equals(obj, "this")) {
      return this;
    } else {
      return code -> code.add("%s.%s", obj, fieldName);
    }
  }

  @Override
  protected void addFields(FieldReceiver fields) {
    fields.add("fieldName", fieldName);
  }
}
