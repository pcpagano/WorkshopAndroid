package android.workshop.clase4

data class Results (
    val id : String,
    val title : String,
    val price : String,
    val thumbnail : String,
    val pictures : List<Pictures>,
    val plain_text : String) {
}