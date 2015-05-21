[![Build Status](https://travis-ci.org/btomala/http-mock.svg?branch=master)](https://travis-ci.org/btomala/http-mock)
[![Download](https://api.bintray.com/packages/btomala/maven/http-mock/images/download.svg) ](https://bintray.com/btomala/maven/http-mock/_latestVersion)
 
# http-mock

Is a simple mocking http server framework based on [akka-http](akka.io) written in scala friendly api in future release ;).

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

Test must executed sequential or create new instance of server for every test/suit.

## todo

 - validation
 - hash for the test - one server for all tests
 - simple support for json body and other content-type
 - use akka-http client in test
 - nice dsl
