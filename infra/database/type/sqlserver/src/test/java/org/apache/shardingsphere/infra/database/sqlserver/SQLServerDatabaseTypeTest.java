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
import org.apache.shardingsphere.infra.database.enums.QuoteCharacter;
import org.apache.shardingsphere.infra.util.spi.type.typed.TypedSPILoader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SQLServerDatabaseTypeTest {
    
    @Test
    void assertGetQuoteCharacter() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getQuoteCharacter(), is(QuoteCharacter.BRACKETS));
    }
    
    @Test
    void assertGetJdbcUrlPrefixes() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getJdbcUrlPrefixes(), is(Arrays.asList("jdbc:microsoft:sqlserver:", "jdbc:sqlserver:")));
    }
    
    @Test
    void assertGetDataSourceMetaData() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getDataSourceMetaData(
                "jdbc:sqlserver://127.0.0.1;DatabaseName=ds_0", "root"), instanceOf(SQLServerDataSourceMetaData.class));
        assertThat(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getDataSourceMetaData(
                "jdbc:microsoft:sqlserver://127.0.0.1;DatabaseName=ds_0", "sa"), instanceOf(SQLServerDataSourceMetaData.class));
    }
    
    @Test
    void assertGetSystemDatabases() {
        assertTrue(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getSystemDatabaseSchemaMap().isEmpty());
    }
    
    @Test
    void assertGetSystemSchemas() {
        assertTrue(TypedSPILoader.getService(DatabaseType.class, "SQLServer").getSystemSchemas().isEmpty());
    }
}
