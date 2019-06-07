ThisBuild / version := "0.1"
ThisBuild / organization := "io.github.fmcgough"
ThisBuild / description := "Fuzzy finder for running test suites in SBT"

ThisBuild / licenses := List("MIT" -> new URL("https://opensource.org/licenses/MIT"))

lazy val root = (project in file("."))
  .settings(
    sbtPlugin := true,
    name := "sbt-fuzzy-test-finder",
    publishMavenStyle := false,
    bintrayRepository := "sbt-plugins",
    bintrayOrganization in bintray := None
)
