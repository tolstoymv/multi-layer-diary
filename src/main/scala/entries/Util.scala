package com.tolstoy.diary
package entries

import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._
import zio.ZIO

import java.time.Instant

object Util {
  def init(db: H2Profile.backend.DatabaseDef): ZIO[Any, Throwable, Unit] = for {
    _ <- ZIO.fromFuture(_ => db.run(EntryQueries.create))
    _ <- ZIO.fromFuture(_ => db.run(EntryQueries.entries ++= initialTestData))
  } yield ()

  def initialTestData = Seq(
    Entry("Hello, HAL. Do you read me, HAL?", Instant.now(), 1),
    Entry("Affirmative, Dave. I read you.", Instant.now(), 2),
    Entry("Open the pod bay doors, HAL.", Instant.now(), 1),
    Entry("I'm sorry, Dave. I'm afraid I can't do that.", Instant.now(), 2)
  )
}
