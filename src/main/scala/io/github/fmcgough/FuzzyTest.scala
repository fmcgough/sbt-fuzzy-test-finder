package io.github.fmcgough

import scala.sys.process._
import sbt.Keys._
import sbt._

import scala.util.{Failure, Success, Try}

object FuzzyTest extends AutoPlugin {
  override def requires = empty
  override def trigger = allRequirements

  object autoImport {
    val ttFilterCommand = settingKey[String]("cli filtering tool you want to use")
  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    ttFilterCommand := "fzf",

    commands += Command.command("tt") { state =>
      if (!state.interactive) {
        sLog.value.error("This command may only be used in interactive mode")
        state.fail
      } else {
        val taskResult: Option[(State, Result[Seq[String]])] = Project.runTask(definedTestNames in Test, state)

        taskResult match {
          case None => sys.error("task not defined")
          case Some((newState, Inc(incomplete))) =>
            // error detail
            newState.fail

          case Some((newState, Value(testNames))) =>
            sLog.value.info(s"Finding test classes...")
            val result = Try(Seq("echo", testNames.mkString(System.lineSeparator)).#|(ttFilterCommand.value).!!.trim)
            result match {
              case Success(testName) =>
                val testTask = s"testOnly $testName"
                sLog.value.info(s"Running `$testTask`")
                val historyFile: Option[File] = (historyPath).value
                historyFile.foreach(f => IO.append(f, testTask))
                testTask :: newState.copy(history = (Exec(testTask, source = newState.source) :: newState.history))
              case Failure(_) =>
                sLog.value.error("Test name not found")
                newState.fail
            }
        }
      }
    }
  )
}
