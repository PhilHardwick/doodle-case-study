package doodle.core

import doodle.backend.Canvas

sealed trait Image {

  def boundingBox(): BoundingBox = this match {
    case Circle(r) => BoundingBox(0, 0, r*2, r*2);
    case Rectangle(w, h) => BoundingBox(0, 0, w, h)
    case Images(seq) => seq.foreach((image) => image draw canvas)
  }

  def on(that: Image): Image =
    Images(Seq(this, that))

  def beside(that: Image): Image =
    Images(Seq(this, that))

  def above(that: Image): Image =
    Images(Seq(this, that))

  def draw(canvas: Canvas): Unit = this match {
    case Circle(r) => canvas.circle(0.0, 0.0, r)
    case Rectangle(w, h) => canvas.rectangle(-w / 2, h / 2, w / 2, -h / 2)
    case Images(seq) => seq.foreach((image) => image draw canvas)
  }

}

final case class Circle(radius: Double) extends Image

final case class Rectangle(width: Double, height: Double) extends Image

final case class Images(seq: Seq[Image]) extends Image
