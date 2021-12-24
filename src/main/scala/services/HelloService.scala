package com.tolstoy.diary
package services

import cats.data.Kleisli
import org.http4s.HttpRoutes
import org.http4s.Request
import org.http4s.Response
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import zio._
import zio.interop.catz._
import zio.interop.catz.implicits._

object HelloService {
  object ioz extends Http4sDsl[Task]
  import ioz._

  val service =
    HttpRoutes.of[Task] { case GET -> Root => Ok("hello!") }
}
