/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.base.flyjiang.baseflyapp.http.db;

/**
 * Created by flyjiang on 2019/8/2.
 */
public class ColumnEntity {

    public String columnName;               //列的名字
    public String columnType;               //列的类型
    public String[] compositePrimaryKey;    //复合主键
    public boolean isPrimary;               //是否是主键
    public boolean isNotNull;               //是否不能为空
    public boolean isAutoincrement;         //AUTOINCREMENT 是否自增

    /**
     * @param compositePrimaryKey 复合主键
     */
    public ColumnEntity(String... compositePrimaryKey) {
        this.compositePrimaryKey = compositePrimaryKey;
    }

    /**
     * @param columnName 列名
     * @param columnType 列的数据类型
     */
    public ColumnEntity(String columnName, String columnType) {
        this(columnName, columnType, false, false, false);
    }

    /**
     * @param columnName 列名
     * @param columnType 列的数据类型
     * @param isPrimary  是否为主键
     * @param isNotNull  是否不能为空
     */
    public ColumnEntity(String columnName, String columnType, boolean isPrimary, boolean isNotNull) {
        this(columnName, columnType, isPrimary, isNotNull, false);
    }

    /**
     * @param columnName      列名
     * @param columnType      列的数据类型
     * @param isPrimary       是否为主键
     * @param isNotNull       是否不能为空
     * @param isAutoincrement 是否自增
     */
    public ColumnEntity(String columnName, String columnType, boolean isPrimary, boolean isNotNull, boolean isAutoincrement) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimary = isPrimary;
        this.isNotNull = isNotNull;
        this.isAutoincrement = isAutoincrement;
    }
}
