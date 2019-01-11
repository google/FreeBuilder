// Autogenerated code. Do not modify.
package org.inferred.freebuilder.processor;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.lang.model.type.TypeMirror;
import org.inferred.freebuilder.processor.util.Excerpt;

/**
 * Auto-generated superclass of {@link org.inferred.freebuilder.processor.Property.Builder}, derived
 * from the API of {@link org.inferred.freebuilder.processor.Property}.
 */
@Generated("org.inferred.freebuilder.processor.Processor")
abstract class Property_Builder {

  /** Creates a new builder using {@code value} as a template. */
  public static org.inferred.freebuilder.processor.Property.Builder from(
      org.inferred.freebuilder.processor.Property value) {
    return new org.inferred.freebuilder.processor.Property.Builder().mergeFrom(value);
  }

  private enum Property {
    TYPE("type"),
    NAME("name"),
    CAPITALIZED_NAME("capitalizedName"),
    ALL_CAPS_NAME("allCapsName"),
    USING_BEAN_CONVENTION("usingBeanConvention"),
    GETTER_NAME("getterName"),
    FULLY_CHECKED_CAST("fullyCheckedCast"),
    ;

    private final String name;

    private Property(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  private TypeMirror type;
  @Nullable private TypeMirror boxedType = null;
  private String name;
  private String capitalizedName;
  private String allCapsName;
  private boolean usingBeanConvention;
  private String getterName;
  private boolean fullyCheckedCast;
  private List<Excerpt> accessorAnnotations = ImmutableList.of();
  private final EnumSet<Property> _unsetProperties = EnumSet.allOf(Property.class);

  /**
   * Sets the value to be returned by {@link org.inferred.freebuilder.processor.Property#getType()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code type} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder setType(TypeMirror type) {
    this.type = Preconditions.checkNotNull(type);
    _unsetProperties.remove(Property.TYPE);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getType()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public TypeMirror getType() {
    Preconditions.checkState(!_unsetProperties.contains(Property.TYPE), "type not set");
    return type;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#getBoxedType()}.
   *
   * @return this {@code Builder} object
   */
  public org.inferred.freebuilder.processor.Property.Builder setBoxedType(
      @Nullable TypeMirror boxedType) {
    this.boxedType = boxedType;
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getBoxedType()}.
   */
  @Nullable
  public TypeMirror getBoxedType() {
    return boxedType;
  }

  /**
   * Sets the value to be returned by {@link org.inferred.freebuilder.processor.Property#getName()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code name} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder setName(String name) {
    this.name = Preconditions.checkNotNull(name);
    _unsetProperties.remove(Property.NAME);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getName()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public String getName() {
    Preconditions.checkState(!_unsetProperties.contains(Property.NAME), "name not set");
    return name;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#getCapitalizedName()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code capitalizedName} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder setCapitalizedName(
      String capitalizedName) {
    this.capitalizedName = Preconditions.checkNotNull(capitalizedName);
    _unsetProperties.remove(Property.CAPITALIZED_NAME);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getCapitalizedName()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public String getCapitalizedName() {
    Preconditions.checkState(
        !_unsetProperties.contains(Property.CAPITALIZED_NAME), "capitalizedName not set");
    return capitalizedName;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#getAllCapsName()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code allCapsName} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder setAllCapsName(String allCapsName) {
    this.allCapsName = Preconditions.checkNotNull(allCapsName);
    _unsetProperties.remove(Property.ALL_CAPS_NAME);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getAllCapsName()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public String getAllCapsName() {
    Preconditions.checkState(
        !_unsetProperties.contains(Property.ALL_CAPS_NAME), "allCapsName not set");
    return allCapsName;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#isUsingBeanConvention()}.
   *
   * @return this {@code Builder} object
   */
  public org.inferred.freebuilder.processor.Property.Builder setUsingBeanConvention(
      boolean usingBeanConvention) {
    this.usingBeanConvention = usingBeanConvention;
    _unsetProperties.remove(Property.USING_BEAN_CONVENTION);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#isUsingBeanConvention()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public boolean isUsingBeanConvention() {
    Preconditions.checkState(
        !_unsetProperties.contains(Property.USING_BEAN_CONVENTION), "usingBeanConvention not set");
    return usingBeanConvention;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#getGetterName()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code getterName} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder setGetterName(String getterName) {
    this.getterName = Preconditions.checkNotNull(getterName);
    _unsetProperties.remove(Property.GETTER_NAME);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getGetterName()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public String getGetterName() {
    Preconditions.checkState(
        !_unsetProperties.contains(Property.GETTER_NAME), "getterName not set");
    return getterName;
  }

  /**
   * Sets the value to be returned by {@link
   * org.inferred.freebuilder.processor.Property#isFullyCheckedCast()}.
   *
   * @return this {@code Builder} object
   */
  public org.inferred.freebuilder.processor.Property.Builder setFullyCheckedCast(
      boolean fullyCheckedCast) {
    this.fullyCheckedCast = fullyCheckedCast;
    _unsetProperties.remove(Property.FULLY_CHECKED_CAST);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns the value that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#isFullyCheckedCast()}.
   *
   * @throws IllegalStateException if the field has not been set
   */
  public boolean isFullyCheckedCast() {
    Preconditions.checkState(
        !_unsetProperties.contains(Property.FULLY_CHECKED_CAST), "fullyCheckedCast not set");
    return fullyCheckedCast;
  }

  /**
   * Adds {@code element} to the list to be returned from {@link
   * org.inferred.freebuilder.processor.Property#getAccessorAnnotations()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code element} is null
   */
  public org.inferred.freebuilder.processor.Property.Builder addAccessorAnnotations(
      Excerpt element) {
    if (accessorAnnotations instanceof ImmutableList) {
      accessorAnnotations = new ArrayList<Excerpt>(accessorAnnotations);
    }
    accessorAnnotations.add(Preconditions.checkNotNull(element));
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Adds each element of {@code elements} to the list to be returned from {@link
   * org.inferred.freebuilder.processor.Property#getAccessorAnnotations()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code elements} is null or contains a null element
   */
  public org.inferred.freebuilder.processor.Property.Builder addAccessorAnnotations(
      Excerpt... elements) {
    return addAllAccessorAnnotations(Arrays.asList(elements));
  }

  /**
   * Adds each element of {@code elements} to the list to be returned from {@link
   * org.inferred.freebuilder.processor.Property#getAccessorAnnotations()}.
   *
   * @return this {@code Builder} object
   * @throws NullPointerException if {@code elements} is null or contains a null element
   */
  public org.inferred.freebuilder.processor.Property.Builder addAllAccessorAnnotations(
      Iterable<? extends Excerpt> elements) {
    if (elements instanceof Collection) {
      int elementsSize = ((Collection<?>) elements).size();
      if (elementsSize != 0) {
        if (accessorAnnotations instanceof ImmutableList) {
          accessorAnnotations = new ArrayList<Excerpt>(accessorAnnotations);
        }
        ((ArrayList<?>) accessorAnnotations)
            .ensureCapacity(accessorAnnotations.size() + elementsSize);
      }
    }
    for (Excerpt element : elements) {
      addAccessorAnnotations(element);
    }
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Clears the list to be returned from {@link
   * org.inferred.freebuilder.processor.Property#getAccessorAnnotations()}.
   *
   * @return this {@code Builder} object
   */
  public org.inferred.freebuilder.processor.Property.Builder clearAccessorAnnotations() {
    if (accessorAnnotations instanceof ImmutableList) {
      accessorAnnotations = ImmutableList.of();
    } else {
      accessorAnnotations.clear();
    }
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns an unmodifiable view of the list that will be returned by {@link
   * org.inferred.freebuilder.processor.Property#getAccessorAnnotations()}. Changes to this builder
   * will be reflected in the view.
   */
  public List<Excerpt> getAccessorAnnotations() {
    if (accessorAnnotations instanceof ImmutableList) {
      accessorAnnotations = new ArrayList<Excerpt>(accessorAnnotations);
    }
    return Collections.unmodifiableList(accessorAnnotations);
  }

  /**
   * Sets all property values using the given {@code org.inferred.freebuilder.processor.Property} as
   * a template.
   */
  public org.inferred.freebuilder.processor.Property.Builder mergeFrom(
      org.inferred.freebuilder.processor.Property value) {
    Property_Builder _defaults = new org.inferred.freebuilder.processor.Property.Builder();
    if (_defaults._unsetProperties.contains(Property.TYPE)
        || !value.getType().equals(_defaults.getType())) {
      setType(value.getType());
    }
    if (value.getBoxedType() != _defaults.getBoxedType()
        && (value.getBoxedType() == null
            || !value.getBoxedType().equals(_defaults.getBoxedType()))) {
      setBoxedType(value.getBoxedType());
    }
    if (_defaults._unsetProperties.contains(Property.NAME)
        || !value.getName().equals(_defaults.getName())) {
      setName(value.getName());
    }
    if (_defaults._unsetProperties.contains(Property.CAPITALIZED_NAME)
        || !value.getCapitalizedName().equals(_defaults.getCapitalizedName())) {
      setCapitalizedName(value.getCapitalizedName());
    }
    if (_defaults._unsetProperties.contains(Property.ALL_CAPS_NAME)
        || !value.getAllCapsName().equals(_defaults.getAllCapsName())) {
      setAllCapsName(value.getAllCapsName());
    }
    if (_defaults._unsetProperties.contains(Property.USING_BEAN_CONVENTION)
        || value.isUsingBeanConvention() != _defaults.isUsingBeanConvention()) {
      setUsingBeanConvention(value.isUsingBeanConvention());
    }
    if (_defaults._unsetProperties.contains(Property.GETTER_NAME)
        || !value.getGetterName().equals(_defaults.getGetterName())) {
      setGetterName(value.getGetterName());
    }
    if (_defaults._unsetProperties.contains(Property.FULLY_CHECKED_CAST)
        || value.isFullyCheckedCast() != _defaults.isFullyCheckedCast()) {
      setFullyCheckedCast(value.isFullyCheckedCast());
    }
    if (value instanceof Value && accessorAnnotations == ImmutableList.<Excerpt>of()) {
      accessorAnnotations = ImmutableList.copyOf(value.getAccessorAnnotations());
    } else {
      addAllAccessorAnnotations(value.getAccessorAnnotations());
    }
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Copies values from the given {@code Builder}. Does not affect any properties not set on the
   * input.
   */
  public org.inferred.freebuilder.processor.Property.Builder mergeFrom(
      org.inferred.freebuilder.processor.Property.Builder template) {
    // Upcast to access private fields; otherwise, oddly, we get an access violation.
    Property_Builder base = template;
    Property_Builder _defaults = new org.inferred.freebuilder.processor.Property.Builder();
    if (!base._unsetProperties.contains(Property.TYPE)
        && (_defaults._unsetProperties.contains(Property.TYPE)
            || !template.getType().equals(_defaults.getType()))) {
      setType(template.getType());
    }
    if (template.getBoxedType() != _defaults.getBoxedType()
        && (template.getBoxedType() == null
            || !template.getBoxedType().equals(_defaults.getBoxedType()))) {
      setBoxedType(template.getBoxedType());
    }
    if (!base._unsetProperties.contains(Property.NAME)
        && (_defaults._unsetProperties.contains(Property.NAME)
            || !template.getName().equals(_defaults.getName()))) {
      setName(template.getName());
    }
    if (!base._unsetProperties.contains(Property.CAPITALIZED_NAME)
        && (_defaults._unsetProperties.contains(Property.CAPITALIZED_NAME)
            || !template.getCapitalizedName().equals(_defaults.getCapitalizedName()))) {
      setCapitalizedName(template.getCapitalizedName());
    }
    if (!base._unsetProperties.contains(Property.ALL_CAPS_NAME)
        && (_defaults._unsetProperties.contains(Property.ALL_CAPS_NAME)
            || !template.getAllCapsName().equals(_defaults.getAllCapsName()))) {
      setAllCapsName(template.getAllCapsName());
    }
    if (!base._unsetProperties.contains(Property.USING_BEAN_CONVENTION)
        && (_defaults._unsetProperties.contains(Property.USING_BEAN_CONVENTION)
            || template.isUsingBeanConvention() != _defaults.isUsingBeanConvention())) {
      setUsingBeanConvention(template.isUsingBeanConvention());
    }
    if (!base._unsetProperties.contains(Property.GETTER_NAME)
        && (_defaults._unsetProperties.contains(Property.GETTER_NAME)
            || !template.getGetterName().equals(_defaults.getGetterName()))) {
      setGetterName(template.getGetterName());
    }
    if (!base._unsetProperties.contains(Property.FULLY_CHECKED_CAST)
        && (_defaults._unsetProperties.contains(Property.FULLY_CHECKED_CAST)
            || template.isFullyCheckedCast() != _defaults.isFullyCheckedCast())) {
      setFullyCheckedCast(template.isFullyCheckedCast());
    }
    addAllAccessorAnnotations(base.accessorAnnotations);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /** Resets the state of this builder. */
  public org.inferred.freebuilder.processor.Property.Builder clear() {
    Property_Builder _defaults = new org.inferred.freebuilder.processor.Property.Builder();
    type = _defaults.type;
    boxedType = _defaults.boxedType;
    name = _defaults.name;
    capitalizedName = _defaults.capitalizedName;
    allCapsName = _defaults.allCapsName;
    usingBeanConvention = _defaults.usingBeanConvention;
    getterName = _defaults.getterName;
    fullyCheckedCast = _defaults.fullyCheckedCast;
    clearAccessorAnnotations();
    _unsetProperties.clear();
    _unsetProperties.addAll(_defaults._unsetProperties);
    return (org.inferred.freebuilder.processor.Property.Builder) this;
  }

  /**
   * Returns a newly-created {@link org.inferred.freebuilder.processor.Property} based on the
   * contents of the {@code Builder}.
   *
   * @throws IllegalStateException if any field has not been set
   */
  public org.inferred.freebuilder.processor.Property build() {
    Preconditions.checkState(_unsetProperties.isEmpty(), "Not set: %s", _unsetProperties);
    return new Value(this);
  }

  /**
   * Returns a newly-created partial {@link org.inferred.freebuilder.processor.Property} for use in
   * unit tests. State checking will not be performed. Unset properties will throw an {@link
   * UnsupportedOperationException} when accessed via the partial object.
   *
   * <p>Partials should only ever be used in tests. They permit writing robust test cases that won't
   * fail if this type gains more application-level constraints (e.g. new required fields) in
   * future. If you require partially complete values in production code, consider using a Builder.
   */
  @VisibleForTesting()
  public org.inferred.freebuilder.processor.Property buildPartial() {
    return new Partial(this);
  }

  private static final class Value extends org.inferred.freebuilder.processor.Property {
    private final TypeMirror type;
    @Nullable private final TypeMirror boxedType;
    private final String name;
    private final String capitalizedName;
    private final String allCapsName;
    private final boolean usingBeanConvention;
    private final String getterName;
    private final boolean fullyCheckedCast;
    private final ImmutableList<Excerpt> accessorAnnotations;

    private Value(Property_Builder builder) {
      this.type = builder.type;
      this.boxedType = builder.boxedType;
      this.name = builder.name;
      this.capitalizedName = builder.capitalizedName;
      this.allCapsName = builder.allCapsName;
      this.usingBeanConvention = builder.usingBeanConvention;
      this.getterName = builder.getterName;
      this.fullyCheckedCast = builder.fullyCheckedCast;
      this.accessorAnnotations = ImmutableList.copyOf(builder.accessorAnnotations);
    }

    @Override
    public TypeMirror getType() {
      return type;
    }

    @Override
    @Nullable
    public TypeMirror getBoxedType() {
      return boxedType;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getCapitalizedName() {
      return capitalizedName;
    }

    @Override
    public String getAllCapsName() {
      return allCapsName;
    }

    @Override
    public boolean isUsingBeanConvention() {
      return usingBeanConvention;
    }

    @Override
    public String getGetterName() {
      return getterName;
    }

    @Override
    public boolean isFullyCheckedCast() {
      return fullyCheckedCast;
    }

    @Override
    public ImmutableList<Excerpt> getAccessorAnnotations() {
      return accessorAnnotations;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Value)) {
        return false;
      }
      Value other = (Value) obj;
      if (!type.equals(other.type)) {
        return false;
      }
      if (boxedType != other.boxedType
          && (boxedType == null || !boxedType.equals(other.boxedType))) {
        return false;
      }
      if (!name.equals(other.name)) {
        return false;
      }
      if (!capitalizedName.equals(other.capitalizedName)) {
        return false;
      }
      if (!allCapsName.equals(other.allCapsName)) {
        return false;
      }
      if (usingBeanConvention != other.usingBeanConvention) {
        return false;
      }
      if (!getterName.equals(other.getterName)) {
        return false;
      }
      if (fullyCheckedCast != other.fullyCheckedCast) {
        return false;
      }
      if (!accessorAnnotations.equals(other.accessorAnnotations)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(
          new Object[] {
            type,
            boxedType,
            name,
            capitalizedName,
            allCapsName,
            usingBeanConvention,
            getterName,
            fullyCheckedCast,
            accessorAnnotations
          });
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("Property{type=").append(type);
      if (boxedType != null) {
        result.append(", boxedType=").append(boxedType);
      }
      return result
          .append(", name=")
          .append(name)
          .append(", capitalizedName=")
          .append(capitalizedName)
          .append(", allCapsName=")
          .append(allCapsName)
          .append(", usingBeanConvention=")
          .append(usingBeanConvention)
          .append(", getterName=")
          .append(getterName)
          .append(", fullyCheckedCast=")
          .append(fullyCheckedCast)
          .append(", accessorAnnotations=")
          .append(accessorAnnotations)
          .append("}")
          .toString();
    }
  }

  private static final class Partial extends org.inferred.freebuilder.processor.Property {
    private final TypeMirror type;
    @Nullable private final TypeMirror boxedType;
    private final String name;
    private final String capitalizedName;
    private final String allCapsName;
    private final boolean usingBeanConvention;
    private final String getterName;
    private final boolean fullyCheckedCast;
    private final ImmutableList<Excerpt> accessorAnnotations;
    private final EnumSet<Property> _unsetProperties;

    Partial(Property_Builder builder) {
      this.type = builder.type;
      this.boxedType = builder.boxedType;
      this.name = builder.name;
      this.capitalizedName = builder.capitalizedName;
      this.allCapsName = builder.allCapsName;
      this.usingBeanConvention = builder.usingBeanConvention;
      this.getterName = builder.getterName;
      this.fullyCheckedCast = builder.fullyCheckedCast;
      this.accessorAnnotations = ImmutableList.copyOf(builder.accessorAnnotations);
      this._unsetProperties = builder._unsetProperties.clone();
    }

    @Override
    public TypeMirror getType() {
      if (_unsetProperties.contains(Property.TYPE)) {
        throw new UnsupportedOperationException("type not set");
      }
      return type;
    }

    @Override
    @Nullable
    public TypeMirror getBoxedType() {
      return boxedType;
    }

    @Override
    public String getName() {
      if (_unsetProperties.contains(Property.NAME)) {
        throw new UnsupportedOperationException("name not set");
      }
      return name;
    }

    @Override
    public String getCapitalizedName() {
      if (_unsetProperties.contains(Property.CAPITALIZED_NAME)) {
        throw new UnsupportedOperationException("capitalizedName not set");
      }
      return capitalizedName;
    }

    @Override
    public String getAllCapsName() {
      if (_unsetProperties.contains(Property.ALL_CAPS_NAME)) {
        throw new UnsupportedOperationException("allCapsName not set");
      }
      return allCapsName;
    }

    @Override
    public boolean isUsingBeanConvention() {
      if (_unsetProperties.contains(Property.USING_BEAN_CONVENTION)) {
        throw new UnsupportedOperationException("usingBeanConvention not set");
      }
      return usingBeanConvention;
    }

    @Override
    public String getGetterName() {
      if (_unsetProperties.contains(Property.GETTER_NAME)) {
        throw new UnsupportedOperationException("getterName not set");
      }
      return getterName;
    }

    @Override
    public boolean isFullyCheckedCast() {
      if (_unsetProperties.contains(Property.FULLY_CHECKED_CAST)) {
        throw new UnsupportedOperationException("fullyCheckedCast not set");
      }
      return fullyCheckedCast;
    }

    @Override
    public ImmutableList<Excerpt> getAccessorAnnotations() {
      return accessorAnnotations;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Partial)) {
        return false;
      }
      Partial other = (Partial) obj;
      if (type != other.type && (type == null || !type.equals(other.type))) {
        return false;
      }
      if (boxedType != other.boxedType
          && (boxedType == null || !boxedType.equals(other.boxedType))) {
        return false;
      }
      if (name != other.name && (name == null || !name.equals(other.name))) {
        return false;
      }
      if (capitalizedName != other.capitalizedName
          && (capitalizedName == null || !capitalizedName.equals(other.capitalizedName))) {
        return false;
      }
      if (allCapsName != other.allCapsName
          && (allCapsName == null || !allCapsName.equals(other.allCapsName))) {
        return false;
      }
      if (usingBeanConvention != other.usingBeanConvention) {
        return false;
      }
      if (getterName != other.getterName
          && (getterName == null || !getterName.equals(other.getterName))) {
        return false;
      }
      if (fullyCheckedCast != other.fullyCheckedCast) {
        return false;
      }
      if (!accessorAnnotations.equals(other.accessorAnnotations)) {
        return false;
      }
      return _unsetProperties.equals(other._unsetProperties);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(
          new Object[] {
            type,
            boxedType,
            name,
            capitalizedName,
            allCapsName,
            usingBeanConvention,
            getterName,
            fullyCheckedCast,
            accessorAnnotations,
            _unsetProperties
          });
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("partial Property{");
      if (!_unsetProperties.contains(Property.TYPE)) {
        result.append("type=").append(type).append(", ");
      }
      if (boxedType != null) {
        result.append("boxedType=").append(boxedType).append(", ");
      }
      if (!_unsetProperties.contains(Property.NAME)) {
        result.append("name=").append(name).append(", ");
      }
      if (!_unsetProperties.contains(Property.CAPITALIZED_NAME)) {
        result.append("capitalizedName=").append(capitalizedName).append(", ");
      }
      if (!_unsetProperties.contains(Property.ALL_CAPS_NAME)) {
        result.append("allCapsName=").append(allCapsName).append(", ");
      }
      if (!_unsetProperties.contains(Property.USING_BEAN_CONVENTION)) {
        result.append("usingBeanConvention=").append(usingBeanConvention).append(", ");
      }
      if (!_unsetProperties.contains(Property.GETTER_NAME)) {
        result.append("getterName=").append(getterName).append(", ");
      }
      if (!_unsetProperties.contains(Property.FULLY_CHECKED_CAST)) {
        result.append("fullyCheckedCast=").append(fullyCheckedCast).append(", ");
      }
      return result
          .append("accessorAnnotations=")
          .append(accessorAnnotations)
          .append("}")
          .toString();
    }
  }
}
