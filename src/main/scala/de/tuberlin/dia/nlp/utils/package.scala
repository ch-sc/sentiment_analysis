package de.tuberlin.dia.nlp

/**
  * @author cschulze
  */
package object utils {

  def readFile(filename: String): String = {
    val url = getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
    val src = scala.io.Source.fromFile(url)
    val buffer = src.getLines().foldLeft(new StringBuffer(1024))((buffer, x) => buffer.append(x))
    src.close()
    buffer.toString
  }

  def readFileLines(filename: String): List[String] = {
    val url = getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
    val src = scala.io.Source.fromFile(url)
    val stopWords = src.getLines().map(x => x).toList
    src.close()
    stopWords
  }

}
