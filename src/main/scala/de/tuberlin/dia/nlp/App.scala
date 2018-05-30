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

    if (args.length < 1)
      throw new IllegalArgumentException("require at least one argument to specify a file that is to be analyzed")

    // prepare analysis
    val processor = new Processor(utils.readFileLines("stopwords.txt"))
    val sentimentAnalysis = new Sentiments("AFINN-111.txt", processor)

    // load input text
    val text = utils.readFile(args(0))
    val data = sentimentAnalysis.analyzeSentiments(text)

    System.out.println(data)
  }
}
