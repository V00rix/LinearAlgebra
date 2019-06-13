package PST.probability.examples

import PST.probability._
import org.scalatest.{FreeSpec, Matchers}


class ConditionalProbability extends FreeSpec with Delta with Matchers {
  /**
    * Cvičení 6.1. Náhodně vybereme kladné celé číslo N, rozdělení pravděpodobnosti je P[N = i] = 2^^(−i)
    * Poté hodíme N kostkami. Nechť S je součet hodnot, které při hodu padly.
    * Určete podmíněné pravděpodobnosti P[N = 2 | S = 4] a P[S = 4 | N je sudé]
    */
  "Ex 6.1" in {
    // P[N = 2 | S = 4] two throws, sum should be 4
    def PN(i: Int) = Math.pow(2, -i)
    val PN2 = PN(2)
    PN2 should be(1d / 4d +- delta)

    val S4_2 = ((p: (Int, Int)) => p._1 + p._2 == 4) within (1 to 6).flatMap(x => (1 to 6).map(y => (x, y)))
    S4_2 should be(1d / 12d +- delta)

    // S == 4: 4 or 3 + 1 or 2 + 2 or 2 + 1 + 1 or 1 + 1 + 1 + 1
    // ???????
  }

  /**
    * Cvičení 6.3. U 10% řidičů, kteří způsobili dopravní nehodu, bylo prokázáno požití alkoholu. Rozsáhlý
    * průzkum ukázal, že riziko nehody se požitím alkoholu zvyšuje 7×. Odhadněte, kolik procent řidičů požilo
    * alkohol.
    */
  "Ex 6.3" in {
//    val usedAlcohol =
    /**
      * When crashed used alcohol
      */
    val AgivenC = .1f // A and C -> P(C|A) = .1f
//    val
//    val noAlcoholStillCrashed = usedAlcoholAndCrashed / 7f // (not A) and C -> P(C|!A) = .1f/7f

  }
}
