name := "twitter-spark-streaming"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++=Seq(
  "org.apache.spark" %% "spark-streaming" % "1.6.1",
  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.1",
  "org.elasticsearch" %% "elasticsearch-spark" % "2.4.3"
)

    