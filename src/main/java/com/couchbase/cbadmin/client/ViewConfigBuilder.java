/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.cbadmin.client;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnunberg
 */
public class ViewConfigBuilder implements ViewConfig {
  private final JsonObject jObj = new JsonObject();
  private final String dName;
  private final String bucket;
  private final List<String> viewNames = new ArrayList<String>();
  private String password;

  public ViewConfigBuilder(String design, String bkt) {
    dName = design;
    bucket = bkt;

    jObj.addProperty("_id", "_design/" + design);
    jObj.addProperty("language", "javascript");
    jObj.add("views", new JsonObject());
  }

  public ViewConfigBuilder view(String name, String map, String reduce) {
    JsonObject def = new JsonObject();
    def.addProperty("map", map);
    if (reduce != null) {
      def.addProperty("reduce", reduce);
    }
    jObj.get("views").getAsJsonObject().add(name, def);
    viewNames.add(name);
    return this;
  }

  public ViewConfigBuilder view(String name, String map) {
    view(name, map, null);
    return this;
  }

  public ViewConfigBuilder password(String pass) {
    password = pass;
    return this;
  }

  public ViewConfig build() {
    return this;
  }

  @Override
  public String getBucketName() {
    return bucket;
  }

  @Override
  public String getDesign() {
    return dName;
  }

  @Override
  public String getDefinition() {
    return jObj.toString();
  }

  @Override
  public String getBucketPassword() {
    return password;
  }

  @Override
  public List<String> getViewNames() {
    return viewNames;
  }
}
