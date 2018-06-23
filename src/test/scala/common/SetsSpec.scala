package common

import common.Set.∅
import common.exceptions.DuplicateElementsException
import org.scalatest.FlatSpec
import types.CartesianProduct

class SetsSpec extends FlatSpec {
  "sets" should "be awesome" in {
    val set1 = new Set(1, 3, 4)
    val set2 = new Set(4, 3, 1)
    val set3 = new Set(4, 3, 1, 2)
    val set4 = new Set()
    val set5 = ∅

    assert(set1 == set2)
    assert(set1 != set3)
    assert(set2 != set3)
    assert(set4 == set5)
    assert(set3 != set5)

    assert(set1 ⊆ set3)

    assert(set4 == ∅)
    assert(∅ == ∅)

    assert((set1 ⋂ set2) == set1)
    assert((set1 \ set2) == ∅)
    assert((set3 \ set2) == new Set(2))
    assert((set3 \ set2) != 2)
    assert(2 ∈: (set3 \ set2))

    assertThrows[DuplicateElementsException] {
      new Set(1, 1)
    }

    assert((new Set(3, 4, 5) ⋃ new Set(3, 6, 7)) == new Set(3, 4, 5, 6, 7))

    val setOfEmpty = new Set(∅, new Set(1,3,4))
    assert(∅ ∈: setOfEmpty)
    assert(new Set(1,3,4) ∈: setOfEmpty)

    val setOfDifferent = new Set(new Set(1,3,4), new Set(1.4,1.3), new Set(∅, new Set(1,3)))
    setOfDifferent.pr()

    val A = set2
    val B = set3
    assert((A ⊆ B) && (A \ B == ∅))

    val C = new Set(1,3,5)
    val D = new Set(6,5,7)

    assert((C ∆ D) == ((C \ D) ⋃ (D \ C)))

    assert((C ∆ D) == new Set(1,3,6,7))

    assert((C ∆ ∅) == C)
    assert((C ∆ C) == ∅)
  }

  "cartesian" should "be awesome" in {
    val cartesian = new Set(1, 2) × new Set(1, 2, 3)
    cartesian.pr()
    assert(cartesian == new CartesianProduct((1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3)))
    assert((new Set(1, 2) × new Set(1, 2, 3)) != (new Set(1, 2, 3) × new Set(1, 2)))

    val A = new Set(1, 2)
    val B1 = ∅
    assert((A × ∅) == (new Set(1, 2) × ∅))
    assert((A × B1) == ∅)
    assert((B1 × A) == ∅)

    val B = new Set(3, 4, 5)
    val C = new Set(7, 1)
    val D = new Set(1, 3, 46, 8, 9)

    assert(((A ⋂ B) × (C ⋂ D)) == ((A × C) ⋂ (B × D)))
    assert(((A ⋃ B) × (C ⋃ D)) != ((A × C) ⋃ (B × D)))

    assert((A × B).size == A.size * B.size)
    assert((A × B × C).size == A.size * B.size * C.size)

    (A × A × B).pr()

    (A × ∅).pr()
    (∅ × A).pr()

    assert((A ^ 2) == (A × A))
    (A ^ 2).pr()
  }
}
