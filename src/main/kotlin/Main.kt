import org.apache.pdfbox.io.MemoryUsageSetting
import org.apache.pdfbox.multipdf.PDFMergerUtility
import org.apache.pdfbox.pdmodel.PDDocument
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    // 引数が指定されてなかったら終了
    if (args.isEmpty()) return

    // 出力ファイルの頭に日付をつけるための処理
    val ldt = LocalDateTime.now()
    val format = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")

    try {
        val pdDocument = PDDocument()
        val merger = PDFMergerUtility()
        // 引数に指定されているものを全部ファイルだと思って結合対象にする
        args.forEach {
            merger.addSource(File(it))
        }
        merger.destinationFileName = ldt.format(format) + "merged.pdf"
        merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}