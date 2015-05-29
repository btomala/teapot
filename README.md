[![Build Status](https://travis-ci.org/btomala/http-mock.svg?branch=master)](https://travis-ci.org/btomala/http-mock)
[![Download](https://api.bintray.com/packages/btomala/maven/http-mock/images/download.svg) ](https://bintray.com/btomala/maven/http-mock/_latestVersion)
 
# teapot

Is a simple mocking http server based on [akka-http](akka.io) written in scala.

## usage 

To use add resolver to your build

```
resolvers += "btomala" at "https://dl.bintray.com/btomala/maven/"

libraryDependencies += "btomala" %% "http-mock" % "0.1"
```

Sample of usage

```scala

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import btomala.http.mock.HttpMock

  val server = new HttpMock()
  val request = HttpResponse()
  val response = HttpResponse()
  
  server.record (request → response)

  //here make request to the server..

```

For more look in to the `HttpMockSpec`

If somethings goes wrong, server response with 418 "I'm a teapot" status.

Test must executed sequential or create new instance of server for every test/suit.

## todo

 - hash for the test - one server for all tests?
 - validation (show differences)
 - simple support for json body and other content-type
 - use akka-http client in test
 - dsl?
