package PST.probability.distributions.continuous

class Normal(val mean: Double = 0d, val variance: Double = 1d) extends Continuous {
  def apply(x: Double): Double = {
    val d = x - mean
    1d / (standardDeviation * Math.sqrt(2d * Math.PI)) * Math.pow(Math.E, -(d * d) / (2d * variance))
  }
  def distribution(a: Double, b: Double): Double = ??? // very hard integral... lookup table...
}
