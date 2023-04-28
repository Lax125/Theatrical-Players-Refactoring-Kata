import java.text.NumberFormat
import java.util.Locale

class StatementPrinter {

    fun print(invoice: Invoice, plays: Map<String, Play>): String {
        var totalDebit = 0
        var volumeCredits = 0
        var result = "Statement for ${invoice.customer}\n"

        val format = { number: Long ->  NumberFormat.getCurrencyInstance(Locale.US).format(number)}

        invoice.performances.forEach { perf ->
            val play = plays.getValue(perf.playID)
            val thisDebit = play.type.cost(perf.audience)
            volumeCredits += play.type.credits(perf.audience)

            // print line for this order
            result += "  ${play.name}: ${format((thisDebit / 100).toLong())} (${perf.audience} seats)\n"

            totalDebit += thisDebit
        }
        result += "Amount owed is ${format((totalDebit / 100).toLong())}\n"
        result += "You earned $volumeCredits credits\n"
        return result
    }

}
