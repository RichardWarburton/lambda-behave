package org.scalatest.lambdabehave

import org.scalatest._
import org.scalatest.events._
import scala.reflect.NameTransformer

object ScalaTestHelper {

  def reportTestStarting(theSuite: org.scalatest.Suite, report: Reporter, tracker: Tracker, testName: String, testText: String, rerunnable: Option[String], location: Option[Location]) {
    report(TestStarting(tracker.nextOrdinal(), theSuite.suiteName, theSuite.suiteId, Some(theSuite.getClass.getName), testName, testText, Some(MotionToSuppress),
      location, rerunnable))
  }

  def reportTestSucceeded(theSuite: org.scalatest.Suite, report: Reporter, tracker: Tracker, testName: String, testText: String, recordedEvents: collection.immutable.IndexedSeq[RecordableEvent], duration: Long, formatter: Formatter, rerunnable: Option[String], location: Option[Location]) {
    report(TestSucceeded(tracker.nextOrdinal(), theSuite.suiteName, theSuite.suiteId, Some(theSuite.getClass.getName), testName, testText, recordedEvents, Some(duration), Some(formatter),
      location, rerunnable))
  }

  def getEscapedIndentedTextForTest(testText: String, level: Int, includeIcon: Boolean) = {
    val decodedTestText = NameTransformer.decode(testText)
    val escapedTestText =
      if (decodedTestText.startsWith("test: "))
        decodedTestText.drop(6)
      else
        decodedTestText
    val formattedText =
      if (includeIcon) {
        val testSucceededIcon = Resources("testSucceededIconChar")
        ("  " * (if (level == 0) 0 else (level - 1))) + Resources("iconPlusShortName", testSucceededIcon, escapedTestText)
      }
      else {
        ("  " * level) + escapedTestText
      }
    IndentedText(formattedText, decodedTestText, level)
  }

  def getIndentedTextForInfo(message: String, level: Int, includeIcon: Boolean, infoIsInsideATest: Boolean) = {
    val formattedText =
      if (includeIcon) {
        val infoProvidedIcon = Resources("infoProvidedIconChar")
        //
        // Inside a test, you want level 1 to stay 1
        // [scalatest] - outermost test (5 milliseconds)
        // [scalatest]   + in outermost test
        //
        // But outside a test, level 1 should be transformed to 0
        // [scalatest] Apple
        // [scalatest] + in Apple
        //
        val indentationLevel =
          level match {
            case 0 => 0
            case 1 if infoIsInsideATest => 1
            case _ => level - 1
          }
        ("  " * indentationLevel) + Resources("iconPlusShortName", infoProvidedIcon, message)
        // ("  " * (if (level <= 1) level else (level - 1))) + Resources("iconPlusShortName", infoProvidedIcon, message)
      }
      else {
        ("  " * level) + message
      }
    IndentedText(formattedText, message, level)
  }

  def createInfoProvided(theSuite: org.scalatest.Suite,
                         report: Reporter,
                         tracker: Tracker,
                         testName: Option[String],
                         message: String,
                         payload: Option[Any],
                         level: Int,
                         location: Option[Location],
                         includeNameInfo: Boolean,
                         includeIcon: Boolean = true) = {
    InfoProvided(
      tracker.nextOrdinal(),
      message,
      if (includeNameInfo)
        Some(NameInfo(theSuite.suiteName, theSuite.suiteId, Some(theSuite.getClass.getName), testName))
      else
        None,
      None,
      Some(getIndentedTextForInfo(message, level, includeIcon, testName.isDefined)),
      location,
      payload
    )
  }

  def createMarkupProvided(
                            theSuite: org.scalatest.Suite,
                            report: Reporter,
                            tracker: Tracker,
                            testName: Option[String],
                            message: String,
                            level: Int,
                            location: Option[Location],
                            includeNameInfo: Boolean,
                            includeIcon: Boolean = true
                            ) = {
    MarkupProvided(
      tracker.nextOrdinal(),
      message,
      if (includeNameInfo)
        Some(NameInfo(theSuite.suiteName, theSuite.suiteId, Some(theSuite.getClass.getName), testName))
      else
        None,
      Some(getIndentedTextForInfo(message, level, includeIcon, testName.isDefined)),
      location
    )
  }

  def createMessageRecorder(reporter: Reporter): MessageRecorder = new MessageRecorder(reporter)

  def createMessageRecordingInformer(thisSuite: Suite, reporter: Reporter, tracker: Tracker, testName: String, messageRecorder: MessageRecorder) =
    MessageRecordingInformer(
      messageRecorder,
      (message, payload, isConstructingThread, testWasPending, testWasCanceled, location) => createInfoProvided(thisSuite, reporter, tracker, Some(testName), message, payload, 2, location, isConstructingThread, true)
    )

  def createMessageRecordingDocumenter(thisSuite: Suite, reporter: Reporter, tracker: Tracker, testName: String, messageRecorder: MessageRecorder) =
    MessageRecordingDocumenter(
      messageRecorder,
      (message, _, isConstructingThread, testWasPending, testWasCanceled, location) => createMarkupProvided(thisSuite, reporter, tracker, Some(testName), message, 2, location, isConstructingThread) // TODO: Need a test that fails because testWasCanceleed isn't being passed
    )

  def checkChosenStyles(configMap: ConfigMap, styleName: String): Unit = Suite.checkChosenStyles(configMap, styleName)

  def wrapReporterIfNecessary(theSuite: Suite, reporter: Reporter): Reporter = Suite.wrapReporterIfNecessary(theSuite, reporter)

  def reportTestIgnored(theSuite: Suite, report: Reporter, tracker: Tracker, testName: String, testText: String, formatter: Formatter, location: Option[Location]): Unit =
    Suite.reportTestIgnored(theSuite, report, tracker, testName, testText, formatter, location)
}