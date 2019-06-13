package PST.probability.events

import PST.probability.P
import PST.probability.exceptions.ProbabilityException

case class ComplexGivenEvent(A: Event, B: Event, AB: ComplexAndEvent) extends ComplexEvent(A, B) {
  def probability: Double = {
    val pB = P(B)
    if (pB != 0f)  P(AB) / P(B)
    else throw new ProbabilityException(s"Probability of $B equals zero. Probability for $A given $B is therefore not defined.")
  }
  override def isDependent: Boolean = probability == P(A)
  def BgivenA: ComplexGivenEvent = ComplexGivenEvent(B, A, AB)
}
