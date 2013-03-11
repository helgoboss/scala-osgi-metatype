package org.helgoboss.scala_osgi_metatype

import adapters.MetaTypeProviderAdapter
import builders.{ObjectClass, ListAttribute, ElementaryAttribute}
import interfaces._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers
import scala.Some

/**
 * Tests the builders.
 */
@RunWith(classOf[JUnitRunner])
class BuilderSpec extends WordSpec with ShouldMatchers {

  "ObjectClass builder" should {
    "construct a corresponding ObjectClassDefinition" in {
      // TODO Do some assertions on the constructed object class definition
    }

    "construct an ObjectClassDefinition which can output a default configuration" in {
      val expectedMap = Map(
        "allowedFruits" -> List("apples"),
        "password" -> Password("secret01")
      )
      assert(objectClassDefinition.defaultConfig === expectedMap)
    }
  }

  /**
   * Creates the test object class definition.
   */
  lazy val objectClassDefinition = ObjectClass(
    id = "efed.de",
    requiredAttributes = List(
      ListAttribute[String](
        id = "allowedFruits",
        default = Some(List("apples")),
        options = Some(Map("apples" -> "Apples", "bananas" -> "Bananas"))
      ),
      ElementaryAttribute[Int](id = "size"),
      ElementaryAttribute[Password](id = "password", default = Some(Password("secret01")))
    )
  )
}