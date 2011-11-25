package org.wikbook.template.processing.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class Annotation implements Serializable {

  private String name;
  private Map<String, Object> values;
  private Map<String, String> javadoc;
  private List<Annotation> childs;

  public Annotation(String name) {
    this.name = "@" + name;
    this.values = new HashMap<String, Object>();
    this.javadoc = new HashMap<String, String>();
    this.childs = new ArrayList<Annotation>();
  }

  public String getName() {
    return name;
  }

  public void addValue(String name, Object value) {
    values.put(name, value);
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public void addJavadoc(String name, String value) {
    javadoc.put(name, value);
  }

  public Map<String, String> getJavadoc() {
    return javadoc;
  }

  public void addChild(Annotation annotation) {
    this.childs.add(annotation);
  }

  public List<Annotation> getChildren() {
    return childs;
  }

}
