package de.tuberlin.dia.nlp.analysis

import java.util.Locale

/**
  * @author cschulze
  */
class Processor(stopWords: List[String]) {

  /**
    * finds all terms (regex: \\W+) in a text, sanitizing them by filtering stop words.
    *
    * @param text input text
    * @return list of terms
    */
  def getWordsSanitized(text: String): List[String] = {
    text.toLowerCase(Locale.ROOT).split("\\W+")
      .map(t => t.replaceAll("[^a-z]", ""))
      .filter(t => !t.isEmpty)
      .filter(t => !stopWords.contains(t))
      .toList
  }

}
