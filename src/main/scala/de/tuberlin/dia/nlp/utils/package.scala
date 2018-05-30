package de.tuberlin.dia.nlp

import java.io.FileNotFoundException

import sun.misc.IOUtils

/**
  * @author cschulze
  */
package object utils {

  def readFile(filename: String): String = {
    readLinesApply(filename, lines => lines.foldLeft(new StringBuffer(1024))((buffer, x) => buffer.append(x)).toString)
  }

  def readFileLines(filename: String): List[String] = {
    readLinesApply(filename, lines => lines.toList)
  }

  def readLinesApply[T](filename: String, function: Function[Iterator[String], T]): T = {
    val url = try {
      getClass.getResource("/" + filename).getPath.replaceAll("%20", " ")
    } catch {
      case ex: Throwable => throw new FileNotFoundException("File [" + filename + "] not found: " + ex.getMessage)
    }
    val src = scala.io.Source.fromFile(url)
    val lines = function.apply(src.getLines())
    src.close()
    lines
  }


}
