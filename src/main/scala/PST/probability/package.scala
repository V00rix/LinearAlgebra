package PST

import PST.probability.events.{Event, SingleEvent}

import scala.language.implicitConversions

package object probability {
  def in(elements: Boolean*): Double =
    if (elements.nonEmpty) elements.count(el => el).toDouble / elements.length.toDouble else 0f

  def not(probability: Double): Double = 1 - probability

  /** */
  def tryOptions(totalOptions: Int, tries: Int): Double = {
    if (totalOptions < 0) throw new Exception("Should contain at least one option")
    if (totalOptions == 1) {
      if (tries >= 1) 1f
      else 0f
    }
    else if (tries < 1) 0f
    else {
      val doubleOptions = totalOptions.toDouble
      1f / doubleOptions + not(1f / doubleOptions) * tryOptions(totalOptions - 1, tries - 1)
    }
  }

  def P(event: Event): Double = event.probability

  implicit def toEvent(probability: Double): SingleEvent = SingleEvent(probability)
  implicit def toProbability(event: Event): Double = P(event)

  implicit class WithinStatement(int: Int) {
    def within(count: Int): Double = int.toDouble / count.toDouble
    def within(count: Seq[_]): Double = int.toDouble / count.length.toDouble
  }

  implicit class WithinSeqStatement[A](p: A => Boolean) {
    def within(seq: Seq[A]): Double = seq.count(p).toDouble / seq.length.toDouble
  }
}
