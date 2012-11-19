/**
 * Copyright (c) 2002-2012 "Neo Technology,"
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
package org.neo4j.cypher.internal.commands.expressions

import org.neo4j.graphdb.Path
import org.neo4j.cypher.internal.symbols._
import collection.Map
import org.neo4j.cypher.internal.helpers.CollectionSupport
import org.neo4j.cypher.internal.pipes.ExecutionContext

case class LengthFunction(inner: Expression)
  extends NullInNullOutExpression(inner)
  with CollectionSupport
with ExpressionWInnerExpression {
  def compute(value: Any, m: ExecutionContext) = value match {
    case path: Path => path.length()
    case s: String  => s.length()
    case x          => makeTraversable(x).toSeq.length
  }

  def rewrite(f: (Expression) => Expression) = f(LengthFunction(inner.rewrite(f)))

  def filter(f: (Expression) => Boolean) = if (f(this))
    Seq(this) ++ inner.filter(f)
  else
    inner.filter(f)

  val myType = LongType()
  val expectedInnerType = AnyCollectionType()

  def symbolTableDependencies = inner.symbolTableDependencies
}