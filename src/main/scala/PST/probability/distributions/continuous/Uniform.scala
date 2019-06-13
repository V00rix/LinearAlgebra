package PST.probability.distributions.continuous

class Uniform(min: Double, max: Double) extends Continuous {
  private val p: Double = 1d / (max - min)

  val mean: Double = (max + min) / 2
  val variance: Double = {
    val d = max - min
    1d / 12 * d * d
  }
  def apply(value: Double): Double = p
  def distribution(a: Double, b: Double): Double = p * (a - b)
}
