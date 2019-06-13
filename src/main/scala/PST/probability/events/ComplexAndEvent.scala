package PST.probability.events

import PST.probability.P

case class ComplexAndEvent(A: Event, B: Event, probability: Double) extends ComplexEvent(A, B) {
  override def isDependent: Boolean= P(A) * P(B) != probability
}
