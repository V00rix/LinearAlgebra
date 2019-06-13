package PST.probability.distributions.discrete

import PST.combinatorics._

class Poisson(val mean: Double) extends Discrete {
  def apply(successes: Int): Double =
    (BigDecimal(Math.pow(mean, successes) * Math.pow(Math.E, -mean)) / BigDecimal(factorial(successes))).toDouble
  def variance: Double = mean
}
