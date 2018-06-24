package common.sets.relations.functions

import common.sets._
import common.sets.relations.Relation
import common.util.exceptions.BadFunctionException
import types.CartesianProduct

/**
  * A function f from a set X to a set Y is defined by a set G of ordered pairs (x, y), such that x ∈ X, y ∈ Y,
  * and every element of X is the first component of exactly one ordered pair in G.
  *
  * @param domain   First set of elements
  * @param codomain Second set of elements
  * @param related  Related elements
  * @tparam A Type of objects in first
  * @tparam B Type of objects in second
  */
class Function[A, B](override val domain: Set[A],
                     override val codomain: Set[B],
                     private[this] val related: CartesianProduct[A, B])
  extends Relation(domain, codomain, related) {
  /**
    * A function f is a triplet f=(F,A,B), where A,B are sets (A is called the domain of f, B the codomain)
    * and F is a relation F ⊆ A × B with the additional properties:
    *
    * (x,y) ∈ F ∧ (x,z) ∈ F
    * ⇒ y = z
    *
    * ∀x ∈ A ∃y ∈ B such that (x,y) ∈ F
    */
  related.foreach(x1 => related.foreach(x2 => if (x1._1 == x2._1)
    if (x1._2 != x2._2) throw new BadFunctionException(x1._1 + " is in relation to both " + x1._2 + " and " + x2._2)
  ))

  domain.elements.forall(x => related.elements.exists(p => p._1 == x))

  def this(predicate: (A, B) => Boolean, first: Set[A], second: Set[B]) =
    this(first, second, new CartesianProduct((first × second).elements.filter(pair => predicate.apply(pair._1, pair._2))))

  def this(definition: A => B, first: Set[A], second: Set[B]) =
    this((a: A, b: B) => b == definition.apply(a), first, second)
}

object Function {
  def apply[A, B](definition: A => B)(domain: Set[A])(codomain: Set[B]) =
    new Function[A, B](definition, domain, codomain)
}
