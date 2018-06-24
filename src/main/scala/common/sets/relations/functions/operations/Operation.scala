package common.sets.relations.functions.operations

import common.sets._
import common.sets.relations.functions.Function
import types.CartesianProduct

/**
  * Binary operation on a set S is a map which sends elements of the Cartesian product S × S to S
  *
  * @param domain  Set of elements
  * @param related Related elements
  * @tparam A Type of objects in first
  */
class Operation[A](override val domain: CartesianProduct[A, A],
                   override val codomain: Set[A],
                   private[this] val related: CartesianProduct[(A, A), A])
  extends Function(domain, codomain, related) {

  def this(predicate: ((A, A), A) => Boolean, domain: CartesianProduct[A, A], codomain: Set[A]) =
    this(domain, codomain, new CartesianProduct((domain × codomain).elements.filter(pair => predicate.apply(pair._1, pair._2))))

  def this(definition: ((A, A)) => A, domain: CartesianProduct[A, A], codomain: Set[A]) =
    this((a: (A, A), b: A) => b == definition.apply(a._1, a._2), domain, codomain)
}

object Operation {
  def apply[A](definition: (A, A) => A)(domain: CartesianProduct[A, A])(codomain: Set[A]) =
    new Operation[A]((p: (A, A)) => definition.apply(p._1, p._2), domain, codomain)
}

