package de.tuberlin.dia.nlp.analysis

import de.tuberlin.dia.nlp.utils

/**
  * @author cschulze
  */
class Sentiments(sentiFile: String, processor: Processor) {

  val sentiments: Map[String, Int] = getSentiments(sentiFile)

  def getSentiments(filename: String): Map[String, Int] = {
    (for (row <- utils.readFileLines(filename)) yield {
      val seg = row.split("\t")
      seg(0) -> seg(1).toInt
    }).toMap
  }

  /**
    * Calculates the sentiment of an input text.
    *
    * Computes basically two vectors: one contains the average sentiment of the input text,
    * the other contains the frequency of relevant sentiment terms within the text.
    *
    * @param text input text
    * @return a tuple containing the two vectors
    */
  def analyzeSentiments(text: String) = {

    val terms = processor.getWordsSanitized(text)

    // tuple containing overall sentiment and number of relevant words
    val (totalSentiment, hits) = terms.filter(t => sentiments.contains(t))
      .foldLeft((0, 0))((acc, x) => (acc._1 + sentiments(x), acc._2 + 1))

    (totalSentiment / hits.toDouble, hits / terms.length.toDouble)
  }

}
