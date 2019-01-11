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
package org.inferred.freebuilder.processor.util;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.truth.Truth.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import static javax.lang.model.util.ElementFilter.fieldsIn;

import com.google.common.collect.ImmutableList;

import org.inferred.freebuilder.processor.util.feature.StaticFeatureSet;
import org.inferred.freebuilder.processor.util.testing.ModelRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.tools.JavaFileObject;

/** Tests for {@link CompilationUnitBuilder}. */
@RunWith(MockitoJUnitRunner.class)
public class CompilationUnitBuilderTest {

  @Rule public final ModelRule model = new ModelRule();
  @Rule public final ExpectedException thrown = ExpectedException.none();
  @Mock private Filer filer;
  @Mock private JavaFileObject sourceFile;
  private final StringWriter source = new StringWriter();
  private TypeElement originatingElement;

  @Before
  public void setup() throws IOException {
    originatingElement = model.newType("package com.example; public class Foo { }");
    when(filer.createSourceFile((CharSequence) any(), (Element[]) any())).thenReturn(sourceFile);
    when(sourceFile.openWriter()).thenReturn(source);
  }

  @Test
  public void testEmptyUnit() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    assertEquals(
        "// Autogenerated code. Do not modify.\n"
            + "package com.example;\n\n\n",
        source.toString());
  }

  @Test
  public void testAddLine() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source
        .addLine("public class Bar {")
        .addLine("  // %s %s", "Foo", 100)
        .addLine("}");
    assertEquals(
        "// Autogenerated code. Do not modify.\n"
            + "package com.example;\n\n"
            + "public class Bar {\n"
            + "  // Foo 100\n"
            + "}\n",
        source.toString());
  }

  @Test
  public void testAddLine_typeInSamePackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be short: %s", originatingElement);
    assertThat(source.toString()).contains("// This should be short: Foo\n");
  }

  @Test
  public void testAddLine_typeInJavaLangPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be short: %s", String.class);
    assertThat(source.toString()).contains("// This should be short: String\n");
  }

  @Test
  public void testAddLine_primitiveType() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be short: %s", int.class);
    assertThat(source.toString()).contains("// This should be short: int\n");
  }

  @Test
  public void testAddLine_typeInDifferentPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s", AtomicLong.class);
    assertThat(source.toString()).contains("import java.util.concurrent.atomic.AtomicLong;\n");
    assertThat(source.toString()).contains("// This should be imported: AtomicLong\n");
  }

  @Test
  public void testAddLine_nestedTypeInDifferentPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s", ImmutableList.Builder.class);
    assertThat(source.toString()).contains("import com.google.common.collect.ImmutableList;\n");
    assertThat(source.toString()).contains("// This should be imported: ImmutableList.Builder\n");
  }

  @Test
  public void testAddLine_typesWithSameName() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s", java.util.List.class);
    source.addLine("// This should be explicit: %s", java.awt.List.class);
    assertThat(source.toString()).contains("import java.util.List;\n");
    assertThat(source.toString()).doesNotContain("import java.awt.List;\n");
    assertThat(source.toString()).contains("// This should be imported: List\n");
    assertThat(source.toString()).contains("// This should be explicit: java.awt.List\n");
  }

  @Test
  public void testAddLine_typeMirrorInJavaLangPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be short: %s", model.typeMirror(String.class));
    assertThat(source.toString()).contains("// This should be short: String\n");
  }

  @Test
  public void testAddLine_typeMirrorInDifferentPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s", model.typeMirror(AtomicLong.class));
    assertThat(source.toString()).contains("import java.util.concurrent.atomic.AtomicLong;");
    assertThat(source.toString()).contains("// This should be imported: AtomicLong\n");
  }

  @Test
  public void testAddLine_genericTypeMirror() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s",
        model.typeMirror("java.util.List<java.lang.String>"));
    assertThat(source.toString()).contains("import java.util.List;\n");
    assertThat(source.toString()).contains("// This should be imported: List<String>\n");
  }

  @Test
  public void testAddLine_typeElementInJavaLangPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be short: %s",
        model.typeUtils().asElement(model.typeMirror(String.class)));
    assertThat(source.toString()).contains("// This should be short: String\n");
  }

  @Test
  public void testAddLine_typeElementInDifferentPackage() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s",
        model.typeUtils().asElement(model.typeMirror(AtomicLong.class)));
    assertThat(source.toString()).contains("import java.util.concurrent.atomic.AtomicLong;\n");
    assertThat(source.toString()).contains("// This should be imported: AtomicLong\n");
  }

  @Test
  public void testAddLine_genericTypeElement() {
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    source.addLine("// This should be imported: %s",
        model.typeUtils().asElement(model.typeMirror("java.util.List<java.lang.String>")));
    assertThat(source.toString()).contains("import java.util.List;\n");
    // Turning a parameterized type mirror into an element loses the type parameters.
    assertThat(source.toString()).contains("// This should be imported: List\n");
  }

  @Test
  public void testAddLine_errorTypeArgument() {
    TypeElement myType = model.newType(
        "package com.example; class MyType {",
        "  java.util.List<NoSuchType<Foo>> foo;",
        "}");
    DeclaredType errorType = (DeclaredType)
        getOnlyElement(fieldsIn(myType.getEnclosedElements())).asType();
    assertEquals(TypeKind.ERROR, errorType.getTypeArguments().get(0).getKind());
    // Note: myType.toString() returns "java.util.List<<any>>" on current compilers. Weird.
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar");
    thrown.expect(IllegalArgumentException.class);
    source.addLine("%s", errorType);
  }

  @Test
  public void testAddLine_nestedTypeReference() {
    // Nested types can be referred to by their simple name inside enclosing or sibling types
    // They cannot be referred to this way outside of the class!
    CompilationUnitBuilder source = newSourceWriter("com.example", "Bar", "Baz", "Bam");
    QualifiedName baz = QualifiedName.of("com.example", "Bar", "Baz");
    QualifiedName bam = QualifiedName.of("com.example", "Bar", "Bam");
    source.addLine("@ExampleAnnotation(%s.class)", baz)
        .addLine("public interface Bar {")
        .addLine("  %s action();", bam)
        .addLine("  interface Baz {}")
        .addLine("  interface Bam {")
        .addLine("    %s transform(%s value);", baz, bam)
        .addLine("  }")
        .addLine("}");
    assertThat(source.toString()).isEqualTo(""
        + "// Autogenerated code. Do not modify.\n"
        + "package com.example;\n\n"
        + "@ExampleAnnotation(Bar.Baz.class)\n"  // Cannot import or refer to Baz directly
        + "public interface Bar {\n"
        + "  Bam action();\n\n"
        + "  interface Baz {}\n\n"
        + "  interface Bam {\n"
        + "    Baz transform(Bam value);\n"
        + "  }\n"
        + "}\n");
  }

  private CompilationUnitBuilder newSourceWriter(
      String pkg,
      String simpleName,
      String... nestedClassNames) {
    ProcessingEnvironment environment = Mockito.spy(model.environment());
    doReturn(filer).when(environment).getFiler();
    QualifiedName type = QualifiedName.of(pkg, simpleName);
    Set<QualifiedName> nestedClasses = new LinkedHashSet<>();
    for (String nestedClassName : nestedClassNames) {
      nestedClasses.add(type.nestedType(nestedClassName));
    }
    return new CompilationUnitBuilder(
        environment,
        type,
        nestedClasses,
        new StaticFeatureSet());
  }
}
