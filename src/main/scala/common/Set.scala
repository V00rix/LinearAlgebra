package common

import scala.reflect.ClassTag

/**
  * A set is a well-defined collection of distinct objects.
  * To define (create) a set means to define that collection of objects.
  *
  * @param elements Collection of distinct objects
  * @tparam A Type of objects
  */
class Set[A: ClassTag](protected val elements: Array[A]) {
  def this(elements: A*) = {
    this(elements.toArray[A])
  }

  def distinct(elements: Array[A]): Boolean = {
    false
  }

  //  def x[B](that: Set[B]): CartesianProduct[A, B] = new CartesianProduct[A, B](this, that)

  /**
    * Sets A and B are equal if and only if they have precisely the same elements.
    *
    */
  override def equals(that: Any): Boolean = {
    that match {
      case that: Set[A] => that.isInstanceOf[Set[A]] && {
        if (size != that.size) return false
        elements.forall(e => that.elements.contains(e))
      }
      case _ => false
    }
  }

  def size: Int = elements.length
}