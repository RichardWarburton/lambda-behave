package com.insightfullogic.lambdabehave

import org.scalatest._
import com.insightfullogic.lambdabehave.example.StackSpec

class ScalaTestWrapperSpec extends FunSpec {

  describe("LambdaBehaveSuite") {

    it("should uses lambda behave fully qualified class name as suiteId") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      assert(s.suiteId == "com.insightfullogic.lambdabehave.example.StackSpec")
    }

    it("should uses lambda behave's suite name as suiteName") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      assert(s.suiteName == "a stack")
    }

    it("should return correct expectedTestCount") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      assert(s.expectedTestCount(Filter.default) == 3)
    }

    it("should return correct testNames") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      val sTestNames = s.testNames
      assert(sTestNames.size == 3)
      assert(sTestNames.contains("be empty when created"))
      assert(sTestNames.contains("push new elements onto the top of the stack"))
      assert(sTestNames.contains("popSince the last element pushed onto the stack"))
    }

    it("should run tests when run method is called") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      val rep = new EventRecordingReporter
      s.run(None, Args(rep))

      val testStartingEvents = rep.testStartingEventsReceived
      assert(testStartingEvents.length == 3)
      assert(testStartingEvents(0).testName == "be empty when created")
      assert(testStartingEvents(1).testName == "push new elements onto the top of the stack")
      assert(testStartingEvents(2).testName == "popSince the last element pushed onto the stack")

      val testSucceededEvents = rep.testSucceededEventsReceived
      assert(testSucceededEvents.length == 3)
      assert(testSucceededEvents(0).testName == "be empty when created")
      assert(testSucceededEvents(1).testName == "push new elements onto the top of the stack")
      assert(testSucceededEvents(2).testName == "popSince the last element pushed onto the stack")
    }

    it("should return com.insightfullogic.lambdabehave.Suite as style spec") {
      val s = new ScalaTestWrapper(classOf[StackSpec])
      assert(s.styleName == "com.insightfullogic.lambdabehave.Suite")
    }

  }

}