package com.tolstoy.diary
package entries

import slick.jdbc.H2Profile
import zio.ZIO

object Util {
  def init(db: H2Profile.backend.DatabaseDef): ZIO[Any, Throwable, Unit] = for {
    _ <- ZIO.fromFuture(_ => db.run(EntryQueries.create))
  } yield ()

}
