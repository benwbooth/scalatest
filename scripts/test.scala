#!/bin/bash
exec scala -savecompiled -classpath "$(f="$0"; while [ -L $f ]; do cd -P "$(dirname "$f")" && f=$(readlink "$f"); done && cd -P "$(dirname "$f")" && while [ ! -d target/lib -a $PWD != / ]; do cd ..; done; pwd)/target/lib/*" "$0" "$@" # -*- scala -*-
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
