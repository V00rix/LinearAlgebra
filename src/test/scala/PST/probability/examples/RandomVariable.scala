package PST.probability.examples

import PST.probability.Delta
import PST.probability.distributions.discrete.Binomial
import org.scalatest.{FreeSpec, Matchers}

class RandomVariable extends FreeSpec with Delta with Matchers {
  /**
    * Cvičení 8.3. V testu je 15 otázek s možnými odpověďmi a)-e), právě jedna je správná. Jaká je pravděpodobnost, že při zcela náhodném
    * tipování trefí student správně aspoň tři otázky? Popište náhodnou veličinu popisující počet správných odpovědí
    */
  "Ex 8.3" in {
    // a, b, c, d, e
    val options = Seq("a", "b", "c", "d", "e")
    val questionsCount = 15

    val guessedQuestions = new Binomial(15, 1d / options.length)
    val probability = guessedQuestions(3) + guessedQuestions(2) + guessedQuestions(1)
    println(probability)
  }
}
