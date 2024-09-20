/*
 * ******************************************************************************
 *  * Copyright (c) 2021 Zerouno S.r.L.. All Rights Reserved.
 *  *
 *  * This software is the confidential and proprietary information of Zerouno
 *  * S.r.L.. ("Confidential Information"). You shall not disclose such
 *  * Confidential Information and shall use it only in accordance with the terms
 *  * of the license agreement you entered into with Zerouno.
 *  *
 *  * ZEROUNO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 *  * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 *  * NON-INFRINGEMENT. ZEROUNO SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 *  * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 *  * DERIVATIVES.
 *  ******************************************************************************
 *
 */

package com.example.demo.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * TODO improve class performance using cache, maps, and resolvers
 */
public final class DataMapperUtils {

  private static final Logger logger = LoggerFactory.getLogger(DataMapperUtils.class);
  /**
   * FIXME create a set of SimpleProperties
   */
  private static final Set<Class<?>> SIMPLE_PROPERTIES = new HashSet<>(Arrays.asList(
      String.class,
      Integer.class,
      Long.class,
      Float.class,
      Double.class,
      Character.class
  ));

  /**
   *
   */
  private DataMapperUtils() {

  }

  /**
   *
   */
  public static final Set<String> evalPatchPropertySet(JsonNode jsonNode) {

    Set<String> set = new HashSet<>();
    evalPatchPropertySet(jsonNode, new StringBuilder(), set);
    return set;
  }

  /**
   *
   */
  private static final void evalPatchPropertySet(
      final JsonNode jsonNode,
      final StringBuilder propertyPrefix,
      final Set<String> set) {

    int prefixLength = propertyPrefix.length();

    Iterator<Entry<String, JsonNode>> i = jsonNode.fields();
    while (i.hasNext()) {
      Entry<String, JsonNode> entry = i.next();

      String name = entry.getKey();
      String nameToCheck = evalNameToCheck(name, propertyPrefix, prefixLength);
      set.add(nameToCheck);

      JsonNode childNode = entry.getValue();
      if (childNode.isObject()) {
        evalPatchPropertySet(childNode, propertyPrefix, set);
      }
    }
  }

  /**
   * Inspired from org.apache.commons.beanutils.BeanUtils.copyProperties(Object, Object)
   */
  public static final <T> T patchProperties(
      final T dest,
      final Object orig,
      final String... propertiesToIgnore) {

    HashSet<String> ignoreSet = null;
    if (propertiesToIgnore != null && propertiesToIgnore.length > 0) {
      ignoreSet = new HashSet<>(Arrays.asList(propertiesToIgnore));
    }

    return patchProperties(
        dest,
        orig,
        new StringBuilder(),
        null,
        ignoreSet);
  }

  /**
   * Inspired from org.apache.commons.beanutils.BeanUtils.copyProperties(Object, Object)
   */
  public static final <T> T patchProperties(
      final T dest,
      final Object orig,
      final Set<String> ignoreSet) {

    return patchProperties(
        dest,
        orig,
        new StringBuilder(),
        null,
        ignoreSet);
  }

  /**
   *
   */
  private static final <T> T patchProperties(
      final T dest,
      final Object orig,
      final StringBuilder propertyPrefix,
      Set<String> patchSet,
      final Set<String> ignoreSet) {

    Objects.requireNonNull(dest, "dest");
    Objects.requireNonNull(orig, "orig");

    int prefixLength = propertyPrefix.length();

    if (orig instanceof Map) {
      patchPropertiesFromMap(dest, orig, propertyPrefix, ignoreSet, prefixLength);
    } else if (orig instanceof JsonNode) {
      patchFromJsonNode(dest, orig, propertyPrefix, ignoreSet, prefixLength);
    } else {
      patchFromBean(dest, orig, propertyPrefix, patchSet, ignoreSet, prefixLength);
    }

    return dest;
  }

  /**
   *
   */
  private static <T> void patchPropertiesFromMap(
      final T dest,
      final Object orig,
      final StringBuilder propertyPrefix,
      final Set<String> ignoreSet,
      final int prefixLength) {

    // Map properties are always of type <String, Object>

    @SuppressWarnings("unchecked") final Map<String, Object> propMap = (Map<String, Object>) orig;

    boolean destAsMap = dest instanceof Map;
    for (final Entry<String, Object> entry : propMap.entrySet()) {

      final String name = entry.getKey();
      final String nameToCheck = evalNameToCheck(name, propertyPrefix, prefixLength);

      if (ignoreSet != null && ignoreSet.contains(nameToCheck)) {
        continue; // Ignore if its name is in the ignoreSet
      }

      if (PropertyUtils.isWriteable(dest, name)) {
        try {
          final Object value = entry.getValue();
          patchPropertyWithObject(dest, name, value, propertyPrefix, null, ignoreSet);
        } catch (final Exception ex) {
          logger.error("patchProperties: error with bean {} on property {}", dest.getClass(), name, ex);
        }
      } else if (destAsMap) {
        try {
          final Object value = entry.getValue();
          patchPropertyWithMap((Map) dest, name, value, propertyPrefix, null, ignoreSet);
        } catch (final Exception ex) {
          logger.error("patchProperties: error with bean {} on property {}", dest.getClass(), name, ex);
        }
      }
    }
  }

  /**
   *
   */
  private static <T> void patchFromBean(
      final T dest,
      final Object orig,
      final StringBuilder propertyPrefix,
      final Set<String> patchSet,
      final Set<String> ignoreSet,
      final int prefixLength) {

    final PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
    for (PropertyDescriptor origDescriptor : origDescriptors) {

      final String name = origDescriptor.getName();
      final String nameToCheck = evalNameToCheck(name, propertyPrefix, prefixLength);

      if ("class".equals(name)
          || (ignoreSet != null && ignoreSet.contains(nameToCheck))
          || (patchSet != null && !patchSet.isEmpty() && !patchSet.contains(nameToCheck))) {
        continue; // Ignore if property name is "class" or if its name is in the ignoreSet
      }

      if (PropertyUtils.isReadable(orig, name) &&
          PropertyUtils.isWriteable(dest, name)) {

        try {
          final Object value = PropertyUtils.getSimpleProperty(orig, name);
          if(value != null)
            patchPropertyWithObject(dest, name, value, propertyPrefix, patchSet, ignoreSet);
        } catch (final Exception ex) {
          logger.error("patchProperties: error with bean {} on property {}", dest.getClass(), name, ex);
        }
      }
    }
  }

  /**
   *
   */
  private static <T> void patchFromJsonNode(
      final T dest,
      final Object orig,
      final StringBuilder propertyPrefix,
      final Set<String> ignoreSet,
      final int prefixLength) {

    final JsonNode jsonNode = (JsonNode) orig;
    Iterator<Entry<String, JsonNode>> i = jsonNode.fields();
    while (i.hasNext()) {
      Entry<String, JsonNode> entry = i.next();

      String name = entry.getKey();
      final String nameToCheck = evalNameToCheck(name, propertyPrefix, prefixLength);

      if (ignoreSet != null && ignoreSet.contains(nameToCheck)) {
        continue; // Ignore if its name is in the ignoreSet
      }

      if (PropertyUtils.isWriteable(dest, name)) {
        try {
          JsonNode value = entry.getValue();
          patchPropertyWithJsonNode(dest, name, value, propertyPrefix, ignoreSet);
        } catch (final Exception ex) {
          logger.error("patchProperties: error with bean {} on property {}", dest.getClass(), name, ex);
        }
      }
    }
  }

  /**
   *
   */
  private static <T> void patchPropertyWithObject(
      final T dest,
      final String name,
      final Object value,
      final StringBuilder propertyPrefix,
      final Set<String> patchSet,
      final Set<String> ignoreSet)
      throws IllegalAccessException,
      InvocationTargetException,
      NoSuchMethodException,
      InstantiationException {

    final PropertyDescriptor destPropDescriptor = PropertyUtils.getPropertyDescriptor(dest, name);
    final Class<?> destPropClass = destPropDescriptor.getPropertyType();

    if (value == null) {
      BeanUtils.copyProperty(dest, name, null);
    } else {
      if (isSimpleProperty(destPropClass) || (destPropClass.equals(Object.class) && isSimpleProperty(value.getClass()))) {
        BeanUtils.copyProperty(dest, name, value);
      } else {
        Object destValue = PropertyUtils.getSimpleProperty(dest, name);
        if (destValue == null) {
          destValue = destPropClass.getDeclaredConstructor().newInstance();
          patchProperties(destValue, value, propertyPrefix, patchSet, ignoreSet);
          BeanUtils.copyProperty(dest, name, destValue);
        } else {
          patchProperties(destValue, value, propertyPrefix, patchSet, ignoreSet);
        }
      }
    }
  }

  /**
   *
   */
  private static <T extends Map<String, Object>> void patchPropertyWithMap(
      final T dest,
      final String name,
      final Object value,
      final StringBuilder propertyPrefix,
      final Set<String> patchSet,
      final Set<String> ignoreSet)
      throws IllegalAccessException,
      InvocationTargetException,
      NoSuchMethodException,
      InstantiationException {

    if (value != null) {
      final Class<?> destPropClass = value.getClass();
      if (isSimpleProperty(destPropClass)) {
        dest.put(name, value);
      } else {
        Object destValue = null;
        try {
          //Controllo se dest è una classe che estende una Map con metodi specifici
          destValue = PropertyUtils.getSimpleProperty(dest, name);
        } catch (final NoSuchMethodException ex){
          //Eccezione ignorata xè metodo specifico non presente, allora ottengo l'oggetto con una get
          destValue = dest.get(name);
        }
        if (destValue == null) {
          try {
            destValue = destPropClass.newInstance();
          } catch (Exception e) {
            destValue = ((TextNode)value).textValue();
          }
          patchProperties(destValue, value, propertyPrefix, patchSet, ignoreSet);
          dest.put(name, destValue);
        } else {
          patchProperties(destValue, value, propertyPrefix, patchSet, ignoreSet);
        }
      }
    }
  }

  /**
   *
   */
  private static <T> void patchPropertyWithJsonNode(
      final T dest,
      final String name,
      final JsonNode jsonValue,
      final StringBuilder propertyPrefix,
      final Set<String> ignoreSet)
      throws IllegalAccessException,
      InvocationTargetException,
      NoSuchMethodException,
      InstantiationException {

    final PropertyDescriptor destPropDescriptor = PropertyUtils.getPropertyDescriptor(dest, name);
    final Class<?> destPropClass = destPropDescriptor.getPropertyType();

    if (jsonValue.isNull()) {
      BeanUtils.copyProperty(dest, name, null);
    } else {
      Object value = jsonValueToObject(jsonValue, destPropClass);
      if (value == null || isSimpleProperty(destPropClass)) {
        BeanUtils.copyProperty(dest, name, value);
      } else {
        Object destValue = PropertyUtils.getSimpleProperty(dest, name);
        if (destValue == null) {
          destValue = destPropClass.newInstance();
          patchProperties(destValue, value, propertyPrefix, null, ignoreSet);
          BeanUtils.copyProperty(dest, name, value);
        } else {
          patchProperties(destValue, value, propertyPrefix, null, ignoreSet);
        }
      }
    }
  }

  /**
   *
   */
  private static final Object jsonValueToObject(JsonNode jsonValue, Class<?> destClass) {
    if (destClass.equals(Boolean.class) || destClass.equals(boolean.class)) {
      return jsonValue.asBoolean();
    } else if (destClass.equals(Byte.class) || destClass.equals(byte.class)) {
      return (byte) jsonValue.asInt();
    } else if (destClass.equals(Character.class) || destClass.equals(char.class)) {
      String str = jsonValue.asText();
      if (str.length() < 1) {
        return null;
      } else {
        return str.charAt(0);
      }
    } else if (destClass.equals(Short.class) || destClass.equals(short.class)) {
      return jsonValue.asInt();
    } else if (destClass.equals(Integer.class) || destClass.equals(int.class)) {
      return jsonValue.asInt();
    } else if (destClass.equals(Long.class) || destClass.equals(long.class)) {
      return jsonValue.asLong();
    } else if (destClass.equals(Double.class) || destClass.equals(double.class)) {
      return jsonValue.asDouble();
    } else if (destClass.equals(Float.class) || destClass.equals(float.class)) {
      return jsonValue.asDouble();
    } else if (destClass.equals(String.class)) {
      return jsonValue.asText();
    } else if (jsonValue.isArray()) {
      // FIXME implement collections and
      return null;
    } else {
      return jsonValue;
    }
  }

  /**
   *
   */
  private static final boolean isSimpleProperty(Class<?> klass) {
    return ClassUtils.isPrimitiveOrWrapper(klass)
            || SIMPLE_PROPERTIES.contains(klass)
            || klass.isEnum()
            || klass.isArray()
            || Collection.class.isAssignableFrom(klass)
            || LocalDate.class.isAssignableFrom(klass)
            || LocalDateTime.class.isAssignableFrom(klass)
            || LocalTime.class.isAssignableFrom(klass)
            || OffsetDateTime.class.isAssignableFrom(klass)
            || OffsetTime.class.isAssignableFrom(klass)
            || BigDecimal.class.isAssignableFrom(klass)
            || BigInteger.class.isAssignableFrom(klass);
  }

  /**
   *
   */
  private static final String evalNameToCheck(String name, final StringBuilder propertyPrefix, int prefixLength) {

    propertyPrefix.setLength(prefixLength);
    if (prefixLength > 0) {
      propertyPrefix.append(".");
    }
    String nameToCheck = propertyPrefix.append(name).toString();
    return nameToCheck;
  }
}
