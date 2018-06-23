package common

import org.scalatest.FlatSpec

class BaseSpec extends FlatSpec {

  "sets" should "be awesome" in {

    val set1 = new Set[Int](1, 3, 4)
    val set2 = new Set[Int](4, 3, 1)
    val set3 = new Set[Int](4, 3, 1, 3)
    val set4 = new Set[Int]()
    val set5 = new Set[Int]()

    assert(set1 == set2)
    assert(set1 != set3)
    assert(set2 != set3)
    assert(set4 == set5)
    assert(set3 != set5)
  }
}
