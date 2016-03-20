package doodle.backend

/**
  * Created by Phil on 17/03/2016.
  */
sealed trait Key {



}

case object Up extends Key

case object Down extends Key

case object Left extends Key

case object Right extends Key