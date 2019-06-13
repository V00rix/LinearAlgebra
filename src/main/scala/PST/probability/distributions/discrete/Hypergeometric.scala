package PST.probability.distributions.discrete

import PST.combinatorics._

class Hypergeometric(M: Int, K: Int, m: Int) extends Discrete {
  def apply(k: Int): Double = BigDecimal((choose(k) from K) * (choose(m - k) from (M - K)) / (choose(m) from M)).toDouble
  val mean: Double = m * K / M
  val variance: Double = m * K * (M - K) * (M - m) / M / M / (M - 1)
}
