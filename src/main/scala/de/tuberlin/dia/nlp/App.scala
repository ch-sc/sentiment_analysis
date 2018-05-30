package de.tuberlin.dia.nlp

import de.tuberlin.dia.nlp.analysis.{Processor, Sentiments}

/**
  * @author cschulze
  */
object App {

  /**
    * This application conducts a sentiment analysis.
    *
    */
  def main(args: Array[String]) = {

    // prepare analysis
    val processor = new Processor(utils.readFileLines("stopwords.txt"))
    val sentiAnalyse = new Sentiments("AFINN-111.txt", processor)

    // load input text
    val text = utils.readFile("test.txt")
    val data = sentiAnalyse.analyzeSentiments(text)

    System.out.println(data)
  }
}
