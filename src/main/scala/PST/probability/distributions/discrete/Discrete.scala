package PST.probability.distributions.discrete

import PST.probability.distributions.Distribution

abstract class Discrete extends Distribution[Int] {
  override def distribution(a: Double, b: Double): Double = apply(Math.floor(b).toInt) - apply(Math.floor(a).toInt)
}
