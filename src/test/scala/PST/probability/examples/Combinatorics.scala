package PST.probability.examples

import PST.combinatorics._
import PST.probability.{Delta, TestingComparable}
import org.scalatest.FreeSpec

import scala.util.Random

class Combinatorics extends FreeSpec with TestingComparable with Delta {
  private val tries = 1e4.toInt

  "Ex. 2.4 (hypergeometric distribution)" - {
    // Within M products are K defective.
    // What probability is that within m random products are exactly k defective?
    val M = 10000
    val K = 3000
    val m = 1000
    val k = 300

    "(solution)" - {
      val totalPossible = choose(m) from M
      val totalDefective = choose(k) from K
      val totalGood = choose(m - k) from M - K
      val totalSelections = totalDefective * totalGood
      val probability = BigDecimal(totalSelections) / BigDecimal(totalPossible)
      expected = probability.toFloat
      logExpected()
    }

    "(testing)" - {
      var matchedK: Int = 0
      var _tries = 0
      for (_ <- 0 to tries) {
        val totalProducts = Random.shuffle(Seq.fill(K) {false} ++ Seq.fill(M - K) {true})
        val chosenProducts = totalProducts.slice(0, m)
        val defectiveCount = chosenProducts.count(el => !el)
        if (defectiveCount == k) matchedK += 1
        _tries += 1
      }

      actual = matchedK.toFloat / tries.toFloat
      logActual()
    }

    "expected should roughly equal actual" in {
      test()(1e-2f)
    }
  }

  "Ex 2.8" - {
    // Sequence of length 20 contains 17 '0' and 3 '1'.
    // What is the probability of no two '1' being near each other
    // What is the probability of no two '1' being near each other given one '1' is on 5th position in the sequence
    val totalChars = 20
    val onesCount = 3
    val zeroesCount = totalChars - onesCount

    "simple" - {

      "(solution)" - {
        // We can insert '1' in 17 + 1 places, so they are not near each other

        val placementOnes = choose(onesCount) from (zeroesCount + 1)
        val totalPlacements = choose(onesCount) from totalChars

        expected = placementOnes.toFloat / totalPlacements.toFloat
        logExpected()
      }

      "(testing)" - {
        var successful = 0
        for (_ <- 0 to tries) {
          val sequence = Random.shuffle(Seq.fill(onesCount) {'1'} ++ Seq.fill(zeroesCount) {'0'})
          if (!sequence.containsSlice("11")) successful += 1
        }

        actual = successful.toFloat / tries.toFloat
        logActual()
      }

      "expected should roughly equal actual" in {
        test()(1e-2f)
      }
    }

    "conditional" - {

    }
  }

}
