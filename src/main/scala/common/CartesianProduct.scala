//package common
//
//class CartesianProduct[A, B](private val setA: Set[A], private val setB: Set[B]) extends Set[(A, B)] {
//  override val elements: Array[(A, B)] = {
//    val s = new Array[(A, B)](setA.length * setB.length)
//
//    for {i <- 0 to setA.length
//         j <- 0 to setB.length} s.update(i * setA.length + j, Tuple2(setA(i), setB(j)))
//
//    s
//  }
//}