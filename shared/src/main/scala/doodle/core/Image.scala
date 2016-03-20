package doodle.core

import doodle.backend._

sealed trait Image {
  def at(currentLocation: Vec): Image = {
    At(currentLocation, this)
  }


  def boundingBox(): BoundingBox = this match {
    case Circle(r) => BoundingBox(-r, r, r, -r);
    case Rectangle(w, h) => BoundingBox(-w/2, h/2, w/2, -h/2)
    case On(a, b) => a.boundingBox() on b.boundingBox()
    case Beside(a, b) => a.boundingBox() beside b.boundingBox()
    case Above(a, b) => a.boundingBox() above b.boundingBox()
    case At(location, image) => image.boundingBox().move(location)
  }

  def on(that: Image): Image =
    On(this, that)

  def beside(that: Image): Image =
    Beside(this, that)

  def above(that: Image): Image =
    Above(this, that)

  def draw(canvas: Canvas): Unit = {
    this.draw(canvas, 0.0, 0.0)
  }

  def draw(canvas: Canvas, originX: Double, originY: Double): Unit = this match {
    case Circle(r) => canvas.circle(originX, originY, r); canvas.setFill(Color.red); canvas.fill();
    case Rectangle(w, h) => canvas.rectangle(originX - (w / 2), originY + (h / 2), w, h); canvas.setFill(Color.green); canvas.fill();
    case On(a, b) =>
      a.draw(canvas, originX, originY)
      b.draw(canvas, originX, originY)
    case Beside(a, b) =>
      val thisBox: BoundingBox = this.boundingBox()
      val aBox: BoundingBox = a.boundingBox()
      val bBox: BoundingBox = b.boundingBox()

      val aOriginX = originX + thisBox.left + (aBox.width / 2)
      val bOriginX = originX + thisBox.right - (bBox.width / 2)

      a.draw(canvas, aOriginX, originY)
      b.draw(canvas, bOriginX, originY)
    case Above(a, b) =>
      val thisBox: BoundingBox = this.boundingBox()
      val aBox: BoundingBox = a.boundingBox()
      val bBox: BoundingBox = b.boundingBox()

      val aOriginY = originY + thisBox.top - (aBox.height / 2)
      val bOriginY = originY + thisBox.bottom + (bBox.height / 2)
      a.draw(canvas, originX, aOriginY)
      b.draw(canvas, originX, bOriginY)
    case At(location, image) =>
      image.draw(canvas, location.x, location.y)
  }

  def currentVelocity(previousVelocity: Vec, input: Key): Vec =
    input match {
      case Up => previousVelocity + Vec(0, 1)
      case Down => previousVelocity + Vec(0, -1)
      case Left => previousVelocity + Vec(-1, 0)
      case Right => previousVelocity + Vec(1, 0)
    }

  def currentLocation(previousLocation: Vec, velocity: Vec): Vec =
    previousLocation + velocity


  def currentBall(currentLocation: Vec): Image =
    this at currentLocation

}

final case class Circle(radius: Double) extends Image

final case class Rectangle(width: Double, height: Double) extends Image

final case class On(a: Image, b: Image) extends Image

final case class Beside(a: Image, b: Image) extends Image

final case class Above(a: Image, b: Image) extends Image

final case class At(currentLocation: Vec, i: Image) extends Image
