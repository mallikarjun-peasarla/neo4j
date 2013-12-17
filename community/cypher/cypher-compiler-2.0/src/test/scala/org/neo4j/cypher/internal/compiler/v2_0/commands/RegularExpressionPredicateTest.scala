/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compiler.v2_0.commands

import org.junit.Test
import org.neo4j.cypher.internal.compiler.v2_0.commands.expressions.{Null, Literal}
import org.neo4j.cypher.internal.compiler.v2_0.pipes.QueryStateHelper
import org.scalatest.Assertions

class RegularExpressionPredicateTest extends Assertions {
  @Test def shouldNotMatchIfTheExpressionEvaluatesToNull() {
    val expression = new LiteralRegularExpression(Null(), Literal(".*"))
    assert(None === expression.isMatch(null)(QueryStateHelper.empty))
  }

  @Test def shouldNotMatchIfTheExpressionEvaluatesToNull2() {
    val expression = new RegularExpression(Null(), Literal(".*"))
    assert(None === expression.isMatch(null)(QueryStateHelper.empty))
  }
}