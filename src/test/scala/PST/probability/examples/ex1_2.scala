package PST.probability.examples

import PST.probability.tryOptions
import org.scalatest.FlatSpec

import scala.util.Random

class ex1_2 extends FlatSpec {
  /**
    *
    */
  "try options" should "work" in {
    assert(tryOptions(1, 1) == 1f)
    assert(tryOptions(2, 1) == 1f / 2f)
    assert(tryOptions(4, 3) == 3f / 4f)
    assert(tryOptions(3, 3) == 1f)
  }

  /**
    * Cvičení 1.2 (4 PINy).
    * Banka poslala jednomu klientovi ke 4 jeho kontům přístupová hesla (PIN),
    * ale neuvedla, které heslo patří ke kterému účtu. Ke každému účtu lze vyzkoušet 3 kódy, po 3 chybách se
    * zablokuje. Navrhněte postup, který dovolí zpřístupnit (v průměru) co nejvíce kont.
    */
  it should "work correctly" in {
    class Konto(val pin: String) {
      private var tries = 3
      def tryPin(pin: String): Boolean =
        if (pin == this.pin) true
        else {
          tries -= 1
          false
        }
      def isBlocked: Boolean = tries == 0

      override def toString: String = "Konto: " + pin
    }

    val tries: Int = 1e6.toInt


    //    val succeedKonto = ClassicalProbability.probability()

    //    for (_ <- tries) {

    //    }

    def tryAllPinsForKonto(konto: Konto, pins: Seq[String]): Boolean = {
      for (p <- pins) {
        if (konto.tryPin(p)) return true
        if (konto.isBlocked) return false
      }
      false
    }

    def tryAllKontosForPin(pins: Seq[String], kontos: Seq[Konto]): Boolean = {
      var knts = kontos

      for (p <- pins) {
        var shouldBreak = false
        for (k <- knts if !shouldBreak) {
          if (k.tryPin(p)) {
            shouldBreak = true
            knts = knts.filter(el => el != k)
          }
          if (k.isBlocked) return false
        }
      }
      true
    }

    var successes = 0
    for (_ <- 1 to tries) {
      val pins = Seq("1111", "2222", "3333", "4444")
      val kontos = Random.shuffle(pins).map(new Konto(_))
      if (tryAllPinsForKonto(kontos.head, pins)) successes += 1
    }

    println(s"Succeeded $successes out of $tries (${successes.toFloat / tries.toFloat})")

    successes = 0
    for (_ <- 1 to tries) {
      val pins = Seq("1111", "2222", "3333", "4444")
      val kontos = Random.shuffle(pins).map(new Konto(_))
      if (tryAllKontosForPin(pins, kontos)) successes += 1
    }

    println(s"Succeeded $successes out of $tries (${successes.toFloat / tries.toFloat})")

  }
}
