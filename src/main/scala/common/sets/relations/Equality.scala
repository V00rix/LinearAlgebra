package common.sets.relations

import common.sets.Set

class Equality[+A](private val set: Set[A]) extends Relation[A, A](set, set, new Set(set.elements.map(e => (e, e)): _*)) {

}

object Equality {
  def from[A, B](that: Relation[A, B]): Equality[A] = new Equality[A](Set.from(that.domain))
}