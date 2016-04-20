package org.inferred.freebuilder.processor.util.feature;

import com.google.common.base.Optional;

import org.inferred.freebuilder.processor.util.ParameterizedType;
import org.inferred.freebuilder.processor.util.QualifiedName;
import org.inferred.freebuilder.processor.util.SourceBuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Types in the java.util.function package, if available. Defaults to {@link #UNAVAILABLE} in tests.
 */
public enum FunctionPackage implements Feature<FunctionPackage> {

  AVAILABLE, UNAVAILABLE;

  /**
   * Constant to pass to {@link SourceBuilder#feature(FeatureType)} to get the current status of
   * {@link FunctionPackage}.
   */
  public static final FeatureType<FunctionPackage> FUNCTION_PACKAGE =
      new FeatureType<FunctionPackage>() {

        @Override
        protected FunctionPackage testDefault() {
          return UNAVAILABLE;
        }

        @Override
        protected FunctionPackage forEnvironment(ProcessingEnvironment env) {
          TypeElement element = env.getElementUtils()
              .getTypeElement(UNARY_OPERATOR.getQualifiedName().toString());
          return (element != null) ? AVAILABLE : UNAVAILABLE;
        }
      };

  private static final ParameterizedType CONSUMER =
      QualifiedName.of("java.util.function", "Consumer").withParameters("T");
  private static final ParameterizedType BI_CONSUMER =
      QualifiedName.of("java.util.function", "BiConsumer").withParameters("T", "U");
  private static final ParameterizedType UNARY_OPERATOR =
      QualifiedName.of("java.util.function", "UnaryOperator").withParameters("T");

  /**
   * Parameterized type for {@code java.util.function.Consumer<T>}, if available.
   */
  public Optional<ParameterizedType> consumer() {
    return ifAvailable(CONSUMER);
  }

  /**
   * Parameterized type for {@code java.util.function.BiConsumer<T>}, if available.
   */
  public Optional<ParameterizedType> biConsumer() {
    return ifAvailable(BI_CONSUMER);
  }

  /**
   * Parameterized type for {@code java.util.function.UnaryOperator<T>}, if available.
   */
  public Optional<ParameterizedType> unaryOperator() {
    return ifAvailable(UNARY_OPERATOR);
  }

  private <T> Optional<T> ifAvailable(T value) {
    return (this == AVAILABLE) ? Optional.of(value) : Optional.<T>absent();
  }
}
