package PST.probability.distributions

import PST.probability.exceptions.ProbabilityException

/**
  *
  * @tparam T ddsa
  */
abstract class Distribution[T] {
  val mean: Double
  val variance: Double
  val standardDeviation: Double = Math.sqrt(variance)

  /**
    * Probability mass or density function, depending on derived class.
    * Maps Values of Random Variable to probability (0, 1)
    *
    * @param x ddqwe
    * @return Probability of event occurring
    */
  def apply(x: T): Double
  def distribution(x: Double): Double = distribution(0, x)

  def distribution(a: Double, b: Double): Double

  def moment(k: Int): Double = {
    k match {
      case 1 => mean
      case 2 => mean * mean + variance
      case x if x < 1 => throw new ProbabilityException("k should be greater than zero")
//      case _ => mean(k)
    }
  }

  def centralMoment(k: Int): Double = {
    k match {
      case 1 => 0
      case 2 => variance
      case x if x < 1 => throw new ProbabilityException("k should be greater than zero")
    }
  }
}
