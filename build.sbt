ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "MultilayerDiary",
    idePackagePrefix := Some("com.tolstoy.diary")
  )

val http4sVersion = "0.23.7"
val slickJodaMapperVersion = "2.4.2"
val circeVersion = "0.14.1"
val zioVersion = "1.0.13"
val slickVersion = "3.3.3"

// Only necessary for SNAPSHOT releases
resolvers += Resolver.sonatypeRepo("snapshots")


libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion,
  "dev.zio" %% "zio-interop-cats" % "3.2.9.0",
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.github.tototoshi" %% "slick-joda-mapper" % slickJodaMapperVersion,
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.h2database" % "h2" % "2.0.202",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion
)
