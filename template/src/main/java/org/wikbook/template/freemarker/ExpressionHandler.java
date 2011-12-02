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

package org.wikbook.template.freemarker;

import freemarker.ext.beans.CollectionModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class ExpressionHandler {

  public static enum Output {

    FLAT () {
      public String prefix() {
        return "flat:";
      }
    },

    LIST () {
      public String prefix() {
        return "list:";
      }
    },

    NONE () {
      public String prefix() {
        return "";
      }
    },

    NOEXPR () {
      public String prefix() {
        return "";
      }
    };

    public abstract String prefix();

  }

  private String expression;
  private String value;
  private Output output;

  public ExpressionHandler() {
    output = Output.NOEXPR;
  }

  public ExpressionHandler(final String expression) {
    this.expression = expression;

    if (expression.startsWith(Output.FLAT.prefix())) {
      output = Output.FLAT;
      value = expression.substring(Output.FLAT.prefix().length());
    }
    else if (expression.startsWith(Output.LIST.prefix())) {
      output = Output.LIST;
      value = expression.substring(Output.LIST.prefix().length());
    }
    else {
      output = Output.NONE;
      value = expression;
    }
  }

  public String getValue() {
    return value;
  }

  public Object get(List<? extends Object> values) {

    if (values != null) {

      //
      switch (output) {

        case FLAT:
          return new SimpleScalar(asString(values));

        case LIST:
          return new CollectionModel(values, new DefaultObjectWrapper());

        case NONE:
          return new CollectionModel(values, new DefaultObjectWrapper());

        case NOEXPR:
          return new SimpleScalar(asString(values));

      }

    }

    //
    if (output.equals(ExpressionHandler.Output.LIST)) {
      return new CollectionModel(Arrays.asList(), new DefaultObjectWrapper());
    }

    //
    return "";

  }

  private String asString(List<? extends Object> l) {

    StringBuilder sb = new StringBuilder();
    for (Object v : l) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(v.toString());
    }

    return sb.toString();

  }
  
}
