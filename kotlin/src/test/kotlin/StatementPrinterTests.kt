
import PlayType.Comedy
import PlayType.Tragedy
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.approvaltests.Approvals.verify

class StatementPrinterTests {

    @Test
    internal fun exampleStatement() {

        val plays = mapOf(
            "hamlet" to Play("Hamlet", Tragedy),
            "as-like" to Play("As You Like It", Comedy),
            "othello" to Play("Othello", Tragedy)
        )

        val invoice = Invoice(
            "BigCo", listOf(
                Performance("hamlet", 55),
                Performance("as-like", 35),
                Performance("othello", 40)
            )
        )

        val statementPrinter = StatementPrinter()
        val result = statementPrinter.print(invoice, plays)

        verify(result)
    }
}
