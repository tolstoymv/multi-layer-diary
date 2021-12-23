package com.tolstoy.diary
package services

import entries.EntryQueries

import cats.data.Kleisli
import org.http4s.HttpRoutes
import org.http4s.Request
import org.http4s.Response
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._
import zio.Task
import zio.ZIO
import zio.interop.catz._
import zio.interop.catz.implicits._

object ioz extends Http4sDsl[Task]

object EntryService {
  object ioz extends Http4sDsl[Task]
  import ioz._

  def service(db: H2Profile.backend.DatabaseDef): Kleisli[Task, Request[Task], Response[Task]] = HttpRoutes
    .of[Task] { case GET -> Root / "entries" =>
      ZIO.fromFuture(ex => db.run(EntryQueries.entries.result)).flatMap(x => Ok(x.mkString("[", ",", "]")))
    }
    .orNotFound

}
