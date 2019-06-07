package PST.probability

class ClassicalProbability(private var _total: Int, private var _success: Int) extends Probability {
  private var probability = ClassicalProbability.probability(_total, _success)
  override implicit def value: Float = probability

  def addTruthy(): Unit = {
    _success += 1
    _total += 1
  }

  def addFalsy(): Unit = {
    _total += 1
    // but probability stays unchanged
  }

}
object ClassicalProbability {
  def probability(total: Int, success: Int): Float = success.toFloat / total.toFloat
}
