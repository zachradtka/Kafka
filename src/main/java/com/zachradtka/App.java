package com.zachradtka;

import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class App {
   public static void main(String[] args) {

      if (args.length != 1) {
         System.err.println("USAGE: Kafka <number of sentences to send>");
         System.exit(1);
      }

      int numMessages = Integer.parseInt(args[0]);


      Properties props = new Properties();

      props.put("metadata.broker.list", "localhost:9092,localhost:9093,localhost:9094");
      props.put("serializer.class", "kafka.serializer.StringEncoder");
      props.put("partitioner.class", "com.zachradtka.SimplePartitioner");
      props.put("request.required.acks", "1");

      ProducerConfig config = new ProducerConfig(props);

      // Define the Producer
      Producer<String, String> producer = new Producer<String, String>(config);

      // Create a message
      Random rnd = new Random();
      String[] sentences = {"Hello, world!", 
            "How are you doing today?",
            "The quick brown fox jumed over the lazy dog."};


      for (int i = 0; i < numMessages; i++) {
         String number = Integer.toString(rnd.nextInt(sentences.length));
         String sentance = sentences[Integer.parseInt(number)];


         KeyedMessage<String, String> data =
               new KeyedMessage<String, String>("sentences", number, sentance);
         producer.send(data);
      }

      producer.close();
   }
}
