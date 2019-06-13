package PST.probability

//class Probability(val value: Float, val name: String = "_") {
//  def and(other: Probability) = new Probability(value * other.value)
//  def inCase(other: Probability) = new Probability(value / other.value)
//  def or(other: Probability) = new Probability(value + other.value)
//  def ==(other: Probability): Boolean = value == other.value
//}
//
//object Probability {
//  def not(probability: Probability) = new Probability(1 - probability.value, s"!${probability.name}")
//  def areIndependent(a: Probability, b: Probability, aAndB: Probability): Boolean = (a and b) == aAndB
//}


// P(A or B) = P(A) + P(B) - P(A and B)

// A and B

// if independent -> P(A and B) = P(A) * P(B) -> P(A or B) = P(A) + P(B) - P(A) * P(B)
//
