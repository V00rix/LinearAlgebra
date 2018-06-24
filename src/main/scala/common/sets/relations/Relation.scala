package common.sets.relations

import common.sets._
import common.util.exceptions.{BadCompositionException, BadRelationException, OutOfDomainException}
import types.CartesianProduct

/**
  * Binary relation R is a subset of a cartesian product:
  * R ⊆ A×B
  *
  * @param domain   First set of elements
  * @param codomain Second set of elements
  * @param related  Related elements
  * @tparam A Type of objects in first
  * @tparam B Type of objects in second
  */
class Relation[+A, +B](protected[relations] val domain: Set[A],
                       protected[relations] val codomain: Set[B],
                       private[this] val related: CartesianProduct[A, B]) extends CartesianProduct(related.elements) {
  // related should be a subset of first × second
  if (!(related ⊆ (domain × codomain))) throw new BadRelationException(related.toString + " ⊄ " + (domain × codomain).toString)

  def apply[C >: A, D >: B](a: C, b: D): Boolean = (a, b) ∈: related

  override def ^(power: Int): Relation[_, _] = {
    power match {
      case p if p == 0 => Equality.from(this)
      case p if p == 1 => this
      case p if p > 1 => this ∘ this
      case _ => throw OutOfDomainException()
    }
  }

  def ∘[C, D >: B](that: Relation[D, C]): Relation[A, C] = {
    if (that.domain != this.codomain)
      throw new BadCompositionException(this.codomain.toString + " != " + that.domain.toString)
    else
      new Relation[A, C]((a, c) => that.domain.elements.exists(b => this (a, b) && that(b, c)), domain, that.codomain)
  }

  def this(predicate: (A, B) => Boolean, first: Set[A], second: Set[B]) =
    this(first, second, new Set[(A, B)]((first × second).elements.filter(pair => predicate.apply(pair._1, pair._2))))

  def inverse(): Relation[B, A] = new Relation(codomain, domain, new CartesianProduct(this.elements.map(x => (x._2, x._1))))

  // TODO
  def matrix(): Matrix[Int] = {
    new Matrix()
  }
}

object Relation {
  def apply[A, B](predicate: (A, B) => Boolean)(first: Set[A])(second: Set[B]) =
    new Relation(first, second, new Set[(A, B)]((first × second).elements.filter(pair => predicate.apply(pair._1, pair._2))))
}