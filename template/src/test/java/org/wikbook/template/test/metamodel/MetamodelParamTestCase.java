/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.wikbook.template.test.metamodel;

import org.wikbook.template.processing.metamodel.MetaModel;
import org.wikbook.template.test.AbstractProcessorTestCase;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class MetamodelParamTestCase extends AbstractProcessorTestCase {

  @Override
  public void setUp() throws Exception {

    super.setUp();
    annotations = "javax.ws.rs.Path,javax.ws.rs.POST,javax.ws.rs.PathParam,javax.ws.rs.QueryParam";

  }

  public void testExists() throws Exception {

    MetaModel metaModel = buildClass("E");
    assertEquals("@PathParam", metaModel.getElements().get(0).getElement().get(0).getElement().get(0).getAnnotation("@PathParam").getName());
    assertEquals("@QueryParam", metaModel.getElements().get(0).getElement().get(0).getElement().get(1).getAnnotation("@QueryParam").getName());

  }

  public void testElementName() throws Exception {

    MetaModel metaModel = buildClass("E");

    assertEquals("pathParameter", metaModel.getElements().get(0).getElement().get(0).getElement().get(0).getAnnotation("@PathParam").getElement().getName());
    assertEquals("queryParameter", metaModel.getElements().get(0).getElement().get(0).getElement().get(1).getAnnotation("@QueryParam").getElement().getName());

  }

  public void testJavadoc() throws Exception {

    MetaModel metaModel = buildClass("E");

    assertEquals("[Path parameter description]", metaModel.getElements().get(0).getElement().get(0).getElement().get(0).getAnnotation("@PathParam").getJavadoc(null).toString());
    assertEquals("[Query parameter description]", metaModel.getElements().get(0).getElement().get(0).getElement().get(1).getAnnotation("@QueryParam").getJavadoc(null).toString());

  }

}
