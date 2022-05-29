import java.util.Date

data class Note(
    val title : String,
    val text : String,
    val date : Long,
)

data class Comment(
    val text : String,
    val date : Long,
    val noteId : Int
)

class Notes {
    private val notes : CRUD<Note> = CRUD()
    private val comments : CRUD <Comment> = CRUD()

    fun add(title: String, text: String): Pair<Int, Note?> {
        val note = Note(title,text,Date().time)
        return notes.create(note)
    }

    fun createComment(id: Int, text: String): Pair<Int, Comment?> {
        val comment = Comment(text,Date().time, id)
        notes.read(id).second ?: return Pair(id, null)
        return comments.create(comment)
    }

    fun delete(id: Int): Boolean {
        return notes.delete(id)
    }

    fun deleteComment(id: Int): Boolean {
        return comments.delete(id)
    }

    fun edit(id:Int, title: String, text: String): Pair<Int, Note?> {
        val oldNote = notes.read(id).second ?: return Pair(id, null)
        val note = oldNote.copy(title = title, text = text)
        return notes.update(id,note)
    }

    fun editComment(id:Int, text: String): Pair<Int, Comment?> {
        val oldComment = comments.read(id).second ?: return Pair(id, null)
        val comment = oldComment.copy(text = text)
        return comments.update(id,comment)
    }

    fun get (): List<Note> {
        return notes.getList()
    }

    fun getById(id: Int): Pair<Int, Note?> {
        return notes.read(id)
    }

    fun getComments(id:Int): List<Comment> {
        return comments.getList().filter { comment -> comment.noteId == id }
    }

    fun restoreComment(id: Int): Pair<Int, Comment?> {
        return comments.restore(id)
    }

}