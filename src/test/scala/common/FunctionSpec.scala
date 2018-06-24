package common

import common.sets.Set
import common.sets.relations.functions._
import org.scalatest.FlatSpec


class FunctionSpec extends FlatSpec {

  "functions" should "be awesome" in {
    val f1 = Function((x: Int) => x * 2) _

    val S1 = new Set[Int](1 to 100: _*)
    val S2 = new Set[Int](1 to 100: _*)

    f1(S1)(S2).print()

  }
}
