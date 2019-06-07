package PST

package object probability {
  def in(elements: Boolean*): Float =
    if (elements.nonEmpty) elements.count(el => el).toFloat / elements.length.toFloat else 0f
}
