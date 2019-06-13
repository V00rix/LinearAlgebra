package PST.probability.distributions.discrete

class Geometric(p: Double) extends Discrete {
  val mean: Double = p / (1 - p)
  val variance: Double = {
    val d = 1 - p
    p / (d * d)
  }
  def apply(successes: Int): Double = Math.pow(p, successes) * (1 - p)
}
