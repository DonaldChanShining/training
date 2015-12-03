package spray.testkit

import org.specs2.execute._
import org.specs2.specification._
import org.specs2.specification.core.SpecificationStructure

trait Specs2Interface extends TestFrameworkInterface with SpecificationStructure with AfterAll {

  def failTest(msg: String) = {
    val trace = new Exception().getStackTrace.toList
    val fixedTrace = trace.drop(trace.indexWhere(_.getClassName.startsWith("org.specs2")) - 1)
    throw new FailureException(Failure(msg, stackTrace = fixedTrace))
  }

  def afterAll = cleanUp()
}