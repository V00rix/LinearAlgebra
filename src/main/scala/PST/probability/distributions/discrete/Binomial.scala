package PST.probability.distributions.discrete

import PST.combinatorics._

class Binomial(tries: Int, p: Double) extends Discrete {
  val mean: Double = tries * p
  val variance: Double = tries * p * (1 - p)
  def apply(successes: Int): Double =
    (BigDecimal(choose(successes) from tries) * BigDecimal(Math.pow(p, successes) * Math.pow(1 - p, tries - successes))).toDouble
}
