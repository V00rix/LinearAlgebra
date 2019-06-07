package PST.probability.basicTests

import PST.probability
import org.scalatest.FlatSpec

class probabilityTests extends FlatSpec {
  "arrayProbability" should "work" in {
    assert(probability.in() == 0f)
    assert(probability.in(false) == 0f)
    assert(probability.in(false, false) == 0f)
    assert(probability.in(true) == 1f)
    assert(probability.in(true, true) == 1f)
    assert(probability.in(true, false) == 1f / 2f)
    assert(probability.in(true, false, false) == 1f / 3f)
    assert(probability.in(true, true, false) == 2f / 3f)
  }
}
