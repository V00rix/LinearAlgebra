package PST.probability.events

import PST.probability.P

case class ComplexOrEvent(A: Event, B: Event, AB: ComplexAndEvent) extends ComplexEvent(A, B) {
  def probability: Double = P(A) + P(B) - P(AB)
  override def isDependent: Boolean = AB.isDependent
}
