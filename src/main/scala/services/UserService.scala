package com.tolstoy.diary
package services

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import zio.Task
import zio.interop.catz._
import zio.interop.catz.implicits._

object UserService {
  object ioz extends Http4sDsl[Task]
  import ioz._

  val service =
    HttpRoutes.of[Task] { case GET -> Root => Ok("hello!") }
}
