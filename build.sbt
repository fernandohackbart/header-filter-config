organization in ThisBuild := "my.lagom.issue"
version in ThisBuild := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `header-filter-config` = (project in file("."))
  .aggregate(`header-filter-api`,
    `header-filter-impl`
    )

lazy val `header-filter-api` = (project in file("header-filter-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `header-filter-impl` = (project in file("header-filter-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    ),
    resolvers += Resolver.jcenterRepo
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`header-filter-api`)
