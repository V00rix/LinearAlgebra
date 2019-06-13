package PST.probability.basicTests

import PST.probability.{Delta, P}
import PST.probability.events._
import PST.probability.exceptions.ProbabilityException
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class MultipleProbabilities extends FlatSpec with Matchers with Delta {
  "Events and their probabilities" should "be compiled and correctly displayed" in {
    val a = SingleEvent(.3f)
    val b = SingleEvent(.4f)
    println(a)
    println(b)
    println(P(a))
    println(P(b))
  }

  "Special events and their probabilities" should "be correctly displayed" in {
    println(ImpossibleEvent)
    println(CertainEvent)
    println(P(CertainEvent))
    println(P(ImpossibleEvent))

    assert(P(CertainEvent) == 1d)
    assert(P(ImpossibleEvent) == 0d)
  }

  "Impossible and Certain Events" should "be correctly implemented" in {
    val A = SingleEvent(Random.nextFloat)
    println(P(A))
    P(ImpossibleEvent and CertainEvent) shouldEqual 0d
    P(ImpossibleEvent or CertainEvent) shouldEqual 1d
    P(ImpossibleEvent given CertainEvent) shouldEqual 0d
    an[ProbabilityException] should be thrownBy {
      P(CertainEvent given ImpossibleEvent)
    }
    P(A and CertainEvent) shouldEqual P(A)
    P(A and ImpossibleEvent) shouldBe 0d
    P(A or CertainEvent) should be(1d +- delta)
    P(A or ImpossibleEvent) should be(P(A) +- delta)
    P(CertainEvent given A) should be(P(A and CertainEvent) / P(A) +- delta)
  }

  "Independent complex events" should "be correctly implemented" in {
    val A = SingleEvent(Random.nextFloat)
    val B = SingleEvent(Random.nextFloat)
    val AAndBb = A and B

    println(AAndBb)
    println(P(AAndBb))

    assert(P(AAndBb) == P(A) * P(B)) // Should be independent
    assert(!AAndBb.isDependent) // Should be independent

    val AOrB = A or B

    println(AOrB)
    println(P(AOrB))

    assert(P(AOrB) == P(A) + P(B) - P(AAndBb))
    assert(P(AOrB) == P(A) + P(B) - P(A and B))

    assert(!AOrB.isDependent)
  }

}
