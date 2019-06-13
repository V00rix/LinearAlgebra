package PST.probability.examples

import PST.probability.events._
import PST.probability.{Delta, P, toEvent, not => notP}
import org.scalatest.{FreeSpec, Matchers}

class BasicProperties extends FreeSpec with Delta with Matchers {
  // Random number from 1 to 100 is dividable by 2
  // What is the probability that it is also dividable by 3 or 5
  "Ex. 3.1" - {
    val totalNumbersCount = 100
    val dividableByTwoCount = (1 to totalNumbersCount).count(_ % 2 == 0)
    val dividableByThreeCount = (1 to totalNumbersCount).count(_ % 3 == 0)
    val dividableByFiveCount = (1 to totalNumbersCount).count(_ % 5 == 0)

    val divByTwo = (dividableByTwoCount.toFloat / totalNumbersCount.toFloat)
    val divByThree = (dividableByThreeCount.toFloat / totalNumbersCount.toFloat)
    val divByFive = (dividableByFiveCount.toFloat / totalNumbersCount.toFloat)


    println(P((divByThree or divByFive) given divByTwo))
  }


  /**
    * Cvičení 3.2. Signál prochází zařízením zleva (L) doprava (P). Jednotlivé bloky mají poruchu s vyznačenou pravděpodobností a výskyty
    * poruch jsou na sobě nezávislé. Určete pravděpodobnost toho, že vyslaná
    * zpráva bude zařízením přenesena.
    */
  // .2 ------\
  // .4 -> .4 -> .1
  // .3 ------/
  "Ex. 3.2" in {
    val AFails = .2
    val BFails =.4 or .4
    val CFails = .3

    val DFails = .1

    // All are independent, thus
    val AllFail = (AFails and BFails and CFails) or DFails

    val succeed = notP(AllFail)

    succeed should be(0.865433d +- delta)
  }

  /**
    * Cvičení 3.3. Náhodné jevy A a B jsou nezávislé a P(A ∪ B) = 0.545, P(A ∩ B) = 0.105. Určete
    * pravděpodobnosti P(A), P(B) a P(A ∩ !B).
    */
  "Ex 3.3" in {
    val AOrB = .545f
    val AAndB = .105f

    val determined = determineFromIndependent(AAndB, AOrB)
    val A = determined._1
    val B = determined._2
    val AandNotB = P(A and notP(B))

    if (Math.abs(A - .35) <= delta) {
      P(B) should be(.3d +- delta)
      P(AandNotB)  should be (.245d +- delta)
    } else if (Math.abs(B - .35) <= delta) {
      P(A) should be(.3d +- delta)
      P(AandNotB)  should be (.195d +- delta)
    } else fail("Neither A not B equaled .35")
  }
}
