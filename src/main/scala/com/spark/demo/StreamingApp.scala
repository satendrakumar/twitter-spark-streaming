package com.spark.demo

import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.spark._


object StreamingApp {
  def main(args: Array[String]): Unit = {
    System.setProperty("twitter4j.oauth.consumerKey", "***********")
    System.setProperty("twitter4j.oauth.consumerSecret", "***********")
    System.setProperty("twitter4j.oauth.accessToken", "***********")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "*******************")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamingApp")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val twitterStream = TwitterUtils.createStream(ssc, None,List("scala","spark","java","akka"))

    val tweets = twitterStream.map{status =>
    Map("text"->  status.getText, "user_name"-> status.getUser.getScreenName,
      "date"-> status.getCreatedAt.toString)
    }

    tweets.foreachRDD{rdd => rdd.saveToEs("spark/tweet")}
    ssc.start()
    ssc.awaitTermination()


  }

}
