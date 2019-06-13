package PST.probability.events

/**
  * Base Event class
  */
abstract class Event {
  /** Probability of Event occurring
    *
    * @return Double from 0 (never occurring) to (1
    */
  def probability: Double
  /** Independent A and B
    */
  def and(B: Event): ComplexAndEvent = ComplexEvent.and(this, B)
  // Possibly dependent A and B
  def and(B: Event, probability: Double): ComplexAndEvent = ComplexEvent.and(this, B, probability)

  // Independent A and B
  def or(B: Event): ComplexOrEvent = ComplexEvent.or(this, B)
  // Possibly dependent A and B
  def or(B: Event, andB: ComplexAndEvent): ComplexOrEvent = ComplexEvent.or(this, B, andB)

  // A with condition of B
  def given(B: Event): ComplexGivenEvent = ComplexEvent.given(this, B)

  def ==(other: Event): Boolean = probability == other.probability
  def ==(probability: Double): Boolean = this.probability == probability
}
