package PST.probability.events

import PST.probability.P

/**
  * Phenomenon, that consists of two Phenomena
  */
object ComplexEvent {
  // Possibly dependent A and B
  def and(A: Event, B: Event, probability: Double): ComplexAndEvent = ComplexAndEvent(A, B, probability)
  // Guaranteed independent A and B
  def and(A: Event, B: Event): ComplexAndEvent = ComplexAndEvent(A, B, P(A) * P(B))

  // Possibly dependent A and B
  def or(A: Event, B: Event, AB: ComplexAndEvent): ComplexOrEvent = ComplexOrEvent(A, B, AB)
  // Independent A and B
  def or(A: Event, B: Event): ComplexOrEvent = ComplexOrEvent(A, B, A and B)

  // Conditional Probability
  def given(A: Event, B: Event): ComplexGivenEvent = ComplexGivenEvent(A, B, A and B)
}

abstract class ComplexEvent(A: Event, B: Event) extends Event {
  def phenomena: (Event, Event) = (A, B)
  def isDependent: Boolean
}
