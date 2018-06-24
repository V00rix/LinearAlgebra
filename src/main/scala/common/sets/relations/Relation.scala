package common.sets.relations

import common.Matrix
import common.exceptions.{BadCompositionException, BadRelationException}
import common.sets._
import types.CartesianProduct

/**
  * Binary relation R is a subset of a cartesian product:
  * R ⊆ A×B
  *
  * @param first   First set of elements
  * @param second  Second set of elements
  * @param related Related elements
  * @tparam A Type of objects in first
  * @tparam B Type of objects in second
  */
class Relation[A, B](private val first: Set[A])(private val second: Set[B])(private val related: CartesianProduct[A, B]) extends Set[(A, B)](related.elements) {
  // related should be a subset of first × second
  if (!(related ⊆ (first × second))) throw new BadRelationException(related.toString + " ⊄ " + (first × second).toString)

  def apply(a: A, b: B): Boolean = (a, b) ∈: related

  def ∘[C](that: Relation[B, C]): Relation[A, C] = {
    // that.first should be same as this.second
    if (that.first != this.second) throw new BadCompositionException(this.second.toString + " != " + that.first.toString)
    // all b so that aRb and bSc
    new Relation[A, C]((a, c) => that.first.elements.exists(b => this (a, b) && that(b, c)))(first)(that.second)
  }

  def this(predicate: (A, B) => Boolean)(first: Set[A])(second: Set[B]) =
    this(first)(second)(new Set[(A, B)]((first × second).elements.filter(pair => predicate.apply(pair._1, pair._2))))

  // TODO
  def matrix(): Matrix[Int] = {
    new Matrix()
  }
}