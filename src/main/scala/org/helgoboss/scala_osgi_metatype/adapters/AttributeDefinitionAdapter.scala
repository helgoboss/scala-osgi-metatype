package org.helgoboss.scala_osgi_metatype.adapters

import org.osgi.service.metatype.{ AttributeDefinition => JAttributeDefinition }
import org.helgoboss.scala_osgi_metatype.interfaces.{Password, ElementaryAttributeDefinition, ValidationResult, AttributeDefinition}

/**
 * Provides the given Scala AttributeDefinition as an OSGi-compliant AttributeDefinition.
 */
abstract class AttributeDefinitionAdapter[T](delegate: AttributeDefinition[T]) extends JAttributeDefinition {
  def getDescription = delegate.description

  def getID = delegate.id

  def getName = delegate.name

  lazy val getOptionLabels = {
    delegate.options map { option =>
      option.keys map { _.toString } toArray
    } orNull
  }

  lazy val getOptionValues = delegate.options map { _.values.toArray } orNull

  lazy val getType = {
    import JAttributeDefinition._

    delegate.valueType match {
      case x if x == classOf[Boolean] => BOOLEAN
      case x if x == classOf[Byte] => BYTE
      case x if x == classOf[Char] => CHARACTER
      case x if x == classOf[Double] => DOUBLE
      case x if x == classOf[Float] => FLOAT
      case x if x == classOf[Int] => INTEGER
      case x if x == classOf[Long] => LONG
      case x if x == classOf[Short] => SHORT
      case x if x == classOf[String] => STRING
      case x if x == classOf[Password] => PASSWORD
    }
  }

  def validate(value: String) = {
    import ValidationResult._

    try {
      // Convert from String to generic type
      val convertedValue = delegate.valueType match {
        case x if x == classOf[Boolean] => value.toBoolean
        case x if x == classOf[Byte] => value.toByte
        case x if x == classOf[Char] => if (value.isEmpty) throw new NumberFormatException else value(0)
        case x if x == classOf[Double] => value.toDouble
        case x if x == classOf[Float] => value.toFloat
        case x if x == classOf[Int] => value.toInt
        case x if x == classOf[Long] => value.toLong
        case x if x == classOf[Short] => value.toShort
        case x if x == classOf[Password] => Password(value)
        case x if x == classOf[String] => value
      }

      // Validate value with generic type
      delegate.validate(convertedValue.asInstanceOf[T]) match {
        case Valid => ""
        case Invalid(reason) => reason
        case NotValidated => null
      }
    } catch {
      case x: NumberFormatException =>
        // Conversion failed. Not valid. Return reason.
        "Incorrect type"
    }
  }
}