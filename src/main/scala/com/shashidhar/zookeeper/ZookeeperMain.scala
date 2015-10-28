package com.shashidhar.zookeeper

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry

/**
 * Created by shashidhar on 28/10/15.
 */
object ZookeeperMain {

  def main(args: Array[String]) {

    //root node on which we want to listen
    val zkConnectionString = "/<app>/config"

    //Create curator client for the zookeeper
    val client = CuratorFrameworkFactory.newClient(zkConnectionString,
      new ExponentialBackoffRetry(1000, 3))

    //Create zookeeper source
    val source = new ZookeeperConfigSource(client,zkConnectionString)

    //Create listener object
    val listener = new ZookeeperUpdateListener(source)
    listener.init()

  }

}
