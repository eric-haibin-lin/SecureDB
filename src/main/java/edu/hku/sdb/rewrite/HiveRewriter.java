/*******************************************************************************
 *    Licensed to the Apache Software Foundation (ASF) under one or more 
 *    contributor license agreements.  See the NOTICE file distributed with 
 *    this work for additional information regarding copyright ownership.
 *    The ASF licenses this file to You under the Apache License, Version 2.0
 *    (the "License"); you may not use this file except in compliance with 
 *    the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *******************************************************************************/

package edu.hku.sdb.rewrite;

import edu.hku.sdb.catalog.DBMeta;
import edu.hku.sdb.parse.ParseNode;

public class HiveRewriter extends AbstractRewriter {

  /**
   * @param dbMeta
   */
  public HiveRewriter(DBMeta dbMeta) {
    super(dbMeta);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.hku.sdb.rewrite.AbstractRewriter#rewrite(edu.hku.sdb.parse.QueryStmt)
   */
  @Override
  public ParseNode rewrite(ParseNode query) {
    return query;
  }

}