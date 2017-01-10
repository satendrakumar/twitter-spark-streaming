package com.spark.demo

import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object StreamingApp {
  def main(args: Array[String]): Unit = {
    System.setProperty("twitter4j.oauth.consumerKey", "***************")
    System.setProperty("twitter4j.oauth.consumerSecret", "***********")
    System.setProperty("twitter4j.oauth.accessToken", "***********")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "*************************")

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamingApp")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val twitterStream = TwitterUtils.createStream(ssc, None)

    val tweets = twitterStream.map(status => status.getText)
    tweets.print()
    ssc.start()
    ssc.awaitTermination()


  }

}
