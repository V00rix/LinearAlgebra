package PST.probability.distributions.discrete

class Uniform(val a: Int, val b: Int) extends Discrete {
  def apply(k: Int): Double = 1d / (b - a)
  val mean: Double = (a + b).toDouble / 2
  val variance: Double = {
    val i = b - a + 1
    (i * i - 1).toDouble / 12
  }
}
