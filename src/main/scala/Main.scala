package com.tolstoy.diary

import entries.Util.init
import services.EntryService
import services.HelloService

import cats.data.Kleisli
import cats.syntax.semigroupk._
import com.tolstoy.diary.controllers.EntryController
import org.http4s.HttpRoutes
import org.http4s.Request
import org.http4s.Response
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import slick.jdbc.H2Profile.api._
import zio._
import zio.interop.catz._

object ioz extends Http4sDsl[Task]

object MyApp extends zio.App {

  private val db = Database.forConfig("chapter01")

  val routes = Router("/hello" -> HelloService.service, "/entries" -> EntryController.routes(db)).orNotFound

  def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    val serverRun = for {
      _ <- init(db)
      res <- server
    } yield res

    serverRun.fold(_ => ExitCode.failure, _ => ExitCode.success)
  }

  val server: ZIO[ZEnv, Throwable, Unit] = ZIO
    .runtime[ZEnv]
    .flatMap { implicit rts =>
      BlazeServerBuilder[Task]
        .bindHttp(8080, "localhost")
        .withHttpApp(routes)
        .serve
        .compile
        .drain
    }

}
