/*
 * Copyright 2014 Google Inc. All rights reserved.
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
package org.inferred.freebuilder.processor;

import org.inferred.freebuilder.FreeBuilder;
import org.inferred.freebuilder.processor.util.feature.FeatureSet;
import org.inferred.freebuilder.processor.util.testing.BehaviorTester;
import org.inferred.freebuilder.processor.util.testing.CompilationException;
import org.inferred.freebuilder.processor.util.testing.ParameterizedBehaviorTestFactory;
import org.inferred.freebuilder.processor.util.testing.ParameterizedBehaviorTestFactory.Shared;
import org.inferred.freebuilder.processor.util.testing.SourceBuilder;
import org.inferred.freebuilder.processor.util.testing.TestBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.UseParametersRunnerFactory;

import java.util.List;

import javax.tools.JavaFileObject;

@RunWith(Parameterized.class)
@UseParametersRunnerFactory(ParameterizedBehaviorTestFactory.class)
public class DefaultMapperMethodTest {

  @Parameters(name = "{0}")
  public static List<FeatureSet> featureSets() {
    return FeatureSets.ALL;
  }

  private static final JavaFileObject REQUIRED_INTEGER_BEAN_TYPE = new SourceBuilder()
      .addLine("package com.example;")
      .addLine("@%s", FreeBuilder.class)
      .addLine("public interface DataType {")
      .addLine("  int getProperty();")
      .addLine("")
      .addLine("  public static class Builder extends DataType_Builder {}")
      .addLine("}")
      .build();

  private static final JavaFileObject DEFAULT_INTEGER_BEAN_TYPE = new SourceBuilder()
      .addLine("package com.example;")
      .addLine("@%s", FreeBuilder.class)
      .addLine("public interface DataType {")
      .addLine("  int getProperty();")
      .addLine("")
      .addLine("  public static class Builder extends DataType_Builder {")
      .addLine("    public Builder() {")
      .addLine("      setProperty(11);")
      .addLine("    }")
      .addLine("  }")
      .addLine("}")
      .build();

  private static final JavaFileObject REQUIRED_INTEGER_PREFIXLESS_TYPE = new SourceBuilder()
      .addLine("package com.example;")
      .addLine("@%s", FreeBuilder.class)
      .addLine("public interface DataType {")
      .addLine("  int property();")
      .addLine("")
      .addLine("  public static class Builder extends DataType_Builder {}")
      .addLine("}")
      .build();

  private static final JavaFileObject DEFAULT_INTEGER_PREFIXLESS_TYPE = new SourceBuilder()
      .addLine("package com.example;")
      .addLine("@%s", FreeBuilder.class)
      .addLine("public interface DataType {")
      .addLine("  int property();")
      .addLine("")
      .addLine("  public static class Builder extends DataType_Builder {")
      .addLine("    public Builder() {")
      .addLine("      property(11);")
      .addLine("    }")
      .addLine("  }")
      .addLine("}")
      .build();

  @Parameter public FeatureSet features;

  @Rule public final ExpectedException thrown = ExpectedException.none();
  @Shared public BehaviorTester behaviorTester;

  @Test
  public void mapReplacesValueToBeReturnedFromGetterForRequiredProperty_bean() {
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("com.example.DataType value = new com.example.DataType.Builder()")
            .addLine("    .setProperty(11)")
            .addLine("    .mapProperty(a -> a + 3)")
            .addLine("    .build();")
            .addLine("assertEquals(14, value.getProperty());")
            .build())
        .runTest();
  }

  @Test
  public void mapReplacesValueToBeReturnedFromGetterForDefaultProperty_bean() {
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("com.example.DataType value = new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> a + 3)")
            .addLine("    .build();")
            .addLine("assertEquals(14, value.getProperty());")
            .build())
        .runTest();
  }

  @Test
  public void mapDelegatesToSetterForValidationForRequiredProperty_bean() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("property must be non-negative");
    behaviorTester
        .with(new Processor(features))
        .with(new SourceBuilder()
            .addLine("package com.example;")
            .addLine("@%s", FreeBuilder.class)
            .addLine("public interface DataType {")
            .addLine("  int getProperty();")
            .addLine("")
            .addLine("  public static class Builder extends DataType_Builder {")
            .addLine("    @Override public Builder setProperty(int property) {")
            .addLine("      if (property < 0) {")
            .addLine("        throw new IllegalArgumentException(")
            .addLine("            \"property must be non-negative\");")
            .addLine("      }")
            .addLine("      return super.setProperty(property);")
            .addLine("    }")
            .addLine("  }")
            .addLine("}")
            .build())
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .setProperty(11)")
            .addLine("    .mapProperty(a -> -3);")
            .build())
        .runTest();
  }

  @Test
  public void mapDelegatesToSetterForValidationForDefaultProperty_bean() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("property must be non-negative");
    behaviorTester
        .with(new Processor(features))
        .with(new SourceBuilder()
            .addLine("package com.example;")
            .addLine("@%s", FreeBuilder.class)
            .addLine("public interface DataType {")
            .addLine("  int getProperty();")
            .addLine("")
            .addLine("  public static class Builder extends DataType_Builder {")
            .addLine("    public Builder() {")
            .addLine("      setProperty(11);")
            .addLine("    }")
            .addLine("")
            .addLine("    @Override public Builder setProperty(int property) {")
            .addLine("      if (property < 0) {")
            .addLine("        throw new IllegalArgumentException(")
            .addLine("            \"property must be non-negative\");")
            .addLine("      }")
            .addLine("      return super.setProperty(property);")
            .addLine("    }")
            .addLine("  }")
            .addLine("}")
            .build())
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> -3);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForRequiredProperty_bean() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .setProperty(11)")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForDefaultProperty_bean() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForUnsetRequiredProperty_bean() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsCompilationExceptionIfMapperReturnsNullForRequiredProperty_bean() {
    thrown.expect(CompilationException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .setProperty(11)")
            .addLine("    .mapProperty(a -> null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsCompilationExceptionIfMapperReturnsNullForDefaultProperty_bean() {
    thrown.expect(CompilationException.class);
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsIllegalStateExceptionIfRequiredPropertyIsUnset_bean() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("property not set");
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_BEAN_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> 14);")
            .build())
        .runTest();
  }

  @Test
  public void mapReplacesValueToBeReturnedFromGetterForRequiredProperty_prefixless() {
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("com.example.DataType value = new com.example.DataType.Builder()")
            .addLine("    .property(11)")
            .addLine("    .mapProperty(a -> a + 3)")
            .addLine("    .build();")
            .addLine("assertEquals(14, value.property());")
            .build())
        .runTest();
  }

  @Test
  public void mapReplacesValueToBeReturnedFromGetterForDefaultProperty_prefixless() {
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("com.example.DataType value = new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> a + 3)")
            .addLine("    .build();")
            .addLine("assertEquals(14, value.property());")
            .build())
        .runTest();
  }

  @Test
  public void mapDelegatesToSetterForValidationForRequiredProperty_prefixless() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("property must be non-negative");
    behaviorTester
        .with(new Processor(features))
        .with(new SourceBuilder()
            .addLine("package com.example;")
            .addLine("@%s", FreeBuilder.class)
            .addLine("public interface DataType {")
            .addLine("  int property();")
            .addLine("")
            .addLine("  public static class Builder extends DataType_Builder {")
            .addLine("    @Override public Builder property(int property) {")
            .addLine("      if (property < 0) {")
            .addLine("        throw new IllegalArgumentException(")
            .addLine("            \"property must be non-negative\");")
            .addLine("      }")
            .addLine("      return super.property(property);")
            .addLine("    }")
            .addLine("  }")
            .addLine("}")
            .build())
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .property(11)")
            .addLine("    .mapProperty(a -> -3);")
            .build())
        .runTest();
  }

  @Test
  public void mapDelegatesToSetterForValidationForDefaultProperty_prefixless() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("property must be non-negative");
    behaviorTester
        .with(new Processor(features))
        .with(new SourceBuilder()
            .addLine("package com.example;")
            .addLine("@%s", FreeBuilder.class)
            .addLine("public interface DataType {")
            .addLine("  int property();")
            .addLine("")
            .addLine("  public static class Builder extends DataType_Builder {")
            .addLine("    public Builder() {")
            .addLine("      property(11);")
            .addLine("    }")
            .addLine("")
            .addLine("    @Override public Builder property(int property) {")
            .addLine("      if (property < 0) {")
            .addLine("        throw new IllegalArgumentException(")
            .addLine("            \"property must be non-negative\");")
            .addLine("      }")
            .addLine("      return super.property(property);")
            .addLine("    }")
            .addLine("  }")
            .addLine("}")
            .build())
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> -3);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForRequiredProperty_prefixless() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .property(11)")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForDefaultProperty_prefixless() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsNpeIfMapperIsNullForUnsetRequiredProperty_prefixless() {
    thrown.expect(NullPointerException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsCompilationExceptionIfMapperReturnsNullForRequiredProperty_prefixless() {
    thrown.expect(CompilationException.class);
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .property(11)")
            .addLine("    .mapProperty(a -> null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsCompilationExceptionIfMapperReturnsNullForDefaultProperty_prefixless() {
    thrown.expect(CompilationException.class);
    behaviorTester
        .with(new Processor(features))
        .with(DEFAULT_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> null);")
            .build())
        .runTest();
  }

  @Test
  public void mapThrowsIllegalStateExceptionIfRequiredPropertyIsUnset_prefixless() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("property not set");
    behaviorTester
        .with(new Processor(features))
        .with(REQUIRED_INTEGER_PREFIXLESS_TYPE)
        .with(new TestBuilder()
            .addLine("new com.example.DataType.Builder()")
            .addLine("    .mapProperty(a -> 14);")
            .build())
        .runTest();
  }
}
