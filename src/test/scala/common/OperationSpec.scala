package common

import common.sets.Set
import common.sets.relations.functions.operations.Operation
import org.scalatest.FlatSpec


class OperationSpec extends FlatSpec {

  "operations" should "be awesome" in {
    val mult = Operation[Int]((x, y) => x * y) _

    val S1 = new Set(1 to 100: _*)
    val S2 = new Set(1 to 100: _*)

    mult(S1 × S2)(S1).print()
  }
}
