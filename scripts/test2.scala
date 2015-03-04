#!/bin/bash
exec scala -savecompiled -classpath "${0%/*}/../target/lib/*" "$0" "$@" # -*- scala -*-
!#

def urlses(cl: ClassLoader): Array[java.net.URL] = cl match {
  case null => Array()
  case u: java.net.URLClassLoader => u.getURLs() ++ urlses(cl.getParent)
  case _ => urlses(cl.getParent)
}
val  urls = urlses(getClass.getClassLoader)
println(urls.filterNot(_.toString.contains("ivy")).mkString("\n"))

println("Hello, world! " + args.toList)

import org.bdgp.scalatest.Bam2Bedgraph._
println(test())
