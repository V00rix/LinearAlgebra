package common.operations

import common.sets.Set

class CartesianProduct[A, B](private val setA: Set[A], private val setB: Set[B]) {
  val set: Array[(A, B)] = {
    var s = new Array[(A, B)](setA.elements.length * setB.elements.length)

    for {i <- 0 to setA.elements.length
         j <- 0 to setB.elements.length} new
  }
}