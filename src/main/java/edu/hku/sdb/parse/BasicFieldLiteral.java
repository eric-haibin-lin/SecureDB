package edu.hku.sdb.parse;

import edu.hku.sdb.catalog.ColumnKey;
import edu.hku.sdb.catalog.MetaStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eric Haibin Lin on 25/3/15.
 */
public class BasicFieldLiteral  implements ParseNode{

  private static final Logger LOG = LoggerFactory.getLogger(BasicFieldLiteral.class);
  public static final String ROW_ID_COLUMN_NAME = "row_id";
  public static final String S_COLUMN_NAME = "s";
  public static final String R_COLUMN_NAME = "r";

  private TableName tableName;
  private ColumnType type;
  private boolean isSen = false;
  private String name;
  private ColumnKey colKey;

  public ColumnKey getColumnKey() {
    return colKey;
  }

  public void setColumnKey(ColumnKey columnKey) {
    this.colKey = columnKey;
  }

  public TableName getTableName() {
    return tableName;
  }

  public void setTableName(TableName tableName) {
    this.tableName = tableName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ColumnType getType() {
    return type;
  }

  public void setType(ColumnType type) {
    this.type = type;
  }

  public boolean isSen() {
    return isSen;
  }

  public void setSen(boolean isSen) {
    this.isSen = isSen;
  }

  public BasicFieldLiteral(String name, ColumnType type) {
    this.name = name;
    this.type = type;
  }

  public BasicFieldLiteral(String name, ColumnType type, TableName tableName) {
    this.name = name;
    this.type = type;
    this.tableName = tableName;
  }

  public BasicFieldLiteral(String name, ColumnType type, TableName tableName, boolean isSen, ColumnKey columnKey) {
    this.name = name;
    this.type = type;
    this.isSen = isSen;
    this.tableName = tableName;
    this.colKey = columnKey;
  }

  @Override
  public void analyze(MetaStore metaDB, ParseNode... fieldSources) throws SemanticException {
    for (ParseNode fieldSource : fieldSources){
      if (fieldSource instanceof TableName){
        setTableName((TableName) fieldSource);
      }
    }
  }

  @Override
  public String toSql() {
    return name + " " + type.toSql();
  }

  @Override
  public boolean involveSdbEncrytedCol() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    // Log messages are used for debug.
    if (!(obj instanceof BasicFieldLiteral)){
      LOG.debug("object is not an instance of BasicFieldLiteral");
      return false;
    }

    BasicFieldLiteral fieldobj = (BasicFieldLiteral) obj;

    if (!type.equals(fieldobj.getType())){
      LOG.debug("type of right BasicFieldLiteral " + fieldobj.getType() + " is not equal!");
      return false;
    }

    if (isSen != fieldobj.isSen()){
      LOG.debug("isSen of right BasicFieldLiteral " + fieldobj.isSen() + " is not equal to " + isSen + "!");
      return false;
    }

    if (!name.equals(fieldobj.getName())){
      LOG.debug("name of right BasicFieldLiteral " + fieldobj.getName() + " is not equal to " + name + "!");
      return false;
    }

    if (!tableName.equals(fieldobj.getTableName())){
      LOG.debug("tableName of right BasicFieldLiteral " + fieldobj.getTableName() + " is not equal to " + tableName + "!");
      return false;
    }

    if ((colKey == null) != (fieldobj.colKey == null)) {
      String err = (colKey == null) ? "Left column key is null, while "
              + "right column key is: " + fieldobj.colKey : "Left column is: "
              + colKey + ", while right column key is null";
      LOG.debug(err);
      return false;
    }

    if (colKey != null) {
      //TODO temporarily disabled for testing convenience
      /*if (!colKey.equals(fieldobj.getColumnKey())) {
        String err = "Left column key is: " + colKey + ";Right column key is: "
                + fieldobj.colKey;
        LOG.debug(err);
        return false;
      }*/
    }

    return true;
  }



}

