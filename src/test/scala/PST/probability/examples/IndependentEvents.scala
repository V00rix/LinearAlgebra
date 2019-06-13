package PST.probability.examples

import PST.probability._
import PST.probability.events.ComplexAndEvent
import org.scalatest.{FreeSpec, Matchers}

class IndependentEvents extends FreeSpec with Delta with Matchers {
  "Ex 5.1" in {
    val A = .5d
    val B = .5d
    val AorBorC = 1d
    val AorC = 3d / 4d

    // A or B ?
    val AorB: Double = A or B
    println(AorB)
    AorB should be(3d / 4d +- delta)

    // C ?
    // A or C = A + C - A and C
    // C = A or C - A + A and C = AorC - A + A*C
    // C - A*C = AorC - A
    // C*(1 - A) = AorC - A
    // C = (AorC - A)/(1-A)
    val C = (AorC - A) / (1 - A)
    println(C)
    C should be(1d / 2d +- delta)

    // A and B and C ?
    //    val AandBandC = A and B and C - incorrect: we know that A,B,C are independent by two, but not by three
    //    val AandB = A and  B
    // AorBorC = A + B + C - AandB - AandC - BandC + AandBandC
    // AandBandC = AorBorC - A - B - C + AandB + AandC + BandC
    val AandBandC = P(AorBorC) - P(A) - P(B) - P(C) + P(A and B) + P(A and C) + P(B and C)
    println(P(AandBandC))
    P(AandBandC) should be(1d / 4d +- delta)
  }

  "Ex 5.2" in {
    val A = .2d
    val B = .3d
    val C = .4d

    P((A or B) and C) should be(.176d +- delta)
  }

  /**
    * Cvičení 5.6. Hodíme dvěma kostkami. Označme jevy
    * A = „na první kostce padlo liché číslo,“
    * B = „na druhé kostce padla nejvýše trojka,“
    * C = „součet hodů je lichý.“
    * Určete, zda jsou jevy A, B, dále B, C a A, C nezávislé. Co lze říci o nezávislosti jevů A, B, C?
    */
  "Ex 5.6" in {
    val totalNumbers = 1 to 6
    val A = ((_: Int) % 2 == 1) within totalNumbers
    A should be(.5d +- delta)

    val B = ((_: Int) <= 3) within totalNumbers
    println(B)
    B should be(.5d +- delta)

    val C = ((p: (Int, Int)) => (p._1 + p._2) % 2 == 0) within (totalNumbers.flatMap(x => totalNumbers.map(y => (x, y))))
    println(C)
    C should be(.5d +- delta)

    // A and B are independent, because two different cubes
    val AandB = A and B
    AandB.probability should be(1d / 4d +- delta)

    // A and C means means on first cube we have odd number and on second cube we have even number, these are independent as well
    val AandC = A and C
    AandC.probability should be(1d / 4d +- delta)

    // B and C can occur when
    //  1 on second and first has even number
    val B1andEven = ((_: Int) == 1) within totalNumbers and .5
    //  2 on second and first has even number
    val B2andEven = ((_: Int) == 2) within totalNumbers and .5
    //  3 on second and first has even number
    val B3andEven = ((_: Int) == 3) within totalNumbers and .5
    // These three are incompatible, thus
    val BandC = ComplexAndEvent(B, C, B1andEven + B2andEven + B3andEven)
    println(P(BandC))

    // A and B and C -> 1st has odd number, -> because sum is odd, on 2nd should have even -> on second got 2
    val AandBandC = P(A) * (((_: Int) == 2) within totalNumbers)
    println(AandBandC)
    P(AandBandC) should be(1d / 12d +- delta)

    val independent = AandBandC == (A * B * C)
    println(independent)
    independent shouldBe false
  }
}
