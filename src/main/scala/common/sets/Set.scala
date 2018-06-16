package common.sets

import common.operations.CartesianProduct

abstract class Set[A] {
  var elements: Array[A] = Array()

  def *[B](that: Set[B]): CartesianProduct[A, B] = new CartesianProduct[A, B](this, that)
}