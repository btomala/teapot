[![Build Status](https://travis-ci.org/btomala/teapot.svg?branch=master)](https://travis-ci.org/btomala/teapot)
[![Download](https://api.bintray.com/packages/btomala/maven/teapot/images/download.svg) ](https://bintray.com/btomala/maven/teapot/_latestVersion)
 
# teapot

Is a simple mocking http server based on [akka-http](akka.io) written in scala.

## usage 

To use add resolver to your build

```
resolvers += "btomala at bintray" at "https://dl.bintray.com/btomala/maven/"

libraryDependencies += "io.github.btomala" %% "teapot" % "0.4"
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
