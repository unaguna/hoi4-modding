package jp.unaguna.hoi4modding.hoi4file

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table

class Hoi4ObjectTest : FunSpec ({
    context("Hoi4Bool.serialize"){
        // Hoi4Bool.serialize()
        io.kotest.data.forAll(
            table(
                headers("bool", "expected str"),
                row(true, "yes"),
                row(false, "no"),
            )
        ) { bool: Boolean, expectedStr: String ->
            test("Hoi4Bool($bool).serialize()") {
                val hoi4Bool = Hoi4FileBool(bool)
                hoi4Bool.serialize() shouldBe expectedStr
            }
        }
    }

    context("Hoi4String.serialize"){
        // Hoi4String.serialize()
        io.kotest.data.forAll(
            table(
                headers("str", "expected str"),
                row("aaa_bbb", "\"aaa_bbb\""),
                row("aaa", "\"aaa\""),
            )
        ) { str: String, expectedStr: String ->
            test("Hoi4String(\"$str\").serialize()") {
                val hoi4String = Hoi4FileString(str)
                hoi4String.serialize() shouldBe expectedStr
            }
        }
    }

    context("Hoi4Number.serialize"){
        // Hoi4Number.serialize()
        io.kotest.data.forAll(
            table(
                headers("number", "expected str"),
                row(1.0, "1.0"),
                row(1.5, "1.5"),
            )
        ) { num: Double, expectedStr: String ->
            test("Hoi4Number($num).serialize()") {
                val hoi4Number = Hoi4FileNumber(num)
                hoi4Number.serialize() shouldBe expectedStr
            }
        }
    }

    context("Hoi4List<Hoi4String>.serialize"){
        // Hoi4List<Hoi4String>.serialize()
        io.kotest.data.forAll(
            table(
                headers("strList", "expected str"),
                row(listOf("aaa"), "\"aaa\"\n"),
                row(listOf("aaa", "bb"), "\"aaa\"\n\"bb\"\n"),
            )
        ) { strList: List<String>, expectedStr: String ->
            test("Hoi4List<Hoi4String>($strList).serialize()") {
                val hoi4List = Hoi4FileList(strList.map { Hoi4FileString(it) })
                hoi4List.serialize() shouldBe expectedStr
            }
        }

        // Hoi4List<Hoi4String>.serialize(Int)
        io.kotest.data.forAll(
            table(
                headers("strList", "indent", "expected str"),
                row(listOf("aaa", "bb"), 0, "\"aaa\"\n\"bb\"\n"),
                row(listOf("aaa", "bb"), 1, "\t\"aaa\"\n\t\"bb\"\n"),
                row(listOf("aaa", "bb"), 2, "\t\t\"aaa\"\n\t\t\"bb\"\n"),
            )
        ) { strList: List<String>, indent: Int, expectedStr: String ->
            test("Hoi4List<Hoi4String>($strList).serialize($indent)") {
                val hoi4List = Hoi4FileList(strList.map { Hoi4FileString(it) })
                hoi4List.serialize(indent) shouldBe expectedStr
            }
        }
    }

    context("Hoi4RelationEq.serialize"){
        // Hoi4RelationEq.serialize()
        test("Hoi4RelationEq(String, Hoi4Bool).serialize()") {
            val hoi4RelationEq = Hoi4FileRelationEq("flag", Hoi4FileBool(true))
            hoi4RelationEq.serialize() shouldBe "flag = yes"
        }
        test("Hoi4RelationEq(String, Hoi4Number).serialize()") {
            val hoi4RelationEq = Hoi4FileRelationEq("amount", Hoi4FileNumber(1.0))
            hoi4RelationEq.serialize() shouldBe "amount = 1.0"
        }
        test("Hoi4RelationEq(String, Hoi4String).serialize()") {
            val hoi4RelationEq = Hoi4FileRelationEq("building", Hoi4FileString("radar"))
            hoi4RelationEq.serialize() shouldBe "building = \"radar\""
        }
        test("Hoi4RelationEq(String, Hoi4List<Hoi4String>).serialize()") {
            val hoi4RelationEq = Hoi4FileRelationEq("building", Hoi4FileList(listOf(Hoi4FileString("radar"), Hoi4FileString("airport"))))
            hoi4RelationEq.serialize() shouldBe "building = {\n\t\"radar\"\n\t\"airport\"\n}"
        }
        test("Hoi4RelationEq(String, Hoi4List<Hoi4RelationEq>).serialize()") {
            val hoi4RelationEq = Hoi4FileRelationEq("add", Hoi4FileList(listOf(Hoi4FileRelationEq("building", Hoi4FileString("radar")), Hoi4FileRelationLt("amount", Hoi4FileNumber(1.0)))))
            hoi4RelationEq.serialize() shouldBe "add = {\n\tbuilding = \"radar\"\n\tamount < 1.0\n}"
        }
        test("Hoi4RelationEq(String, Hoi4List<Hoi4RelationEq>).serialize(1)") {
            val hoi4RelationEq = Hoi4FileRelationEq("add", Hoi4FileList(listOf(Hoi4FileRelationEq("building", Hoi4FileList(listOf(Hoi4FileRelationEq("building", Hoi4FileString("radar"))))), Hoi4FileRelationLt("amount", Hoi4FileNumber(1.0)))))
            hoi4RelationEq.serialize(1) shouldBe "add = {\n" +
                    "\t\tbuilding = {\n" +
                    "\t\t\tbuilding = \"radar\"\n" +
                    "\t\t}\n" +
                    "\t\tamount < 1.0\n" +
                    "\t}"
        }
    }

    context("Hoi4RelationLt.serialize"){
        // Hoi4RelationEq.serialize()
        test("Hoi4RelationLt(String, Hoi4Bool).serialize()") {
            val hoi4RelationLt = Hoi4FileRelationLt("flag", Hoi4FileBool(true))
            hoi4RelationLt.serialize() shouldBe "flag < yes"
        }
        test("Hoi4RelationLt(String, Hoi4Number).serialize()") {
            val hoi4RelationLt = Hoi4FileRelationLt("amount", Hoi4FileNumber(1.0))
            hoi4RelationLt.serialize() shouldBe "amount < 1.0"
        }
        test("Hoi4RelationLt(String, Hoi4String).serialize()") {
            val hoi4RelationLt = Hoi4FileRelationLt("building", Hoi4FileString("radar"))
            hoi4RelationLt.serialize() shouldBe "building < \"radar\""
        }
    }

    context("Hoi4RelationGt.serialize"){
        // Hoi4RelationEq.serialize()
        test("Hoi4RelationGt(String, Hoi4Bool).serialize()") {
            val hoi4RelationGt = Hoi4FileRelationGt("flag", Hoi4FileBool(true))
            hoi4RelationGt.serialize() shouldBe "flag > yes"
        }
        test("Hoi4RelationGt(String, Hoi4Number).serialize()") {
            val hoi4RelationGt = Hoi4FileRelationGt("amount", Hoi4FileNumber(1.0))
            hoi4RelationGt.serialize() shouldBe "amount > 1.0"
        }
        test("Hoi4RelationGt(String, Hoi4String).serialize()") {
            val hoi4RelationGt = Hoi4FileRelationGt("building", Hoi4FileString("radar"))
            hoi4RelationGt.serialize() shouldBe "building > \"radar\""
        }
    }
})
