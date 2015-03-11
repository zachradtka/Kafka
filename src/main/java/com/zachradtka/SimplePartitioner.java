package com.zachradtka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {

   public SimplePartitioner (VerifiableProperties props) {
      
   }
   
   @Override
   public int partition(Object key, int a_numPartitions) {

      int partition = 0;

      // Make sure we are getting a string
      if (!(key instanceof String)) {
         throw new IllegalArgumentException();
      }

      String stringKey = (String) key;

      partition = Integer.parseInt(stringKey) % a_numPartitions;


      return partition;
   }

}
