package de.tuberlin.dia.nlp

/**
  * @author cschulze
  */
package object utils {

  def readFile(filename: String): String = {
    readLinesApply(filename, lines => lines.foldLeft(new StringBuffer(1024))((buffer, x) => buffer.append(x)).toString)

//    val url = getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
//    val src = scala.io.Source.fromFile(url)
//    val buffer = src.getLines().foldLeft(new StringBuffer(1024))((buffer, x) => buffer.append(x))
//    src.close()
//    buffer.toString
  }

  def readFileLines(filename: String): List[String] = {
    readLinesApply(filename, lines => lines.toList)

//    val url = getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
//    val src = scala.io.Source.fromFile(url)
//    val lines = src.getLines().toList
//    src.close()
//    lines
  }

  def readLinesApply[T](filename: String, function: Function[Iterator[String], T]): T = {
    val url = getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
    val src = scala.io.Source.fromFile(url)

    val result = function.apply(src.getLines())
    src.close()

    result
  }

}
