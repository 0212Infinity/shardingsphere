/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.database.sqlserver;

import org.apache.shardingsphere.infra.database.spi.DatabaseType;
import org.apache.shardingsphere.infra.database.enums.NullsOrderType;
import org.apache.shardingsphere.infra.database.enums.QuoteCharacter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Database type of SQLServer.
 */
public final class SQLServerDatabaseType implements DatabaseType {
    
    @Override
    public QuoteCharacter getQuoteCharacter() {
        return QuoteCharacter.BRACKETS;
    }
    
    @Override
    public NullsOrderType getDefaultNullsOrderType() {
        return NullsOrderType.FIRST;
    }
    
    @Override
    public Collection<String> getJdbcUrlPrefixes() {
        return Arrays.asList("jdbc:microsoft:sqlserver:", "jdbc:sqlserver:");
    }
    
    @Override
    public SQLServerDataSourceMetaData getDataSourceMetaData(final String url, final String username) {
        return new SQLServerDataSourceMetaData(url);
    }
    
    @Override
    public Map<String, Collection<String>> getSystemDatabaseSchemaMap() {
        return Collections.emptyMap();
    }
    
    @Override
    public Collection<String> getSystemSchemas() {
        return Collections.emptyList();
    }
    
    @Override
    public String getType() {
        return "SQLServer";
    }
}
