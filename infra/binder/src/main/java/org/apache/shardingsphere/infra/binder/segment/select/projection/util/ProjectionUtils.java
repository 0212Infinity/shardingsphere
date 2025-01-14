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

package org.apache.shardingsphere.infra.binder.segment.select.projection.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.database.opengauss.OpenGaussDatabaseType;
import org.apache.shardingsphere.infra.database.oracle.OracleDatabaseType;
import org.apache.shardingsphere.infra.database.postgresql.PostgreSQLDatabaseType;
import org.apache.shardingsphere.infra.database.spi.DatabaseType;
import org.apache.shardingsphere.infra.database.enums.QuoteCharacter;
import org.apache.shardingsphere.sql.parser.sql.common.value.identifier.IdentifierValue;

/**
 * Projection utility class.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProjectionUtils {
    
    /**
     * Get column label from alias.
     * 
     * @param alias alias
     * @param databaseType database type
     * @return column label
     */
    public static String getColumnLabelFromAlias(final IdentifierValue alias, final DatabaseType databaseType) {
        if (QuoteCharacter.NONE != alias.getQuoteCharacter()) {
            return alias.getValue();
        }
        if (databaseType instanceof PostgreSQLDatabaseType || databaseType instanceof OpenGaussDatabaseType) {
            return alias.getValue().toLowerCase();
        }
        if (databaseType instanceof OracleDatabaseType) {
            return alias.getValue().toUpperCase();
        }
        return alias.getValue();
    }
    
    /**
     * Get column name from function.
     * 
     * @param functionName function name
     * @param functionExpression function expression
     * @param databaseType database type
     * @return column name
     */
    public static String getColumnNameFromFunction(final String functionName, final String functionExpression, final DatabaseType databaseType) {
        if (databaseType instanceof PostgreSQLDatabaseType || databaseType instanceof OpenGaussDatabaseType) {
            return functionName.toLowerCase();
        }
        if (databaseType instanceof OracleDatabaseType) {
            return functionExpression.replace(" ", "").toUpperCase();
        }
        return functionExpression;
    }
}
