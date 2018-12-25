/*
 * Copyright 2015 Google Inc. All rights reserved.
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

import static com.google.common.truth.Truth.assertThat;
import static java.util.stream.Collectors.joining;
import static org.inferred.freebuilder.processor.GenericTypeElementImpl.newTopLevelGenericType;
import static org.inferred.freebuilder.processor.util.ClassTypeImpl.INTEGER;
import static org.inferred.freebuilder.processor.util.ClassTypeImpl.STRING;
import static org.inferred.freebuilder.processor.util.FunctionalType.consumer;
import static org.inferred.freebuilder.processor.util.PrimitiveTypeImpl.INT;
import static org.inferred.freebuilder.processor.util.WildcardTypeImpl.wildcardSuper;

import org.inferred.freebuilder.processor.GenericTypeElementImpl.GenericTypeMirrorImpl;
import org.inferred.freebuilder.processor.Metadata.Property;
import org.inferred.freebuilder.processor.util.CompilationUnitBuilder;
import org.inferred.freebuilder.processor.util.QualifiedName;
import org.inferred.freebuilder.processor.util.SourceBuilder;
import org.inferred.freebuilder.processor.util.SourceStringBuilder;
import org.inferred.freebuilder.processor.util.feature.Feature;
import org.inferred.freebuilder.processor.util.feature.GuavaLibrary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class MapSourceTest {

  @Test
  public void test_guava_j8() {
    Metadata metadata = createMetadata(true);

    String source = generateSource(metadata, GuavaLibrary.AVAILABLE);
    assertThat(source).isEqualTo(Stream.of(
        "/** Auto-generated superclass of {@link Person.Builder}, "
            + "derived from the API of {@link Person}. */",
        "abstract class Person_Builder {",
        "",
        "  /** Creates a new builder using {@code value} as a template. */",
        "  public static Person.Builder from(Person value) {",
        "    return new Person.Builder().mergeFrom(value);",
        "  }",
        "",
        "  private final LinkedHashMap<Integer, String> name = new LinkedHashMap<>();",
        "",
        "  /**",
        "   * Associates {@code key} with {@code value} in the map to be returned from {@link",
        "   * Person#getName()}. If the map previously contained a mapping for the key, "
            + "the old value is",
        "   * replaced by the specified value.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code value} is null",
        "   */",
        "  public Person.Builder putName(int key, String value) {",
        "    Objects.requireNonNull(value);",
        "    name.put(key, value);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies all of the mappings from {@code map} to the map to be returned from {@link",
        "   * Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code map} is null or contains a null key or value",
        "   */",
        "  public Person.Builder putAllName(Map<? extends Integer, ? extends String> map) {",
        "    for (Map.Entry<? extends Integer, ? extends String> entry : map.entrySet()) {",
        "      putName(entry.getKey(), entry.getValue());",
        "    }",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes the mapping for {@code key} from the map to be returned from "
            + "{@link Person#getName()},",
        "   * if one is present.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder removeName(int key) {",
        "    name.remove(key);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Invokes {@code mutator} with the map to be returned from {@link Person#getName()}.",
        "   *",
        "   * <p>This method mutates the map in-place. {@code mutator} is a void consumer, so any "
            + "value",
        "   * returned from a lambda will be ignored. Take care not to call pure functions, like "
            + "{@link",
        "   * Collection#stream()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code mutator} is null",
        "   */",
        "  public Person.Builder mutateName(Consumer<? super Map<Integer, String>> mutator) {",
        "    // If putName is overridden, this method will be updated to delegate to it",
        "    mutator.accept(name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes all of the mappings from the map to be returned from "
            + "{@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder clearName() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns an unmodifiable view of the map that will be returned by "
            + "{@link Person#getName()}.",
        "   * Changes to this builder will be reflected in the view.",
        "   */",
        "  public Map<Integer, String> getName() {",
        "    return Collections.unmodifiableMap(name);",
        "  }",
        "",
        "  /** Sets all property values using the given {@code Person} as a template. */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    putAllName(value.getName());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}. "
            + "Does not affect any properties not set on the",
        "   * input.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    // Upcast to access private fields; otherwise, oddly, we get an access violation.",
        "    Person_Builder base = template;",
        "    putAllName(base.name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Resets the state of this builder. */",
        "  public Person.Builder clear() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Returns a newly-created {@link Person} based on the contents of the "
            + "{@code Builder}. */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person} for use in unit tests. "
            + "State checking will not",
        "   * be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests. "
            + "They permit writing robust test cases that won't",
        "   * fail if this type gains more application-level constraints "
            + "(e.g. new required fields) in",
        "   * future. If you require partially complete values in production code, "
            + "consider using a Builder.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "}\n").collect(joining("\n")));
  }

  @Test
  public void test_noGuava_j8() {
    Metadata metadata = createMetadata(true);

    assertThat(generateSource(metadata)).isEqualTo(Stream.of(
        "/** Auto-generated superclass of {@link Person.Builder}, "
            + "derived from the API of {@link Person}. */",
        "abstract class Person_Builder {",
        "",
        "  /** Creates a new builder using {@code value} as a template. */",
        "  public static Person.Builder from(Person value) {",
        "    return new Person.Builder().mergeFrom(value);",
        "  }",
        "",
        "  private final LinkedHashMap<Integer, String> name = new LinkedHashMap<>();",
        "",
        "  /**",
        "   * Associates {@code key} with {@code value} in the map to be returned from {@link",
        "   * Person#getName()}. If the map previously contained a mapping for the key, "
            + "the old value is",
        "   * replaced by the specified value.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code value} is null",
        "   */",
        "  public Person.Builder putName(int key, String value) {",
        "    Objects.requireNonNull(value);",
        "    name.put(key, value);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies all of the mappings from {@code map} to the map to be returned from {@link",
        "   * Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code map} is null or contains a null key or value",
        "   */",
        "  public Person.Builder putAllName(Map<? extends Integer, ? extends String> map) {",
        "    for (Map.Entry<? extends Integer, ? extends String> entry : map.entrySet()) {",
        "      putName(entry.getKey(), entry.getValue());",
        "    }",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes the mapping for {@code key} from the map to be returned from "
            + "{@link Person#getName()},",
        "   * if one is present.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder removeName(int key) {",
        "    name.remove(key);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Invokes {@code mutator} with the map to be returned from {@link Person#getName()}.",
        "   *",
        "   * <p>This method mutates the map in-place. {@code mutator} is a void consumer, so any "
            + "value",
        "   * returned from a lambda will be ignored. Take care not to call pure functions, like "
            + "{@link",
        "   * Collection#stream()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code mutator} is null",
        "   */",
        "  public Person.Builder mutateName(Consumer<? super Map<Integer, String>> mutator) {",
        "    // If putName is overridden, this method will be updated to delegate to it",
        "    mutator.accept(name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes all of the mappings from the map to be returned from "
            + "{@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder clearName() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns an unmodifiable view of the map that will be returned by "
            + "{@link Person#getName()}.",
        "   * Changes to this builder will be reflected in the view.",
        "   */",
        "  public Map<Integer, String> getName() {",
        "    return Collections.unmodifiableMap(name);",
        "  }",
        "",
        "  /** Sets all property values using the given {@code Person} as a template. */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    putAllName(value.getName());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}. "
            + "Does not affect any properties not set on the",
        "   * input.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    // Upcast to access private fields; otherwise, oddly, we get an access violation.",
        "    Person_Builder base = template;",
        "    putAllName(base.name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Resets the state of this builder. */",
        "  public Person.Builder clear() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Returns a newly-created {@link Person} based on the contents of the "
            + "{@code Builder}. */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person} for use in unit tests. "
            + "State checking will not",
        "   * be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests. "
            + "They permit writing robust test cases that won't",
        "   * fail if this type gains more application-level constraints "
            + "(e.g. new required fields) in",
        "   * future. If you require partially complete values in production code, "
            + "consider using a Builder.",
        "   */",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = immutableMap(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = immutableMap(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static <K, V> Map<K, V> immutableMap(Map<K, V> entries) {",
        "    switch (entries.size()) {",
        "      case 0:",
        "        return Collections.emptyMap();",
        "      case 1:",
        "        Map.Entry<K, V> entry = entries.entrySet().iterator().next();",
        "        return Collections.singletonMap(entry.getKey(), entry.getValue());",
        "      default:",
        "        return Collections.unmodifiableMap(new LinkedHashMap<>(entries));",
        "    }",
        "  }",
        "}\n").collect(joining("\n")));
  }

  @Test
  public void test_prefixless() {
    Metadata metadata = createMetadata(false);

    assertThat(generateSource(metadata, GuavaLibrary.AVAILABLE)).isEqualTo(Stream.of(
        "/** Auto-generated superclass of {@link Person.Builder}, "
            + "derived from the API of {@link Person}. */",
        "abstract class Person_Builder {",
        "",
        "  /** Creates a new builder using {@code value} as a template. */",
        "  public static Person.Builder from(Person value) {",
        "    return new Person.Builder().mergeFrom(value);",
        "  }",
        "",
        "  private final LinkedHashMap<Integer, String> name = new LinkedHashMap<>();",
        "",
        "  /**",
        "   * Associates {@code key} with {@code value} in the map to be returned from "
            + "{@link Person#name()}.",
        "   * If the map previously contained a mapping for the key, "
            + "the old value is replaced by the",
        "   * specified value.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code value} is null",
        "   */",
        "  public Person.Builder putName(int key, String value) {",
        "    Objects.requireNonNull(value);",
        "    name.put(key, value);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies all of the mappings from {@code map} to the map to be returned from {@link",
        "   * Person#name()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code map} is null or contains a null key or value",
        "   */",
        "  public Person.Builder putAllName(Map<? extends Integer, ? extends String> map) {",
        "    for (Map.Entry<? extends Integer, ? extends String> entry : map.entrySet()) {",
        "      putName(entry.getKey(), entry.getValue());",
        "    }",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes the mapping for {@code key} from the map to be returned from "
            + "{@link Person#name()}, if",
        "   * one is present.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder removeName(int key) {",
        "    name.remove(key);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Invokes {@code mutator} with the map to be returned from {@link Person#name()}.",
        "   *",
        "   * <p>This method mutates the map in-place. {@code mutator} is a void consumer, so any "
            + "value",
        "   * returned from a lambda will be ignored. Take care not to call pure functions, like "
            + "{@link",
        "   * Collection#stream()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code mutator} is null",
        "   */",
        "  public Person.Builder mutateName(Consumer<? super Map<Integer, String>> mutator) {",
        "    // If putName is overridden, this method will be updated to delegate to it",
        "    mutator.accept(name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes all of the mappings from the map to be returned from {@link Person#name()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder clearName() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns an unmodifiable view of the map that will be returned by "
            + "{@link Person#name()}. Changes",
        "   * to this builder will be reflected in the view.",
        "   */",
        "  public Map<Integer, String> name() {",
        "    return Collections.unmodifiableMap(name);",
        "  }",
        "",
        "  /** Sets all property values using the given {@code Person} as a template. */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    putAllName(value.name());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}. "
            + "Does not affect any properties not set on the",
        "   * input.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    // Upcast to access private fields; otherwise, oddly, we get an access violation.",
        "    Person_Builder base = template;",
        "    putAllName(base.name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Resets the state of this builder. */",
        "  public Person.Builder clear() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /** Returns a newly-created {@link Person} based on the contents of the "
            + "{@code Builder}. */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person} for use in unit tests. "
            + "State checking will not",
        "   * be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests. "
            + "They permit writing robust test cases that won't",
        "   * fail if this type gains more application-level constraints "
            + "(e.g. new required fields) in",
        "   * future. If you require partially complete values in production code, "
            + "consider using a Builder.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> name() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> name() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "}\n").collect(joining("\n")));
  }

  private static String generateSource(Metadata metadata, Feature<?>... features) {
    SourceBuilder sourceBuilder = SourceStringBuilder.simple(features);
    new CodeGenerator().writeBuilderSource(sourceBuilder, metadata);
    return CompilationUnitBuilder.formatSource(sourceBuilder.toString());
  }

  /**
   * Returns a {@link Metadata} instance for a FreeBuilder type with a single property, name, of
   * type {@code Map<Integer, String>}, and which does not override any methods.
   * @param bean TODO
   */
  private static Metadata createMetadata(boolean bean) {
    GenericTypeElementImpl map = newTopLevelGenericType("java.util.Map");
    GenericTypeMirrorImpl mapIntString = map.newMirror(INTEGER, STRING);
    QualifiedName person = QualifiedName.of("com.example", "Person");
    QualifiedName generatedBuilder = QualifiedName.of("com.example", "Person_Builder");
    Property name = new Property.Builder()
        .setAllCapsName("NAME")
        .setBoxedType(mapIntString)
        .setCapitalizedName("Name")
        .setFullyCheckedCast(true)
        .setGetterName(bean ? "getName" : "name")
        .setName("name")
        .setType(mapIntString)
        .setUsingBeanConvention(bean)
        .build();
    Metadata metadata = new Metadata.Builder()
        .setBuilder(person.nestedType("Builder").withParameters())
        .setExtensible(true)
        .setBuilderFactory(BuilderFactory.NO_ARGS_CONSTRUCTOR)
        .setBuilderSerializable(false)
        .setGeneratedBuilder(generatedBuilder.withParameters())
        .setInterfaceType(false)
        .setPartialType(generatedBuilder.nestedType("Partial").withParameters())
        .addProperties(name)
        .setPropertyEnum(generatedBuilder.nestedType("Property").withParameters())
        .setType(person.withParameters())
        .setValueType(generatedBuilder.nestedType("Value").withParameters())
        .build();
    return metadata.toBuilder()
        .clearProperties()
        .addProperties(name.toBuilder()
            .setCodeGenerator(new MapProperty(
                metadata,
                name,
                false,
                INTEGER,
                Optional.of(INT),
                STRING,
                Optional.empty(),
                consumer(wildcardSuper(mapIntString))))
            .build())
        .build();
  }

}
