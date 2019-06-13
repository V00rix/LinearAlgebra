package PST.probability.distributions.continuous

class Exponential(rate: Double) extends Continuous {
  val mean: Double = 1d / rate
  val variance: Double = 1d / rate / rate
  def apply(x: Double): Double = rate * Math.pow(Math.E, -rate * x)
  def distribution(a: Double, b: Double): Double = Math.pow(Math.E, -rate * a) - Math.pow(Math.E, -rate * b)
}
