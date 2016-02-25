package doodle.core

import doodle.backend.Canvas

sealed trait Image {

  def boundingBox(): BoundingBox = ???

  def on(that: Image): Image =
    Images(Seq(this, that))

  def beside(that: Image): Image =
    Images(Seq(this, that))

  def above(that: Image): Image =
    Images(Seq(this, that))

  def draw(canvas: Canvas): Unit = this match {
    case Circle(r) => canvas.circle(0.0, 0.0, r)
    case Rectangle(w, h) => canvas.rectangle(-w / 2, h / 2, w / 2, -h / 2)
  }

}

final case class Circle(radius: Double) extends Image

final case class Rectangle(width: Double, height: Double) extends Image

final case class Images(seq: Seq[Image]) extends Image {

  override def draw(canvas: Canvas): Unit = {
    seq.foreach((image) => image draw canvas)
  }

}
