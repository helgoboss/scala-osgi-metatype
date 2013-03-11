package org.helgoboss.scala_osgi_metatype.adapters

import org.osgi.service.metatype.{ MetaTypeProvider => JMetaTypeProvider }
import org.helgoboss.scala_osgi_metatype.interfaces.MetaTypeProvider

/**
 * Provides the given Scala MetaTypeProvider as an OSGi-compliant MetaTypeProvider.
 */
class MetaTypeProviderAdapter(delegate: MetaTypeProvider) extends JMetaTypeProvider {
  def getLocales = delegate.locales.toArray

  def getObjectClassDefinition(id: String, locale: String) = {
    val d = delegate.getObjectClassDefinition(id, Option(locale))
    new ObjectClassDefinitionAdapter(d)
  }
}
