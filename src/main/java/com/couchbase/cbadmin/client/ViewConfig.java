/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.cbadmin.client;

import java.util.Collection;

/**
 *
 * @author mnunberg
 */
public interface ViewConfig {
  public String getDesign();
  public String getDefinition();
  public String getBucketName();
  public String getBucketPassword();
  public Collection<String> getViewNames();
}
