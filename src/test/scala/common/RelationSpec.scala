package common

import common.sets.Set
import common.sets.Set._
import common.sets.relations.{Equality, Relation}
import common.util.exceptions.{BadCompositionException, BadRelationException}
import org.scalatest.FlatSpec

class RelationSpec extends FlatSpec {

  "relations" should "be awesome" in {
    val setA = new Set(1, 3)
    val setB = new Set(3, 5)

    val relation1 = new Relation(setA, setB, new Set((1, 3), (1, 5)))

    assert(relation1 ⊆ (setA × setB))
    assert((∅ ⊆ relation1) && (relation1 ⊆ (setA × setB)))

    assertThrows[BadRelationException] {
      new Relation(new Set(1, 3), new Set(5, 6, 7), new Set((31, 3)))
    }

    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


    val predicateRelation = new Relation[Int, Int]((a, b) => a >= b, new Set[Int](1, 3, 4), new Set[Int](3, 4, 5))
    predicateRelation.print()

  }

  "composition" should "work" in {
    val S1 = new Set((1 to 10): _*)
    val S2 = new Set((5 to 8): _*)
    val R1 = new Relation((a: Int, b: Int) => (a % b == 0 && a != b && b != 1), S1, S1)
    R1.print()
    val R2 = new Relation((a: Int, b: Int) => b == a * 2, S1, S1)
    R2.print()
    (R1 ∘ R2).print()
    println((S1 × S1).cardinality)
    println(R1.cardinality)
    println(R2.cardinality)
    println((R1 ∘ R2).cardinality)
    println((R2 ∘ R1).cardinality)
    val R3 = R1 ^ 2
    println(R3)
  }

  "inversion" should "work" in {
    val relationCreator = Relation((a: Int, b: Int) => a * 2 == b) _
    val mySet = new Set((1 to 100 by 1): _*)
    val mySet2 = new Set((1 to 30 by 1): _*)
    val withBothSets = relationCreator(mySet2)(mySet)

    assertThrows[BadCompositionException] {
      (withBothSets ^ 2).print()
    }

    assert((withBothSets ∘ withBothSets.inverse()) == Equality.from(withBothSets))

    val S = new Set(1 to 10: _*)
    val equality = new Equality[Int](S)
    assert(equality ⊆ (S × S))

    assert((equality ^ 4) == equality)
    assert((withBothSets ^ 0) == Equality.from(withBothSets))
  }
}
