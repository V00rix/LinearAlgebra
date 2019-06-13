package PST.probability

package object events {
  def determineFromIndependent(AandB: Float, AorB: Float): (Event, Event) = {
    // P(A or B) = P(A) + P(B) - P(A and B)
    // P(A) + P(B) = P(A or B) + P(A and B)
    val sum = AorB + AandB

    // A and B are independent -> P(A and B) = P(A) * P(B)
    val product = AandB

    // x + y = sum
    // x * y = product
    // x = sum - y
    // (sum - y) * y = product
    // y^2 - sum*y + product = 0
    // D = sum^2 - 4*product
    val D = sum * sum - 4 * product

    // y[1,2] = (sum +- sqrt(D)) / 2
    val y = (sum + Math.sqrt(D)) / 2
    val x = sum - y
    (x, y)
  }
}
